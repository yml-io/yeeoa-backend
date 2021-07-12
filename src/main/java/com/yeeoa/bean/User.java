package com.yeeoa.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{

	public User() {
	}

	private static final long serialVersionUID = -339516038496531943L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

//	@ApiComment(value = "User Name", sample = "user")
	private String username;
//	@ApiComment(value = "User Email", sample = "123@163.com")
	private String email;
//	@ApiComment(value = "School", sample = "Nan Jing University")
	private String school;
//	@ApiComment(value = "Encrypted Password (Sha256)", sample = "123")
	private String password;
//	@ApiComment(value = "What Gender of the User is", sample = "Male")
	private String gender;
//	@ApiComment(value = "Image URL the User used", sample = "http://img.sina.com/xxx.png")
	private String avatar;
//	@ApiComment(value = "Indicates whether the user is an administrator", sample = "123")
//	@RestPackIgnore
	private byte isAdmin;
//	@ApiComment(value = "A String which describe the account status", sample = "processing")
	private String status;
//	@ApiComment(value = "Use for encryption", sample = "")
	private String salt;
//	@ApiComment(value = "A String List which join with '|'", sample = "user|guest|viewer")
	private String roles;
//	@ApiComment(value = "id", sample = "")
	private int id;
//	@ApiComment(value = "createTime", sample = "")
	private java.sql.Timestamp createTime;
//	@ApiComment(value = "enabled", sample = "1")
	private byte enabled;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}
