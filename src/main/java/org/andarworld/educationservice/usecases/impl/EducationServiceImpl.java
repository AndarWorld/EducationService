package org.andarworld.educationservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.educationservice.usecases.EducationService;
import org.andarworld.educationservice.usecases.dto.CourseResponseDto;
import org.andarworld.educationservice.usecases.dto.EducationResponseDto;
import org.andarworld.educationservice.usecases.dto.UniversityResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final RestTemplate restTemplate;

    @Override
    public EducationResponseDto getEducation(String uuid) {
        log.debug("Get education with uuid of university");
        ResponseEntity<UniversityResponseDto> responseEntity = restTemplate.getForEntity(
                        String.format("http://UNIVERSITY-SERVICE/universities/%s", uuid),
                        UniversityResponseDto.class);
        ResponseEntity<List<CourseResponseDto>> courseEntity = restTemplate.exchange(
                        String.format("http://Course-Service/courses/%s", uuid),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>(){});
        return new EducationResponseDto(responseEntity.getBody(), courseEntity.getBody());
    }
}
