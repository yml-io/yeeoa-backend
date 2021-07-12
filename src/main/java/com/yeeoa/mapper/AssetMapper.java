package com.yeeoa.mapper;

import com.yeeoa.bean.Asset;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AssetMapper extends BaseMapper<Asset> {

	@Insert("insert into asset" +
			"(lesson_id, type, comment, name, hashid, url, parsed_content) " +
			" values " +
			"(#{lessonID},#{type},#{comment},#{name},#{hashid},#{url}, #{parsedContent})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int add(Asset asset);

	@Update("<script>" +
			"update asset set " +
			"<if test='id!=null'> id = #{id}</if>" +
			"<if test='lessonID!=null'>, lesson_id = #{lessonID}</if>" +
			"<if test='type!=null'>, type = #{type}</if>" +
			"<if test='comment!=null'>, comment = #{comment}</if>" +
			"<if test='name!=null'>, name = #{name}</if>" +
			"<if test='hashid!=null'>, hashid = #{hashid}</if>" +
			"<if test='url!=null'>, url = #{url}</if>" +
			"<if test='parsedContent!=null'>, parsed_content = #{parsedContent}</if>" +
			"where id=#{id}" +
			"</script>")
	int update(Asset asset);

	@Delete("delete from asset where id=#{sid}")
    int deleteById(int sid);

	@Select("select * from asset where id=#{sid}")
	@Results(id = "selectAssetMap1",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="type", column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="comment", column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "hashid", column = "hashid", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "url", column = "url", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "parsedContent", column = "parsed_content", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Asset queryById(int sid);


	@Select("select * from asset")
	@Results(id = "selectAssetMap2",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="type", column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="comment", column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "hashid", column = "hashid", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "url", column = "url", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "parsedContent", column = "parsed_content", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Asset> queryAll();

	@Select("select count(*) from asset where ${condition}")
	int queryAndcount(@Param("condition") String condition);
	/*
	 *	custom mapper interface
	 * */
	@Select("select * from asset where lesson_id=#{lessonID} limit #{count} offset #{offset}")
	@Results(id = "selectAssetMap3",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="type", column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="comment", column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "hashid", column = "hashid", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "url", column = "url", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "parsedContent", column = "parsed_content", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Asset> queryByLessonID(@Param("lessonID") int lessonID, @Param("offset") int offset, @Param("count") int count);


	@Select("select * from asset where hashid=#{hashID}")
	@Results(id = "selectAssetMap4",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="type", column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="comment", column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "hashid", column = "hashid", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "url", column = "url", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "parsedContent", column = "parsed_content", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	Asset queryByHashID(@Param("hashID") String hashID);


	@Select("select * from asset where type=#{type} limit #{count} offset #{offset}")
	@Results(id = "selectAssetMap5",value= {
			@Result(property="lessonID", column="lesson_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
			@Result(property="type", column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="comment", column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property="name", column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "hashid", column = "hashid", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "url", column = "url", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(property = "parsedContent", column = "parsed_content", javaType = String.class, jdbcType=JdbcType.VARCHAR),
			@Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType= JdbcType.INTEGER),
			@Result(property = "createTime", column = "create_time", javaType = java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP),
			@Result(property = "enabled", column = "enabled", javaType = Byte.class, jdbcType=JdbcType.TINYINT)
	})
	List<Asset> queryByType(@Param("type") String type, @Param("offset") int offset, @Param("count") int count);
}
