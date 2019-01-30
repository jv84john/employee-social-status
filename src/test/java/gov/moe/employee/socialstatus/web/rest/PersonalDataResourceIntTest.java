package gov.moe.employee.socialstatus.web.rest;

import gov.moe.employee.socialstatus.EmployeesocialstatusApp;

import gov.moe.employee.socialstatus.domain.PersonalData;
import gov.moe.employee.socialstatus.repository.PersonalDataRepository;
import gov.moe.employee.socialstatus.service.PersonalDataService;
import gov.moe.employee.socialstatus.service.dto.PersonalDataDTO;
import gov.moe.employee.socialstatus.service.mapper.PersonalDataMapper;
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
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static gov.moe.employee.socialstatus.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PersonalDataResource REST controller.
 *
 * @see PersonalDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeesocialstatusApp.class)
public class PersonalDataResourceIntTest {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORACLE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORACLE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_FUNCTION = "AAAAAAAAAA";
    private static final String UPDATED_JOB_FUNCTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPOINTMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPOINTMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SCHOOL_ADMINISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_ADMINISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_SOCIAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private PersonalDataMapper personalDataMapper;

    @Autowired
    private PersonalDataService personalDataService;

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

    private MockMvc restPersonalDataMockMvc;

    private PersonalData personalData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonalDataResource personalDataResource = new PersonalDataResource(personalDataService);
        this.restPersonalDataMockMvc = MockMvcBuilders.standaloneSetup(personalDataResource)
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
    public static PersonalData createEntity(EntityManager em) {
        PersonalData personalData = new PersonalData()
            .fullName(DEFAULT_FULL_NAME)
            .oracleNumber(DEFAULT_ORACLE_NUMBER)
            .jobFunction(DEFAULT_JOB_FUNCTION)
            .appointmentDate(DEFAULT_APPOINTMENT_DATE)
            .schoolAdministration(DEFAULT_SCHOOL_ADMINISTRATION)
            .gender(DEFAULT_GENDER)
            .sector(DEFAULT_SECTOR)
            .socialStatus(DEFAULT_SOCIAL_STATUS)
            .nationality(DEFAULT_NATIONALITY)
            .contactNumber(DEFAULT_CONTACT_NUMBER)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedAt(DEFAULT_MODIFIED_AT)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return personalData;
    }

