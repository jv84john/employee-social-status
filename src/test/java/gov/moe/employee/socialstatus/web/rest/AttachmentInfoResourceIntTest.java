package gov.moe.employee.socialstatus.web.rest;

import gov.moe.employee.socialstatus.EmployeesocialstatusApp;

import gov.moe.employee.socialstatus.domain.AttachmentInfo;
import gov.moe.employee.socialstatus.repository.AttachmentInfoRepository;
import gov.moe.employee.socialstatus.service.AttachmentInfoService;
import gov.moe.employee.socialstatus.service.dto.AttachmentInfoDTO;
import gov.moe.employee.socialstatus.service.mapper.AttachmentInfoMapper;
import gov.moe.employee.socialstatus.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static gov.moe.employee.socialstatus.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AttachmentInfoResource REST controller.
 *
 * @see AttachmentInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeesocialstatusApp.class)
public class AttachmentInfoResourceIntTest {

    private static final String DEFAULT_MARRIAGE_CERT = "AAAAAAAAAA";
    private static final String UPDATED_MARRIAGE_CERT = "BBBBBBBBBB";

    private static final String DEFAULT_SALARY_CERT = "AAAAAAAAAA";
    private static final String UPDATED_SALARY_CERT = "BBBBBBBBBB";

    private static final String DEFAULT_DEATH_CERT = "AAAAAAAAAA";
    private static final String UPDATED_DEATH_CERT = "BBBBBBBBBB";

    private static final String DEFAULT_DIVORCE_DOC = "AAAAAAAAAA";
    private static final String UPDATED_DIVORCE_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_SYNOPSIS = "AAAAAAAAAA";
    private static final String UPDATED_SYNOPSIS = "BBBBBBBBBB";

    @Autowired
    private AttachmentInfoRepository attachmentInfoRepository;

    @Autowired
    private AttachmentInfoMapper attachmentInfoMapper;

    @Autowired
    private AttachmentInfoService attachmentInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAttachmentInfoMockMvc;

