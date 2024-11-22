package org.andarworld.educationservice.usecases.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.educationservice.usecases.dto.CourseResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseServiceClient {
    private final RestTemplate restTemplate;

    public List<CourseResponseDto> getAllCourses(String universityUuid) {
        ResponseEntity<List<CourseResponseDto>> courseEntity = restTemplate.exchange(
                String.format("http://Course-Service/api/courses/%s", universityUuid),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){});
        if(courseEntity.getStatusCode().is2xxSuccessful()) {
            return courseEntity.getBody();
        } else {
            log.error("No course found for university uuid: {}", universityUuid);
            throw new RuntimeException("No corresponding course found");
        }
    }
}
