package co.jobgem.service;

import co.jobgem.domain.jobgem.model.SavedJobDTO;
import co.jobgem.domain.jobgem.model.SavedJobInputDTO;
import co.jobgem.entity.Job;
import co.jobgem.entity.SavedJob;
import co.jobgem.entity.UserEntity;
import co.jobgem.exception.JobNotFoundException;
import co.jobgem.exception.SavedJobNotFoundException;
import co.jobgem.exception.UserNotFoundException;
import co.jobgem.mapper.SavedJobMapper;
import co.jobgem.repository.JobRepository;
import co.jobgem.repository.SavedJobRepository;
import co.jobgem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private static final SavedJobMapper savedJobMapper = SavedJobMapper.INSTANCE;

    public SavedJobService(SavedJobRepository savedJobRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.savedJobRepository = savedJobRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public List<SavedJobDTO> getAllSavedJobs() {
        return savedJobRepository.findAll().stream()
                .map(savedJobMapper::toSavedJobDTO)
                .toList();
    }

    public SavedJobDTO getSavedJobById(Long id) {
        return savedJobRepository.findById(id)
                .map(savedJobMapper::toSavedJobDTO)
                .orElseThrow(() -> new RuntimeException("Saved job not found"));
    }

    public SavedJobDTO createSavedJob(SavedJobInputDTO savedJobInputDTO) {
        SavedJob savedJob = savedJobMapper.toSavedJob(savedJobInputDTO);

        if (savedJobInputDTO.getJobId() != null) {
            Job job = jobRepository.findById(savedJobInputDTO.getJobId())
                    .orElseThrow(() -> new JobNotFoundException("Job with id: " + savedJobInputDTO.getJobId() + " not found"));
            savedJob.setJob(job);
        }

        if (savedJobInputDTO.getUserId() != null) {
            UserEntity user = userRepository.findById(savedJobInputDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + savedJobInputDTO.getJobId() + " not found"));
            savedJob.setUser(user);
        }

        savedJob.setSavedAt(LocalDateTime.now());

        SavedJob savedSavedJob = savedJobRepository.save(savedJob);
        return savedJobMapper.toSavedJobDTO(savedSavedJob);
    }

    public SavedJobDTO patchSavedJob(Long id, SavedJobInputDTO savedJobInputDTO) {
        SavedJob savedJob = savedJobRepository.findById(id)
                .orElseThrow(() -> new SavedJobNotFoundException("Saved job with id: " + id + " not found"));
        // TODO: implement correct saving user and job
        savedJobMapper.updateSavedJobFromDto(savedJobInputDTO, savedJob);
        return savedJobMapper.toSavedJobDTO(savedJob);
    }

    public void deleteSavedJob(Long id) {
        savedJobRepository.deleteById(id);
    }
}
