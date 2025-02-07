package co.jobgem.dto;

import java.time.LocalDateTime;

public class SavedJobDTO {
    private Long id;
    private Long userId;
    private Long jobId;
    private LocalDateTime savedAt;

    // Constructors
    public SavedJobDTO() {
    }

    public SavedJobDTO(Long id, Long userId, Long jobId, LocalDateTime savedAt) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.savedAt = savedAt;
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

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
