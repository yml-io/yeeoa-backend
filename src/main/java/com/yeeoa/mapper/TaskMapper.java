package com.yeeoa.mapper;

import com.yeeoa.bean.Task;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

	@Insert("insert into task" +
			"(lesson_id, task_id, task_title, objectives, status) " +
			" values " +
			"(#{lessonID},#{taskID},#{taskTitle},#{objectives},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Task task);

	@Update("<script>" +
			"update task set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='lessonID!=null'>, lesson_id = #{lessonID}</if>" +
			"<if test='taskID!=null'>, task_id = #{taskID}</if>" +
			"<if test='taskTitle!=null'>, task_title = #{taskTitle}</if>" +
			"<if test='objectives!=null'>, objectives = #{objectives}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Task task);

	@Delete("delete from task where id=#{sid}")
    int deleteById(int sid);

	@Select("select * from task where id=#{sid}")
//	@ResultMap("com.yeeoa.mapper.resultmap.programResultMap")

	@Results(id = "selectTaskMap1",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="taskID", column="task_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="taskTitle", column="task_title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="lesson",column="lesson_id",one=@One(select="com.yeeoa.mapper.LessonMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Task queryById(int sid);


	@Select("select * from task")
	@Results(id = "selectTaskMap2",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="taskID", column="task_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="taskTitle", column="task_title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Task> queryAll();

	@Select("select count(*) from task where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from task where lesson_id=#{lessonID} limit #{count} offset #{offset}")
	@Results(id = "selectTaskMap3",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="taskID", column="task_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="taskTitle", column="task_title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Task> queryByLessonID(@Param("lessonID") int lessonID, @Param("offset") int offset, @Param("count") int count);


	@Select("select * from task limit #{count} offset #{offset}")
	@Results(id = "selectTaskMap4",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="taskID", column="task_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="taskTitle", column="task_title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="lesson",column="lesson_id",one=@One(select="com.yeeoa.mapper.LessonMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Task> queryWithJoinLesson(@Param("offset") int offset, @Param("count") int count);

}
