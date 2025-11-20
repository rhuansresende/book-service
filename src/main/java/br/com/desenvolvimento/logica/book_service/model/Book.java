package br.com.desenvolvimento.logica.book_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false, length = 200)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Situacao status;

    @Column(name = "createAt", columnDefinition = "timestamp")
    private LocalDateTime createAt;

    @PrePersist
    public void prePersist() {
        this.status = Situacao.ATIVO;
        this.createAt = LocalDateTime.now();
    }

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

    public Situacao getStatus() {
        return status;
    }

    public void setStatus(Situacao status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
