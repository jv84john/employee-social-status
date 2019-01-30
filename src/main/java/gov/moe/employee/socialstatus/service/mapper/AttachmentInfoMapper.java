package gov.moe.employee.socialstatus.service.mapper;

import gov.moe.employee.socialstatus.domain.*;
import gov.moe.employee.socialstatus.service.dto.AttachmentInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AttachmentInfo and its DTO AttachmentInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttachmentInfoMapper extends EntityMapper<AttachmentInfoDTO, AttachmentInfo> {



    default AttachmentInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        AttachmentInfo attachmentInfo = new AttachmentInfo();
        attachmentInfo.setId(id);
        return attachmentInfo;
    }
}
