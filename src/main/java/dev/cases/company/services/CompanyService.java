package dev.cases.company.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cases.company.models.Company;
import dev.cases.company.models.Contact;
import dev.cases.company.repositories.CompanyRepository;
import dev.cases.company.repositories.ContactRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ContactRepository contactRepository;

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findByVatNumber(String vatNumber) {
		return companyRepository.findByVatNumber(vatNumber);
	}

	public Company addContactToCompany(Long companyId, Long contactId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found"));
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new RuntimeException("Contact not found"));

		company.getContacts().add(contact);
		return companyRepository.save(company);
	}

	public Company addCompany(Company company) throws Exception {
		if (companyRepository.existsByVatNumber(company.getVatNumber())) {
			throw new Exception("Company already exists with this VAT number");
		}
		return companyRepository.save(company);
	}

	public boolean existsByVatNumber(String vatNumber) {
		return companyRepository.existsByVatNumber(vatNumber);
	}

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	public void delete(Company company) {
		companyRepository.delete(company);
	}
}