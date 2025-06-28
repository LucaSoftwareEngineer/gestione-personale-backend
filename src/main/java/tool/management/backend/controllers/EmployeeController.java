package tool.management.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import tool.management.backend.dto.EmployeeAddRequest;
import tool.management.backend.dto.EmployeeEditRequest;
import tool.management.backend.models.Employee;
import tool.management.backend.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> employeeAdd(@RequestBody EmployeeAddRequest json) {
        try {
            Employee employee = employeeService.employeeAdd(json);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Employee> employeeEdit(@PathVariable Long id, @RequestBody EmployeeEditRequest entity) {
        try {
            Employee employee = employeeService.employeeEdit(entity, id);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
