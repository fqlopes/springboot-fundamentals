package com.fqlopes.springboot_fundamentals.school.repository;

import com.fqlopes.springboot_fundamentals.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
