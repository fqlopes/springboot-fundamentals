package com.fqlopes.springboot_fundamentals.student.service;

import com.fqlopes.springboot_fundamentals.student.dto.StudentDto;
import com.fqlopes.springboot_fundamentals.student.dto.StudentResponseDto;
import com.fqlopes.springboot_fundamentals.student.entity.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;


    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentToStudentDto () {
        StudentDto studentDto = new StudentDto("John", "Nada", "john@nada.com", 1);

        Student student = mapper.toStudent(studentDto);

        assertEquals(studentDto.getFirstName(), student.getFirstName());
        assertEquals(studentDto.getLastName(), student.getLastName());
        assertEquals(studentDto.getEmail()  , student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.getSchoolId(), student.getSchool().getId());

    }

    @Test
    public void should_throw_null_pointer_exception_studentDto_to_student_when_studentDto_is_null(){


        var exception = assertThrows(NullPointerException.class, () -> {
            Student student = mapper.toStudent(null);
        });

        assertEquals("The studentDto should not be null", exception.getMessage());

    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student("John", "Nada", "john@nada.com", 1);

        //When
        StudentResponseDto studentDto = mapper.toStudentResponseDto(student);

        //Then
        assertEquals(student.getFirstName(), studentDto.getFirstName());
        assertEquals(student.getLastName(), studentDto.getLastName());
        assertEquals(student.getEmail(), studentDto.getEmail());

    }

}