package com.fqlopes.springboot_fundamentals.student.service;

import com.fqlopes.springboot_fundamentals.student.dto.StudentDto;
import com.fqlopes.springboot_fundamentals.student.dto.StudentResponseDto;
import com.fqlopes.springboot_fundamentals.student.entity.Student;
import com.fqlopes.springboot_fundamentals.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private int number;

    //service to be tested
    @InjectMocks
    StudentService studentService;

    //dependencies
    @Mock
    StudentRepository repository;

    @Mock
    StudentMapper studentMapper;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveAStudent() {
        //given
        StudentDto dto = new StudentDto("John", "Nada", "john@nada.com", 1);
        Student student = new Student("John", "Nada", "john@nada.com", 2);
        Student savedStudent = new Student("John", "Nada", "john@nada.com", 2);
        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("John", "Nada", "john@nada.com"));

        //when
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        //then
        assertEquals(dto.getFirstName(), responseDto.getFirstName());
        assertEquals(dto.getLastName(), responseDto.getLastName());
        assertEquals(dto.getEmail(), responseDto.getEmail());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void shouldReturnStudentList() {
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Nada", "john@nada.com", 2));

        //mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Nada", "john@nada.com"));

        //when
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        //then
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldFindStudentById() {
        //given
        Integer id = 1;
        Student student = new Student("John", "Nada", "john@nada.com", 2);

        //mock the calls
        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Nada", "john@nada.com"));

        //when
        StudentResponseDto dto = studentService.findStudentById(id);

        //then
        assertEquals(dto.getFirstName(), student.getFirstName());
        assertEquals(dto.getLastName(), student.getLastName());
        assertEquals(dto.getEmail(), student.getEmail());

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldFindStudentByName() {
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Nada", "john@nada.com", 2));

        //mock calls
        when(repository.findAllByFirstNameContaining(anyString())).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Nada", "john@nada.com"));

        //when
        List<StudentResponseDto> responseDtos = studentService.findStudentByName(anyString());

        //then
        assertEquals(students.size(), responseDtos.size());
    }

    @Test
    public void shouldDeleteStudent(){
        //given
        Integer id = 1;
        Student student = new Student("John", "Nada", "john@nada.com", 2);

        //mock calls
        when(repository.findById(id)).thenReturn(Optional.of(student));

        //when
        repository.delete(student);

        //then
        verify(repository, times(1)).delete(student);


    }
}