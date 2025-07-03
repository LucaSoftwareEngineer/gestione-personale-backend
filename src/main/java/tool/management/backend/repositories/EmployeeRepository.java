package tool.management.backend.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tool.management.backend.models.Company;
import tool.management.backend.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByCompanyAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseOrderByIdEmployee(Pageable pageable, Company company, String nome, String cognome);

    @Query(" SELECT COUNT(*) FROM Employee WHERE company = :company ")
    Integer getSize(Company company);

}
