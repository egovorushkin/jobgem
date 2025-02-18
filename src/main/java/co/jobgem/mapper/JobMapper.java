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

    JobDTO toJobDTO(Job job);

    Job toJob(JobInputDTO jobInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateJobFromDto(JobInputDTO jobInputDTO, @MappingTarget Job job);
}
