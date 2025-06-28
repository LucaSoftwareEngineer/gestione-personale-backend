package tool.management.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tool.management.backend.models.Company;
import tool.management.backend.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getAllCompany() {
        return this.companyRepository.findAll();
    }

}
