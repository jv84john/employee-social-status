package gov.moe.employee.socialstatus.service.impl;

import gov.moe.employee.socialstatus.service.DependentInfoService;
import gov.moe.employee.socialstatus.domain.DependentInfo;
import gov.moe.employee.socialstatus.repository.DependentInfoRepository;
import gov.moe.employee.socialstatus.service.dto.DependentInfoDTO;
import gov.moe.employee.socialstatus.service.mapper.DependentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DependentInfo.
 */
@Service
@Transactional
public class DependentInfoServiceImpl implements DependentInfoService {

    private final Logger log = LoggerFactory.getLogger(DependentInfoServiceImpl.class);

    private final DependentInfoRepository dependentInfoRepository;

    private final DependentInfoMapper dependentInfoMapper;

    public DependentInfoServiceImpl(DependentInfoRepository dependentInfoRepository, DependentInfoMapper dependentInfoMapper) {
        this.dependentInfoRepository = dependentInfoRepository;
        this.dependentInfoMapper = dependentInfoMapper;
    }

    /**
     * Save a dependentInfo.
     *
     * @param dependentInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DependentInfoDTO save(DependentInfoDTO dependentInfoDTO) {
        log.debug("Request to save DependentInfo : {}", dependentInfoDTO);
        DependentInfo dependentInfo = dependentInfoMapper.toEntity(dependentInfoDTO);
        dependentInfo = dependentInfoRepository.save(dependentInfo);
        return dependentInfoMapper.toDto(dependentInfo);
    }

    /**
     * Get all the dependentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DependentInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DependentInfos");
        return dependentInfoRepository.findAll(pageable)
            .map(dependentInfoMapper::toDto);
    }


    /**
     * Get one dependentInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DependentInfoDTO> findOne(Long id) {
        log.debug("Request to get DependentInfo : {}", id);
        return dependentInfoRepository.findById(id)
            .map(dependentInfoMapper::toDto);
    }

    /**
     * Delete the dependentInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DependentInfo : {}", id);        dependentInfoRepository.deleteById(id);
    }
}
