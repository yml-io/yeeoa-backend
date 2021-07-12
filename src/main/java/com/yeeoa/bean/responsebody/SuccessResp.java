package com.yeeoa.bean.responsebody;

public class SuccessResp implements HttpResponse{
    public SuccessResp() {
        this.isSuccess = true;
    }
    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    private boolean isSuccess;

}
