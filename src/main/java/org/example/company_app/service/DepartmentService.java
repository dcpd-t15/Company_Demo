package org.example.company_app.service;

import org.example.company_app.enitity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getDepartments();
    Department getDepartment(Long id);
    void deleteDepartment(Long id);
    Department assignProjectToDepartment(Long departmentId, Long projectId);
}
