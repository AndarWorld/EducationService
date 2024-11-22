package org.andarworld.educationservice.usecases.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.andarworld.educationservice.usecases.dto.CourseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseServiceFeignClient {

    @GetMapping("/api/courses/{uuid}")
    @Retry(name = "course-service")
    @CircuitBreaker(name = "course-service", fallbackMethod = "getAllCoursesFallback")
    List<CourseResponseDto> getAllCourses(@PathVariable String uuid);

    default List<CourseResponseDto> getAllCoursesFallback(String uuid) {
        return List.of(new CourseResponseDto
                ("0.0.0.0.", "TestName", "This answer returned due to error",
                        null, null));
    };

}
