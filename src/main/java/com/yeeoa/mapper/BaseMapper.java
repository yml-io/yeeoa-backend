package com.yeeoa.mapper;

import java.util.List;

// 提供基本 mapper 的功能约定
public interface BaseMapper<T> {
    int add(T element);
    int update(T element);

    int deleteById(int sid);

    T queryById(int sid);

    List<T> queryAll();

    List<T> queryAllWithPaging(int offset, int count);

    int queryAndcount(String condition);
}
