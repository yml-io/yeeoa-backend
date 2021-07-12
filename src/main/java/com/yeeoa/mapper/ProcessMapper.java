package com.yeeoa.mapper;

import com.yeeoa.bean.Process;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProcessMapper extends BaseMapper<Process> {

	@Insert("insert into process" +
			"(unit_id, name, objectives, status) " +
			" values " +
			"(#{unitID},#{name},#{objectives},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Process process);

	@Update("<script>" +
			"update process set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='unitID!=null'>, unit_id = #{unitID}</if>" +
			"<if test='name!=null'>, name = #{name}</if>" +
			"<if test='objectives!=null'>, objectives = #{objectives}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Process process);

	@Delete("delete from process where id=#{sid}")
    int deleteById(int sid);

	@Select("select * from process where id=#{sid}")
	@Results(id = "selectProcessMap1",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Process queryById(int sid);


	@Select("select * from process")
	@Results(id = "selectProcessMap2",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Process> queryAll();

	@Select("select count(*) from process where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from process where unit_id=#{unitID} limit #{count} offset #{offset}")
	@Results(id = "selectProcessMap3",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Process> queryByUnitID(@Param("unitID") int programID, @Param("offset") int offset, @Param("count") int count);


	@Select("select * from process limit #{count} offset #{offset}")
	@Results(id = "selectProcessMap4",value= {
			@Result(property="unitID", column="unit_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="objectives", column="objectives", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT),

			@Result(property="unit",column="unit_id",one=@One(select="com.yeeoa.mapper.UnitMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Process> queryWithJoinUnit(@Param("offset") int offset, @Param("count") int count);

}
