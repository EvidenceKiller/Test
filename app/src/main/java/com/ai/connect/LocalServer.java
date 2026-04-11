package com.ai.connect;

import com.adl.base.common.AdlJsonUtil;
import com.adl.base.connect.AdlSubjectHandleSample;
import com.adl.base.connect.AdlTcpServer;
import com.adl.base.connect.AdlTcpServerCallback;
import com.adl.base.connect.core.AdlTcpChannel;
import com.adl.base.connect.core.AdlTcpDesc;
import com.adl.base.connect.core.AdlTcpReadCallback;
import com.adl.base.connect.core.AdlTcpSendCallback;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.ai.connect.bean.TestVideoBean;

import java.io.File;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public class LocalServer {
    private final String TAG = "LocalServer";
    private Object lock = new Object();
    // 创建一个服务, 负责切换视频文件
    private AdlTcpServer mServer;
    // 通道管理
    private ConcurrentHashMap<String, AdlTcpChannel> mChannelMaps = new ConcurrentHashMap<>();
    private File mDir;
    private int mPort;
    private OnTestDataListener mListener = null;

    public LocalServer(OnTestDataListener listener) {
        this.mListener = listener;
    }

    public void createServer(int serverPort) {
        synchronized (lock) {
            mPort = serverPort;
            if (mServer == null || !mServer.isAlive()) {

                AdlLogger.d("AdlTcpServer 注册成功，开始监听端口: " + serverPort);
                mServer = AdlTcpServer.create(serverPort);
                mServer.start(new AdlTcpServerCallback() {
                    @Override
                    public void onNewSocket(Socket socket) {
                        AdlLogger.d("AdlTcpServer 收到新的连接: " + socket.getInetAddress().getHostAddress());
                        try {
                            AdlTcpReadCallback readCallback = new AdlTcpReadCallback() {

                                @Override
                                public void onAction(AdlTcpDesc desc, String action) {
                                    AdlLogger.d("AdlTcpServer AdlTcpReadCallback onAction: " + action);
                                }

                                @Override
                                public void onCommon(AdlTcpDesc desc, String content) {
                                    AdlLogger.d("AdlTcpServer AdlTcpReadCallback onAction: ");
                                }

                                @Override
                                public void onData(AdlTcpDesc desc, byte[] buffer, int offset, int len, long frameTime) {
                                    String data = new String(buffer, offset, len, StandardCharsets.UTF_8);
                                    TestVideoBean testVideoBean = AdlJsonUtil.toObject(data, TestVideoBean.class);
                                    if (testVideoBean != null && mListener != null) {
                                        mListener.onReceive(testVideoBean);
                                    }

                                    AdlLogger.d(TAG + " AdlTcpServer AdlTcpReadCallback onData: data: " + data);
                                }
                                @Override
                                public void onFile(AdlTcpDesc desc, String path, String param) {
                                }

                                @Override
                                public void onStatus(AdlTcpDesc desc, int code, String error) {
                                }
                            };

                            AdlTcpSendCallback sendCallback = new AdlTcpSendCallback() {

                                @Override
                                public void onStatus(AdlTcpChannel channel, AdlTcpDesc desc, int code, String error) {
                                    AdlLogger.d("AdlTcpServer AdlTcpSendCallback onStatus: ");
                                }

                                @Override
                                public void onError(AdlTcpChannel channel, AdlTcpDesc desc, Exception e) {
                                    AdlLogger.d("AdlTcpServer AdlTcpSendCallback onError: ");
                                    if (channel != null) {
                                        synchronized (mChannelMaps) {
                                            mChannelMaps.remove(channel.getIp());
                                        }
                                    }
                                }
                            };
                            mDir = AdlFileHelper.createDirectory("NetPlayer"/* + mPort*/);
                            AdlTcpChannel channel = new AdlTcpChannel(socket, mDir, false, readCallback, sendCallback);
                            mChannelMaps.put(channel.getIp(), channel);
                        } catch (Exception e) {
                            e.printStackTrace();
                            AdlLogger.d("AdlTcpServer AdlTcpSendCallback onError: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onStatus(int code, String msg) {
                        AdlLogger.d("AdlTcpServer 状态: " + code + " " + msg);
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

    public void stopServer() {
        synchronized (lock) {
            if (mServer != null) {
                //mServer.stopListener();
                mServer = null;
            }
        }
    }

    public interface OnTestDataListener {
        void onReceive(TestVideoBean data);
    }
}
