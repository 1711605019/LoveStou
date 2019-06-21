package com.example.lovestou.bean;

public class stNewsBean {
    private String title;
    private String img;
    private String href;

    public stNewsBean(String title, String img, String href) {
        this.title = title;
        this.img = img;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
