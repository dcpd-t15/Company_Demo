package org.example.company_app.service;

import lombok.RequiredArgsConstructor;
import org.example.company_app.enitity.Department;
import org.example.company_app.enitity.Employee;
import org.example.company_app.enitity.Project;
import org.example.company_app.repository.DepartmentRepository;
import org.example.company_app.repository.EmployeeRepository;
import org.example.company_app.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee assignProjectToEmployee(Long employeeId, Long projectId) {
        Employee employee = getEmployee(employeeId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        employee.setProject(project);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
