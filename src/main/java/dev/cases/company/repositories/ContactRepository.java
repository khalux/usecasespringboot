package dev.cases.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cases.company.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}