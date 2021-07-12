package com.yeeoa.service.impl;

import com.yeeoa.bean.Program;
import com.yeeoa.bean.settings.AppProperties;
import com.yeeoa.mapper.ProgramMapper;
import com.yeeoa.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("programService")
public class ProgramServiceImp implements ProgramService {

	@Autowired
	private ProgramMapper programMapper;

	@Autowired
	private AppProperties appProperties;

	@Override
	public int add(Program program) {
		return this.programMapper.add(program);
	}

	@Override
	public int update(Program program) {
		return this.programMapper.update(program);
	}

	@Override
	public int deleteById(int sid) {
		return this.programMapper.deleteById(sid);
	}

	@Override
	public Program queryById(int sid) {
		return this.programMapper.queryById(sid);
	}

	@Override
	public List<Program> queryAll() {
		return this.programMapper.queryAll();
	}

	@Override
	public List<Program> queryAllWithPaging(int offset, int count) {
		return this.programMapper.queryAllWithPaging(offset, count);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.programMapper.queryAndcount(condition);
	}



	private List<Map<String, String>> convertListToMap(List<String> strList, String d) {
		List<Map<String, String>> result = new ArrayList<>();

		for(int i = 0; i < strList.size(); i++) {
			String[] strShards = strList.get(i).split(d);
			if (strShards.length > 1) {
				Map<String, String> curMap = new HashMap<>();
				curMap.put("code", strShards[0]);
				curMap.put("value", strShards[1]);
				result.add(curMap);
			}
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getProgramType() {
	    System.out.println(  this.appProperties.getProgramType() );
		List<Map<String, String>> result = convertListToMap(this.appProperties.getProgramType(), ":");
		return result;
	}

	@Override
	public List<Map<String, String>> getCountry() {
		List<Map<String, String>> result = convertListToMap(this.appProperties.getCountry(), ":");
		return result;
	}

	@Override
	public List<Map<String, String>> getSchoolCode() {
		List<Map<String, String>> result = convertListToMap(this.appProperties.getSchoolCode(), ":");
		return result;
	}

	@Override
	public List<Map<String, String>> getGrade() {
		List<Map<String, String>> result = convertListToMap(this.appProperties.getGrade(), ":");
		return result;
	}

	@Override
	public List<Map<String, String>> getSemesterMap() {
		List<Map<String, String>> result = convertListToMap(this.appProperties.getSemesterCountryMap(), ":");
		// convert value of map to list<String>
		return result;
	}
}
