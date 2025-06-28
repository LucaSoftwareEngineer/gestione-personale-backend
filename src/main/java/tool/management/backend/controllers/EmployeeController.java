package tool.management.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import tool.management.backend.dto.EmployeeAddRequest;
import tool.management.backend.models.Employee;
import tool.management.backend.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
