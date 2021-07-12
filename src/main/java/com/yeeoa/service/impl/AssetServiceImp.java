package com.yeeoa.service.impl;

import com.yeeoa.bean.Asset;
import com.yeeoa.mapper.AssetMapper;
import com.yeeoa.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assetService")
public class AssetServiceImp implements AssetService {

	@Autowired
	private AssetMapper assetMapper;

	@Override
	public int add(Asset asset) {
		return this.assetMapper.add(asset);
	}

	@Override
	public int update(Asset asset) {
		return this.assetMapper.update(asset);
	}

	@Override
	public int deleteById(int sid) {
		return this.assetMapper.deleteById(sid);
	}

	@Override
	public Asset queryById(int sid) {
		return this.assetMapper.queryById(sid);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.assetMapper.queryAndcount(condition);
	}

	@Override
	public List<Asset> queryAll() {
		return this.assetMapper.queryAll();
	}

	@Override
	public List<Asset> queryByLessonID(int lessonID, int offset, int count) {
		return this.assetMapper.queryByLessonID(lessonID, offset, count);
	}

	@Override
	public List<Asset> queryByType(String type, int offset, int count) {
		return this.assetMapper.queryByType(type, offset, count);
	}

	@Override
	public Asset queryByHashID(String hashid) {
		return this.assetMapper.queryByHashID(hashid);
	}
}
