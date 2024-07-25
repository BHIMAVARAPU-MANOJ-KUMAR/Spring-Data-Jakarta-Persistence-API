package com.datajpa.springdatajpa;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * import org.springframework.data.domain.Page;
 * import org.springframework.data.domain.PageRequest;
 * import org.springframework.data.domain.Sort;
 * import org.springframework.data.domain.Sort.Direction;
 */

import com.datajpa.springdatajpa.model.Book;
import com.datajpa.springdatajpa.model.Course;
import com.datajpa.springdatajpa.model.Department;
import com.datajpa.springdatajpa.model.Enrollment;
import com.datajpa.springdatajpa.model.EnrollmentId;
import com.datajpa.springdatajpa.model.Student;
import com.datajpa.springdatajpa.model.StudentIdCard;
import com.datajpa.springdatajpa.repository.StudentIdCardRepository;
import com.datajpa.springdatajpa.repository.StudentRepository;
import com.datajpa.springdatajpa.repository.StudentRepositoryPageSort;
import com.github.javafaker.Faker;

@SpringBootApplication
public class SpringdatajpaApplication {
	Logger logger = Logger.getLogger(getClass().getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentRepositoryPageSort pageSort, StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@yahoo.com", firstName, lastName);

			Student students = new Student(firstName,lastName,email,faker.number().numberBetween(12, 25));

			students.addBook(new Book(LocalDateTime.now(Clock.systemDefaultZone()), "Clean Code"));
			students.addBook(new Book(LocalDateTime.now(Clock.systemDefaultZone()).minusDays(10), "Think and Grow Rich"));
			students.addBook(new Book(LocalDateTime.now(Clock.systemDefaultZone()).minusMonths(1), "Spring Data JPA"));
			students.addBook(new Book(LocalDateTime.now(Clock.systemDefaultZone()).minusYears(2), "Spring RESTAPI'S with HATEOS"));
			students.addBook(new Book(LocalDateTime.now(Clock.systemDefaultZone()).minusWeeks(3), "HTTP Guide"));

			StudentIdCard studentIdCard = new StudentIdCard("CBENU4EIE17010",students);

			/**
			 * studentIdCardRepository.save(studentIdCard);
			 */

			students.setStudentIdCard(studentIdCard);

			students.addEnrollment(new Enrollment(
					new EnrollmentId(1L, 1L),
					students,
					new Course("Electronics and Computer Engg", Department.COMPUTER_ENGINEERING), LocalDateTime.now()));

			students.addEnrollment(new Enrollment(
					new EnrollmentId(1L,2L),
					students,
					new Course("AmigosCode Spring Data JPA", Department.INFORMATION_TECHNOLOGY), LocalDateTime.now().minusDays(18)));

			students.addEnrollment(new Enrollment(
					new EnrollmentId(1L,3L),
					students,
					new Course("Computer Science", Department.COMPUTER_SCIENCE), LocalDateTime.now().minusWeeks(4)));

			/**
			 * students.enrollToCourse(new Course("Computer Science", Department.COMPUTER_SCIENCE));
			 * students.enrollToCourse(new Course("AmigosCode Spring Data JPA", Department.INFORMATION_TECHNOLOGY));
			 * students.enrollToCourse(new Course("Electronics and Computer Engg", Department.COMPUTER_ENGINEERING));
			 */

			studentRepository.save(students);

			studentRepository.findById(1L)
					.ifPresent(s-> {
						logger.info("Fetch Book By Lazy...");
						List<Book> books = students.getBooks();
						books.forEach(book->
							logger.info(s.getFirstName() + " borrowed " + book.getBookName())
						);
					});
			/**
			 * studentRepository.findById(1L)
			 * 					.ifPresent(s-> {
			 * 						System.out.println("Fetch Book By Eager...");
			 * 						List<Book> bookList = students.getBooks();
			 * 						bookList.forEach(book ->  {
			 * 							System.out.println(s.getFirstName() + " borrowed " + book.getBookName());
			 * 						                        });
			 * 						});
			 */

			/**
			 * 			studentRepository.findById(1L).ifPresentOrElse(stud -> { System.out.println(stud); }, () -> {
			 * 				System.out.println("Student Not Found.");
			 * 			            });
			 */

			/**
			 * studentIdCardRepository.findById(1L)
			 * 							.ifPresentOrElse(studentId -> { System.out.println(studentId); }, () -> {
			 * 								System.out.println("Student Id Not Found.");
			 * 						});
			 */


			/**
			 * studentRepository.deleteById(1L);
			 */


			/**
			 * generateRandomStudents(studentRepository);
			 */

			/**
			 * PageRequest pageRequest = PageRequest.of(
			 * 					0,
			 * 					10,
			 * 					Sort.by("age").ascending());
			 * 			Page<Student> page = pageSort.findAll(pageRequest);
			 * 			System.out.println(page);
			 */

			
			/** 
			 * Pagination and Sorting 
			 * We want to sort by the firstName ASC
			 * We want to sort by the firstName DESC
			 * We want to sort by the firstName ASC and age DESC
			 * */

			/**
			 * Sort firstNameSort = Sort.by(Direction.ASC, "firstName");
			 * Sort firstNameSort1 = Sort.by(Direction.DESC, "firstName");
			 * Sort sort1 = Sort.by("firstName").ascending().and(Sort.by("age").descending());
			 * studentRepository.findAll(firstNameSort).forEach(student -> System.out.println(student.getFirstName()));
			 * studentRepository.findAll(firstNameSort1).forEach(student -> System.out.println(student.getFirstName()));
			 * studentRepository.findAll(sort1).forEach(student-> System.out.println(student.getFirstName() + " " + student.getAge()));
			 */

			/**
			 * Student manoj = new Student("Manoj", "BH", "manojbh1999@gmail.com", 24);
			 * Student yaswanth = new Student("Yaswanth", "Kallam", "yaswanth.kallam@gmail.com", 25);
			 * Student prasanthi = new Student("Manoj", "BH", "pbhimav2002@gmail.com", 46);
			 * Student sravya = new Student("Sravya", "Jayasree", "jayasree.sravya@outlook.com", 24);
			 */

			/**
			 * System.out.println("Adding Manoj, Yaswanth, Prasanthi & Sravya.");
			 */

			/**
			 * studentRepository.saveAll(List.of(manoj,yaswanth,prasanthi,sravya));
			 */

			/**
			 * System.out.println("Number of Students :- ");
			 * System.out.println(studentRepository.count());
			 */

			/**
			 * studentRepository.findById(2L).ifPresentOrElse(student -> {
			 * 				System.out.println(student);
			 * 			            }, () -> { System.out.println("Student with the ID 2 Not Found.");
			 * });
			 */

			/**
			 * studentRepository.findById(3L).ifPresentOrElse(student -> {
			 * 				System.out.println(student);
			 * 			            }, () -> { System.out.println("Student with the ID 3 Not Found.");
			 * });
			 */

			/**
			 * System.out.println("SELECT ALL STUDENTS");
			 * List<Student> allStudents = studentRepository.findAll();
			 * allStudents.forEach(System.out::println);
			 */

			/**
			 * System.out.println("DELETE yaswanth");
			 * studentRepository.deleteById(1L);
			 */

			/**
			 * System.out.println("Number of Students :- ");
			 * System.out.println(studentRepository.count());
			 */

			
			/**
			 * studentRepository.findStudentByEmail("manojbh1999@gmail.com")
			 * 		.ifPresentOrElse(System.out::println, () -> System.out.println("Student with email manojbh1999@gmail.com Not Found."));
			 */

			/**
			 * studentRepository.findStudentByEmail("bmkreddy2305@gmail.com")
			 * 		.ifPresentOrElse(System.out::println, () -> System.out.println("Student with email bmkreddy2305@gmail.com Not Found."));
			 */

			/**
			 * studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("Manoj", 26).forEach(System.out::println);
			 */

			/**
			 * studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThan("Manoj", 25).forEach(System.out::println);
			 */

			/**
			 * studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual("Manoj", 26).forEach(System.out::println);
			 */

			/**
			 * studentRepository.findStudentByEmail1("jayasree.sravya@outlook.com")
			 * 			.ifPresentOrElse(System.out::println, ()-> System.out.println("Student with email jayasree.sravya@outlook.com Not Found."));
			 */

			/**
			 * studentRepository.findStudentsByFirstNameEqualsAndAgeIsLessThanEqual("Sravya", 20)
			 * 			.ifPresentOrElse(System.out::println, ()->System.out.println("No Details Present."));
			 */

			/**
			 * studentRepository.findStudentByEmail2("jayasree.sravya@outlook.com")
			 * 			.ifPresentOrElse(System.out::println, ()->System.out.println("Student with email jayasree.sravya@outlook.com Not Found"));
			 */

			/**
			 * studentRepository.findStudentByLastName("Reddy")
			 * 			.ifPresentOrElse(System.out::println, ()-> System.out.println("Last Name Not Found"));
			 */

			/**
			 * System.out.println("Deleting Manoj 3");
			 * System.out.println(studentRepository.deleteStudentById(3L));
			 */
		};
	}

	/**
	 *private void generateRandomStudents(StudentRepository studentRepository) {
	 * 		Faker faker = new Faker();
	 * 		for (int i = 0; i <= 20; i++) {
	 * 			String firstName = faker.name().firstName();
	 * 			String lastName = faker.name().lastName();
	 * 			String email = String.format("%s.%s@outlook.in", firstName, lastName);
	 * 			Student student = new Student(firstName,lastName,email,faker.number().numberBetween(12, 90));
	 * 			studentRepository.save(student);
	 * 		        }
	 * }
	 */

	/**
	 *private void sorting(StudentRepository studentRepository) {
	 * 		Sort sort1 = Sort.by("firstName").ascending().and(Sort.by("email"));
	 * 		studentRepository.findAll(sort1).forEach(student-> System.out.println(student.getFirstName() + " " + student.getEmail()));
	 * }
	 */
}
