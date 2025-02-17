package co.jobgem.service;

import co.jobgem.domain.jobgem.model.ResumeDTO;
import co.jobgem.domain.jobgem.model.ResumeInputDTO;
import co.jobgem.entity.Resume;
import co.jobgem.entity.UserEntity;
import co.jobgem.exception.ResumeNotFoundException;
import co.jobgem.exception.UserNotFoundException;
import co.jobgem.mapper.ResumeMapper;
import co.jobgem.repository.ResumeRepository;
import co.jobgem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private static final ResumeMapper resumeMapper = ResumeMapper.INSTANCE;

    public ResumeService(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public List<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll().stream()
                .map(resumeMapper::toResumeDTO)
                .toList();
    }

    public ResumeDTO getResumeById(Long id) {
        return resumeRepository.findById(id)
                .map(resumeMapper::toResumeDTO)
                .orElseThrow(() -> new ResumeNotFoundException("Resume with id: " + id + " not found"));
    }

    public ResumeDTO createResume(ResumeInputDTO resumeInputDTO) {
        Resume resume = resumeMapper.toResume(resumeInputDTO);

        if (resumeInputDTO.getUserId() != null) {
            UserEntity user = userRepository.findById(resumeInputDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + resumeInputDTO.getUserId() + " not found"));
            resume.setUser(user);
        }
        resume.setCreatedAt(LocalDateTime.now());
        Resume savedResume = resumeRepository.save(resume);
        return resumeMapper.toResumeDTO(savedResume);
    }

    public ResumeDTO patchResume(Long id, ResumeInputDTO resumeInputDTO) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume with id: " + id + " not found"));

        resumeMapper.updateResumeFromDto(resumeInputDTO, resume);
        resume.setUpdatedAt(LocalDateTime.now());

        Resume updatedResume = resumeRepository.save(resume);
        return resumeMapper.toResumeDTO(updatedResume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}