package com.yeeoa.mapper;

import com.yeeoa.bean.Unit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UnitMapper extends BaseMapper<Unit> {

	@Insert("insert into unit" +
			"(program_id, unit_id, label, name, estimated_hours, actual_hours, description, targeted_problems, created_products,  shared_problem, objectives, overviews, status) " +
			" values " +
			"(#{programID},#{unitID},#{label},#{name},#{estimatedHours},#{actualHours},#{description},#{targetedProblems},#{createdProducts},#{sharedProblem},#{objectives},#{overviews},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Unit unit);

	@Update("<script>" +
			"update unit set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='programID!=null'>, program_id = #{programID}</if>" +
			"<if test='unitID!=null'>, unit_id = #{unitID}</if>" +
			"<if test='label!=null'>, label = #{label}</if>" +
			"<if test='name!=null'>, name = #{name}</if>" +
			"<if test='estimatedHours!=null'>, estimated_hours = #{estimatedHours}</if>" +
			"<if test='actualHours!=null'>, actual_hours = #{actualHours}</if>" +
			"<if test='description!=null'>, description = #{description}</if>" +
			"<if test='targetedProblems!=null'>, targeted_problems = #{targetedProblems}</if>" +
			"<if test='createdProducts!=null'>, created_products = #{createdProducts}</if>" +
			"<if test='sharedProblem!=null'>, shared_problem = #{sharedProblem}</if>" +
			"<if test='objectives!=null'>, objectives = #{objectives}</if>" +
			"<if test='overviews!=null'>, overviews = #{overviews}</if>" +
			"<if test='status!=null'>, status = #{status}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Unit unit);
	
	@Delete("delete from unit where id=#{sid}")
    int deleteById(int sid);
	
	@Select("select * from unit where id=#{sid}")
//	@ResultMap("com.yeeoa.mapper.resultmap.programResultMap")

	@Results(id = "selectUnitMap1",value= {
			@Result(property="programID", column="program_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="unitID", column="unit_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="label", column="label", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedHours", column="estimated_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="actualHours", column="actual_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="description", column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="targetedProblems", column="targeted_problems", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="createdProducts", column="created_products", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="sharedProblem", column="shared_problem", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="overviews", column="overviews", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="program",column="program_id",one=@One(select="com.yeeoa.mapper.ProgramMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Unit queryById(int sid);


	@Select("select * from unit")
	@Results(id = "selectUnitMap2",value= {
			@Result(property="programID", column="program_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="unitID", column="unit_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="label", column="label", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedHours", column="estimated_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="actualHours", column="actual_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="description", column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="targetedProblems", column="targeted_problems", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="createdProducts", column="created_products", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="sharedProblem", column="shared_problem", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="overviews", column="overviews", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Unit> queryAll();

	@Select("select count(*) from unit where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from unit where program_id=#{programID} limit #{count} offset #{offset}")
	@Results(id = "selectUnitMap3",value= {
			@Result(property="programID", column="program_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="unitID", column="unit_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="label", column="label", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedHours", column="estimated_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="actualHours", column="actual_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="description", column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="targetedProblems", column="targeted_problems", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="createdProducts", column="created_products", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="sharedProblem", column="shared_problem", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="overviews", column="overviews", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Unit> queryByProgramID(@Param("programID") int programID,@Param("offset") int offset, @Param("count") int count);


	@Select("select * from unit limit #{count} offset #{offset}")
	@Results(id = "selectUnitMap4",value= {
			@Result(property="programID", column="program_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="unitID", column="unit_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="label", column="label", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="estimatedHours", column="estimated_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="actualHours", column="actual_hours", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="description", column="description", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="targetedProblems", column="targeted_problems", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="createdProducts", column="created_products", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="sharedProblem", column="shared_problem", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "objectives", column = "objectives", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="overviews", column="overviews", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "status", column = "status", javaType = String.class, jdbcType=JdbcType.VARCHAR),

			@Result(property="program",column="program_id",one=@One(select="com.yeeoa.mapper.ProgramMapper.queryById")),

			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Unit> queryWithJoinProgram(@Param("offset") int offset, @Param("count") int count);

}
