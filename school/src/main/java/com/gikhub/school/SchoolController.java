package com.gikhub.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@RequestBody School newSchool){
        service.save(newSchool);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(@PathVariable("school-id") Integer schoolId) {
        return ResponseEntity.ok(service.findSchoolWithStudents(schoolId));
    }
}
