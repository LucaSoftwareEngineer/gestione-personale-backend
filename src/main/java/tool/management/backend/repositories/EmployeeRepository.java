package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tool.management.backend.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
