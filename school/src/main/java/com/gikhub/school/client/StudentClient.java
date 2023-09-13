package com.gikhub.school.client;

import com.gikhub.school.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.student-url}")
public interface StudentClient {

    // Open feign does the implementation of communication with the student microservice
    @GetMapping("/school/{school-id}")
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);
}
