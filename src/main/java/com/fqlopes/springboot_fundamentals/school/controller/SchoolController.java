package com.fqlopes.springboot_fundamentals.school.controller;


import com.fqlopes.springboot_fundamentals.school.dto.SchoolDto;
import com.fqlopes.springboot_fundamentals.school.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    //fields
    private final SchoolService schoolService;

    //constructor
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    //controller methods

    @PostMapping("/schools")
    public SchoolDto create(@RequestBody SchoolDto dto){
        return schoolService.create(dto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return schoolService.findAll();
    }
}
