package com.yeeoa.bean.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
//@ConfigurationProperties(prefix="yeeoa")
@PropertySource("classpath:app.properties")
public class AppProperties {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getProgramType() {
        return programType;
    }

    public void setProgramType(List<String> programType) {
        this.programType = programType;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(List<String> schoolCode) {
        this.schoolCode = schoolCode;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public List<String> getSemesterCountryMap() {
        return semesterCountryMap;
    }

    public void setSemesterCountryMap(List<String> semesterCountryMap) {
        this.semesterCountryMap = semesterCountryMap;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }


    @Value("${yeeoa.name}")
	private String name;
    @Value("${yeeoa.version}")
	private String version;
    @Value("#{'${yeeoa.programType}'.split(',')}")
    private List<String> programType;
    @Value("#{'${yeeoa.country}'.split(',')}")
    private List<String> country;
    @Value("#{'${yeeoa.schoolCode}'.split(',')}")
    private List<String> schoolCode;
    @Value("#{'${yeeoa.grade}'.split(',')}")
    private List<String> grade;
    @Value("#{'${yeeoa.semesterCountryMap}'.split(',')}")
    private List<String> semesterCountryMap;
    @Value("${yeeoa.uploadDir}")
    private String uploadDir;


}
