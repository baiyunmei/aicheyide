package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.Department;
import com.duma.repository.DepartmentRepository;
import com.duma.service.DepartmentService;
import com.duma.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DepartmentResource REST controller.
 *
 * @see DepartmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DepartmentResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEPARTMENT_NUMBER = 1;
    private static final Integer UPDATED_DEPARTMENT_NUMBER = 2;

    private static final Long DEFAULT_SUPERIOR_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_SUPERIOR_DEPARTMENT_ID = 2L;

    private static final String DEFAULT_DEPARTMENT_HEADS = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_HEADS = "BBBBBBBBBB";

    private static final Long DEFAULT_DEPARTMENT_HEADS_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_HEADS_ID = 2L;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepartmentMockMvc;

    private Department department;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentResource departmentResource = new DepartmentResource(departmentService);
        this.restDepartmentMockMvc = MockMvcBuilders.standaloneSetup(departmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Department createEntity(EntityManager em) {
        Department department = new Department()
                .companyId(DEFAULT_COMPANY_ID)
                .department(DEFAULT_DEPARTMENT)
                .departmentNumber(DEFAULT_DEPARTMENT_NUMBER)
                .superiorDepartmentId(DEFAULT_SUPERIOR_DEPARTMENT_ID)
                .departmentHeads(DEFAULT_DEPARTMENT_HEADS)
                .departmentHeadsId(DEFAULT_DEPARTMENT_HEADS_ID);
        return department;
    }

    @Before
    public void initTest() {
        department = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartment() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department

        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(department)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate + 1);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testDepartment.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testDepartment.getDepartmentNumber()).isEqualTo(DEFAULT_DEPARTMENT_NUMBER);
        assertThat(testDepartment.getSuperiorDepartmentId()).isEqualTo(DEFAULT_SUPERIOR_DEPARTMENT_ID);
        assertThat(testDepartment.getDepartmentHeads()).isEqualTo(DEFAULT_DEPARTMENT_HEADS);
        assertThat(testDepartment.getDepartmentHeadsId()).isEqualTo(DEFAULT_DEPARTMENT_HEADS_ID);
    }

    @Test
    @Transactional
    public void createDepartmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department with an existing ID
        Department existingDepartment = new Department();
        existingDepartment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDepartment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDepartments() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList
        restDepartmentMockMvc.perform(get("/api/departments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT.toString())))
            .andExpect(jsonPath("$.[*].departmentNumber").value(hasItem(DEFAULT_DEPARTMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].superiorDepartmentId").value(hasItem(DEFAULT_SUPERIOR_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentHeads").value(hasItem(DEFAULT_DEPARTMENT_HEADS.toString())))
            .andExpect(jsonPath("$.[*].departmentHeadsId").value(hasItem(DEFAULT_DEPARTMENT_HEADS_ID.intValue())));
    }

    @Test
    @Transactional
    public void getDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", department.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(department.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT.toString()))
            .andExpect(jsonPath("$.departmentNumber").value(DEFAULT_DEPARTMENT_NUMBER))
            .andExpect(jsonPath("$.superiorDepartmentId").value(DEFAULT_SUPERIOR_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.departmentHeads").value(DEFAULT_DEPARTMENT_HEADS.toString()))
            .andExpect(jsonPath("$.departmentHeadsId").value(DEFAULT_DEPARTMENT_HEADS_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDepartment() throws Exception {
        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartment() throws Exception {
        // Initialize the database
        departmentService.save(department);

        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department
        Department updatedDepartment = departmentRepository.findOne(department.getId());
        updatedDepartment
                .companyId(UPDATED_COMPANY_ID)
                .department(UPDATED_DEPARTMENT)
                .departmentNumber(UPDATED_DEPARTMENT_NUMBER)
                .superiorDepartmentId(UPDATED_SUPERIOR_DEPARTMENT_ID)
                .departmentHeads(UPDATED_DEPARTMENT_HEADS)
                .departmentHeadsId(UPDATED_DEPARTMENT_HEADS_ID);

        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDepartment)))
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testDepartment.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testDepartment.getDepartmentNumber()).isEqualTo(UPDATED_DEPARTMENT_NUMBER);
        assertThat(testDepartment.getSuperiorDepartmentId()).isEqualTo(UPDATED_SUPERIOR_DEPARTMENT_ID);
        assertThat(testDepartment.getDepartmentHeads()).isEqualTo(UPDATED_DEPARTMENT_HEADS);
        assertThat(testDepartment.getDepartmentHeadsId()).isEqualTo(UPDATED_DEPARTMENT_HEADS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Create the Department

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(department)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDepartment() throws Exception {
        // Initialize the database
        departmentService.save(department);

        int databaseSizeBeforeDelete = departmentRepository.findAll().size();

        // Get the department
        restDepartmentMockMvc.perform(delete("/api/departments/{id}", department.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
    }
}
