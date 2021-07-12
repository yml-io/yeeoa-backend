package com.yeeoa.service.impl;

import com.yeeoa.bean.Step;
import com.yeeoa.mapper.StepMapper;
import com.yeeoa.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stepService")
public class StepServiceImp implements StepService {

	@Autowired
	private StepMapper stepMapper;

	@Override
	public int add(Step step) {
		return this.stepMapper.add(step);
	}

	@Override
	public int update(Step step) {
		return this.stepMapper.update(step);
	}

	@Override
	public int deleteById(int sid) {
		return this.stepMapper.deleteById(sid);
	}

	@Override
	public Step queryById(int sid) {
		return this.stepMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.stepMapper.queryAndcount(condition);
	}

	@Override
	public List<Step> queryAll() {
		return this.stepMapper.queryAll();
	}

	@Override
	public List<Step> queryByTaskID(int taskID, int offset, int count) {
		return this.stepMapper.queryByTaskID(taskID, offset, count);
	}

	@Override
	public List<Step> queryWithJoinTask(int offset, int limit) {
		return this.stepMapper.queryWithJoinTask(offset, limit);
	}
}
