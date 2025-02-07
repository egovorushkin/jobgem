package co.jobgem.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<UserEntity> users;

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<Job> jobs;

    public Skill(Long id) {
        this.id = id;
    }

    public Skill(Long id, String name, Set<UserEntity> users, Set<Job> jobs) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.jobs = jobs;
    }

    public Skill() {

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

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
