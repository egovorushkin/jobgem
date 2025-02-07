package co.jobgem.controller;

import co.jobgem.dto.SavedJobDTO;
import co.jobgem.service.SavedJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @GetMapping
    public ResponseEntity<List<SavedJobDTO>> getAllSavedJobs() {
        return ResponseEntity.ok(savedJobService.getAllSavedJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedJobDTO> getSavedJobById(@PathVariable Long id) {
        return ResponseEntity.ok(savedJobService.getSavedJobById(id));
    }

    @PostMapping
    public ResponseEntity<SavedJobDTO> createSavedJob(@RequestBody SavedJobDTO savedJobDTO) {
        return ResponseEntity.ok(savedJobService.createSavedJob(savedJobDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedJob(@PathVariable Long id) {
        savedJobService.deleteSavedJob(id);
        return ResponseEntity.noContent().build();
    }
}
