package gov.moe.employee.socialstatus.service.mapper;

import gov.moe.employee.socialstatus.domain.*;
import gov.moe.employee.socialstatus.service.dto.PersonalDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PersonalData and its DTO PersonalDataDTO.
 */
@Mapper(componentModel = "spring", uses = {AttachmentInfoMapper.class})
public interface PersonalDataMapper extends EntityMapper<PersonalDataDTO, PersonalData> {

    @Mapping(source = "attachmentInfo.id", target = "attachmentInfoId")
    PersonalDataDTO toDto(PersonalData personalData);

    @Mapping(source = "attachmentInfoId", target = "attachmentInfo")
    PersonalData toEntity(PersonalDataDTO personalDataDTO);

    default PersonalData fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalData personalData = new PersonalData();
        personalData.setId(id);
        return personalData;
    }
}
