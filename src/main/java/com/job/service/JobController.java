package com.job.service;

import com.job.service.request.JobRequestDto;
import com.job.service.response.JobResponseDto;
import com.job.service.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService service;

    @PostMapping
    public JobResponseDto createJob(@Valid @RequestBody JobRequestDto dto){
        return service.createJob(dto);
    }

}
