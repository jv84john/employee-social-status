package gov.moe.employee.socialstatus.service.mapper;

import gov.moe.employee.socialstatus.domain.*;
import gov.moe.employee.socialstatus.service.dto.DependentInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DependentInfo and its DTO DependentInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonalDataMapper.class})
public interface DependentInfoMapper extends EntityMapper<DependentInfoDTO, DependentInfo> {

    @Mapping(source = "personalData.id", target = "personalDataId")
    DependentInfoDTO toDto(DependentInfo dependentInfo);

    @Mapping(source = "personalDataId", target = "personalData")
    DependentInfo toEntity(DependentInfoDTO dependentInfoDTO);

    default DependentInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DependentInfo dependentInfo = new DependentInfo();
        dependentInfo.setId(id);
        return dependentInfo;
    }
}
