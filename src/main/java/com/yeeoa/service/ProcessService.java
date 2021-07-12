package com.yeeoa.service;

import com.yeeoa.bean.Process;
import java.util.List;

public interface ProcessService {
	int add(Process process);
    int update(Process process);
    int deleteById(int sid);
    Process queryById(int sid);
    List<Process> queryAll();
    List<Process> queryWithJoinUnit(int offset, int limit);

    List<Process> queryByUnitID(int unitID, int offset, int count);

    int queryAndcount(String condition);
}
