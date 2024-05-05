package dev.cases.company.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cases.company.models.Contact;
import dev.cases.company.services.ContactService;
import jakarta.validation.Valid;
/**
 * @Created 05/05/2024
 * @project CaseJavaCompanyApplication
 * @Author K.ABIDA
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping("/add")
	public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.save(contact));
	}

	@GetMapping("/get-all")
	public List<Contact> getAllContacts() {
		return contactService.findAll();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Long id, @Valid @RequestBody Contact contactDetails) {
		return contactService.findById(id).map(contact -> {
			contact.setFirstName(contactDetails.getFirstName());
			contact.setLastName(contactDetails.getLastName());
			contact.setAddress(contactDetails.getAddress());
			contact.setContractType(contactDetails.getContractType());
			contact.setVatNumber(contactDetails.getVatNumber());
			contactService.save(contact);
			return ResponseEntity.ok(contact);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable Long id) {
		return contactService.findById(id).map(contact -> {
			contactService.delete(contact);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
