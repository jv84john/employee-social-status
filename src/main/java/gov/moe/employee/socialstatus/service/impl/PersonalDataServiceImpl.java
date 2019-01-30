package gov.moe.employee.socialstatus.service.impl;

import gov.moe.employee.socialstatus.service.PersonalDataService;
import gov.moe.employee.socialstatus.domain.PersonalData;
import gov.moe.employee.socialstatus.repository.PersonalDataRepository;
import gov.moe.employee.socialstatus.service.dto.PersonalDataDTO;
import gov.moe.employee.socialstatus.service.mapper.PersonalDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PersonalData.
 */
@Service
@Transactional
public class PersonalDataServiceImpl implements PersonalDataService {

    private final Logger log = LoggerFactory.getLogger(PersonalDataServiceImpl.class);

    private final PersonalDataRepository personalDataRepository;

    private final PersonalDataMapper personalDataMapper;

    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, PersonalDataMapper personalDataMapper) {
        this.personalDataRepository = personalDataRepository;
        this.personalDataMapper = personalDataMapper;
    }

    /**
     * Save a personalData.
     *
     * @param personalDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonalDataDTO save(PersonalDataDTO personalDataDTO) {
        log.debug("Request to save PersonalData : {}", personalDataDTO);
        PersonalData personalData = personalDataMapper.toEntity(personalDataDTO);
        personalData = personalDataRepository.save(personalData);
        return personalDataMapper.toDto(personalData);
    }

    /**
     * Get all the personalData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonalDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PersonalData");
        return personalDataRepository.findAll(pageable)
            .map(personalDataMapper::toDto);
    }


    /**
     * Get one personalData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalDataDTO> findOne(Long id) {
        log.debug("Request to get PersonalData : {}", id);
        return personalDataRepository.findById(id)
            .map(personalDataMapper::toDto);
    }

    /**
     * Delete the personalData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonalData : {}", id);        personalDataRepository.deleteById(id);
    }
}
