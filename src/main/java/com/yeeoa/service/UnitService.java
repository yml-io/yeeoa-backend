package com.yeeoa.service;

import com.yeeoa.bean.Unit;

import java.util.List;

public interface UnitService {
	int add(Unit unit);
    int update(Unit unit);
    int deleteById(int sid);
    Unit queryById(int sid);
    List<Unit> queryAll();
    List<Unit> queryWithJoinProgram(int offset, int limit);

    List<Unit> queryByProgramID(int programID, int offset, int count);

    int queryAndcount(String condition);
}
