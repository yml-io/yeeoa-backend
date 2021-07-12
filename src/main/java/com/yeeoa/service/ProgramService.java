package com.yeeoa.service;


import com.yeeoa.bean.Program;

import java.util.List;
import java.util.Map;

public interface ProgramService {
	int add(Program student);
    int update(Program student);
    int deleteById(int sid);
    Program queryById(int sid);
    List<Program> queryAll();

    List<Program> queryAllWithPaging(int offset, int count);

    int queryAndcount(String condition);



    // get static infomation methods
    List<Map<String, String>> getProgramType();
    List<Map<String, String>> getCountry();
    List<Map<String, String>> getSchoolCode();
    List<Map<String, String>> getGrade();
    List<Map<String, String>> getSemesterMap();
}
