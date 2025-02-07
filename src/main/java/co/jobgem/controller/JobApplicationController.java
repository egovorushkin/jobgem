package co.jobgem.controller;

import co.jobgem.dto.JobApplicationDTO;
import co.jobgem.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDTO>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> getJobApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(jobApplicationService.getJobApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<JobApplicationDTO> createJobApplication(@RequestBody JobApplicationDTO jobApplicationDTO) {
        return ResponseEntity.ok(jobApplicationService.createJobApplication(jobApplicationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> updateJobApplication(@PathVariable Long id, @RequestBody JobApplicationDTO jobApplicationDTO) {
        return ResponseEntity.ok(jobApplicationService.updateJobApplication(id, jobApplicationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.noContent().build();
    }
}