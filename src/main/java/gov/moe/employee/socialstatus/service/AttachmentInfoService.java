package gov.moe.employee.socialstatus.service;

import gov.moe.employee.socialstatus.service.dto.AttachmentInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AttachmentInfo.
 */
public interface AttachmentInfoService {

    /**
     * Save a attachmentInfo.
     *
     * @param attachmentInfoDTO the entity to save
     * @return the persisted entity
     */
    AttachmentInfoDTO save(AttachmentInfoDTO attachmentInfoDTO);

    /**
     * Get all the attachmentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AttachmentInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" attachmentInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AttachmentInfoDTO> findOne(Long id);

    /**
     * Delete the "id" attachmentInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
