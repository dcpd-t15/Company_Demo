package org.example.company_app.service;

import org.example.company_app.enitity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee getEmployee(Long id);
    Employee assignProjectToEmployee(Long employeeId, Long projectId);
    void deleteEmployee(Long id);
}
