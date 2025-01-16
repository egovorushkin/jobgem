package com.jobgem.mapper;

import com.jobgem.dto.SkillDTO;
import com.jobgem.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDTO skillToSkillDTO(SkillEntity skill);

    SkillEntity skillDTOToSkill(SkillDTO skillDTO);
}
