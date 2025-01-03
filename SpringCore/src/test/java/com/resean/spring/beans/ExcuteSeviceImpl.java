package com.resean.spring.beans;

public class ExcuteSeviceImpl implements ExcuteSevice{

    private String msg;

    public ExcuteSeviceImpl(String msg) {
        this.msg = msg;
    }

    public void excute() {
        System.out.println(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
