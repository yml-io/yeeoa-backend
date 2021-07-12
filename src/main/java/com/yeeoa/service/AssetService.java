package com.yeeoa.service;

import com.yeeoa.bean.Asset;

import java.util.List;

public interface AssetService {
	int add(Asset unit);
    int update(Asset unit);
    int deleteById(int sid);
    Asset queryById(int sid);
    List<Asset> queryAll();

    List<Asset> queryByLessonID(int lessonID, int offset, int count);
    List<Asset> queryByType(String type, int offset, int count);

    int queryAndcount(String condition);

    Asset queryByHashID(String hashid);


}
