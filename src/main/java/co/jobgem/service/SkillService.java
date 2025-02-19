package co.jobgem.service;

import co.jobgem.domain.jobgem.model.SkillDTO;
import co.jobgem.domain.jobgem.model.SkillInputDTO;
import co.jobgem.entity.Skill;
import co.jobgem.exception.SkillJobNotFoundException;
import co.jobgem.mapper.SkillMapper;
import co.jobgem.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private static final SkillMapper skillMapper = SkillMapper.INSTANCE;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillMapper::toSkillDTO)
                .toList();
    }

    public SkillDTO getSkillById(Long id) {
        return skillRepository.findById(id)
                .map(skillMapper::toSkillDTO)
                .orElseThrow(() -> new SkillJobNotFoundException("Skill with id: " + id + " not found"));
    }

    public SkillDTO createSkill(SkillInputDTO skillInputDTO) {
        Skill skill = skillMapper.toSkill(skillInputDTO);
        Skill savedSkill = skillRepository.save(skill);
        return skillMapper.toSkillDTO(savedSkill);
    }

    public SkillDTO patchSkill(Long id, SkillInputDTO skillInputDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillJobNotFoundException("Skill with id: " + id + " not found"));

        skillMapper.updateSkillFromDto(skillInputDTO, skill);
        Skill updatedSkill = skillRepository.save(skill);
        return skillMapper.toSkillDTO(updatedSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
