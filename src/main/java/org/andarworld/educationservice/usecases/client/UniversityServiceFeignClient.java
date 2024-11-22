package org.andarworld.educationservice.usecases.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.andarworld.educationservice.usecases.dto.UniversityResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "UNIVERSITY-SERVICE")
public interface UniversityServiceFeignClient {

    @GetMapping("/api/universities/{uuid}")
    @Retry(name = "university-service")
    @CircuitBreaker(name = "university-service", fallbackMethod = "getUniversityFallback")
    UniversityResponseDto getUniversity(@PathVariable String uuid);

    default UniversityResponseDto getUniversityFallback(String uuid, Throwable throwable) {
        return new UniversityResponseDto("FallbackTitle", "FallbackCity",
                "This answer returned due to error", null, null, null);
    }
}