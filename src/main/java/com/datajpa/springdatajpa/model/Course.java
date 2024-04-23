package com.datajpa.springdatajpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "course_sequence")
    @Column(name = "course_id", updatable = false, columnDefinition = "INTEGER")
    private Long id;

    @Column(name = "course_name", nullable = false, updatable = false,
            columnDefinition = "TEXT"
    )
    private String courseName;

    @Column(name = "department_name", nullable = false, updatable = false,
            columnDefinition = "TEXT"
    )
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course() {}

    public Course(String courseName, Department department) {
        this.courseName = courseName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
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
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", department=" + department +
                ", enrollments=" + enrollments +
                '}';
    }
}
