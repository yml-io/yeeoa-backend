package com.yeeoa.mapper;

import com.yeeoa.bean.Step;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StepMapper extends BaseMapper<Step> {

	@Insert("insert into step" +
			"(task_id, step_id, estimated_times, step_actions, step_details, display_page, page, table_resource, status) " +
			" values " +
			"(#{taskID},#{stepID},#{estimatedTimes},#{stepActions},#{stepDetails},#{displayPage},#{page},#{tableResource},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Step step);

	@Update("<script>" +
			"update step set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='taskID!=null'>, task_id = #{taskID}</if>" +
			"<if test='stepID!=null'>, step_id = #{stepID}</if>" +
			"<if test='estimatedTimes!=null'>, estimated_times = #{estimatedTimes}</if>" +
			"<if test='stepActions!=null'>, step_actions = #{stepActions}</if>" +
			"<if test='stepDetails!=null'>, step_details = #{stepDetails}</if>" +
			"<if test='displayPage!=null'>, display_page = #{displayPage}</if>" +
			"<if test='page!=null'>, page = #{page}</if>" +
			"<if test='tableResource!=null'>, table_resource = #{tableResource}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Step step);

	@Delete("delete from step where id=#{sid}")
    int deleteById(int sid);

	@Select("select * from step where id=#{sid}")
	@Results(id = "selectStepMap1",value= {
			@Result(property="taskID", column="task_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="stepID", column="step_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedTimes", column="estimated_times", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="stepActions", column="step_actions", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stepDetails", column = "step_details", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "displayPage", column = "display_page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "page", column = "page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "tableResource", column = "table_resource", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="task",column="task_id",one=@One(select="com.yeeoa.mapper.TaskMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Step queryById(int sid);


	@Select("select * from step")
	@Results(id = "selectStepMap2",value= {
			@Result(property="taskID", column="task_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="stepID", column="step_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedTimes", column="estimated_times", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="stepActions", column="step_actions", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stepDetails", column = "step_details", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "displayPage", column = "display_page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "page", column = "page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "tableResource", column = "table_resource", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Step> queryAll();

	@Select("select count(*) from step where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from step where task_id=#{taskID} limit #{count} offset #{offset}")
	@Results(id = "selectStepMap3",value= {
			@Result(property="taskID", column="task_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="stepID", column="step_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedTimes", column="estimated_times", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="stepActions", column="step_actions", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stepDetails", column = "step_details", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "displayPage", column = "display_page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "page", column = "page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "tableResource", column = "table_resource", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Step> queryByTaskID(@Param("taskID") int taskID, @Param("offset") int offset, @Param("count") int count);

	@Select("select * from step limit #{count} offset #{offset}")
	@Results(id = "selectStepMap4",value= {
			@Result(property="taskID", column="task_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="stepID", column="step_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedTimes", column="estimated_times", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="stepActions", column="step_actions", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "stepDetails", column = "step_details", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "displayPage", column = "display_page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "page", column = "page", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "tableResource", column = "table_resource", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="task",column="task_id",one=@One(select="com.yeeoa.mapper.TaskMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Step> queryWithJoinTask(@Param("offset") int offset, @Param("count") int count);
}
