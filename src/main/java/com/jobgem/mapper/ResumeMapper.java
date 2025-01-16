package com.jobgem.mapper;

import com.jobgem.dto.ResumeDTO;
import com.jobgem.entity.ResumeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

    @Mapping(source = "user.id", target = "userId")
    ResumeDTO resumeToResumeDTO(ResumeEntity resume);

    @Mapping(source = "userId", target = "user.id")
    ResumeEntity resumeDTOToResume(ResumeDTO resumeDTO);
}
