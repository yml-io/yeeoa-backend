package com.yeeoa.service.impl;

import com.yeeoa.bean.Unit;
import com.yeeoa.mapper.UnitMapper;
import com.yeeoa.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("unitService")
public class UnitServiceImp implements UnitService {

	@Autowired
	private UnitMapper UnitMapper;

	@Override
	public int add(Unit unit) {
		return this.UnitMapper.add(unit);
	}

	@Override
	public int update(Unit unit) {
		return this.UnitMapper.update(unit);
	}

	@Override
	public int deleteById(int sid) {
		return this.UnitMapper.deleteById(sid);
	}

	@Override
	public Unit queryById(int sid) {
		return this.UnitMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.UnitMapper.queryAndcount(condition);
	}

	@Override
	public List<Unit> queryAll() {
		return this.UnitMapper.queryAll();
	}

	@Override
	public List<Unit> queryByProgramID(int programID, int offset, int count) {
		return this.UnitMapper.queryByProgramID(programID, offset, count);
	}

	@Override
	public List<Unit> queryWithJoinProgram(int offset, int limit) {
		return this.UnitMapper.queryWithJoinProgram(offset, limit);
	}
}
