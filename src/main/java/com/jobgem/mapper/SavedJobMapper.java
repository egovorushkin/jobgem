package com.jobgem.mapper;

import com.jobgem.dto.SavedJobDTO;
import com.jobgem.entity.SavedJobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SavedJobMapper {
    SavedJobMapper INSTANCE = Mappers.getMapper(SavedJobMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "job.id", target = "jobId")
    SavedJobDTO savedJobToSavedJobDTO(SavedJobEntity savedJob);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "jobId", target = "job.id")
    SavedJobEntity savedJobDTOToSavedJob(SavedJobDTO savedJobDTO);
}
