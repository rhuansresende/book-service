package br.com.desenvolvimento.logica.book_service.repository;

import br.com.desenvolvimento.logica.book_service.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByDocument(String document);
}
