package co.jobgem.mapper;

import co.jobgem.domain.jobgem.model.CompanyDTO;
import co.jobgem.domain.jobgem.model.CompanyInputDTO;
import co.jobgem.entity.Company;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO toCompanyDTO(Company company);

    Company toCompany(CompanyInputDTO companyInputDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompanyFromDto(CompanyInputDTO companyInputDTO, @MappingTarget Company company);
}
