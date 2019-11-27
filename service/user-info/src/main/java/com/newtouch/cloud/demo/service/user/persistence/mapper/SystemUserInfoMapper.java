package com.newtouch.cloud.demo.service.user.persistence.mapper;

import com.newtouch.cloud.demo.service.user.persistence.model.SystemUserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SystemUserInfoMapper {
    @Delete({
        "delete from NEWTOUCH.SYSTEM_USER_INFO",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into NEWTOUCH.SYSTEM_USER_INFO (ID, NICKNAME, ",
        "EMAIL, USERNAME)",
        "values (#{id,jdbcType=CHAR}, #{nickname,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR})"
    })
    int insert(SystemUserInfo record);

    @Select({
        "select",
        "ID, NICKNAME, EMAIL, USERNAME",
        "from NEWTOUCH.SYSTEM_USER_INFO",
        "where ID = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="NICKNAME", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR)
    })
    SystemUserInfo selectByPrimaryKey(String id);

    @Select({
        "select",
        "ID, NICKNAME, EMAIL, USERNAME",
        "from NEWTOUCH.SYSTEM_USER_INFO"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="NICKNAME", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="USERNAME", property="username", jdbcType=JdbcType.VARCHAR)
    })
    List<SystemUserInfo> selectAll();

    @Update({
        "update NEWTOUCH.SYSTEM_USER_INFO",
        "set NICKNAME = #{nickname,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "USERNAME = #{username,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemUserInfo record);
}