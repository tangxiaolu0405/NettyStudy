package com.xiaolu.GS.bean;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {
    public String url;
    public String method;
    public T data;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", data=" + data +
                '}';
    }
}
