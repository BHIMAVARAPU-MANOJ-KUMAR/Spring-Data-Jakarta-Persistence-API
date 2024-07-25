package com.datajpa.springdatajpa.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card", uniqueConstraints = {@UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")})
public class StudentIdCard {
	
	@Id
	@SequenceGenerator(name = "student_id_card_sequence", sequenceName = "student_id_card_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "student_id_card_sequence")
	@Column(name = "id", updatable = false, columnDefinition = "INTEGER")
	private Long id;
	
	@Column(name = "card_number", nullable = false, length = 15, columnDefinition = "VARCHAR(15)")
	private String cardNumber;

	/**
	 * By default, for One-to-One Relationship the fetch type is EAGER. fetch = FetchType.EAGER
	 * It's not a problem if you don't include this property.
	 * */
	@OneToOne(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
	)
	@JoinColumn(name = "sudent_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "student_id_card_student_id_fk")
	)
	private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentIdCard() {}
	
	public StudentIdCard(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public StudentIdCard(String cardNumber, Student student){
		this.cardNumber = cardNumber;
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentIdCard{" +
				"id=" + id +
				", cardNumber='" + cardNumber + '\'' +
				", student=" + student +
				'}';
	}
}
