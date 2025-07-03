package tool.management.backend.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import tool.management.backend.dto.EmployeeAddRequest;
import tool.management.backend.dto.EmployeeDeleteResponse;
import tool.management.backend.dto.EmployeeEditRequest;
import tool.management.backend.models.Employee;
import tool.management.backend.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity<PagedModel<Employee>> employeeGetByAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam Long idCompany,
            @RequestParam String nome,
            @RequestParam String cognome
            ) {
        try {
            return ResponseEntity.ok().body(employeeService.employeeGetByAll(page, size, idCompany, nome, cognome));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get/size")
    public ResponseEntity<Integer> employeeSize(@RequestParam Long idCompany) {
        try {
            return ResponseEntity.ok().body(employeeService.employeeSize(idCompany));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Employee>> employeeGetById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(employeeService.employeeGetById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

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

    @DeleteMapping("/delete")
    public ResponseEntity<EmployeeDeleteResponse> employeeDelete(@RequestParam Long id) {
        if (employeeService.employeeDelete(id)) {
            return ResponseEntity.ok().body(new EmployeeDeleteResponse(true));
        }
        return ResponseEntity.badRequest().body(new EmployeeDeleteResponse(false));
    }

}
