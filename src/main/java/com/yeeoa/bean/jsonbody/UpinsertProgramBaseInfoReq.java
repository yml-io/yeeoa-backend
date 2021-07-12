package com.yeeoa.bean.jsonbody;

import java.util.List;
import java.util.Map;

public class UpinsertProgramBaseInfoReq {
    private String programID;
    private String programName;
    private String grade;
    private String programOwner;
    private String semester;
    private String programFeature;
    private String ageLevel;
    private String threeObj;
    private String programOverview;
    private String school;


    public String getThreeObj() {
        return threeObj;
    }

    public void setThreeObj(String threeObj) {
        this.threeObj = threeObj;
    }
    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProgramOwner() {
        return programOwner;
    }

    public void setProgramOwner(String programOwner) {
        this.programOwner = programOwner;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getProgramFeature() {
        return programFeature;
    }

    public void setProgramFeature(String programFeature) {
        this.programFeature = programFeature;
    }

    public String getAgeLevel() {
        return ageLevel;
    }

    public void setAgeLevel(String ageLevel) {
        this.ageLevel = ageLevel;
    }



    public String getProgramOverview() {
        return programOverview;
    }

    public void setProgramOverview(String programOverview) {
        this.programOverview = programOverview;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
