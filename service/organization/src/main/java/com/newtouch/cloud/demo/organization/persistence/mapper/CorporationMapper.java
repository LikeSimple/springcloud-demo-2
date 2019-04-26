package com.newtouch.cloud.demo.organization.persistence.mapper;

import com.newtouch.cloud.demo.organization.persistence.model.Corporation;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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