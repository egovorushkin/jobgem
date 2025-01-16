package com.jobgem.dto;

import java.time.LocalDateTime;

public class JobApplicationDTO {
    private Long id;
    private Long userId;
    private Long jobId;
    private Long resumeId;
    private LocalDateTime applicationDate;
    private String status;
    private String coverLetter;

    // Constructors
    public JobApplicationDTO() {
    }

    public JobApplicationDTO(Long id, Long userId, Long jobId, Long resumeId, LocalDateTime applicationDate, String status, String coverLetter) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.resumeId = resumeId;
        this.applicationDate = applicationDate;
        this.status = status;
        this.coverLetter = coverLetter;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}

