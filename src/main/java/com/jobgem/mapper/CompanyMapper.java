package com.jobgem.mapper;

import com.jobgem.dto.CompanyDTO;
import com.jobgem.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO companyToCompanyDTO(CompanyEntity company);

    CompanyEntity companyDTOToCompany(CompanyDTO companyDTO);
}
