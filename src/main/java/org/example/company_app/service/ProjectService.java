package org.example.company_app.service;

import org.example.company_app.enitity.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    List<Project> getProjects();
    Project getProject(Long id);
    void deleteProject(Long id);
    Project assignEmployeeToProject(Long projectId, Long employeeId);
}
