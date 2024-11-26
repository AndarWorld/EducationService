package org.andarworld.educationservice.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.educationservice.usecases.EducationService;
import org.andarworld.educationservice.usecases.dto.EducationResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@Slf4j
@RequiredArgsConstructor
public class EducationApiController {

    private final EducationService educationService;
    private final SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    @Value("${from.config.application}")
    private String str1;

    @Value("${from.config.educationservice}")
    private String str2;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getAdminString() {
        List<String> educationResponseDto = educationService.getAdminEducation();
        return ResponseEntity.ok(educationResponseDto);
    }

    @GetMapping("/confServ")
    public String getConfServString() {
        return str1 +"   " + str2;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EducationResponseDto> getUniversityCourses(@PathVariable("uuid") String uuid) {
        log.debug("Get University Courses");
        EducationResponseDto educationResponseDto = educationService.getEducation(uuid);
        return ResponseEntity.ok(educationResponseDto);
    }
}
