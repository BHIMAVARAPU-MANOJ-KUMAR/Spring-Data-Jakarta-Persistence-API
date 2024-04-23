package com.datajpa.springdatajpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.datajpa.springdatajpa.model.Student;

@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findStudentByEmail(String email);
	List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
	List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, Integer age);
	List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, Integer age);

	/**
	 * Query Syntax - Native Query
	 * 	nativeQuery by default is False
	 */
	@Query(value = "SELECT * FROM student WHERE email =?1", nativeQuery = true)
	Optional<Student> findStudentByEmail1(String email);

	/**
	 * Query Syntax - Native Query
	 * 	For the query below, the firstName method argument is passed as the query parameter with respect to index 1
	 * 	age method argument is passed as the query parameter with respect to index 2
	 */
	@Query(value = "SELECT * FROM student WHERE first_name = :first_name AND age >= :age", nativeQuery= true)
	Optional<Student> findStudentsByFirstNameEqualsAndAgeIsLessThanEqual(@Param("first_name") String firstName, @Param("age") Integer age);

	/**
	 * Query Syntax is JPQL - Java Persistence Query Language
	 * 	Indexed Arguments Query
	 */
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	public Optional<Student> findStudentByEmail2(String email);

	/**
	 * Query Syntax is JPQL - Java Persistence Query Language
	 * 	Parameterized Arguments Query
	 * 	With JPQL queries that have argument parameters,
	 * 	Spring Data passes the method arguments to the query in the same order as the method declaration.
	 * 	Named Parameters
	 */
	@Query("SELECT s FROM Student s WHERE s.lastName = :last_name")
	public Optional<Student> findStudentByLastName(@Param("last_name") String lastName);
	
	/**
	 * Indicates a query method should be considered as modifying query as that changes the way it needs to be executed.
	 * This annotation is only considered if used on query methods defined through a @Query annotation. It's not
	 * applied on custom implementation methods or queries derived from the method name as they already have control over
	 * the underlying data access APIs or specify if they are modifying by their name. 
	 * Queries that require a @Modifying annotation include INSERT, UPDATE, DELETE, and DDL
	 * statements. */
	
	@Transactional
	@Modifying
	@Query("DELETE Student u WHERE u.id = ?1")
	public int deleteStudentById(Long id);
}
