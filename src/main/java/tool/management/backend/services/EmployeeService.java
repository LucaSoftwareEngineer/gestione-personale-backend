package tool.management.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tool.management.backend.dto.EmployeeAddRequest;
import tool.management.backend.models.Company;
import tool.management.backend.models.Employee;
import tool.management.backend.repositories.CompanyRepository;
import tool.management.backend.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    public Employee employeeAdd(EmployeeAddRequest json) {
        Company company = companyRepository.findById(json.getIdCompany()).get();
        Employee newEmployee = new Employee(
                null,
                json.getNome(),
                json.getCognome(),
                json.getDataNascita(),
                json.getDataAssunzione(),
                json.getRal(),
                json.getDataLicenziamento(),
                company);
        return employeeRepository.save(newEmployee);
    }

}
