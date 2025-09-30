package com.fqlopes.springboot_fundamentals.student.dto;

import jakarta.validation.constraints.NotEmpty;

public class StudentDto {

    @NotEmpty(message = "É necessário conter um nome")
    private String firstName;

    @NotEmpty(message = "É necessário conter um sobrenome")
    private String lastName;

    private String email;
    private Integer schoolId;

    public StudentDto(String firstName, String lastName, String email, Integer schoolId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.schoolId = schoolId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
