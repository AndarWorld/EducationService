package org.andarworld.educationservice.usecases.client;

import org.andarworld.educationservice.usecases.dto.CourseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseServiceFeignClient {

    @GetMapping("/api/courses/{uuid}")
    List<CourseResponseDto> getAllCourses(@PathVariable String uuid);

}
