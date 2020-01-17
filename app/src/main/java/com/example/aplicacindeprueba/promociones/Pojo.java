package com.example.aplicacindeprueba.promociones;

public class Pojo {
    String url;
    String name;
    String desc;

    public Pojo(String url, String name, String desc) {
        this.url = url;
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
