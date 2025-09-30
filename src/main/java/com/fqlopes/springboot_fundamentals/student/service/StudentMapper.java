package com.fqlopes.springboot_fundamentals.student.service;

import com.fqlopes.springboot_fundamentals.student.dto.StudentDto;
import com.fqlopes.springboot_fundamentals.student.dto.StudentResponseDto;
import com.fqlopes.springboot_fundamentals.school.entity.School;
import com.fqlopes.springboot_fundamentals.student.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){
        if (dto == null){
            throw new NullPointerException("The studentDto should not be null");
        }


        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        School school = new School();
        school.setId(dto.getSchoolId());

        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }


}
