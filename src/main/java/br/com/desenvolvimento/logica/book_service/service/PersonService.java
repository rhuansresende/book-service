package br.com.desenvolvimento.logica.book_service.service;

import br.com.desenvolvimento.logica.book_service.model.Person;
import br.com.desenvolvimento.logica.book_service.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findByDocument(String document){
        return personRepository.findPersonByDocument(document);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}
