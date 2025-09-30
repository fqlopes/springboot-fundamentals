package com.fqlopes.springboot_fundamentals.school.service;

import com.fqlopes.springboot_fundamentals.school.dto.SchoolDto;
import com.fqlopes.springboot_fundamentals.school.entity.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {


    //mapping school objects from DTO
    public School toSchool(SchoolDto dto){
        return new School(dto.getName());
    }

    //mapping DTO from School objects
    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
}
