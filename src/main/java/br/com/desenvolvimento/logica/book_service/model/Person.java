package br.com.desenvolvimento.logica.book_service.model;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Audited
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullname;

    @Column(name = "birth_date", columnDefinition = "timestamp")
    private LocalDate birthDate;

    @Column(unique = true, nullable = false, length = 30)
    private String document;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(fullname, person.fullname) && Objects.equals(birthDate, person.birthDate) && Objects.equals(document, person.document) && Objects.equals(email, person.email) && Objects.equals(phone, person.phone) && Objects.equals(createdAt, person.createdAt) && Objects.equals(updatedAt, person.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, birthDate, document, email, phone, createdAt, updatedAt);
    }
}
