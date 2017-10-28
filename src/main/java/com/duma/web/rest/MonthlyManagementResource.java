package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.MonthlyManagement;
import com.duma.service.MonthlyManagementService;
import com.duma.web.rest.util.HeaderUtil;
import com.duma.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
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
 * REST controller for managing MonthlyManagement.
 */
@RestController
@RequestMapping("/api")
public class MonthlyManagementResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyManagementResource.class);

    private static final String ENTITY_NAME = "monthlyManagement";
        
    private final MonthlyManagementService monthlyManagementService;

    public MonthlyManagementResource(MonthlyManagementService monthlyManagementService) {
        this.monthlyManagementService = monthlyManagementService;
    }

    /**
     * POST  /monthly-managements : Create a new monthlyManagement.
     *
     * @param monthlyManagement the monthlyManagement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monthlyManagement, or with status 400 (Bad Request) if the monthlyManagement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monthly-managements")
    @Timed
    public ResponseEntity<MonthlyManagement> createMonthlyManagement(@RequestBody MonthlyManagement monthlyManagement) throws URISyntaxException {
        log.debug("REST request to save MonthlyManagement : {}", monthlyManagement);
        if (monthlyManagement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new monthlyManagement cannot already have an ID")).body(null);
        }
        MonthlyManagement result = monthlyManagementService.save(monthlyManagement);
        return ResponseEntity.created(new URI("/api/monthly-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monthly-managements : Updates an existing monthlyManagement.
     *
     * @param monthlyManagement the monthlyManagement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monthlyManagement,
     * or with status 400 (Bad Request) if the monthlyManagement is not valid,
     * or with status 500 (Internal Server Error) if the monthlyManagement couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monthly-managements")
    @Timed
    public ResponseEntity<MonthlyManagement> updateMonthlyManagement(@RequestBody MonthlyManagement monthlyManagement) throws URISyntaxException {
        log.debug("REST request to update MonthlyManagement : {}", monthlyManagement);
        if (monthlyManagement.getId() == null) {
            return createMonthlyManagement(monthlyManagement);
        }
        MonthlyManagement result = monthlyManagementService.save(monthlyManagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monthlyManagement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monthly-managements : get all the monthlyManagements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of monthlyManagements in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/monthly-managements")
    @Timed
    public ResponseEntity<List<MonthlyManagement>> getAllMonthlyManagements(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MonthlyManagements");
        Page<MonthlyManagement> page = monthlyManagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monthly-managements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /monthly-managements/:id : get the "id" monthlyManagement.
     *
     * @param id the id of the monthlyManagement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monthlyManagement, or with status 404 (Not Found)
     */
    @GetMapping("/monthly-managements/{id}")
    @Timed
    public ResponseEntity<MonthlyManagement> getMonthlyManagement(@PathVariable Long id) {
        log.debug("REST request to get MonthlyManagement : {}", id);
        MonthlyManagement monthlyManagement = monthlyManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(monthlyManagement));
    }

    /**
     * DELETE  /monthly-managements/:id : delete the "id" monthlyManagement.
     *
     * @param id the id of the monthlyManagement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monthly-managements/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonthlyManagement(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyManagement : {}", id);
        monthlyManagementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
