package gov.moe.employee.socialstatus.web.rest;

import gov.moe.employee.socialstatus.EmployeesocialstatusApp;

import gov.moe.employee.socialstatus.domain.DependentInfo;
import gov.moe.employee.socialstatus.repository.DependentInfoRepository;
import gov.moe.employee.socialstatus.service.DependentInfoService;
import gov.moe.employee.socialstatus.service.dto.DependentInfoDTO;
import gov.moe.employee.socialstatus.service.mapper.DependentInfoMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static gov.moe.employee.socialstatus.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DependentInfoResource REST controller.
 *
 * @see DependentInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeesocialstatusApp.class)
public class DependentInfoResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MARRIAGE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MARRIAGE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_WORKING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WORKING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_HIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_HOUSE_ALLOWANCE_PROVIDED = "AAAAAAAAAA";
    private static final String UPDATED_HOUSE_ALLOWANCE_PROVIDED = "BBBBBBBBBB";

    private static final String DEFAULT_GOVERNMENT_HOUSING = "AAAAAAAAAA";
    private static final String UPDATED_GOVERNMENT_HOUSING = "BBBBBBBBBB";

    @Autowired
    private DependentInfoRepository dependentInfoRepository;

    @Autowired
    private DependentInfoMapper dependentInfoMapper;

    @Autowired
    private DependentInfoService dependentInfoService;

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

    private MockMvc restDependentInfoMockMvc;

    private DependentInfo dependentInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DependentInfoResource dependentInfoResource = new DependentInfoResource(dependentInfoService);
        this.restDependentInfoMockMvc = MockMvcBuilders.standaloneSetup(dependentInfoResource)
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
    public static DependentInfo createEntity(EntityManager em) {
        DependentInfo dependentInfo = new DependentInfo()
            .name(DEFAULT_NAME)
            .marriageDate(DEFAULT_MARRIAGE_DATE)
            .workingStatus(DEFAULT_WORKING_STATUS)
            .employer(DEFAULT_EMPLOYER)
            .employerType(DEFAULT_EMPLOYER_TYPE)
            .hireDate(DEFAULT_HIRE_DATE)
            .houseAllowanceProvided(DEFAULT_HOUSE_ALLOWANCE_PROVIDED)
            .governmentHousing(DEFAULT_GOVERNMENT_HOUSING);
        return dependentInfo;
    }

    @Before
    public void initTest() {
        dependentInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDependentInfo() throws Exception {
        int databaseSizeBeforeCreate = dependentInfoRepository.findAll().size();

        // Create the DependentInfo
        DependentInfoDTO dependentInfoDTO = dependentInfoMapper.toDto(dependentInfo);
        restDependentInfoMockMvc.perform(post("/api/dependent-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the DependentInfo in the database
        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        DependentInfo testDependentInfo = dependentInfoList.get(dependentInfoList.size() - 1);
        assertThat(testDependentInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDependentInfo.getMarriageDate()).isEqualTo(DEFAULT_MARRIAGE_DATE);
        assertThat(testDependentInfo.getWorkingStatus()).isEqualTo(DEFAULT_WORKING_STATUS);
        assertThat(testDependentInfo.getEmployer()).isEqualTo(DEFAULT_EMPLOYER);
        assertThat(testDependentInfo.getEmployerType()).isEqualTo(DEFAULT_EMPLOYER_TYPE);
        assertThat(testDependentInfo.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testDependentInfo.getHouseAllowanceProvided()).isEqualTo(DEFAULT_HOUSE_ALLOWANCE_PROVIDED);
        assertThat(testDependentInfo.getGovernmentHousing()).isEqualTo(DEFAULT_GOVERNMENT_HOUSING);
    }

    @Test
    @Transactional
    public void createDependentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dependentInfoRepository.findAll().size();

        // Create the DependentInfo with an existing ID
        dependentInfo.setId(1L);
        DependentInfoDTO dependentInfoDTO = dependentInfoMapper.toDto(dependentInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDependentInfoMockMvc.perform(post("/api/dependent-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DependentInfo in the database
        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependentInfoRepository.findAll().size();
        // set the field null
        dependentInfo.setName(null);

        // Create the DependentInfo, which fails.
        DependentInfoDTO dependentInfoDTO = dependentInfoMapper.toDto(dependentInfo);

        restDependentInfoMockMvc.perform(post("/api/dependent-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDependentInfos() throws Exception {
        // Initialize the database
        dependentInfoRepository.saveAndFlush(dependentInfo);

        // Get all the dependentInfoList
        restDependentInfoMockMvc.perform(get("/api/dependent-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dependentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].marriageDate").value(hasItem(DEFAULT_MARRIAGE_DATE.toString())))
            .andExpect(jsonPath("$.[*].workingStatus").value(hasItem(DEFAULT_WORKING_STATUS.toString())))
            .andExpect(jsonPath("$.[*].employer").value(hasItem(DEFAULT_EMPLOYER.toString())))
            .andExpect(jsonPath("$.[*].employerType").value(hasItem(DEFAULT_EMPLOYER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].houseAllowanceProvided").value(hasItem(DEFAULT_HOUSE_ALLOWANCE_PROVIDED.toString())))
            .andExpect(jsonPath("$.[*].governmentHousing").value(hasItem(DEFAULT_GOVERNMENT_HOUSING.toString())));
    }
    
    @Test
    @Transactional
    public void getDependentInfo() throws Exception {
        // Initialize the database
        dependentInfoRepository.saveAndFlush(dependentInfo);

        // Get the dependentInfo
        restDependentInfoMockMvc.perform(get("/api/dependent-infos/{id}", dependentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dependentInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.marriageDate").value(DEFAULT_MARRIAGE_DATE.toString()))
            .andExpect(jsonPath("$.workingStatus").value(DEFAULT_WORKING_STATUS.toString()))
            .andExpect(jsonPath("$.employer").value(DEFAULT_EMPLOYER.toString()))
            .andExpect(jsonPath("$.employerType").value(DEFAULT_EMPLOYER_TYPE.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.houseAllowanceProvided").value(DEFAULT_HOUSE_ALLOWANCE_PROVIDED.toString()))
            .andExpect(jsonPath("$.governmentHousing").value(DEFAULT_GOVERNMENT_HOUSING.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDependentInfo() throws Exception {
        // Get the dependentInfo
        restDependentInfoMockMvc.perform(get("/api/dependent-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDependentInfo() throws Exception {
        // Initialize the database
        dependentInfoRepository.saveAndFlush(dependentInfo);

        int databaseSizeBeforeUpdate = dependentInfoRepository.findAll().size();

        // Update the dependentInfo
        DependentInfo updatedDependentInfo = dependentInfoRepository.findById(dependentInfo.getId()).get();
        // Disconnect from session so that the updates on updatedDependentInfo are not directly saved in db
        em.detach(updatedDependentInfo);
        updatedDependentInfo
            .name(UPDATED_NAME)
            .marriageDate(UPDATED_MARRIAGE_DATE)
            .workingStatus(UPDATED_WORKING_STATUS)
            .employer(UPDATED_EMPLOYER)
            .employerType(UPDATED_EMPLOYER_TYPE)
            .hireDate(UPDATED_HIRE_DATE)
            .houseAllowanceProvided(UPDATED_HOUSE_ALLOWANCE_PROVIDED)
            .governmentHousing(UPDATED_GOVERNMENT_HOUSING);
        DependentInfoDTO dependentInfoDTO = dependentInfoMapper.toDto(updatedDependentInfo);

        restDependentInfoMockMvc.perform(put("/api/dependent-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependentInfoDTO)))
            .andExpect(status().isOk());

        // Validate the DependentInfo in the database
        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeUpdate);
        DependentInfo testDependentInfo = dependentInfoList.get(dependentInfoList.size() - 1);
        assertThat(testDependentInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDependentInfo.getMarriageDate()).isEqualTo(UPDATED_MARRIAGE_DATE);
        assertThat(testDependentInfo.getWorkingStatus()).isEqualTo(UPDATED_WORKING_STATUS);
        assertThat(testDependentInfo.getEmployer()).isEqualTo(UPDATED_EMPLOYER);
        assertThat(testDependentInfo.getEmployerType()).isEqualTo(UPDATED_EMPLOYER_TYPE);
        assertThat(testDependentInfo.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testDependentInfo.getHouseAllowanceProvided()).isEqualTo(UPDATED_HOUSE_ALLOWANCE_PROVIDED);
        assertThat(testDependentInfo.getGovernmentHousing()).isEqualTo(UPDATED_GOVERNMENT_HOUSING);
    }

    @Test
    @Transactional
    public void updateNonExistingDependentInfo() throws Exception {
        int databaseSizeBeforeUpdate = dependentInfoRepository.findAll().size();

        // Create the DependentInfo
        DependentInfoDTO dependentInfoDTO = dependentInfoMapper.toDto(dependentInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDependentInfoMockMvc.perform(put("/api/dependent-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DependentInfo in the database
        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDependentInfo() throws Exception {
        // Initialize the database
        dependentInfoRepository.saveAndFlush(dependentInfo);

        int databaseSizeBeforeDelete = dependentInfoRepository.findAll().size();

        // Delete the dependentInfo
        restDependentInfoMockMvc.perform(delete("/api/dependent-infos/{id}", dependentInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DependentInfo> dependentInfoList = dependentInfoRepository.findAll();
        assertThat(dependentInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DependentInfo.class);
        DependentInfo dependentInfo1 = new DependentInfo();
        dependentInfo1.setId(1L);
        DependentInfo dependentInfo2 = new DependentInfo();
        dependentInfo2.setId(dependentInfo1.getId());
        assertThat(dependentInfo1).isEqualTo(dependentInfo2);
        dependentInfo2.setId(2L);
        assertThat(dependentInfo1).isNotEqualTo(dependentInfo2);
        dependentInfo1.setId(null);
        assertThat(dependentInfo1).isNotEqualTo(dependentInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DependentInfoDTO.class);
        DependentInfoDTO dependentInfoDTO1 = new DependentInfoDTO();
        dependentInfoDTO1.setId(1L);
        DependentInfoDTO dependentInfoDTO2 = new DependentInfoDTO();
        assertThat(dependentInfoDTO1).isNotEqualTo(dependentInfoDTO2);
        dependentInfoDTO2.setId(dependentInfoDTO1.getId());
        assertThat(dependentInfoDTO1).isEqualTo(dependentInfoDTO2);
        dependentInfoDTO2.setId(2L);
        assertThat(dependentInfoDTO1).isNotEqualTo(dependentInfoDTO2);
        dependentInfoDTO1.setId(null);
        assertThat(dependentInfoDTO1).isNotEqualTo(dependentInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dependentInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dependentInfoMapper.fromId(null)).isNull();
    }
}
