package org.andarworld.educationservice.usecases.dto;

import java.util.List;

public record EducationResponseDto(
        UniversityResponseDto university,
        List<CourseResponseDto> courses
) {
}
