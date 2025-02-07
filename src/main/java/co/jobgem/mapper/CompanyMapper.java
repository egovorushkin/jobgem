package co.jobgem.mapper;

import co.jobgem.dto.CompanyDTO;
import co.jobgem.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO companyToCompanyDTO(CompanyEntity company);

    CompanyEntity companyDTOToCompany(CompanyDTO companyDTO);
}
