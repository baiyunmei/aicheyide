package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.PleasePayeeAudit;
import com.duma.service.PleasePayeeAuditService;
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
 * REST controller for managing PleasePayeeAudit.
 */
@RestController
@RequestMapping("/api")
public class PleasePayeeAuditResource {

    private final Logger log = LoggerFactory.getLogger(PleasePayeeAuditResource.class);

    private static final String ENTITY_NAME = "pleasePayeeAudit";
        
    private final PleasePayeeAuditService pleasePayeeAuditService;

    public PleasePayeeAuditResource(PleasePayeeAuditService pleasePayeeAuditService) {
        this.pleasePayeeAuditService = pleasePayeeAuditService;
    }

    /**
     * POST  /please-payee-audits : Create a new pleasePayeeAudit.
     *
     * @param pleasePayeeAudit the pleasePayeeAudit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pleasePayeeAudit, or with status 400 (Bad Request) if the pleasePayeeAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/please-payee-audits")
    @Timed
    public ResponseEntity<PleasePayeeAudit> createPleasePayeeAudit(@RequestBody PleasePayeeAudit pleasePayeeAudit) throws URISyntaxException {
        log.debug("REST request to save PleasePayeeAudit : {}", pleasePayeeAudit);
        if (pleasePayeeAudit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pleasePayeeAudit cannot already have an ID")).body(null);
        }
        PleasePayeeAudit result = pleasePayeeAuditService.save(pleasePayeeAudit);
        return ResponseEntity.created(new URI("/api/please-payee-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /please-payee-audits : Updates an existing pleasePayeeAudit.
     *
     * @param pleasePayeeAudit the pleasePayeeAudit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pleasePayeeAudit,
     * or with status 400 (Bad Request) if the pleasePayeeAudit is not valid,
     * or with status 500 (Internal Server Error) if the pleasePayeeAudit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/please-payee-audits")
    @Timed
    public ResponseEntity<PleasePayeeAudit> updatePleasePayeeAudit(@RequestBody PleasePayeeAudit pleasePayeeAudit) throws URISyntaxException {
        log.debug("REST request to update PleasePayeeAudit : {}", pleasePayeeAudit);
        if (pleasePayeeAudit.getId() == null) {
            return createPleasePayeeAudit(pleasePayeeAudit);
        }
        PleasePayeeAudit result = pleasePayeeAuditService.save(pleasePayeeAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pleasePayeeAudit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /please-payee-audits : get all the pleasePayeeAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pleasePayeeAudits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/please-payee-audits")
    @Timed
    public ResponseEntity<List<PleasePayeeAudit>> getAllPleasePayeeAudits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PleasePayeeAudits");
        Page<PleasePayeeAudit> page = pleasePayeeAuditService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/please-payee-audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /please-payee-audits/:id : get the "id" pleasePayeeAudit.
     *
     * @param id the id of the pleasePayeeAudit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pleasePayeeAudit, or with status 404 (Not Found)
     */
    @GetMapping("/please-payee-audits/{id}")
    @Timed
    public ResponseEntity<PleasePayeeAudit> getPleasePayeeAudit(@PathVariable Long id) {
        log.debug("REST request to get PleasePayeeAudit : {}", id);
        PleasePayeeAudit pleasePayeeAudit = pleasePayeeAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pleasePayeeAudit));
    }

    /**
     * DELETE  /please-payee-audits/:id : delete the "id" pleasePayeeAudit.
     *
     * @param id the id of the pleasePayeeAudit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/please-payee-audits/{id}")
    @Timed
    public ResponseEntity<Void> deletePleasePayeeAudit(@PathVariable Long id) {
        log.debug("REST request to delete PleasePayeeAudit : {}", id);
        pleasePayeeAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
