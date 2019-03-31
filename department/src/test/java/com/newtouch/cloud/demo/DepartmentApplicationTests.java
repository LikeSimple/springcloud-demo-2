package com.newtouch.cloud.demo;

import com.netflix.discovery.converters.Auto;
import com.newtouch.cloud.demo.persistence.mapper.DepartmentMapper;
import com.newtouch.cloud.demo.persistence.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class DepartmentApplicationTests {

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Test
	public void InsertDepartmentTest() {

		Department department = new Department();
		department.setId(3);
		department.setDeptName("Dept_03");
		assert (1 == departmentMapper.insert(department));
	}

	@Test
	public void DeleteDepartmentTest() {
		assert (1 == departmentMapper.deleteByPrimaryKey(1));
	}

	@Test
	public void SelectDepartmentTest() {
		assert (null != departmentMapper.selectByPrimaryKey(1));
		Assert.notEmpty(departmentMapper.selectAll(), "没有查到部门数据");
	}

	@Test
	public void ModifyDepartmentTest() {
		Department department = new Department();
		department.setId(1);
		department.setDeptName("Department_01");
		assert(1 == departmentMapper.updateByPrimaryKey(department));
	}

	@Test
	public void ControllerTest() {



	}
}
