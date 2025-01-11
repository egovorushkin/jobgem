package com.jobgem.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String company;
    private String location;
    private String description;
    private String employmentType;
    private double salary;
}
