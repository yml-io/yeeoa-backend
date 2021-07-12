package com.yeeoa.service.impl;

import com.yeeoa.bean.Process;
import com.yeeoa.mapper.ProcessMapper;
import com.yeeoa.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("ProcessService")
public class ProcessServiceImp implements ProcessService {

	@Autowired
	private ProcessMapper processMapper;

	@Override
	public int add(Process process) {
		return this.processMapper.add(process);
	}

	@Override
	public int update(Process process) {
		return this.processMapper.update(process);
	}

	@Override
	public int deleteById(int sid) {
		return this.processMapper.deleteById(sid);
	}

	@Override
	public Process queryById(int sid) {
		return this.processMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.processMapper.queryAndcount(condition);
	}

	@Override
	public List<Process> queryAll() {
		return this.processMapper.queryAll();
	}

	@Override
	public List<Process> queryByUnitID(int unitID, int offset, int count) {
		return this.processMapper.queryByUnitID(unitID, offset, count);
	}

	@Override
	public List<Process> queryWithJoinUnit(int offset, int limit) {
		return this.processMapper.queryWithJoinUnit(offset, limit);
	}
}
