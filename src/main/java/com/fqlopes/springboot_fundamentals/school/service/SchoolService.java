package com.fqlopes.springboot_fundamentals.school.service;

import com.fqlopes.springboot_fundamentals.school.dto.SchoolDto;
import com.fqlopes.springboot_fundamentals.school.entity.School;
import com.fqlopes.springboot_fundamentals.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    //fields
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    //constructor
    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    //helper methods for the controller
    public SchoolDto create(@RequestBody SchoolDto dto){

        School school = schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> findAll(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

}
