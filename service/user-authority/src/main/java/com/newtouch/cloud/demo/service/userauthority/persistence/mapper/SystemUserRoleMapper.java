package com.newtouch.cloud.demo.service.userauthority.persistence.mapper;

import com.newtouch.cloud.demo.service.userauthority.persistence.model.SystemUserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SystemUserRoleMapper {
    @Delete({
        "delete from NEWTOUCH.SYSTEM_USER_ROLE",
        "where SYSTEM_USER_ID = #{systemUserId,jdbcType=CHAR}",
          "and ROLE_ID = #{roleId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("systemUserId") String systemUserId, @Param("roleId") String roleId);

    @Insert({
        "insert into NEWTOUCH.SYSTEM_USER_ROLE (SYSTEM_USER_ID, ROLE_ID, ",
        "CREATED_TIME)",
        "values (#{systemUserId,jdbcType=CHAR}, #{roleId,jdbcType=CHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemUserRole record);

    @Select({
        "select",
        "SYSTEM_USER_ID, ROLE_ID, CREATED_TIME",
        "from NEWTOUCH.SYSTEM_USER_ROLE",
        "where SYSTEM_USER_ID = #{systemUserId,jdbcType=CHAR}",
          "and ROLE_ID = #{roleId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="SYSTEM_USER_ID", property="systemUserId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemUserRole selectByPrimaryKey(@Param("systemUserId") String systemUserId, @Param("roleId") String roleId);

    @Select({
        "select",
        "SYSTEM_USER_ID, ROLE_ID, CREATED_TIME",
        "from NEWTOUCH.SYSTEM_USER_ROLE"
    })
    @Results({
        @Result(column="SYSTEM_USER_ID", property="systemUserId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SystemUserRole> selectAll();

    @Update({
        "update NEWTOUCH.SYSTEM_USER_ROLE",
        "set CREATED_TIME = #{createdTime,jdbcType=TIMESTAMP}",
        "where SYSTEM_USER_ID = #{systemUserId,jdbcType=CHAR}",
          "and ROLE_ID = #{roleId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemUserRole record);
}