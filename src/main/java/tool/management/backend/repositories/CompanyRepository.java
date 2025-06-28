package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tool.management.backend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
