package com.yeeoa.service.impl;

import com.yeeoa.bean.Task;
import com.yeeoa.mapper.TaskMapper;
import com.yeeoa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImp implements TaskService {

	@Autowired
	private TaskMapper taskMapper;

	@Override
	public int add(Task task) {
		return this.taskMapper.add(task);
	}

	@Override
	public int update(Task task) {
		return this.taskMapper.update(task);
	}

	@Override
	public int deleteById(int sid) {
		return this.taskMapper.deleteById(sid);
	}

	@Override
	public Task queryById(int sid) {
		return this.taskMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.taskMapper.queryAndcount(condition);
	}

	@Override
	public List<Task> queryAll() {
		return this.taskMapper.queryAll();
	}

	@Override
	public List<Task> queryByLessonID(int lessonID, int offset, int count) {
		return this.taskMapper.queryByLessonID(lessonID, offset, count);
	}

	@Override
	public List<Task> queryWithJoinLesson(int offset, int limit) {
		return this.taskMapper.queryWithJoinLesson(offset, limit);
	}
}
