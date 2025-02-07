package co.jobgem.service;

import co.jobgem.domain.jobgem.model.CompanyDTO;
import co.jobgem.domain.jobgem.model.CompanyInputDTO;
import co.jobgem.entity.Company;
import co.jobgem.exception.CompanyNotFoundException;
import co.jobgem.mapper.CompanyMapper;
import co.jobgem.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private static final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toCompanyDTO)
                .toList();
    }

    public CompanyDTO getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::toCompanyDTO)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id: " + id + " not found"));
    }

    public CompanyDTO createCompany(CompanyInputDTO companyInputDTO) {
        Company company = companyMapper.toCompany(companyInputDTO);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toCompanyDTO(savedCompany);
    }

    public CompanyDTO patchCompany(Long id, CompanyInputDTO companyInputDTO) {
        Company companyForUpdate = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id: " + id + " not found"));

        companyMapper.updateCompanyFromDto(companyInputDTO, companyForUpdate);
        Company updatedCompany = companyRepository.save(companyForUpdate);
        return companyMapper.toCompanyDTO(updatedCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}