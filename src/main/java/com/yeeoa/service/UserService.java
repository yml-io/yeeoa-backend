package com.yeeoa.service;
import com.yeeoa.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
	int add(User user);
    int update(User user);
    int deleteById(int sid);
    User queryById(int sid);

    User queryByUsername(String username);

    User getUserInfo(String username);
    User queryForLogin(String username, String password);
    List<User> queryAll();

    List<User> queryAllWithPaging(int offset, int count);

    int queryAndcount(String condition);


    List<String> getUserRoles(int userId);
    User getJwtTokenInfo(String username);
    String generateJwtToken(String username);
    void deleteLoginInfo(String username);
}
