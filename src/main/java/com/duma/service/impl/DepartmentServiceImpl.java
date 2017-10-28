package com.duma.service.impl;

import com.duma.service.DepartmentService;
import com.duma.domain.Department;
import com.duma.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Department.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Save a department.
     *
     * @param department the entity to save
     * @return the persisted entity
     */
    @Override
    public Department save(Department department) {
        log.debug("Request to save Department : {}", department);
        Department result = departmentRepository.save(department);
        return result;
    }

    /**
     *  Get all the departments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Department> findAll(Pageable pageable) {
        log.debug("Request to get all Departments");
        Page<Department> result = departmentRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one department by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Department findOne(Long id) {
        log.debug("Request to get Department : {}", id);
        Department department = departmentRepository.findOne(id);
        return department;
    }

    /**
     *  Delete the  department by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        departmentRepository.delete(id);
    }
}
