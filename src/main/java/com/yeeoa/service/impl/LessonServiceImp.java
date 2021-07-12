package com.yeeoa.service.impl;

import com.yeeoa.bean.Lesson;
import com.yeeoa.mapper.LessonMapper;
import com.yeeoa.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("lessonService")
public class LessonServiceImp implements LessonService {

	@Autowired
	private LessonMapper lessonMapper;

	@Override
	public int add(Lesson lesson) {
		return this.lessonMapper.add(lesson);
	}

	@Override
	public int update(Lesson lesson) {
		return this.lessonMapper.update(lesson);
	}

	@Override
	public int deleteById(int sid) {
		return this.lessonMapper.deleteById(sid);
	}

	@Override
	public Lesson queryById(int sid) {
		return this.lessonMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.lessonMapper.queryAndcount(condition);
	}

	@Override
	public List<Lesson> queryAll() {
		return this.lessonMapper.queryAll();
	}

	@Override
	public List<Lesson> queryByUnitID(int unitID, int offset, int count) {
		return this.lessonMapper.queryByUnitID(unitID, offset, count);
	}

	@Override
	public List<Lesson> queryWithJoinUnit(int offset, int count) {
		return this.lessonMapper.queryWithJoinUnit(offset, count);
	}

}
