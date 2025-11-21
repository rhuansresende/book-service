package br.com.desenvolvimento.logica.book_service.service;

import br.com.desenvolvimento.logica.book_service.model.Book;
import br.com.desenvolvimento.logica.book_service.model.Situacao;
import br.com.desenvolvimento.logica.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        book.setStatus(Situacao.INATIVO);
        update(book);
    }
}
