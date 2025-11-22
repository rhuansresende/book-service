package br.com.desenvolvimento.logica.book_service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PersonDTO implements Serializable {

    private String fullname;
    private String document;
    private String email;
    private Boolean active;
    private List<String> roles;

    public PersonDTO() {}

    public PersonDTO(String fullname, String document, String email, Boolean active, List<String> roles) {
        this.fullname = fullname;
        this.document = document;
        this.email = email;
        this.active = active;
        this.roles = roles;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(fullname, personDTO.fullname) && Objects.equals(document, personDTO.document) && Objects.equals(email, personDTO.email) && Objects.equals(active, personDTO.active) && Objects.equals(roles, personDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, document, email, active, roles);
    }
}
