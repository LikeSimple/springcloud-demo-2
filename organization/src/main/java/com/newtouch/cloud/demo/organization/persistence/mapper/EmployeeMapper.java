package com.newtouch.cloud.demo.organization.persistence.mapper;

import com.newtouch.cloud.demo.organization.persistence.model.Employee;
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
public interface EmployeeMapper {
    @Delete({
        "delete from employee",
        "where employee_id = #{employeeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer employeeId);

    @Insert({
        "insert into employee (employee_id, employee_name, ",
        "dept_id, corp_id)",
        "values (#{employeeId,jdbcType=INTEGER}, #{employeeName,jdbcType=VARCHAR}, ",
        "#{deptId,jdbcType=INTEGER}, #{corpId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="employeeId", before=true, resultType=Integer.class)
    int insert(Employee record);

    @Select({
        "select",
        "employee_id, employee_name, dept_id, corp_id",
        "from employee",
        "where employee_id = #{employeeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="employee_id", property="employeeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_name", property="employeeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER)
    })
    Employee selectByPrimaryKey(Integer employeeId);

    @Select({
        "select",
        "employee_id, employee_name, dept_id, corp_id",
        "from employee",
        "order by employee_name"
    })
    @Results({
        @Result(column="employee_id", property="employeeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_name", property="employeeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="corp_id", property="corpId", jdbcType=JdbcType.INTEGER)
    })
    List<Employee> selectAll();

    @Update({
        "update employee",
        "set employee_name = #{employeeName,jdbcType=VARCHAR},",
          "dept_id = #{deptId,jdbcType=INTEGER},",
          "corp_id = #{corpId,jdbcType=INTEGER}",
        "where employee_id = #{employeeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Employee record);
}