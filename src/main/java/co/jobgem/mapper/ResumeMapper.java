package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.JobApplicationInputDTO;
import co.jobgem.domain.jobgem.model.ResumeDTO;
import co.jobgem.domain.jobgem.model.ResumeInputDTO;
import co.jobgem.entity.JobApplication;
import co.jobgem.entity.Resume;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimeToOffsetDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "localDateTimeToOffsetDateTime")
    ResumeDTO toResumeDTO(Resume resume);

    @Mapping(source = "userId", target = "user.id")
    Resume toResume(ResumeInputDTO resumeInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateResumeFromDto(ResumeInputDTO resumeInputDTO, @MappingTarget Resume resume);

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }
}
