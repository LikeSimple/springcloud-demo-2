package com.newtouch.cloud.demo.auth.persistence.mapper;


import com.newtouch.cloud.demo.auth.persistence.model.SystemRoleAuthority;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SystemRoleAuthorityMapper {
    @Delete({
        "delete from NEWTOUCH.SYSTEM_ROLE_AUTHORITY",
        "where ROLE_ID = #{roleId,jdbcType=CHAR}",
          "and AUTHORITY_ID = #{authorityId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("authorityId") String authorityId);

    @Insert({
        "insert into NEWTOUCH.SYSTEM_ROLE_AUTHORITY (ROLE_ID, AUTHORITY_ID, ",
        "CREATED_TIME)",
        "values (#{roleId,jdbcType=CHAR}, #{authorityId,jdbcType=CHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemRoleAuthority record);

    @Select({
        "select",
        "ROLE_ID, AUTHORITY_ID, CREATED_TIME",
        "from NEWTOUCH.SYSTEM_ROLE_AUTHORITY",
        "where ROLE_ID = #{roleId,jdbcType=CHAR}",
          "and AUTHORITY_ID = #{authorityId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="AUTHORITY_ID", property="authorityId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemRoleAuthority selectByPrimaryKey(@Param("roleId") String roleId, @Param("authorityId") String authorityId);

    @Select({
        "select",
        "ROLE_ID, AUTHORITY_ID, CREATED_TIME",
        "from NEWTOUCH.SYSTEM_ROLE_AUTHORITY"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="AUTHORITY_ID", property="authorityId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SystemRoleAuthority> selectAll();

    @Update({
        "update NEWTOUCH.SYSTEM_ROLE_AUTHORITY",
        "set CREATED_TIME = #{createdTime,jdbcType=TIMESTAMP}",
        "where ROLE_ID = #{roleId,jdbcType=CHAR}",
          "and AUTHORITY_ID = #{authorityId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemRoleAuthority record);
}