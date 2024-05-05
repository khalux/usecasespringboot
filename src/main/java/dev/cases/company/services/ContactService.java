package dev.cases.company.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cases.company.models.Contact;
import dev.cases.company.repositories.ContactRepository;

/**
 * @Created 05/05/2024
 * @project CaseJavaCompanyApplication
 * @Author K.ABIDA
 */
@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;

	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	public Optional<Contact> findById(Long id) {
		return contactRepository.findById(id);
	}

	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

	public Contact updateContact(Long id, Contact contact) throws Exception {
		if (!contactRepository.existsById(id)) {
			throw new Exception("Company already exists with this VAT number");
		}
		contact.setId(id);
		return contactRepository.save(contact);
	}
}