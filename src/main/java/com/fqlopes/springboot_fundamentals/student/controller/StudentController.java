package com.fqlopes.springboot_fundamentals.student.controller;



import com.fqlopes.springboot_fundamentals.student.dto.StudentDto;
import com.fqlopes.springboot_fundamentals.student.dto.StudentResponseDto;
import com.fqlopes.springboot_fundamentals.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    //fields
    private final StudentService studentService;

    //constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Controller methods
    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto dto){
        return studentService.saveStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent(){
        return studentService.findAllStudent();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(@PathVariable("student-id") Integer id){
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name){
        return studentService.findStudentByName(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("student-id") Integer id){
        studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName =((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
