package com.newtouch.cloud.demo.service.userauthority.persistence.mapper;

import com.newtouch.cloud.demo.service.userauthority.persistence.model.SystemRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SystemRoleMapper {
    @Delete({
        "delete from NEWTOUCH.SYSTEM_ROLE",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into NEWTOUCH.SYSTEM_ROLE (ID, NAME, DESCRIPTION, ",
        "CREATED_TIME, MODIFIED_TIME)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=CHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemRole record);

    @Select({
        "select",
        "ID, NAME, DESCRIPTION, CREATED_TIME, MODIFIED_TIME",
        "from NEWTOUCH.SYSTEM_ROLE",
        "where ID = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFIED_TIME", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemRole selectByPrimaryKey(String id);

    @Select({
        "select",
        "ID, NAME, DESCRIPTION, CREATED_TIME, MODIFIED_TIME",
        "from NEWTOUCH.SYSTEM_ROLE",
        "order by name"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFIED_TIME", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SystemRole> selectAll();

    @Update({
        "update NEWTOUCH.SYSTEM_ROLE",
        "set NAME = #{name,jdbcType=CHAR},",
          "DESCRIPTION = #{description,jdbcType=VARCHAR},",
          "CREATED_TIME = #{createdTime,jdbcType=TIMESTAMP},",
          "MODIFIED_TIME = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemRole record);
}