package co.jobgem.mapper;

import co.jobgem.dto.SavedJobDTO;
import co.jobgem.entity.SavedJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SavedJobMapper {
    SavedJobMapper INSTANCE = Mappers.getMapper(SavedJobMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "job.id", target = "jobId")
    SavedJobDTO savedJobToSavedJobDTO(SavedJob savedJob);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "jobId", target = "job.id")
    SavedJob savedJobDTOToSavedJob(SavedJobDTO savedJobDTO);
}
