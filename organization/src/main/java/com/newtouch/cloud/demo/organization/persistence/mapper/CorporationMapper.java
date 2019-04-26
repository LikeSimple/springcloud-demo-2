package com.newtouch.cloud.demo.organization.persistence.mapper;

import com.newtouch.cloud.demo.organization.persistence.model.Corporation;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CorporationMapper {
    @Delete({
        "delete from corporation",
        "where corp_id = #{corpId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer corpId);

    @Insert({
        "insert into corporation (corp_id, corp_name)",
        "values (#{corpId,jdbcType=INTEGER}, #{corpName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="corpId", before=true, resultType=Integer.class)
    int insert(Corporation record);

    @Select({
        "select",
        "corp_id, corp_name",
        "from corporation",
        "where corp_id = #{corpId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="corp_name", property="corpName", jdbcType=JdbcType.VARCHAR)
    })
    Corporation selectByPrimaryKey(Integer corpId);

    @Select({
        "select",
        "corp_id, corp_name",
        "from corporation",
        "order by corp_name"
    })
    @Results({
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="corp_name", property="corpName", jdbcType=JdbcType.VARCHAR)
    })
    List<Corporation> selectAll();

    @Update({
        "update corporation",
        "set corp_name = #{corpName,jdbcType=VARCHAR}",
        "where corp_id = #{corpId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Corporation record);
}