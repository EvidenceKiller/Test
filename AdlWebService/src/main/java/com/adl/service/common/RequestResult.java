package com.adl.service.common;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : 类描述
 */
public class RequestResult {

    private int code;
    private String msg;
    private Object content;

    public RequestResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RequestResult(int code, Object text) {
        this.code = code;
        this.content = text;
    }

    public boolean isOk() {
        return code == 200;
    }

    public boolean isCode500() {
        return code == 500;
    }

    public boolean isCode50000() {
        return code > 50000;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public static RequestResult okContent(Object content) {
        return new RequestResult(200, content);
    }

    public static RequestResult error(String text) {
        return error(-1, text);
    }

    public static RequestResult error(int code, String text) {
        return new RequestResult(code, text);
    }
}
