package com.job.service.service;

import com.job.service.entity.JobEntity;
import com.job.service.repo.JobRepo;
import com.job.service.request.JobRequestDto;
import com.job.service.response.JobResponseDto;
import com.job.service.security.UserContext;
import com.job.service.security.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepo jobRepo;

    public JobResponseDto createJob(JobRequestDto dto){

        UserContext userContext = UserContextHolder.get();
        if(!userContext.hasRole("ADMIN") && !userContext.hasRole("EMPLOYER")){
            throw new AccessDeniedException("Invalid role");
        } JobEntity entity = new JobEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setCompany(dto.getCompany());
        entity.setCreatedBy(userContext.getUsername());
        entity.setLastUpdatedBy(userContext.getUsername());
        entity.setCreatedDate(Instant.now());
        entity.setLastUpdatedDate(Instant.now());
        jobRepo.save(entity);
        return JobResponseDto.builder().msg("Job Created Successfully").build();
    }
}
