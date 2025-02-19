package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.SkillDTO;
import co.jobgem.domain.jobgem.model.SkillInputDTO;
import co.jobgem.entity.Skill;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDTO toSkillDTO(Skill skill);

    Skill toSkill(SkillInputDTO skillInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSkillFromDto(SkillInputDTO skillInputDTO, @MappingTarget Skill skill);
}
