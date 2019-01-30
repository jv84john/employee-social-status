package gov.moe.employee.socialstatus.web.rest;
import gov.moe.employee.socialstatus.service.DependentInfoService;
import gov.moe.employee.socialstatus.web.rest.errors.BadRequestAlertException;
import gov.moe.employee.socialstatus.web.rest.util.HeaderUtil;
import gov.moe.employee.socialstatus.web.rest.util.PaginationUtil;
import gov.moe.employee.socialstatus.service.dto.DependentInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DependentInfo.
 */
@RestController
@RequestMapping("/api")
public class DependentInfoResource {

    private final Logger log = LoggerFactory.getLogger(DependentInfoResource.class);

    private static final String ENTITY_NAME = "dependentInfo";

    private final DependentInfoService dependentInfoService;

    public DependentInfoResource(DependentInfoService dependentInfoService) {
        this.dependentInfoService = dependentInfoService;
    }

    /**
     * POST  /dependent-infos : Create a new dependentInfo.
     *
     * @param dependentInfoDTO the dependentInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dependentInfoDTO, or with status 400 (Bad Request) if the dependentInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dependent-infos")
    public ResponseEntity<DependentInfoDTO> createDependentInfo(@Valid @RequestBody DependentInfoDTO dependentInfoDTO) throws URISyntaxException {
        log.debug("REST request to save DependentInfo : {}", dependentInfoDTO);
        if (dependentInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new dependentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DependentInfoDTO result = dependentInfoService.save(dependentInfoDTO);
        return ResponseEntity.created(new URI("/api/dependent-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dependent-infos : Updates an existing dependentInfo.
     *
     * @param dependentInfoDTO the dependentInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dependentInfoDTO,
     * or with status 400 (Bad Request) if the dependentInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the dependentInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dependent-infos")
    public ResponseEntity<DependentInfoDTO> updateDependentInfo(@Valid @RequestBody DependentInfoDTO dependentInfoDTO) throws URISyntaxException {
        log.debug("REST request to update DependentInfo : {}", dependentInfoDTO);
        if (dependentInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DependentInfoDTO result = dependentInfoService.save(dependentInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dependentInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dependent-infos : get all the dependentInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dependentInfos in body
     */
    @GetMapping("/dependent-infos")
    public ResponseEntity<List<DependentInfoDTO>> getAllDependentInfos(Pageable pageable) {
        log.debug("REST request to get a page of DependentInfos");
        Page<DependentInfoDTO> page = dependentInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dependent-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dependent-infos/:id : get the "id" dependentInfo.
     *
     * @param id the id of the dependentInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dependentInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/dependent-infos/{id}")
    public ResponseEntity<DependentInfoDTO> getDependentInfo(@PathVariable Long id) {
        log.debug("REST request to get DependentInfo : {}", id);
        Optional<DependentInfoDTO> dependentInfoDTO = dependentInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dependentInfoDTO);
    }

    /**
     * DELETE  /dependent-infos/:id : delete the "id" dependentInfo.
     *
     * @param id the id of the dependentInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dependent-infos/{id}")
    public ResponseEntity<Void> deleteDependentInfo(@PathVariable Long id) {
        log.debug("REST request to delete DependentInfo : {}", id);
        dependentInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
