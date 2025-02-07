package co.jobgem.service;

import co.jobgem.dto.SkillDTO;
import co.jobgem.entity.Skill;
import co.jobgem.mapper.SkillMapper;
import co.jobgem.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillMapper::skillToSkillDTO)
                .toList();
    }

    public SkillDTO getSkillById(Long id) {
        return skillRepository.findById(id)
                .map(skillMapper::skillToSkillDTO)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = skillMapper.skillDTOToSkill(skillDTO);
        Skill savedSkill = skillRepository.save(skill);
        return skillMapper.skillToSkillDTO(savedSkill);
    }

    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setName(skillDTO.getName());

        Skill updatedSkill = skillRepository.save(skill);
        return skillMapper.skillToSkillDTO(updatedSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
