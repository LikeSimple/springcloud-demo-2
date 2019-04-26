package com.newtouch.cloud.demo.organization.persistence.mapper;

import com.newtouch.cloud.demo.organization.persistence.model.Department;
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
public interface DepartmentMapper {
    @Delete({
        "delete from department",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer deptId);

    @Insert({
        "insert into department (dept_id, dept_name, ",
        "prev_dept_id, corp_id)",
        "values (#{deptId,jdbcType=INTEGER}, #{deptName,jdbcType=VARCHAR}, ",
        "#{prevDeptId,jdbcType=INTEGER}, #{corpId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="deptId", before=true, resultType=Integer.class)
    int insert(Department record);

    @Select({
        "select",
        "dept_id, dept_name, prev_dept_id, corp_id",
        "from department",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR),
        @Result(column="prev_dept_id", property="prevDeptId", jdbcType=JdbcType.INTEGER),
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER)
    })
    Department selectByPrimaryKey(Integer deptId);

    @Select({
        "select",
        "dept_id, dept_name, prev_dept_id, corp_id",
        "from department",
        "order by dept_name"
    })
    @Results({
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR),
        @Result(column="prev_dept_id", property="prevDeptId", jdbcType=JdbcType.INTEGER),
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER)
    })
    List<Department> selectAll();

    @Update({
        "update department",
        "set dept_name = #{deptName,jdbcType=VARCHAR},",
          "prev_dept_id = #{prevDeptId,jdbcType=INTEGER},",
          "corp_id = #{corpId,jdbcType=INTEGER}",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Department record);
}