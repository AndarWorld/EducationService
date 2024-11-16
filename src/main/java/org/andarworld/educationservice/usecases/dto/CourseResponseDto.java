package org.andarworld.educationservice.usecases.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record CourseResponseDto(

        String specialtyCode,

        String name,

        String description,

        String periodFrom,

        String periodTo
) {
}
