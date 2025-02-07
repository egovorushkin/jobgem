package co.jobgem.api;

import co.jobgem.domain.jobgem.api.CompanyApiDelegate;
import co.jobgem.domain.jobgem.model.CompanyDTO;
import co.jobgem.domain.jobgem.model.CompanyInputDTO;
import co.jobgem.exception.CompanyNotFoundException;
import co.jobgem.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyApiDelegateIml implements CompanyApiDelegate {

    private final CompanyService companyService;

    public CompanyApiDelegateIml(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @Override
    public ResponseEntity<CompanyDTO> getCompanyById(Long companyId) {
        try {
            return ResponseEntity.ok(companyService.getCompanyById(companyId));
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<CompanyDTO> createCompany(CompanyInputDTO companyInputDTO) {
        try {
            return ResponseEntity.ok(companyService.createCompany(companyInputDTO));
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCompany(Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CompanyDTO> patchCompany(Long companyId, CompanyInputDTO companyInputDTO) {
        return ResponseEntity.ok(companyService.patchCompany(companyId, companyInputDTO));
    }
}
