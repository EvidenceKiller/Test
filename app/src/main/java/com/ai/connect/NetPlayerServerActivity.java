package com.ai.connect;

import android.os.Bundle;

import com.adl.base.common.AdlJsonUtil;
import com.adl.base.connect.AdlSubjectHandle;
import com.adl.base.connect.AdlTcpServer;
import com.adl.base.connect.AdlTcpServerCallback;
import com.adl.base.connect.core.AdlTcpChannel;
import com.adl.base.connect.core.AdlTcpDesc;
import com.adl.base.connect.core.AdlTcpReadCallback;
import com.adl.base.connect.core.AdlTcpSendCallback;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.adl.ts.general.R;
import com.ai.connect.bean.TestVideoBean;
import com.ai.test.BaseActivity;
import com.google.gson.Gson;

import java.io.File;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class NetPlayerServerActivity extends BaseActivity {

    private final String TAG = "TcpTestActivity";

    private final int masterPort1 = 9081;
    private final int masterPort2 = 9082;
    private final int masterPort3 = 9083;
    private final int masterPort4 = 9084;
    NetServerPortLayout win1;
    NetServerPortLayout win2;
    NetServerPortLayout win3;
    NetServerPortLayout win4;

    LocalServer mServer;
    private final int mServerPort1 = 12081;

    private AdlJsonUtil mJsonUtil = new AdlJsonUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_net_player_server);

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        win1 = findViewById(R.id.win_1);
        win2 = findViewById(R.id.win_2);
        win3 = findViewById(R.id.win_3);
        win4 = findViewById(R.id.win_4);

        win1.setPortIndex(1, masterPort1, 8880);
        win1.setRootDir("test/longRunner1");

        win2.setPortIndex(2, masterPort2, 8881);
        win2.setRootDir("test/longRunner1");

        win3.setPortIndex(3, masterPort3, 8882);
        win3.setRootDir("longRunner1");

        win4.setPortIndex(4, masterPort4, 8883);
        win4.setRootDir("longRunner1");

        mServer = new LocalServer(new LocalServer.OnTestDataListener() {
            @Override
            public void onReceive(TestVideoBean data) {
                AdlLogger.d("LocalServer onReceive: " + data);
                processData(data);
            }
        });
        mServer.createServer(mServerPort1);
    }

    private void processData(TestVideoBean data) {
        if (data == null) {
            return;
        }
        if (data.getServerInsidePort() == masterPort1) {
            win1.setVideoFile(data.getInsideVideo());
        } else if (data.getServerInsidePort() == masterPort2) {
            win2.setVideoFile(data.getInsideVideo());
        }
        if (data.getServerOutsidePort() == masterPort1) {
            win1.setVideoFile(data.getOutsideVideo());
        } else if (data.getServerOutsidePort() == masterPort2) {
            win2.setVideoFile(data.getOutsideVideo());
        }
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
        win1.onStop();
        win2.onStop();
        win3.onStop();
        win4.onStop();
    }


}
