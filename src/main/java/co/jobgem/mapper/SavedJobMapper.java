package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.JobApplicationInputDTO;
import co.jobgem.domain.jobgem.model.SavedJobDTO;
import co.jobgem.domain.jobgem.model.SavedJobInputDTO;
import co.jobgem.entity.JobApplication;
import co.jobgem.entity.SavedJob;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface SavedJobMapper {
    SavedJobMapper INSTANCE = Mappers.getMapper(SavedJobMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(target = "savedAt", source = "savedAt", qualifiedByName = "localDateTimeToOffsetDateTime")
    SavedJobDTO toSavedJobDTO(SavedJob savedJob);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "jobId", target = "job.id")
    @Mapping(target = "savedAt", source = "savedAt", qualifiedByName = "offsetDateTimeToLocalDateTime")
    SavedJob toSavedJob(SavedJobInputDTO savedJobInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "savedAt", source = "savedAt", qualifiedByName = "offsetDateTimeToLocalDateTime")
    void updateSavedJobFromDto(SavedJobInputDTO savedJobInputDTO, @MappingTarget SavedJob savedJob);

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }
}
