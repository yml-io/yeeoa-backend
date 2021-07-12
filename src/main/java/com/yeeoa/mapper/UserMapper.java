package com.yeeoa.mapper;

import com.yeeoa.bean.Program;
import com.yeeoa.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

	@Insert("insert into user(username, email, school, password, gender, avatar, is_admin, status) values(#{username},#{email},#{school},#{password},#{gender},#{avatar},#{isAdmin},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(User user);

	@Update("<script>" +
			"update into set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='username!=null'>, username = #{username}</if>" +
			"<if test='email!=null'>, email = #{email}</if>" +
			"<if test='school!=null'>, school = #{school}</if>" +
			"<if test='password!=null'>, password = #{password}</if>" +
			"<if test='gender!=null'>, gender = #{gender}</if>" +
			"<if test='avatar!=null'>, avatar = #{avatar}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"<if test='salt!=null'>, salt = #{salt}</if>" +
			"<if test='roles!=null'>, roles = #{roles}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(User user);
	
	@Delete("delete from user where id=#{sid}")
    int deleteById(int sid);
	
	@Select("select * from user where id=#{sid}")
//	@ResultMap("com.yeeoa.mapper.resultmap.programResultMap")
	@Results(id = "selectUserMap1",value= {
			@Result(property="username", column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="email", column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "gender", column = "gender", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "avatar", column = "avatar", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "isAdmin", column = "is_admin", javaType = Byte.class, jdbcType=JdbcType.TINYINT),
			@Result(property="status", column="status", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="salt", column="salt", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="roles", column="roles", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	User queryById(int sid);


	@Select("select * from user")
	@Results(id = "selectUserMap2",value= {
			@Result(property="username", column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="email", column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "gender", column = "gender", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "avatar", column = "avatar", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "isAdmin", column = "is_admin", javaType = Byte.class, jdbcType=JdbcType.TINYINT),
			@Result(property="status", column="status", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="salt", column="salt", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="roles", column="roles", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<User> queryAll();


	@Select("select * from user where username=#{username}")
	@Results(id = "selectUserByUsernameMap",value= {
			@Result(property="username", column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="email", column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "gender", column = "gender", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "avatar", column = "avatar", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "isAdmin", column = "is_admin", javaType = Byte.class, jdbcType=JdbcType.TINYINT),
			@Result(property="status", column="status", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="salt", column="salt", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="roles", column="roles", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	User queryByUsername(@Param("username") String username);

	@Select("select * from user where ${condition}")
	@Results(id = "selectUserConditionMap",value= {
			@Result(property="username", column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="email", column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "gender", column = "gender", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "avatar", column = "avatar", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "isAdmin", column = "is_admin", javaType = Byte.class, jdbcType=JdbcType.TINYINT),
			@Result(property="status", column="status", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="salt", column="salt", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="roles", column="roles", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<User> queryByCondition(@Param("condition") String condition);

	@Select("select * from user where username=#{username} and password=#{password}")
	@Results(id = "userLoginMap1",value= {
			@Result(property="username", column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="email", column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "gender", column = "gender", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "avatar", column = "avatar", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "isAdmin", column = "is_admin", javaType = Byte.class, jdbcType=JdbcType.TINYINT),
			@Result(property="status", column="status", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="salt", column="salt", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="roles", column="roles", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	User queryForLogin(@Param("username") String username, @Param("password") String password);
}
