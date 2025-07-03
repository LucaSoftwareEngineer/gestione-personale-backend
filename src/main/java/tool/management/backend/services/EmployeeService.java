package tool.management.backend.services;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import tool.management.backend.dto.EmployeeAddRequest;
import tool.management.backend.dto.EmployeeEditRequest;
import tool.management.backend.models.Company;
import tool.management.backend.models.Employee;
import tool.management.backend.repositories.CompanyRepository;
import tool.management.backend.repositories.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public PagedModel<Employee> employeeGetByAll(int page, int size, Long idCompany, String nome, String cognome) {
        Pageable pageable = PageRequest.of(page, size);
        Company company = companyRepository.findById(idCompany).get();
        return new PagedModel<>(employeeRepository.findAllByCompanyAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseOrderByIdEmployee(pageable, company, nome, cognome));
    }

    public List<Employee> employeeGetById(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

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

    public Employee employeeEdit(EmployeeEditRequest json, Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setNome(json.getNome());
        employee.setCognome(json.getCognome());
        employee.setDataNascita(json.getDataNascita());
        employee.setDataAssunzione(json.getDataAssunzione());
        employee.setDataLicenziamento(json.getDataLicenziamento());
        employee.setRal(json.getRal());
        return employeeRepository.save(employee);
    }

    public Boolean employeeDelete(Long id) {
        try {
            Employee employee = employeeRepository.findById(id).get();
            employeeRepository.delete(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Integer employeeSize(Long idCompany) {
        Company company = companyRepository.findById(idCompany).get();
        return employeeRepository.getSize(company);
    }

}
