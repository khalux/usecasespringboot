package dev.cases.company.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import dev.cases.company.common.Vars;

@Entity
@Table(name = "companies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
    private String name;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
    private String address;

	@NotNull(message = Vars.ERROR_MSG_FOR_MANDATORY_FIELD)
	@NotEmpty(message = Vars.ERROR_MSG_FOR_EMPTY_FIELD)
    @Column(unique = true)
    private String vatNumber;

    @ManyToMany
    @JoinTable(
        name = "company_contact",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contacts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
