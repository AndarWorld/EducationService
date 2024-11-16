package org.andarworld.educationservice.usecases;

import org.andarworld.educationservice.usecases.dto.EducationResponseDto;

public interface EducationService {
        EducationResponseDto getEducation(String uuid);
}
