package dev.cases.company.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cases.company.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByVatNumber(String vatNumber);
    boolean existsByVatNumber(String vatNumber);
}