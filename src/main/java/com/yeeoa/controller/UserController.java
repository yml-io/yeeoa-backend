package com.yeeoa.controller;

import com.yeeoa.bean.User;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;

import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description = "User Interface")
@Controller("user")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "*",maxAge = 3600)
	@ApiOperation(value = "登錄", notes="返回的token需要在之後的請求中攜帶")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HttpResponse login(@ApiJsonObject(name = "user_login", value = {
			@ApiJsonProperty(key = "username", example = "user1", description = "用户名"),
			@ApiJsonProperty(key = "password", example = "bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a", description = "密码")
			})
			@RequestBody Map<String, Object> requestParams) {
		Subject subject = SecurityUtils.getSubject();
		try {
			//将用户请求参数封装后，直接提交给Shiro处理
			UsernamePasswordToken token = new UsernamePasswordToken((String)requestParams.get("username"), (String)requestParams.get("password"));
			subject.login(token);
			//Shiro认证通过后会将user信息放到subject内，生成token并返回
			User user = (User) subject.getPrincipal();
			String newToken = userService.generateJwtToken(user.getUsername());

			SuccessWithDataResp successResp = new SuccessWithDataResp();
			class LoginData {
				public boolean isAdmin() {
					return isAdmin;
				}

				public void setAdmin(boolean admin) {
					isAdmin = admin;
				}

				public String getAvatar() {
					return avatar;
				}

				public void setAvatar(String avatar) {
					this.avatar = avatar;
				}

				public String getSessionToken() {
					return sessionToken;
				}

				public void setSessionToken(String sessionToken) {
					this.sessionToken = sessionToken;
				}

				boolean isAdmin;
				String avatar;
				String sessionToken;
			}
			LoginData loginData = new LoginData();
			loginData.isAdmin = user.getIsAdmin() == 1;
			loginData.avatar = user.getAvatar();
			loginData.sessionToken = newToken;
			successResp.setData(loginData);
			return successResp;
		} catch (AuthenticationException e) {
			// 如果校验失败，shiro会抛出异常，返回客户端失败
			FailedResp failedResp = new FailedResp();
			failedResp.setMessage("authentication failed.");
			return failedResp;
		} catch (Exception e) {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}

//		User user = this.userService.queryForLogin(
//				(String)requestParams.get("username"),
//				(String)requestParams.get("password")
//		);

//		if (user != null) {
//
//		} else {
//
//		}
	}

	@ApiOperation(value = "登出", notes="用戶退出登錄狀態， token 將不可用")
	public HttpResponse logout() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipals() != null) {
			User user = (User)subject.getPrincipals().getPrimaryPrincipal();
			userService.deleteLoginInfo(user.getUsername());
		}
		SecurityUtils.getSubject().logout();

		SuccessResp successResp = new SuccessResp();
		return successResp;
	}

//	@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//	@RequiresPermissions("vip")
//	@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
@ApiOperation(value = "用戶列表", notes="獲取用戶列表")
	@RequiresRoles("admin")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<User> unitList = this.userService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(unitList);
		return successResp;
	}

	@ApiOperation(value = "查詢用戶信息", notes="單用戶查詢")
	@RequiresRoles("admin")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "user_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "10", description = "Integer")
	})	@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		User user = this.userService.queryById(sid);
		if (user != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(user);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	@ApiOperation(value = "查詢用戶信息根据用户名", notes="單用戶查詢")
	@RequestMapping(value = "/query_by_username", method = RequestMethod.POST)
	public HttpResponse queryByUsername(@ApiJsonObject(name = "user_query_by_username", value = {
			@ApiJsonProperty(key = "username", example = "user", description = "String")
	})	@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		String username = (String)requestParams.get("username");

		User user = this.userService.queryByUsername(username);
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(user);
		return successResp;

	}

	@ApiOperation(value = "用戶創建", notes="創建用戶")
	@RequiresRoles("admin")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "user_create", value = {
			@ApiJsonProperty(key = "username", example = "user1", description = "用户名"),
			@ApiJsonProperty(key = "email", example = "test@163.com", description = "联系邮箱"),
			@ApiJsonProperty(key = "school", example = "Nan Jing University", description = "所述学校"),
			@ApiJsonProperty(key = "password", example = "bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a", description = "Sha512加密的密码，无salt"),
			@ApiJsonProperty(key = "gender", example = "male", description = "性别"),
			@ApiJsonProperty(key = "avatar", example = "http://image.sina.com/111111.jpg", description = "用户图片资源"),
			@ApiJsonProperty(key = "status", example = "processing", description = "自定义状态")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		User user = new User();

		user.setUsername((String)requestParams.get("username"));
		user.setEmail((String)requestParams.getOrDefault("email", ""));
		user.setSchool((String)requestParams.getOrDefault("school", ""));
		user.setPassword((String)requestParams.get("password"));
		user.setGender((String)requestParams.getOrDefault("gender", ""));
		user.setAvatar((String)requestParams.getOrDefault("avatar", ""));
		user.setStatus((String)requestParams.getOrDefault("status", ""));

		int result = this.userService.add(user);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(user);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	@ApiOperation(value = "更新用戶", notes="多用途更新用戶")
	@RequiresRoles("admin")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "user_update", value = {
			@ApiJsonProperty(key = "username", example = "user1", description = "用户名"),
			@ApiJsonProperty(key = "email", example = "test@163.com", description = "联系邮箱"),
			@ApiJsonProperty(key = "school", example = "Nan Jing University", description = "所述学校"),
			@ApiJsonProperty(key = "password", example = "bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a", description = "Sha512加密的密码，无salt"),
			@ApiJsonProperty(key = "gender", example = "male", description = "性别"),
			@ApiJsonProperty(key = "avatar", example = "http://image.sina.com/111111.jpg", description = "用户图片资源"),
			@ApiJsonProperty(key = "status", example = "processing", description = "自定义状态")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		User user = new User();
		user.setId((Integer)requestParams.get("id"));
		user.setUsername((String)requestParams.getOrDefault("username", null));
		user.setEmail((String)requestParams.getOrDefault("email", null));
		user.setSchool((String)requestParams.getOrDefault("school", null));
		user.setPassword((String)requestParams.getOrDefault("password", null));
		user.setGender((String)requestParams.getOrDefault("gender", null));
		user.setAvatar((String)requestParams.getOrDefault("avatar", null));
		user.setStatus((String)requestParams.getOrDefault("status", null));

		int result = this.userService.update(user);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(user);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	@ApiOperation(value = "刪除用戶", notes="真真正正的在數據庫裡面刪除")
	@RequiresRoles("admin")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "user_delete", value = {
			@ApiJsonProperty(key = "id", example = "10", description = "Integer"),
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.userService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}
}