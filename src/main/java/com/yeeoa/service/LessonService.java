package com.yeeoa.service;

import com.yeeoa.bean.Lesson;

import java.util.List;

public interface LessonService {
	int add(Lesson unit);
    int update(Lesson unit);
    int deleteById(int sid);
    Lesson queryById(int sid);
    List<Lesson> queryAll();

    List<Lesson> queryByUnitID(int lessonID, int offset, int count);

    int queryAndcount(String condition);

    List<Lesson> queryWithJoinUnit(int offset, int count);
}
