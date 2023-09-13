package com.gikhub.school;

import com.gikhub.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository repository;
    private final StudentClient client;

    public void save(School newSchool) {
        repository.save(newSchool);
    }

    public List<School> findAll(){
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(School.builder()
                        .name("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
                );
        // find all the students from the student microservice
        System.out.println("schoolId>>>>>>>> = " + school);
        var students = client.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();

    }
}
