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
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Project saveProject(Project project) {
        Department department = departmentRepository.findById(project.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        project.setDepartment(department);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project assignEmployeeToProject(Long projectId, Long employeeId) {
        Project project = getProject(projectId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        project.getEmployees().add(employee);
        employee.setProject(project);
        return projectRepository.save(project);
    }
}