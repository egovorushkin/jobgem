package co.jobgem.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(nullable = false)
    private Double rating;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobs;

    public Company(Long id) {
        this.id = id;
    }

    public Company(Long id,
                   String name,
                   String description,
                   String websiteUrl,
                   String logoUrl,
                   Double rating,
                   Set<Job> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.logoUrl = logoUrl;
        this.rating = rating;
        this.jobs = jobs;
    }

    public Company() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
