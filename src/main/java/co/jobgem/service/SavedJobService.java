package co.jobgem.service;

import co.jobgem.dto.SavedJobDTO;
import co.jobgem.entity.SavedJob;
import co.jobgem.mapper.SavedJobMapper;
import co.jobgem.repository.SavedJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final SavedJobMapper savedJobMapper;

    public SavedJobService(SavedJobRepository savedJobRepository, SavedJobMapper savedJobMapper) {
        this.savedJobRepository = savedJobRepository;
        this.savedJobMapper = savedJobMapper;
    }

    public List<SavedJobDTO> getAllSavedJobs() {
        return savedJobRepository.findAll().stream()
                .map(savedJobMapper::savedJobToSavedJobDTO)
                .toList();
    }

    public SavedJobDTO getSavedJobById(Long id) {
        return savedJobRepository.findById(id)
                .map(savedJobMapper::savedJobToSavedJobDTO)
                .orElseThrow(() -> new RuntimeException("Saved job not found"));
    }

    public SavedJobDTO createSavedJob(SavedJobDTO savedJobDTO) {
        SavedJob savedJob = savedJobMapper.savedJobDTOToSavedJob(savedJobDTO);
        SavedJob savedSavedJob = savedJobRepository.save(savedJob);
        return savedJobMapper.savedJobToSavedJobDTO(savedSavedJob);
    }

    public void deleteSavedJob(Long id) {
        savedJobRepository.deleteById(id);
    }
}
