package com.yeeoa.service;

import com.yeeoa.bean.Step;

import java.util.List;

public interface StepService {
	int add(Step step);
    int update(Step step);
    int deleteById(int sid);
    Step queryById(int sid);
    List<Step> queryAll();
    List<Step> queryWithJoinTask(int offset, int limit);

    List<Step> queryByTaskID(int taskID, int offset, int count);

    int queryAndcount(String condition);
}
