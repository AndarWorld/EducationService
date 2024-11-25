package org.andarworld.educationservice.usecases;

import org.andarworld.educationservice.usecases.dto.EducationResponseDto;

import java.util.List;

public interface EducationService {
        EducationResponseDto getEducation(String uuid);
        List<String> getAdminEducation();
}
