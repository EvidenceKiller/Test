package com.ai.connect;

import android.os.Bundle;

import com.adl.base.connect.AdlObserverHandle;
import com.adl.base.connect.AdlSubjectHandle;
import com.adl.base.connect.AdlTcpServer;
import com.adl.base.connect.AdlTcpServerCallback;
import com.adl.base.connect.core.AdlTcpChannel;
import com.adl.base.connect.core.AdlTcpDesc;
import com.adl.base.connect.core.AdlTcpFrame;
import com.adl.base.connect.core.AdlTcpFrameData;
import com.adl.base.connect.core.AdlTcpReadCallback;
import com.adl.base.connect.core.AdlTcpSendCallback;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;

import java.io.File;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class TcpTestActivity extends BaseActivity {

    private final String TAG = "TcpTestActivity";

    private Object lock = new Object();

    // 创建一个服务, 负责切换视频文件
    private AdlTcpServer mServer;
    private final int mServerPort = 12081;

    // 通道管理
    private List<AdlTcpChannel> mChannels = new ArrayList<>();
    private File mDir;

    AdlTcpChannel mClientChannel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_net_test);

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());
        findViewById(R.id.bnt_start_server).setOnClickListener(view -> {
            createServer();
        });
        findViewById(R.id.bnt_start_client).setOnClickListener(view -> {
            createClient();

        });
        findViewById(R.id.bnt_send_to_server).setOnClickListener(view -> {
            if (mClientChannel != null) {
                mClientChannel.sendFrame(new AdlTcpFrameData("Hello Server".getBytes(StandardCharsets.UTF_8), System.currentTimeMillis()));
            }
        });
        findViewById(R.id.bnt_send_to_client).setOnClickListener(view -> {
            for (int i = 0; i < mChannels.size(); i++) {
                AdlTcpChannel channel = mChannels.get(i);
                channel.sendFrame(new AdlTcpFrameData("Hello Client".getBytes(StandardCharsets.UTF_8), System.currentTimeMillis()));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void createServer() {
        synchronized (lock) {

            if (mServer == null || !mServer.isAlive()) {

                AdlLogger.d(TAG + " AdlTcpServer 注册成功，开始监听端口: " + mServerPort);
                mServer = AdlTcpServer.create(mServerPort);
                mServer.start(new AdlTcpServerCallback() {
                    @Override
                    public void onNewSocket(Socket socket) {
                        AdlLogger.d(TAG + " AdlTcpServer 收到新的连接: " + socket.getInetAddress().getHostAddress());
                        try {
                            AdlTcpReadCallback readCallback = new AdlTcpReadCallback() {

                                @Override
                                public void onAction(AdlTcpDesc desc, String action) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onAction: " + action);
                                }

                                @Override
                                public void onCommon(AdlTcpDesc desc, String content) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onAction: ");
                                }

                                @Override
                                public void onData(AdlTcpDesc desc, byte[] buffer, int offset, int len, long frameTime) {

                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onData: " + buffer.length + "; len: " + len + "; offset: " + offset);
                                    String data = new String(buffer, offset, len, StandardCharsets.UTF_8);
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onData: data: " + data);
                                }

                                @Override
                                public void onFile(AdlTcpDesc desc, String path, String param) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onFile: ");
                                }

                                @Override
                                public void onStatus(AdlTcpDesc desc, int code, String error) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onStatus: ");
                                }
                            };

                            AdlTcpSendCallback sendCallback = new AdlTcpSendCallback() {

                                @Override
                                public void onStatus(AdlTcpChannel channel, AdlTcpDesc desc, int code, String error) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpSendCallback onStatus: ");
                                }

                                @Override
                                public void onError(AdlTcpChannel channel, AdlTcpDesc desc, Exception e) {
                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpSendCallback onError: ");
                                    if (channel != null) {
                                        synchronized (mChannels) {
                                            mChannels.remove(channel);
                                        }
                                    }
                                }
                            };
                            mDir = AdlFileHelper.createDirectory("NetPlayer");
                            AdlTcpChannel channel = new AdlTcpChannel(socket, mDir, false, readCallback, sendCallback);
                            AdlLogger.d(TAG + " AdlTcpServer AdlTcpSendCallback OK: " + channel.getIp());
                            mChannels.add(channel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onStatus(int code, String msg) {
                        AdlLogger.d("状态: " + code + " " + msg);
                        if (code == -10) {
                            synchronized ( lock) {
                               mServer = null;
                            }
                        }
                    }
                });
            }
        }

    }

    private void createClient() {
        new Thread(() -> {
            mClientChannel = createChannel("172.16.40.33", mServerPort);
        }).start();

    }

    public AdlTcpChannel createChannel(String ip, int port) {
        try {
            Socket mSocket = new Socket(ip, port);
            mSocket.setKeepAlive(true);
            AdlTcpChannel channel = null;
            if (channel != null) {
                channel.stop();
                channel = null;
            }
            AdlTcpReadCallback mReadCallback = new AdlTcpReadCallback() {

                @Override
                public void onAction(AdlTcpDesc desc, String action) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onAction: ");
                }

                @Override
                public void onCommon(AdlTcpDesc desc, String content) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onCommon: ");
                }

                @Override
                public void onData(AdlTcpDesc desc, byte[] buffer, int offset, int len, long frameTime) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onData: " + buffer.length + "; len: " + len + "; offset: " + offset);
                    String data = new String(buffer, offset, len, StandardCharsets.UTF_8);
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onData: data: " + data);
                }

                @Override
                public void onFile(AdlTcpDesc desc, String path, String param) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onFile: ");
                }

                @Override
                public void onStatus(AdlTcpDesc desc, int code, String error) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpReadCallback onStatus: ");
                }
            };
            AdlTcpSendCallback mSendCallback = new AdlTcpSendCallback() {

                @Override
                public void onStatus(AdlTcpChannel channel, AdlTcpDesc desc, int code, String error) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpSendCallback onStatus: ");
                }

                @Override
                public void onError(AdlTcpChannel channel, AdlTcpDesc desc, Exception e) {
                    AdlLogger.d(TAG + " AdlTcpChannel AdlTcpSendCallback onError: ");
                }
            };
            File mDirData = AdlFileHelper.createDirectory("NetPlayer");
            channel = new AdlTcpChannel(mSocket, mDirData, true, mReadCallback, mSendCallback);

            return channel;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
