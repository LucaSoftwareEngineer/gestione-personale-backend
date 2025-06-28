package tool.management.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import tool.management.backend.models.Company;
import tool.management.backend.services.CompanyService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("api/company/all")
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }

}
