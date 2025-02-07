package co.jobgem.mapper;

import co.jobgem.dto.JobApplicationDTO;
import co.jobgem.entity.JobApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "resume.id", target = "resumeId")
    JobApplicationDTO jobApplicationToJobApplicationDTO(JobApplicationEntity jobApplication);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "jobId", target = "job.id")
    @Mapping(source = "resumeId", target = "resume.id")
    JobApplicationEntity jobApplicationDTOToJobApplication(JobApplicationDTO jobApplicationDTO);
}