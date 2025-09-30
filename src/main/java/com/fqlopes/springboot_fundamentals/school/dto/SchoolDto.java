package com.fqlopes.springboot_fundamentals.school.dto;

public class SchoolDto {

    private String name;

    public SchoolDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
