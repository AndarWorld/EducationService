package org.andarworld.educationservice.usecases.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record UniversityResponseDto(
        String title,

        String city,

        String description,

        String periodFrom,

        Boolean governmentStatus,

        String fromWho
) {
}
