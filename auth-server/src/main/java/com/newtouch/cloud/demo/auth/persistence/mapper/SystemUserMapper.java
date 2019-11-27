package com.newtouch.cloud.demo.auth.persistence.mapper;


import com.newtouch.cloud.demo.auth.persistence.model.SystemUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SystemUserMapper {
    @Delete({
        "delete from NEWTOUCH.SYSTEM_USER",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into NEWTOUCH.SYSTEM_USER (ID, USERNAME, ",
        "PASSWORD, ENABLED, ",
        "LOCKED, ACCOUNT_EXPIRE, ",
        "CREDENTIAL_EXPIRE, CREATED_TIME, ",
        "MODIFIED_TIME)",
        "values (#{id,jdbcType=CHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=CHAR}, #{enabled,jdbcType=BOOLEAN}, ",
        "#{locked,jdbcType=BOOLEAN}, #{accountExpire,jdbcType=TIMESTAMP}, ",
        "#{credentialExpire,jdbcType=TIMESTAMP}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemUser record);

    @Select({
        "select",
        "ID, USERNAME, PASSWORD, ENABLED, LOCKED, ACCOUNT_EXPIRE, CREDENTIAL_EXPIRE, ",
        "CREATED_TIME, MODIFIED_TIME",
        "from NEWTOUCH.SYSTEM_USER",
        "where ID = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BOOLEAN),
        @Result(column="LOCKED", property="locked", jdbcType=JdbcType.BOOLEAN),
        @Result(column="ACCOUNT_EXPIRE", property="accountExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREDENTIAL_EXPIRE", property="credentialExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFIED_TIME", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemUser selectByPrimaryKey(String id);

    @Select({
        "select",
        "ID, USERNAME, PASSWORD, ENABLED, LOCKED, ACCOUNT_EXPIRE, CREDENTIAL_EXPIRE, ",
        "CREATED_TIME, MODIFIED_TIME",
        "from NEWTOUCH.SYSTEM_USER",
        "order by username"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BOOLEAN),
        @Result(column="LOCKED", property="locked", jdbcType=JdbcType.BOOLEAN),
        @Result(column="ACCOUNT_EXPIRE", property="accountExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREDENTIAL_EXPIRE", property="credentialExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFIED_TIME", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SystemUser> selectAll();

    @Update({
        "update NEWTOUCH.SYSTEM_USER",
        "set USERNAME = #{username,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=CHAR},",
          "ENABLED = #{enabled,jdbcType=BOOLEAN},",
          "LOCKED = #{locked,jdbcType=BOOLEAN},",
          "ACCOUNT_EXPIRE = #{accountExpire,jdbcType=TIMESTAMP},",
          "CREDENTIAL_EXPIRE = #{credentialExpire,jdbcType=TIMESTAMP},",
          "CREATED_TIME = #{createdTime,jdbcType=TIMESTAMP},",
          "MODIFIED_TIME = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemUser record);
}