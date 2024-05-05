package dev.cases.company.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import dev.cases.company.common.Vars;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @Created 05/05/2024
 * @project CaseJavaCompanyApplication
 * @Author K.ABIDA
 */
@Entity
@Table(name = "contacts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
	private String firstName;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
	private String lastName;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
	private String address;

	@Column(nullable = true)
	private String vatNumber; // Seulement pour les freelancers

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Contract type must be either 'EMPLOYEE' or 'FREELANCE'")
	private ContractType contractType;

	public enum ContractType {
		EMPLOYEE, FREELANCE
	}

	@ManyToMany(mappedBy = "contacts")
	private Set<Company> companies = new HashSet<>();

	@PrePersist
	@PreUpdate
	private void validate() {
		if (contractType == ContractType.FREELANCE && (vatNumber == null || vatNumber.trim().isEmpty())) {
			throw new RuntimeException("VAT number is required for freelance contacts.", null);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}
}
