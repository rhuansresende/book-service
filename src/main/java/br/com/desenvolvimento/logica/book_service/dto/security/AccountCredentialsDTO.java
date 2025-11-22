package br.com.desenvolvimento.logica.book_service.dto.security;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AccountCredentialsDTO implements Serializable {

    private String fullname;
    private String birthDate;
    private String document;
    private String email;
    private String phone;
    private String password;
    private List<String> roles;

    public AccountCredentialsDTO() {
    }

    public AccountCredentialsDTO(String fullname, String birthDate, String document,
                                 String email, String phone, String password, List<String> roles) {
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.document = document;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountCredentialsDTO that = (AccountCredentialsDTO) o;
        return Objects.equals(fullname, that.fullname) && Objects.equals(birthDate, that.birthDate) && Objects.equals(document, that.document) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, birthDate, document, email, phone, password, roles);
    }
}
