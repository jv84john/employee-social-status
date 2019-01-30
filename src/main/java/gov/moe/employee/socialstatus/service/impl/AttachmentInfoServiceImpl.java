package gov.moe.employee.socialstatus.service.impl;

import gov.moe.employee.socialstatus.service.AttachmentInfoService;
import gov.moe.employee.socialstatus.domain.AttachmentInfo;
import gov.moe.employee.socialstatus.repository.AttachmentInfoRepository;
import gov.moe.employee.socialstatus.service.dto.AttachmentInfoDTO;
import gov.moe.employee.socialstatus.service.mapper.AttachmentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AttachmentInfo.
 */
@Service
@Transactional
public class AttachmentInfoServiceImpl implements AttachmentInfoService {

    private final Logger log = LoggerFactory.getLogger(AttachmentInfoServiceImpl.class);

    private final AttachmentInfoRepository attachmentInfoRepository;

    private final AttachmentInfoMapper attachmentInfoMapper;

    public AttachmentInfoServiceImpl(AttachmentInfoRepository attachmentInfoRepository, AttachmentInfoMapper attachmentInfoMapper) {
        this.attachmentInfoRepository = attachmentInfoRepository;
        this.attachmentInfoMapper = attachmentInfoMapper;
    }

    /**
     * Save a attachmentInfo.
     *
     * @param attachmentInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AttachmentInfoDTO save(AttachmentInfoDTO attachmentInfoDTO) {
        log.debug("Request to save AttachmentInfo : {}", attachmentInfoDTO);
        AttachmentInfo attachmentInfo = attachmentInfoMapper.toEntity(attachmentInfoDTO);
        attachmentInfo = attachmentInfoRepository.save(attachmentInfo);
        return attachmentInfoMapper.toDto(attachmentInfo);
    }

    /**
     * Get all the attachmentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AttachmentInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AttachmentInfos");
        return attachmentInfoRepository.findAll(pageable)
            .map(attachmentInfoMapper::toDto);
    }


    /**
     * Get one attachmentInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttachmentInfoDTO> findOne(Long id) {
        log.debug("Request to get AttachmentInfo : {}", id);
        return attachmentInfoRepository.findById(id)
            .map(attachmentInfoMapper::toDto);
    }

    /**
     * Delete the attachmentInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttachmentInfo : {}", id);        attachmentInfoRepository.deleteById(id);
    }
}
