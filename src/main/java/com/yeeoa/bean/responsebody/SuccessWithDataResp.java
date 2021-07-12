package com.yeeoa.bean.responsebody;

import org.json.JSONObject;

public class SuccessWithDataResp implements HttpResponse{
    public SuccessWithDataResp() {
        this.isSuccess = true;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private boolean isSuccess;
    private Object data;



}
