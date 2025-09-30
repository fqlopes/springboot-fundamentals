package com.fqlopes.springboot_fundamentals.student.service;


import com.fqlopes.springboot_fundamentals.student.dto.StudentDto;
import com.fqlopes.springboot_fundamentals.student.dto.StudentResponseDto;
import com.fqlopes.springboot_fundamentals.student.entity.Student;
import com.fqlopes.springboot_fundamentals.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    //fields
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    //constructor
    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }


    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudent(){

        return repository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(@PathVariable("student-id") Integer id){

        return repository.findById(id)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name){
        return repository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(@PathVariable("student-id") Integer id){
        var student = repository.findById(id).orElse(null);
        repository.delete(student);
    }
}
