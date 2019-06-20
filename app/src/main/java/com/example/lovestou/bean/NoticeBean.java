package com.example.lovestou.bean;

public class NoticeBean {
    private String title;
    private String time;
    private String href;

    public NoticeBean(String title, String time, String href) {
        this.title = title;
        this.time = time;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
