package com.newtouch.cloud.demo.persistence.mapper;

import com.newtouch.cloud.demo.persistence.model.Department;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}