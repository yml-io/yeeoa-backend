package com.yeeoa.bean.jsonbody;

import java.util.List;
import java.util.Map;

public class UnitAddProgramUnitReq {
    private String sessionToken;
    private int programID;
    private String unitId;
    private String unitName;
    private String sharedProblem;
    private String threeObj;
    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }


    public String getThreeObj() {
        return threeObj;
    }

    public void setThreeObj(String threeObj) {
        this.threeObj = threeObj;
    }




    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }




    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSharedProblem() {
        return sharedProblem;
    }

    public void setSharedProblem(String sharedProblem) {
        this.sharedProblem = sharedProblem;
    }




}