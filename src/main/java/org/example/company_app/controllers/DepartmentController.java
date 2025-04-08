package org.example.company_app.controllers;

import lombok.RequiredArgsConstructor;
import org.example.company_app.enitity.Department;
import org.example.company_app.repository.DepartmentRepository;
import org.example.company_app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{departmentId}/projects/{projectId}")
    public ResponseEntity<Department> assignProjectToDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long projectId) {
        return ResponseEntity.ok(departmentService.assignProjectToDepartment(departmentId, projectId));
    }
}
