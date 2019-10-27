package com.newtouch.cloud.demo.service.userauthority.persistence.mapper;

import com.newtouch.cloud.demo.service.userauthority.persistence.model.SystemRoleAuthority;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

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