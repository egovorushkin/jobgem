package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.JobDTO;
import co.jobgem.domain.jobgem.model.JobInputDTO;
import co.jobgem.entity.Job;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

//    @Mapping(source = "company.id", target = "companyId")
//    @Mapping(source = "company.name", target = "companyName")
    JobDTO toJobDTO(Job job);

//    @Mapping(source = "companyId", target = "company.id")
    Job toJob(JobInputDTO jobInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateJobFromDto(JobInputDTO jobInputDTO, @MappingTarget Job job);
}
