package dev.lxzx.entity;

import java.io.Serializable;

public class ResponseItem<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private T payload;

    public String getStatus() {
        return status;
    }

    public T getPayload() {
        return payload;
    }

    private ResponseItem<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    private ResponseItem<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public static <T> ResponseItem<T> success(T payload) {
        return new ResponseItem<T>().setStatus("200").setPayload(payload);
    }

    public static <T> ResponseItem<T> success() {
        return success(null);
    }

    public static <T> ResponseItem<T> error(String status, T payload) {
        return new ResponseItem<T>().setStatus(status).setPayload(payload);
    }

    public static <T> ResponseItem<T> error(String status) {
        return error(status, null);
    }
}