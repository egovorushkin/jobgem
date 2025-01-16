package com.jobgem.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "salary_max")
    private BigDecimal salaryMax;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Column(nullable = false)
    private String location;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<SkillEntity> requiredSkills;

    @OneToMany(mappedBy = "job")
    private Set<JobApplicationEntity> applications;

    @OneToMany(mappedBy = "job")
    private Set<SavedJobEntity> savedBy;

    public JobEntity(Long id) {
        this.id = id;
    }

    public JobEntity(Long id,
                     String title,
                     String description,
                     CompanyEntity company,
                     BigDecimal salaryMin,
                     BigDecimal salaryMax,
                     LocalDate postDate,
                     LocalDate expirationDate,
                     JobType jobType,
                     String location,
                     Set<SkillEntity> requiredSkills,
                     Set<JobApplicationEntity> applications,
                     Set<SavedJobEntity> savedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.postDate = postDate;
        this.expirationDate = expirationDate;
        this.jobType = jobType;
        this.location = location;
        this.requiredSkills = requiredSkills;
        this.applications = applications;
        this.savedBy = savedBy;
    }

    public JobEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<SkillEntity> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<SkillEntity> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<JobApplicationEntity> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobApplicationEntity> applications) {
        this.applications = applications;
    }

    public Set<SavedJobEntity> getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Set<SavedJobEntity> savedBy) {
        this.savedBy = savedBy;
    }
}
