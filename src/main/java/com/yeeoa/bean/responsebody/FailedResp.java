package com.yeeoa.bean.responsebody;

import org.json.JSONObject;

public class FailedResp implements HttpResponse{
    public FailedResp() {
        this.isSuccess = false;
    }
    private boolean isSuccess;
    private String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
