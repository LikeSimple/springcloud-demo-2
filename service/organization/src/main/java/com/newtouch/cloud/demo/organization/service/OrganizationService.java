package com.newtouch.cloud.demo.organization.service;

import com.newtouch.cloud.demo.organization.persistence.model.Corporation;
import com.newtouch.cloud.demo.organization.persistence.model.Department;
import com.newtouch.cloud.demo.organization.persistence.model.Employee;
import com.newtouch.cloud.demo.organization.service.criteria.CorporationCriteria;
import com.newtouch.cloud.demo.organization.service.criteria.DepartmentCriteria;
import com.newtouch.cloud.demo.organization.service.criteria.EmployeeCriteria;

import java.util.List;

public interface OrganizationService {

    List<Corporation> getCorporationByCriteria(CorporationCriteria corporationCriteria);

    Corporation createCorporation(Corporation corporation);

    Corporation updateCorporation(Corporation corporation);

    boolean deleteCorporation(String corporationId);

    List<Department> getDepartmentByCriteria(DepartmentCriteria departMentCriteria);

    Department createDepartment(Department department);

    Department updateDepartment(Department department);

    boolean deleteDepartment(String departmentId);

    List<Employee> getEmployeeByCriteria(EmployeeCriteria employeeCriteria);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(String employeeId);

}
