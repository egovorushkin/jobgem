package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.JobApplicationDTO;
import co.jobgem.domain.jobgem.model.JobApplicationInputDTO;
import co.jobgem.entity.JobApplication;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    @Mapping(target = "applicationDate", source = "applicationDate", qualifiedByName = "localDateTimeToOffsetDateTime")
    JobApplicationDTO toJobApplicationDTO(JobApplication jobApplication);

    @Mapping(target = "applicationDate", source = "applicationDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    JobApplication toJobApplication(JobApplicationInputDTO jobApplicationInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "applicationDate", source = "applicationDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    void updateJobApplicationFromDto(JobApplicationInputDTO jobApplicationInputDTO, @MappingTarget JobApplication jobApplication);

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }
}