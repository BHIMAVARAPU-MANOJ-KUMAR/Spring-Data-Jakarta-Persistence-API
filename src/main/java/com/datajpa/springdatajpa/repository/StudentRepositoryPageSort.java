package com.datajpa.springdatajpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.datajpa.springdatajpa.model.Student;

public interface StudentRepositoryPageSort extends PagingAndSortingRepository<Student, Long>{
}
