package com.jobgem.mapper;

import com.jobgem.dto.JobDTO;
import com.jobgem.entity.JobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    JobDTO jobToJobDTO(JobEntity job);

    @Mapping(source = "companyId", target = "company.id")
    JobEntity jobDTOToJob(JobDTO jobDTO);
}
