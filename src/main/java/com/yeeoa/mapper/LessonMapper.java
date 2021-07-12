package com.yeeoa.mapper;

import com.yeeoa.bean.Lesson;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

	@Insert("insert into lesson" +
			"(unit_id, user_id, lesson_no, title, timelength, focus, imperatives, description, stage, objectives, status) " +
			" values " +
			"(#{unitID},#{userID},#{lessonNo},#{title},#{timelength},#{focus},#{imperatives},#{description},#{stage},#{objectives},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Lesson lesson);

	@Update("<script>" +
			"update lesson set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='unitID!=null'>, unit_id = #{unitID}</if>" +
			"<if test='userID!=null'>, user_id = #{userID}</if>" +
			"<if test='lessonNo!=null'>, lesson_no = #{lessonNo}</if>" +
			"<if test='title!=null'>, title = #{title}</if>" +
			"<if test='timelength!=null'>, timelength = #{timelength}</if>" +
			"<if test='focus!=null'>, focus = #{focus}</if>" +
			"<if test='imperatives!=null'>, imperatives = #{imperatives}</if>" +
			"<if test='description!=null'>, description = #{description}</if>" +
			"<if test='stage!=null'>, stage = #{stage}</if>" +
			"<if test='objectives!=null'>, objectives = #{objectives}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Lesson lesson);

	@Delete("delete from lesson where id=#{sid}")
    int deleteById(int sid);

	@Select("select * from lesson where id=#{sid}")
	@Results(id = "selectLessonMap1",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="userID", column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="lessonNo", column="lesson_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="timelength", column="timelength", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "focus", column = "focus", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imperatives", column = "imperatives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "description", column = "description", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stage", column = "stage", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="unit",column="unit_id",one=@One(select="com.yeeoa.mapper.UnitMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Lesson queryById(int sid);


	@Select("select * from lesson")
	@Results(id = "selectLessonMap2",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="userID", column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="lessonNo", column="lesson_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="timelength", column="timelength", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "focus", column = "focus", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imperatives", column = "imperatives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "description", column = "description", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stage", column = "stage", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Lesson> queryAll();

	@Select("select count(*) from lesson where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from lesson where unit_id=#{unitID} limit #{count} offset #{offset}")
	@Results(id = "selectLessonMap3",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="userID", column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="lessonNo", column="lesson_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="timelength", column="timelength", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "focus", column = "focus", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imperatives", column = "imperatives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "description", column = "description", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stage", column = "stage", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Lesson> queryByUnitID(@Param("unitID") int unitID, @Param("offset") int offset, @Param("count") int count);



	@Select("select * from lesson limit #{count} offset #{offset}")
	@Results(id = "selectLessonMap5",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="userID", column="user_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="lessonNo", column="lesson_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="timelength", column="timelength", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "focus", column = "focus", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "imperatives", column = "imperatives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "description", column = "description", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stage", column = "stage", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="unit",column="unit_id",one=@One(select="com.yeeoa.mapper.UnitMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)

	})
	List<Lesson> queryWithJoinUnit(@Param("offset") int offset, @Param("count") int count);

}
