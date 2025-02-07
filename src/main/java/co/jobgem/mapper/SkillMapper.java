package co.jobgem.mapper;

import co.jobgem.dto.SkillDTO;
import co.jobgem.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDTO skillToSkillDTO(Skill skill);

    Skill skillDTOToSkill(SkillDTO skillDTO);
}
