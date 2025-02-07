package co.jobgem.service;

import co.jobgem.dto.CompanyDTO;
import co.jobgem.entity.CompanyEntity;
import co.jobgem.mapper.CompanyMapper;
import co.jobgem.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::companyToCompanyDTO)
                .toList();
    }

    public CompanyDTO getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::companyToCompanyDTO)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        CompanyEntity company = companyMapper.companyDTOToCompany(companyDTO);
        CompanyEntity savedCompany = companyRepository.save(company);
        return companyMapper.companyToCompanyDTO(savedCompany);
    }

    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setWebsiteUrl(companyDTO.getWebsiteUrl());
        company.setLogoUrl(companyDTO.getLogoUrl());
        company.setRating(companyDTO.getRating());

        CompanyEntity updatedCompany = companyRepository.save(company);
        return companyMapper.companyToCompanyDTO(updatedCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}