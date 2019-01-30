package gov.moe.employee.socialstatus.service;

import gov.moe.employee.socialstatus.service.dto.DependentInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DependentInfo.
 */
public interface DependentInfoService {

    /**
     * Save a dependentInfo.
     *
     * @param dependentInfoDTO the entity to save
     * @return the persisted entity
     */
    DependentInfoDTO save(DependentInfoDTO dependentInfoDTO);

    /**
     * Get all the dependentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DependentInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dependentInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DependentInfoDTO> findOne(Long id);

    /**
     * Delete the "id" dependentInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
