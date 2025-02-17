package co.jobgem.api;

import co.jobgem.domain.jobgem.api.ResumeApiDelegate;
import co.jobgem.domain.jobgem.model.ResumeDTO;
import co.jobgem.domain.jobgem.model.ResumeInputDTO;
import co.jobgem.exception.ResumeNotFoundException;
import co.jobgem.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeApiDelegateImpl implements ResumeApiDelegate {

    private final ResumeService resumeService;

    public ResumeApiDelegateImpl(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @Override
    public ResponseEntity<List<ResumeDTO>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    @Override
    public ResponseEntity<ResumeDTO> getResumeById(Long resumeId) {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId));
    }

    @Override
    public ResponseEntity<ResumeDTO> createResume(ResumeInputDTO resumeInputDTO) {
        try {
            return ResponseEntity.ok(resumeService.createResume(resumeInputDTO));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteResume(Long resumeId) {
        resumeService.deleteResume(resumeId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResumeDTO> patchResume(Long resumeId, ResumeInputDTO resumeInputDTO) {
        return ResponseEntity.ok(resumeService.patchResume(resumeId, resumeInputDTO));
    }
}
