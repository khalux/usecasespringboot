package dev.cases.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.cases.company.models.Company;
import dev.cases.company.services.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// Add Company, return bade request if company already exist with same VAT
	@PostMapping("/add")
	public Company createCompany(@RequestBody Company company) {
		if (companyService.existsByVatNumber(company.getVatNumber())) {
			throw new RuntimeException("Company already exist with vat: " + company.getVatNumber());
		}
		return companyService.save(company);
	}

	// Update company, return not found if not exist
	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
		Company company = companyService.findById(id)
				.orElseThrow(() -> new RuntimeException("Company not found with id " + id));
		company.setName(companyDetails.getName());
		company.setAddress(companyDetails.getAddress());
		company.setVatNumber(companyDetails.getVatNumber());
		return companyService.save(company);
	}

	// Delete company, return not found if not exist
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
		return companyService.findById(id).map(company -> {
			companyService.delete(company);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// get all companies
	@GetMapping("/get-all")
	public List<Company> getAllCompanies() {
		return companyService.findAll();
	}

	// find company by vat, return not found if not exists.
	@GetMapping("/get-by-vat")
	public ResponseEntity<Company> getCompanyByVatNumber(@RequestParam String vatNumber) {
		Optional<Company> company = companyService.findByVatNumber(vatNumber);
		return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// add contact to company, return not found if company or contact not exist
	@PostMapping("/{companyId}/add/contacts/{contactId}")
	public ResponseEntity<Company> addContactToCompany(@PathVariable Long companyId, @PathVariable Long contactId) {
		Company updatedCompany = companyService.addContactToCompany(companyId, contactId);
		return ResponseEntity.ok(updatedCompany);
	}
}
