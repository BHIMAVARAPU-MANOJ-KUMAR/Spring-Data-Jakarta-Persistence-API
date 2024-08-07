package com.datajpa.springdatajpa.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Student")
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "student_sequence")
	@Column(name = "id", updatable = false, columnDefinition = "INTEGER")
	private Long id;
	
	@Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
	private String firstName;
	
	@Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
	private String lastName;
	
	@Column(name = "email", nullable = false, columnDefinition = "TEXT")
	private String email;
	
	@Column(name = "age", nullable = false, columnDefinition = "INTEGER")
	private Integer age;

	@OneToOne(mappedBy = "student", orphanRemoval = true,
			cascade = {CascadeType.PERSIST,
					CascadeType.REMOVE}
	)
	private StudentIdCard studentIdCard;

	@OneToMany(mappedBy = "student", orphanRemoval = true, cascade = {CascadeType.PERSIST,
			CascadeType.REMOVE}
	)
	/**
	 * For One-to-Many, by default the fetch type is LAZY.
	 * Change the fetch type to LAZY. If you want.
	 * */
	private List<Book> books = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
			mappedBy = "student"
	)
	private final List<Enrollment> enrollments = new ArrayList<>();
	
	public Student() {}

	public Student(String firstName, String lastName, String email, Integer age, StudentIdCard studentIdCard, List<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.studentIdCard = studentIdCard;
		this.books = books;
	}

	public Student(String firstName, String lastName, String email, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public StudentIdCard getStudentIdCard() {
		return studentIdCard;
	}
	public void setStudentIdCard(StudentIdCard studentIdCard) {
		this.studentIdCard = studentIdCard;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		if (!this.books.contains(book)) {
			this.books.add(book);
			book.setStudent(this);
		}
	}

	public void removeBook(Book book) {
		if (this.books.contains(book)){
			this.books.remove(book);
			book.setStudent(null);
		}
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void addEnrollment(Enrollment enrollment) {
		if (!enrollments.contains(enrollment)){
			enrollments.add(enrollment);
		}
	}

	public void removeEnrollment(Enrollment enrollment){
        enrollments.remove(enrollment);
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				", studentIdCard=" + studentIdCard +
				", books=" + books +
				", enrollments=" + enrollments +
				'}';
	}
}
