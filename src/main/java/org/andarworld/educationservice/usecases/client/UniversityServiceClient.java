package org.andarworld.educationservice.usecases.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.educationservice.usecases.dto.UniversityResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class UniversityServiceClient {
    private final RestTemplate restTemplate;

    public UniversityResponseDto getUniversity(String uuid) {
        ResponseEntity<UniversityResponseDto> responseEntity = restTemplate.getForEntity(
                String.format("http://UNIVERSITY-SERVICE/api/universities/%s", uuid),
                UniversityResponseDto.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            log.error("No corresponding university found for uuid: {}", uuid);
            throw new RuntimeException("No university found with uuid " + uuid);
        }
    }
}
