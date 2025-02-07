package co.jobgem.api;

import co.jobgem.domain.jobgem.api.JobApiDelegate;
import co.jobgem.domain.jobgem.model.JobDTO;
import co.jobgem.domain.jobgem.model.JobInputDTO;
import co.jobgem.exception.CompanyNotFoundException;
import co.jobgem.exception.JobNotFoundException;
import co.jobgem.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApiDelegateImpl implements JobApiDelegate {

    private final JobService jobService;

    public JobApiDelegateImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @Override
    public ResponseEntity<JobDTO> getJobById(Long jobId) {
        try {
            JobDTO job = jobService.getJobById(jobId);
            return ResponseEntity.ok(job);
        } catch (JobNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<JobDTO> createJob(JobInputDTO jobInputDTO) {
        try {
            JobDTO createdJob = jobService.createJob(jobInputDTO);
            return ResponseEntity.ok(createdJob);
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteJob(Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<JobDTO> patchJob(Long jobId, JobInputDTO jobInputDTO) {
        JobDTO patchedJob = jobService.patchJob(jobId, jobInputDTO);
        return ResponseEntity.ok(patchedJob);
    }
}