    private AttachmentInfo attachmentInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttachmentInfoResource attachmentInfoResource = new AttachmentInfoResource(attachmentInfoService);
        this.restAttachmentInfoMockMvc = MockMvcBuilders.standaloneSetup(attachmentInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttachmentInfo createEntity(EntityManager em) {
        AttachmentInfo attachmentInfo = new AttachmentInfo()
            .marriageCert(DEFAULT_MARRIAGE_CERT)
            .salaryCert(DEFAULT_SALARY_CERT)
            .deathCert(DEFAULT_DEATH_CERT)
            .divorceDoc(DEFAULT_DIVORCE_DOC)
            .synopsis(DEFAULT_SYNOPSIS);
        return attachmentInfo;
    }

    @Before
    public void initTest() {
        attachmentInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttachmentInfo() throws Exception {
        int databaseSizeBeforeCreate = attachmentInfoRepository.findAll().size();

        // Create the AttachmentInfo
        AttachmentInfoDTO attachmentInfoDTO = attachmentInfoMapper.toDto(attachmentInfo);
        restAttachmentInfoMockMvc.perform(post("/api/attachment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachmentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the AttachmentInfo in the database
        List<AttachmentInfo> attachmentInfoList = attachmentInfoRepository.findAll();
        assertThat(attachmentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        AttachmentInfo testAttachmentInfo = attachmentInfoList.get(attachmentInfoList.size() - 1);
        assertThat(testAttachmentInfo.getMarriageCert()).isEqualTo(DEFAULT_MARRIAGE_CERT);
        assertThat(testAttachmentInfo.getSalaryCert()).isEqualTo(DEFAULT_SALARY_CERT);
        assertThat(testAttachmentInfo.getDeathCert()).isEqualTo(DEFAULT_DEATH_CERT);
        assertThat(testAttachmentInfo.getDivorceDoc()).isEqualTo(DEFAULT_DIVORCE_DOC);
        assertThat(testAttachmentInfo.getSynopsis()).isEqualTo(DEFAULT_SYNOPSIS);
    }

    @Test
    @Transactional
    public void createAttachmentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attachmentInfoRepository.findAll().size();

        // Create the AttachmentInfo with an existing ID
        attachmentInfo.setId(1L);
        AttachmentInfoDTO attachmentInfoDTO = attachmentInfoMapper.toDto(attachmentInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttachmentInfoMockMvc.perform(post("/api/attachment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachmentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttachmentInfo in the database
        List<AttachmentInfo> attachmentInfoList = attachmentInfoRepository.findAll();
        assertThat(attachmentInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttachmentInfos() throws Exception {
        // Initialize the database
        attachmentInfoRepository.saveAndFlush(attachmentInfo);

        // Get all the attachmentInfoList
        restAttachmentInfoMockMvc.perform(get("/api/attachment-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attachmentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].marriageCert").value(hasItem(DEFAULT_MARRIAGE_CERT.toString())))
            .andExpect(jsonPath("$.[*].salaryCert").value(hasItem(DEFAULT_SALARY_CERT.toString())))
            .andExpect(jsonPath("$.[*].deathCert").value(hasItem(DEFAULT_DEATH_CERT.toString())))
            .andExpect(jsonPath("$.[*].divorceDoc").value(hasItem(DEFAULT_DIVORCE_DOC.toString())))
            .andExpect(jsonPath("$.[*].synopsis").value(hasItem(DEFAULT_SYNOPSIS.toString())));
    }
    
    @Test
    @Transactional
    public void getAttachmentInfo() throws Exception {
        // Initialize the database
        attachmentInfoRepository.saveAndFlush(attachmentInfo);

        // Get the attachmentInfo
        restAttachmentInfoMockMvc.perform(get("/api/attachment-infos/{id}", attachmentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attachmentInfo.getId().intValue()))
            .andExpect(jsonPath("$.marriageCert").value(DEFAULT_MARRIAGE_CERT.toString()))
            .andExpect(jsonPath("$.salaryCert").value(DEFAULT_SALARY_CERT.toString()))
            .andExpect(jsonPath("$.deathCert").value(DEFAULT_DEATH_CERT.toString()))
            .andExpect(jsonPath("$.divorceDoc").value(DEFAULT_DIVORCE_DOC.toString()))
            .andExpect(jsonPath("$.synopsis").value(DEFAULT_SYNOPSIS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttachmentInfo() throws Exception {
        // Get the attachmentInfo
        restAttachmentInfoMockMvc.perform(get("/api/attachment-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttachmentInfo() throws Exception {
        // Initialize the database
        attachmentInfoRepository.saveAndFlush(attachmentInfo);

        int databaseSizeBeforeUpdate = attachmentInfoRepository.findAll().size();

        // Update the attachmentInfo
        AttachmentInfo updatedAttachmentInfo = attachmentInfoRepository.findById(attachmentInfo.getId()).get();
        // Disconnect from session so that the updates on updatedAttachmentInfo are not directly saved in db
        em.detach(updatedAttachmentInfo);
        updatedAttachmentInfo
            .marriageCert(UPDATED_MARRIAGE_CERT)
            .salaryCert(UPDATED_SALARY_CERT)
            .deathCert(UPDATED_DEATH_CERT)
            .divorceDoc(UPDATED_DIVORCE_DOC)
            .synopsis(UPDATED_SYNOPSIS);
        AttachmentInfoDTO attachmentInfoDTO = attachmentInfoMapper.toDto(updatedAttachmentInfo);

        restAttachmentInfoMockMvc.perform(put("/api/attachment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachmentInfoDTO)))
            .andExpect(status().isOk());

        // Validate the AttachmentInfo in the database
        List<AttachmentInfo> attachmentInfoList = attachmentInfoRepository.findAll();
        assertThat(attachmentInfoList).hasSize(databaseSizeBeforeUpdate);
        AttachmentInfo testAttachmentInfo = attachmentInfoList.get(attachmentInfoList.size() - 1);
        assertThat(testAttachmentInfo.getMarriageCert()).isEqualTo(UPDATED_MARRIAGE_CERT);
        assertThat(testAttachmentInfo.getSalaryCert()).isEqualTo(UPDATED_SALARY_CERT);
        assertThat(testAttachmentInfo.getDeathCert()).isEqualTo(UPDATED_DEATH_CERT);
        assertThat(testAttachmentInfo.getDivorceDoc()).isEqualTo(UPDATED_DIVORCE_DOC);
        assertThat(testAttachmentInfo.getSynopsis()).isEqualTo(UPDATED_SYNOPSIS);
    }

    @Test
    @Transactional
    public void updateNonExistingAttachmentInfo() throws Exception {
        int databaseSizeBeforeUpdate = attachmentInfoRepository.findAll().size();

        // Create the AttachmentInfo
        AttachmentInfoDTO attachmentInfoDTO = attachmentInfoMapper.toDto(attachmentInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttachmentInfoMockMvc.perform(put("/api/attachment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachmentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttachmentInfo in the database
        List<AttachmentInfo> attachmentInfoList = attachmentInfoRepository.findAll();
        assertThat(attachmentInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttachmentInfo() throws Exception {
        // Initialize the database
        attachmentInfoRepository.saveAndFlush(attachmentInfo);

        int databaseSizeBeforeDelete = attachmentInfoRepository.findAll().size();

        // Delete the attachmentInfo
        restAttachmentInfoMockMvc.perform(delete("/api/attachment-infos/{id}", attachmentInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttachmentInfo> attachmentInfoList = attachmentInfoRepository.findAll();
        assertThat(attachmentInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttachmentInfo.class);
        AttachmentInfo attachmentInfo1 = new AttachmentInfo();
        attachmentInfo1.setId(1L);
        AttachmentInfo attachmentInfo2 = new AttachmentInfo();
        attachmentInfo2.setId(attachmentInfo1.getId());
        assertThat(attachmentInfo1).isEqualTo(attachmentInfo2);
        attachmentInfo2.setId(2L);
        assertThat(attachmentInfo1).isNotEqualTo(attachmentInfo2);
        attachmentInfo1.setId(null);
        assertThat(attachmentInfo1).isNotEqualTo(attachmentInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttachmentInfoDTO.class);
        AttachmentInfoDTO attachmentInfoDTO1 = new AttachmentInfoDTO();
        attachmentInfoDTO1.setId(1L);
        AttachmentInfoDTO attachmentInfoDTO2 = new AttachmentInfoDTO();
        assertThat(attachmentInfoDTO1).isNotEqualTo(attachmentInfoDTO2);
        attachmentInfoDTO2.setId(attachmentInfoDTO1.getId());
        assertThat(attachmentInfoDTO1).isEqualTo(attachmentInfoDTO2);
        attachmentInfoDTO2.setId(2L);
        assertThat(attachmentInfoDTO1).isNotEqualTo(attachmentInfoDTO2);
        attachmentInfoDTO1.setId(null);
        assertThat(attachmentInfoDTO1).isNotEqualTo(attachmentInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(attachmentInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(attachmentInfoMapper.fromId(null)).isNull();
    }
}
