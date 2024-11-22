package org.andarworld.educationservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.educationservice.usecases.EducationService;
import org.andarworld.educationservice.usecases.client.CourseServiceClient;
import org.andarworld.educationservice.usecases.client.CourseServiceFeignClient;
import org.andarworld.educationservice.usecases.client.UniversityServiceClient;
import org.andarworld.educationservice.usecases.client.UniversityServiceFeignClient;
import org.andarworld.educationservice.usecases.dto.CourseResponseDto;
import org.andarworld.educationservice.usecases.dto.EducationResponseDto;
import org.andarworld.educationservice.usecases.dto.UniversityResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    // private final UniversityServiceClient universityServiceClient;
//    private final CourseServiceClient courseServiceClient;
    private final UniversityServiceFeignClient universityServiceClient;
    private final CourseServiceFeignClient courseServiceClient;

    @Override
    public EducationResponseDto getEducation(String uuid) {
        log.debug("Get education with uuid of university");
        UniversityResponseDto universityResponseDto = universityServiceClient.getUniversity(uuid);
        List<CourseResponseDto> courseResponseDtos = courseServiceClient.getAllCourses(uuid);
        return new EducationResponseDto(universityResponseDto, courseResponseDtos);
    }
}
