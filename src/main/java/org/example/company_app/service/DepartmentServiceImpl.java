package org.example.company_app.service;

import lombok.RequiredArgsConstructor;
import org.example.company_app.enitity.Department;
import org.example.company_app.enitity.Project;
import org.example.company_app.repository.DepartmentRepository;
import org.example.company_app.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = getDepartment(id);
        departmentRepository.delete(department);
    }

    @Override
    public Department assignProjectToDepartment(Long departmentId, Long projectId) {
        Department department = getDepartment(departmentId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        department.getProjects().add(project);
        project.setDepartment(department);
        return departmentRepository.save(department);
    }
}
