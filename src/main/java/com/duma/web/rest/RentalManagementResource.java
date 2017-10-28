package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.RentalManagement;
import com.duma.service.RentalManagementService;
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
 * REST controller for managing RentalManagement.
 */
@RestController
@RequestMapping("/api")
public class RentalManagementResource {

    private final Logger log = LoggerFactory.getLogger(RentalManagementResource.class);

    private static final String ENTITY_NAME = "rentalManagement";
        
    private final RentalManagementService rentalManagementService;

    public RentalManagementResource(RentalManagementService rentalManagementService) {
        this.rentalManagementService = rentalManagementService;
    }

    /**
     * POST  /rental-managements : Create a new rentalManagement.
     *
     * @param rentalManagement the rentalManagement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rentalManagement, or with status 400 (Bad Request) if the rentalManagement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rental-managements")
    @Timed
    public ResponseEntity<RentalManagement> createRentalManagement(@RequestBody RentalManagement rentalManagement) throws URISyntaxException {
        log.debug("REST request to save RentalManagement : {}", rentalManagement);
        if (rentalManagement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rentalManagement cannot already have an ID")).body(null);
        }
        RentalManagement result = rentalManagementService.save(rentalManagement);
        return ResponseEntity.created(new URI("/api/rental-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rental-managements : Updates an existing rentalManagement.
     *
     * @param rentalManagement the rentalManagement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rentalManagement,
     * or with status 400 (Bad Request) if the rentalManagement is not valid,
     * or with status 500 (Internal Server Error) if the rentalManagement couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rental-managements")
    @Timed
    public ResponseEntity<RentalManagement> updateRentalManagement(@RequestBody RentalManagement rentalManagement) throws URISyntaxException {
        log.debug("REST request to update RentalManagement : {}", rentalManagement);
        if (rentalManagement.getId() == null) {
            return createRentalManagement(rentalManagement);
        }
        RentalManagement result = rentalManagementService.save(rentalManagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rentalManagement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rental-managements : get all the rentalManagements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rentalManagements in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/rental-managements")
    @Timed
    public ResponseEntity<List<RentalManagement>> getAllRentalManagements(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RentalManagements");
        Page<RentalManagement> page = rentalManagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rental-managements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rental-managements/:id : get the "id" rentalManagement.
     *
     * @param id the id of the rentalManagement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rentalManagement, or with status 404 (Not Found)
     */
    @GetMapping("/rental-managements/{id}")
    @Timed
    public ResponseEntity<RentalManagement> getRentalManagement(@PathVariable Long id) {
        log.debug("REST request to get RentalManagement : {}", id);
        RentalManagement rentalManagement = rentalManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rentalManagement));
    }

    /**
     * DELETE  /rental-managements/:id : delete the "id" rentalManagement.
     *
     * @param id the id of the rentalManagement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rental-managements/{id}")
    @Timed
    public ResponseEntity<Void> deleteRentalManagement(@PathVariable Long id) {
        log.debug("REST request to delete RentalManagement : {}", id);
        rentalManagementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
