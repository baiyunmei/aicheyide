package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.FormalContract;
import com.duma.service.FormalContractService;
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
 * REST controller for managing FormalContract.
 */
@RestController
@RequestMapping("/api")
public class FormalContractResource {

    private final Logger log = LoggerFactory.getLogger(FormalContractResource.class);

    private static final String ENTITY_NAME = "formalContract";
        
    private final FormalContractService formalContractService;

    public FormalContractResource(FormalContractService formalContractService) {
        this.formalContractService = formalContractService;
    }

    /**
     * POST  /formal-contracts : Create a new formalContract.
     *
     * @param formalContract the formalContract to create
     * @return the ResponseEntity with status 201 (Created) and with body the new formalContract, or with status 400 (Bad Request) if the formalContract has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/formal-contracts")
    @Timed
    public ResponseEntity<FormalContract> createFormalContract(@RequestBody FormalContract formalContract) throws URISyntaxException {
        log.debug("REST request to save FormalContract : {}", formalContract);
        if (formalContract.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new formalContract cannot already have an ID")).body(null);
        }
        FormalContract result = formalContractService.save(formalContract);
        return ResponseEntity.created(new URI("/api/formal-contracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /formal-contracts : Updates an existing formalContract.
     *
     * @param formalContract the formalContract to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated formalContract,
     * or with status 400 (Bad Request) if the formalContract is not valid,
     * or with status 500 (Internal Server Error) if the formalContract couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/formal-contracts")
    @Timed
    public ResponseEntity<FormalContract> updateFormalContract(@RequestBody FormalContract formalContract) throws URISyntaxException {
        log.debug("REST request to update FormalContract : {}", formalContract);
        if (formalContract.getId() == null) {
            return createFormalContract(formalContract);
        }
        FormalContract result = formalContractService.save(formalContract);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, formalContract.getId().toString()))
            .body(result);
    }

    /**
     * GET  /formal-contracts : get all the formalContracts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of formalContracts in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/formal-contracts")
    @Timed
    public ResponseEntity<List<FormalContract>> getAllFormalContracts(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of FormalContracts");
        Page<FormalContract> page = formalContractService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/formal-contracts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /formal-contracts/:id : get the "id" formalContract.
     *
     * @param id the id of the formalContract to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the formalContract, or with status 404 (Not Found)
     */
    @GetMapping("/formal-contracts/{id}")
    @Timed
    public ResponseEntity<FormalContract> getFormalContract(@PathVariable Long id) {
        log.debug("REST request to get FormalContract : {}", id);
        FormalContract formalContract = formalContractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(formalContract));
    }

    /**
     * DELETE  /formal-contracts/:id : delete the "id" formalContract.
     *
     * @param id the id of the formalContract to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/formal-contracts/{id}")
    @Timed
    public ResponseEntity<Void> deleteFormalContract(@PathVariable Long id) {
        log.debug("REST request to delete FormalContract : {}", id);
        formalContractService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
