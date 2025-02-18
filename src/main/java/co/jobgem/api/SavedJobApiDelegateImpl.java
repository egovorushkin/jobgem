package co.jobgem.api;

import co.jobgem.domain.jobgem.api.SavedJobApiDelegate;
import co.jobgem.domain.jobgem.model.SavedJobDTO;
import co.jobgem.domain.jobgem.model.SavedJobInputDTO;
import co.jobgem.exception.SavedJobNotFoundException;
import co.jobgem.service.SavedJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobApiDelegateImpl implements SavedJobApiDelegate {

    private final SavedJobService savedJobService;

    public SavedJobApiDelegateImpl(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @Override
    public ResponseEntity<List<SavedJobDTO>> getAllSavedJobs() {
        return ResponseEntity.ok(savedJobService.getAllSavedJobs());
    }

    @Override
    public ResponseEntity<SavedJobDTO> getSavedJobById(Long savedJobId) {
        try {
            return ResponseEntity.ok(savedJobService.getSavedJobById(savedJobId));
        } catch (SavedJobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<SavedJobDTO> createSavedJob(SavedJobInputDTO savedJobInputDTO) {
        try {
            return ResponseEntity.ok(savedJobService.createSavedJob(savedJobInputDTO));
        } catch (SavedJobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteSavedJob(Long savedJobId) {
        savedJobService.deleteSavedJob(savedJobId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SavedJobDTO> patchSavedJob(Long savedJobId, SavedJobInputDTO savedJobInputDTO) {
        return ResponseEntity.ok(savedJobService.patchSavedJob(savedJobId, savedJobInputDTO));
    }
}