    @Before
    public void initTest() {
        personalData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonalData() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();

        // Create the PersonalData
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);
        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testPersonalData.getOracleNumber()).isEqualTo(DEFAULT_ORACLE_NUMBER);
        assertThat(testPersonalData.getJobFunction()).isEqualTo(DEFAULT_JOB_FUNCTION);
        assertThat(testPersonalData.getAppointmentDate()).isEqualTo(DEFAULT_APPOINTMENT_DATE);
        assertThat(testPersonalData.getSchoolAdministration()).isEqualTo(DEFAULT_SCHOOL_ADMINISTRATION);
        assertThat(testPersonalData.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPersonalData.getSector()).isEqualTo(DEFAULT_SECTOR);
        assertThat(testPersonalData.getSocialStatus()).isEqualTo(DEFAULT_SOCIAL_STATUS);
        assertThat(testPersonalData.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testPersonalData.getContactNumber()).isEqualTo(DEFAULT_CONTACT_NUMBER);
        assertThat(testPersonalData.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPersonalData.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPersonalData.getModifiedAt()).isEqualTo(DEFAULT_MODIFIED_AT);
        assertThat(testPersonalData.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createPersonalDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();

        // Create the PersonalData with an existing ID
        personalData.setId(1L);
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setFullName(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOracleNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setOracleNumber(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJobFunctionIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setJobFunction(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get all the personalDataList
        restPersonalDataMockMvc.perform(get("/api/personal-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalData.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].oracleNumber").value(hasItem(DEFAULT_ORACLE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].jobFunction").value(hasItem(DEFAULT_JOB_FUNCTION.toString())))
            .andExpect(jsonPath("$.[*].appointmentDate").value(hasItem(DEFAULT_APPOINTMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].schoolAdministration").value(hasItem(DEFAULT_SCHOOL_ADMINISTRATION.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].sector").value(hasItem(DEFAULT_SECTOR.toString())))
            .andExpect(jsonPath("$.[*].socialStatus").value(hasItem(DEFAULT_SOCIAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY.toString())))
            .andExpect(jsonPath("$.[*].contactNumber").value(hasItem(DEFAULT_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.toString())));
    }
    
    @Test
    @Transactional
    public void getPersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get the personalData
        restPersonalDataMockMvc.perform(get("/api/personal-data/{id}", personalData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personalData.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.oracleNumber").value(DEFAULT_ORACLE_NUMBER.toString()))
            .andExpect(jsonPath("$.jobFunction").value(DEFAULT_JOB_FUNCTION.toString()))
            .andExpect(jsonPath("$.appointmentDate").value(DEFAULT_APPOINTMENT_DATE.toString()))
            .andExpect(jsonPath("$.schoolAdministration").value(DEFAULT_SCHOOL_ADMINISTRATION.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.sector").value(DEFAULT_SECTOR.toString()))
            .andExpect(jsonPath("$.socialStatus").value(DEFAULT_SOCIAL_STATUS.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.contactNumber").value(DEFAULT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.modifiedAt").value(DEFAULT_MODIFIED_AT.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonalData() throws Exception {
        // Get the personalData
        restPersonalDataMockMvc.perform(get("/api/personal-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // Update the personalData
        PersonalData updatedPersonalData = personalDataRepository.findById(personalData.getId()).get();
        // Disconnect from session so that the updates on updatedPersonalData are not directly saved in db
        em.detach(updatedPersonalData);
        updatedPersonalData
            .fullName(UPDATED_FULL_NAME)
            .oracleNumber(UPDATED_ORACLE_NUMBER)
            .jobFunction(UPDATED_JOB_FUNCTION)
            .appointmentDate(UPDATED_APPOINTMENT_DATE)
            .schoolAdministration(UPDATED_SCHOOL_ADMINISTRATION)
            .gender(UPDATED_GENDER)
            .sector(UPDATED_SECTOR)
            .socialStatus(UPDATED_SOCIAL_STATUS)
            .nationality(UPDATED_NATIONALITY)
            .contactNumber(UPDATED_CONTACT_NUMBER)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .modifiedBy(UPDATED_MODIFIED_BY);
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(updatedPersonalData);

        restPersonalDataMockMvc.perform(put("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isOk());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testPersonalData.getOracleNumber()).isEqualTo(UPDATED_ORACLE_NUMBER);
        assertThat(testPersonalData.getJobFunction()).isEqualTo(UPDATED_JOB_FUNCTION);
        assertThat(testPersonalData.getAppointmentDate()).isEqualTo(UPDATED_APPOINTMENT_DATE);
        assertThat(testPersonalData.getSchoolAdministration()).isEqualTo(UPDATED_SCHOOL_ADMINISTRATION);
        assertThat(testPersonalData.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPersonalData.getSector()).isEqualTo(UPDATED_SECTOR);
        assertThat(testPersonalData.getSocialStatus()).isEqualTo(UPDATED_SOCIAL_STATUS);
        assertThat(testPersonalData.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testPersonalData.getContactNumber()).isEqualTo(UPDATED_CONTACT_NUMBER);
        assertThat(testPersonalData.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPersonalData.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPersonalData.getModifiedAt()).isEqualTo(UPDATED_MODIFIED_AT);
        assertThat(testPersonalData.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonalData() throws Exception {
        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // Create the PersonalData
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalDataMockMvc.perform(put("/api/personal-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeDelete = personalDataRepository.findAll().size();

        // Delete the personalData
        restPersonalDataMockMvc.perform(delete("/api/personal-data/{id}", personalData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalData.class);
        PersonalData personalData1 = new PersonalData();
        personalData1.setId(1L);
        PersonalData personalData2 = new PersonalData();
        personalData2.setId(personalData1.getId());
        assertThat(personalData1).isEqualTo(personalData2);
        personalData2.setId(2L);
        assertThat(personalData1).isNotEqualTo(personalData2);
        personalData1.setId(null);
        assertThat(personalData1).isNotEqualTo(personalData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalDataDTO.class);
        PersonalDataDTO personalDataDTO1 = new PersonalDataDTO();
        personalDataDTO1.setId(1L);
        PersonalDataDTO personalDataDTO2 = new PersonalDataDTO();
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
        personalDataDTO2.setId(personalDataDTO1.getId());
        assertThat(personalDataDTO1).isEqualTo(personalDataDTO2);
        personalDataDTO2.setId(2L);
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
        personalDataDTO1.setId(null);
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(personalDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(personalDataMapper.fromId(null)).isNull();
    }
}
