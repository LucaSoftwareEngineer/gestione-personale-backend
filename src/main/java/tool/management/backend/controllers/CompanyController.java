package tool.management.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import tool.management.backend.models.Company;
import tool.management.backend.services.CompanyService;
import tool.management.backend.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @GetMapping("api/company/all")
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }

    @GetMapping("api/company/of/user")
    public ResponseEntity<Company> getCompanyOfUser(@RequestParam String token) {
        try {
            return ResponseEntity.ok().body(userService.getCompanyOfUser(token));
        } catch (Exception e) {
            return ResponseEntity.ok().body(null);
        }
    }

}
