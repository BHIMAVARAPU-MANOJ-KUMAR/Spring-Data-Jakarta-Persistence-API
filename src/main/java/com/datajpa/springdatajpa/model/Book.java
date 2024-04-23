package com.datajpa.springdatajpa.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "book_sequence")
    @Column(name = "id", updatable = false, columnDefinition = "INTEGER")
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @Column(name = "book_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(250)")
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_book_fk"))
    private Student student;

    public Book() {}

    public Book(LocalDateTime createdAt, String bookName){
        this.createdAt = createdAt;
        this.bookName = bookName;
    }

    public Book(LocalDateTime createdAt, String bookName, Student student) {
        this.createdAt = createdAt;
        this.bookName = bookName;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                ", student=" + student +
                '}';
    }
}
