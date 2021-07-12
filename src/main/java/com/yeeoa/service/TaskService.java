package com.yeeoa.service;

import com.yeeoa.bean.Task;

import java.util.List;

public interface TaskService {
	int add(Task task);
    int update(Task task);
    int deleteById(int sid);
    Task queryById(int sid);
    List<Task> queryAll();
    List<Task> queryWithJoinLesson(int offset, int limit);

    List<Task> queryByLessonID(int lessonID, int offset, int count);

    int queryAndcount(String condition);
}
