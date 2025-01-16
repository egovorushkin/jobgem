package com.jobgem.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<UserEntity> users;

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<JobEntity> jobs;

    public SkillEntity(Long id) {
        this.id = id;
    }

    public SkillEntity(Long id, String name, Set<UserEntity> users, Set<JobEntity> jobs) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.jobs = jobs;
    }

    public SkillEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<JobEntity> getJobs() {
        return jobs;
    }

    public void setJobs(Set<JobEntity> jobs) {
        this.jobs = jobs;
    }
}
