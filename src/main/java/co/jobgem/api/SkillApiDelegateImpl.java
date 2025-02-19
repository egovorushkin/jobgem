package co.jobgem.api;

import co.jobgem.domain.jobgem.api.SkillApiDelegate;
import co.jobgem.domain.jobgem.model.SkillDTO;
import co.jobgem.domain.jobgem.model.SkillInputDTO;
import co.jobgem.exception.SkillJobNotFoundException;
import co.jobgem.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillApiDelegateImpl implements SkillApiDelegate {

    private final SkillService skillService;

    public SkillApiDelegateImpl(SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @Override
    public ResponseEntity<SkillDTO> getSkillById(Long skillId) {
        try {
            return ResponseEntity.ok(skillService.getSkillById(skillId));
        } catch (SkillJobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<SkillDTO> createSkill(SkillInputDTO skillInputDTO) {
        try {
            return ResponseEntity.ok(skillService.createSkill(skillInputDTO));
        } catch (SkillJobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteSkill(Long skillId) {
        skillService.deleteSkill(skillId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SkillDTO> patchSkill(Long skillId, SkillInputDTO skillInputDTO) {
        try {
            return ResponseEntity.ok(skillService.patchSkill(skillId, skillInputDTO));
        } catch (SkillJobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
