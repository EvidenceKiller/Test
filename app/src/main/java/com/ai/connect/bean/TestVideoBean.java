package com.ai.connect.bean;

/**
 * 测试视频数据
 */
public class TestVideoBean {
    // 视频所在路径
    private String outsideVideo;
    private String insideVideo;
    // 测试信号超时时长
    private long timeOut;
    // 服务器端口
    private int serverInsidePort;
    private int serverOutsidePort;

    public TestVideoBean() {
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public String getOutsideVideo() {
        return outsideVideo;
    }

    public void setOutsideVideo(String outsideVideo) {
        this.outsideVideo = outsideVideo;
    }

    public String getInsideVideo() {
        return insideVideo;
    }

    public void setInsideVideo(String insideVideo) {
        this.insideVideo = insideVideo;
    }

    public int getServerInsidePort() {
        return serverInsidePort;
    }

    public void setServerInsidePort(int serverInsidePort) {
        this.serverInsidePort = serverInsidePort;
    }

    public int getServerOutsidePort() {
        return serverOutsidePort;
    }

    public void setServerOutsidePort(int serverOutsidePort) {
        this.serverOutsidePort = serverOutsidePort;
    }

    @Override
    public String toString() {
        return "TestVideoBean{" +
                "outsideVideo='" + outsideVideo + '\'' +
                ", insideVideo='" + insideVideo + '\'' +
                ", timeOut=" + timeOut +
                ", serverInsidePort=" + serverInsidePort +
                ", serverOutsidePort=" + serverOutsidePort +
                '}';
    }
}
