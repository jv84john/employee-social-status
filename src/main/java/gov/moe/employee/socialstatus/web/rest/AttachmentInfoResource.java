package gov.moe.employee.socialstatus.web.rest;
import gov.moe.employee.socialstatus.service.AttachmentInfoService;
import gov.moe.employee.socialstatus.web.rest.errors.BadRequestAlertException;
import gov.moe.employee.socialstatus.web.rest.util.HeaderUtil;
import gov.moe.employee.socialstatus.web.rest.util.PaginationUtil;
import gov.moe.employee.socialstatus.service.dto.AttachmentInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AttachmentInfo.
 */
@RestController
@RequestMapping("/api")
public class AttachmentInfoResource {

    private final Logger log = LoggerFactory.getLogger(AttachmentInfoResource.class);

    private static final String ENTITY_NAME = "attachmentInfo";

    private final AttachmentInfoService attachmentInfoService;

    public AttachmentInfoResource(AttachmentInfoService attachmentInfoService) {
        this.attachmentInfoService = attachmentInfoService;
    }

    /**
     * POST  /attachment-infos : Create a new attachmentInfo.
     *
     * @param attachmentInfoDTO the attachmentInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attachmentInfoDTO, or with status 400 (Bad Request) if the attachmentInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attachment-infos")
    public ResponseEntity<AttachmentInfoDTO> createAttachmentInfo(@RequestBody AttachmentInfoDTO attachmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to save AttachmentInfo : {}", attachmentInfoDTO);
        if (attachmentInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new attachmentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttachmentInfoDTO result = attachmentInfoService.save(attachmentInfoDTO);
        return ResponseEntity.created(new URI("/api/attachment-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attachment-infos : Updates an existing attachmentInfo.
     *
     * @param attachmentInfoDTO the attachmentInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attachmentInfoDTO,
     * or with status 400 (Bad Request) if the attachmentInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the attachmentInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attachment-infos")
    public ResponseEntity<AttachmentInfoDTO> updateAttachmentInfo(@RequestBody AttachmentInfoDTO attachmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to update AttachmentInfo : {}", attachmentInfoDTO);
        if (attachmentInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttachmentInfoDTO result = attachmentInfoService.save(attachmentInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attachmentInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attachment-infos : get all the attachmentInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of attachmentInfos in body
     */
    @GetMapping("/attachment-infos")
    public ResponseEntity<List<AttachmentInfoDTO>> getAllAttachmentInfos(Pageable pageable) {
        log.debug("REST request to get a page of AttachmentInfos");
        Page<AttachmentInfoDTO> page = attachmentInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attachment-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /attachment-infos/:id : get the "id" attachmentInfo.
     *
     * @param id the id of the attachmentInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attachmentInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/attachment-infos/{id}")
    public ResponseEntity<AttachmentInfoDTO> getAttachmentInfo(@PathVariable Long id) {
        log.debug("REST request to get AttachmentInfo : {}", id);
        Optional<AttachmentInfoDTO> attachmentInfoDTO = attachmentInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attachmentInfoDTO);
    }

    /**
     * DELETE  /attachment-infos/:id : delete the "id" attachmentInfo.
     *
     * @param id the id of the attachmentInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attachment-infos/{id}")
    public ResponseEntity<Void> deleteAttachmentInfo(@PathVariable Long id) {
        log.debug("REST request to delete AttachmentInfo : {}", id);
        attachmentInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
