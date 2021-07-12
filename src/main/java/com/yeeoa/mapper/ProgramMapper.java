package com.yeeoa.mapper;

import com.yeeoa.bean.Program;
import com.yeeoa.bean.Unit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProgramMapper extends BaseMapper<Program> {

	@Insert("insert into program (" +
			"program_id, " +
			"title, " +
			"brief, " +
			"summary, " +
			"name, " +
			"owner, " +
			"img_src, " +
			"grade, " +
			"semester, " +
			"objectives, " +
			"status, " +
			"feature, " +
			"agelevel, " +
			"overview, " +
			"school)" +
			"     values  (  " +
			"#{programID}," +
			"#{title}," +
			"#{brief}," +
			"#{summary}," +
			"#{name}," +
			"#{owner}," +
			"#{imgSrc}," +
			"#{grade}," +
			"#{semester}," +
			"#{objectives}," +
			"#{status}," +
			"#{feature}," +
			"#{agelevel}," +
			"#{overview}," +
			"#{school})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Program program);

	@Update("<script>" +
			"update program set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='programID!=null'>, program_id = #{programID}</if>" +
			"<if test='title!=null'>, title = #{title}</if>" +
			"<if test='brief!=null'>, brief = #{brief}</if>" +
			"<if test='summary!=null'>, summary = #{summary}</if>" +
			"<if test='name!=null'>, name = #{name}</if>" +
			"<if test='owner!=null'>, owner = #{owner}</if>" +
			"<if test='imgSrc!=null'>, img_src = #{imgSrc}</if>" +
			"<if test='grade!=null'>, grade = #{grade}</if>" +
			"<if test='semester!=null'>, semester = #{semester}</if>" +
			"<if test='objectives!=null'>, objectives = #{objectives}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"<if test='feature!=null'>, feature = #{feature}</if>" +
			"<if test='agelevel!=null'>, agelevel = #{agelevel}</if>" +
			"<if test='overview!=null'>, overview = #{overview}</if>" +
			"<if test='school!=null'>, school = #{school}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Program program);
	
	@Delete("delete from program where id=#{sid}")
    int deleteById(int sid);
	
	@Select("select * from program where id=#{sid}")
//	@ResultMap("com.yeeoa.mapper.resultmap.programResultMap.selectProgramMap")
	@Results(id = "selectProgramMap1",value= {
			@Result(property="programID", column="program_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="brief", column="brief", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="summary", column="summary", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "owner", column = "owner", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imgSrc", column = "img_src", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "grade", column = "grade", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "semester", column = "semester", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "feature", column = "feature", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "agelevel", column = "agelevel", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "overview", column = "overview", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Program queryById(int sid);


	@Select("select * from program")
	@Results(id = "selectProgramMap2",value= {
			@Result(property="programID", column="program_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="brief", column="brief", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="summary", column="summary", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "owner", column = "owner", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imgSrc", column = "img_src", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "grade", column = "grade", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "semester", column = "semester", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "feature", column = "feature", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "agelevel", column = "agelevel", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "overview", column = "overview", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Program> queryAll();

	@Select("select count(*) from program where ${condition}")
	int queryAndcount(@Param("condition") String condition);


	/*
	 *	custom mapper interface
	 * */
	@Select("select * from program limit #{count} offset #{offset}")
	@Results(id = "selectProgramMap3",value= {
			@Result(property="programID", column="program_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="brief", column="brief", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="summary", column="summary", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "owner", column = "owner", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imgSrc", column = "img_src", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "grade", column = "grade", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "semester", column = "semester", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "feature", column = "feature", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "agelevel", column = "agelevel", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "overview", column = "overview", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Program> queryAllWithPaging(@Param("offset") int offset, @Param("count") int count);
}
