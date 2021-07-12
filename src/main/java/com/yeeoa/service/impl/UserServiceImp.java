package com.yeeoa.service.impl;

import com.yeeoa.bean.Program;
import com.yeeoa.bean.User;
import com.yeeoa.mapper.ProgramMapper;
import com.yeeoa.mapper.UserMapper;
import com.yeeoa.security.configuration.JwtUtils;
import com.yeeoa.service.ProgramService;
import com.yeeoa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("UserService")
public class UserServiceImp implements UserService {
	private static final String encryptSalt = ""; //"F12839WhsnnEV$#23b";

	@Autowired
	private UserMapper userMapper;

	@Override
	public int add(User user) {
		return this.userMapper.add(user);
	}

	@Override
	public int update(User user) {
		return this.userMapper.update(user);
	}

	@Override
	public int deleteById(int sid) {
		return this.userMapper.deleteById(sid);
	}

	@Override
	public User queryById(int sid) {
		return this.userMapper.queryById(sid);
	}

	@Override
	public User queryByUsername(String username) {
		User user = this.userMapper.queryByUsername(username);
		// 隐藏字段
		if (user != null) {
			user.setPassword("");
		}
		return user;
	}

	@Override
	public User queryForLogin(String username, String password) {
		User result = this.userMapper.queryForLogin(username, password);
		return result;
	}

	@Override
	public List<User> queryAll() {
		return this.userMapper.queryAll();
	}

	@Override
	public List<User> queryAllWithPaging(int offset, int count) {
		return this.userMapper.queryAllWithPaging(offset, count);
	}

	@Override
	public int queryAndcount(String condition) {
		return this.userMapper.queryAndcount(condition);
	}

	@Override
	public User getUserInfo(String username) {
		User user = this.userMapper.queryByUsername(username);
		return user;
	}

	// security
	/**
	 * 保存user登录信息，返回token
	 */
	public String generateJwtToken(String username) {
		String salt = "12345";//JwtUtils.generateSalt();
		/**
		 * @todo 将salt保存到数据库或者缓存中
		 * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
		 */
		return JwtUtils.sign(username, salt, 1800); //生成jwt token，设置过期时间为半小时
	}

	/**
	 * 清除token信息
	 */
	public void deleteLoginInfo(String username) {
		/**
		 * @todo 删除数据库或者缓存中保存的salt
		 * redisTemplate.delete("token:"+username);
		 */

	}

	/**
	 * 获取上次token生成时的salt值和登录用户信息
	 * @param username
	 * @return
	 */
	public User getJwtTokenInfo(String username) {
		String salt = "12345";
		/**
		 * @todo 从数据库或者缓存中取出jwt token生成时用的salt
		 * salt = redisTemplate.opsForValue().get("token:"+username);
		 */
		User user = getUserInfo(username);
		user.setSalt(salt);
		return user;
	}
	/**
	 * 获取用户角色列表，强烈建议从缓存中获取
	 * @param userId
	 * @return
	 */
	public List<String> getUserRoles(int userId){
		return Arrays.asList("admin");
	}

}
