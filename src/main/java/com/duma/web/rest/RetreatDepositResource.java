package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.RetreatDeposit;
import com.duma.service.RetreatDepositService;
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
 * REST controller for managing RetreatDeposit.
 */
@RestController
@RequestMapping("/api")
public class RetreatDepositResource {

    private final Logger log = LoggerFactory.getLogger(RetreatDepositResource.class);

    private static final String ENTITY_NAME = "retreatDeposit";
        
    private final RetreatDepositService retreatDepositService;

    public RetreatDepositResource(RetreatDepositService retreatDepositService) {
        this.retreatDepositService = retreatDepositService;
    }

    /**
     * POST  /retreat-deposits : Create a new retreatDeposit.
     *
     * @param retreatDeposit the retreatDeposit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new retreatDeposit, or with status 400 (Bad Request) if the retreatDeposit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/retreat-deposits")
    @Timed
    public ResponseEntity<RetreatDeposit> createRetreatDeposit(@RequestBody RetreatDeposit retreatDeposit) throws URISyntaxException {
        log.debug("REST request to save RetreatDeposit : {}", retreatDeposit);
        if (retreatDeposit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new retreatDeposit cannot already have an ID")).body(null);
        }
        RetreatDeposit result = retreatDepositService.save(retreatDeposit);
        return ResponseEntity.created(new URI("/api/retreat-deposits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /retreat-deposits : Updates an existing retreatDeposit.
     *
     * @param retreatDeposit the retreatDeposit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated retreatDeposit,
     * or with status 400 (Bad Request) if the retreatDeposit is not valid,
     * or with status 500 (Internal Server Error) if the retreatDeposit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/retreat-deposits")
    @Timed
    public ResponseEntity<RetreatDeposit> updateRetreatDeposit(@RequestBody RetreatDeposit retreatDeposit) throws URISyntaxException {
        log.debug("REST request to update RetreatDeposit : {}", retreatDeposit);
        if (retreatDeposit.getId() == null) {
            return createRetreatDeposit(retreatDeposit);
        }
        RetreatDeposit result = retreatDepositService.save(retreatDeposit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, retreatDeposit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /retreat-deposits : get all the retreatDeposits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of retreatDeposits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/retreat-deposits")
    @Timed
    public ResponseEntity<List<RetreatDeposit>> getAllRetreatDeposits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RetreatDeposits");
        Page<RetreatDeposit> page = retreatDepositService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/retreat-deposits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /retreat-deposits/:id : get the "id" retreatDeposit.
     *
     * @param id the id of the retreatDeposit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the retreatDeposit, or with status 404 (Not Found)
     */
    @GetMapping("/retreat-deposits/{id}")
    @Timed
    public ResponseEntity<RetreatDeposit> getRetreatDeposit(@PathVariable Long id) {
        log.debug("REST request to get RetreatDeposit : {}", id);
        RetreatDeposit retreatDeposit = retreatDepositService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(retreatDeposit));
    }

    /**
     * DELETE  /retreat-deposits/:id : delete the "id" retreatDeposit.
     *
     * @param id the id of the retreatDeposit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/retreat-deposits/{id}")
    @Timed
    public ResponseEntity<Void> deleteRetreatDeposit(@PathVariable Long id) {
        log.debug("REST request to delete RetreatDeposit : {}", id);
        retreatDepositService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
