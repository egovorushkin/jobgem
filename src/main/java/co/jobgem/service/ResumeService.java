package co.jobgem.service;

import co.jobgem.dto.ResumeDTO;
import co.jobgem.entity.ResumeEntity;
import co.jobgem.mapper.ResumeMapper;
import co.jobgem.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    public ResumeService(ResumeRepository resumeRepository, ResumeMapper resumeMapper) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
    }

    public List<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll().stream()
                .map(resumeMapper::resumeToResumeDTO)
                .toList();
    }

    public ResumeDTO getResumeById(Long id) {
        return resumeRepository.findById(id)
                .map(resumeMapper::resumeToResumeDTO)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }

    public ResumeDTO createResume(ResumeDTO resumeDTO) {
        ResumeEntity resume = resumeMapper.resumeDTOToResume(resumeDTO);
        ResumeEntity savedResume = resumeRepository.save(resume);
        return resumeMapper.resumeToResumeDTO(savedResume);
    }

    public ResumeDTO updateResume(Long id, ResumeDTO resumeDTO) {
        ResumeEntity resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setTitle(resumeDTO.getTitle());
        resume.setFileUrl(resumeDTO.getFileUrl());
        resume.setUpdatedAt(resumeDTO.getUpdatedAt());

        ResumeEntity updatedResume = resumeRepository.save(resume);
        return resumeMapper.resumeToResumeDTO(updatedResume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}