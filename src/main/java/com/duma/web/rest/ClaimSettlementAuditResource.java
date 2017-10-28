package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.ClaimSettlementAudit;
import com.duma.service.ClaimSettlementAuditService;
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
 * REST controller for managing ClaimSettlementAudit.
 */
@RestController
@RequestMapping("/api")
public class ClaimSettlementAuditResource {

    private final Logger log = LoggerFactory.getLogger(ClaimSettlementAuditResource.class);

    private static final String ENTITY_NAME = "claimSettlementAudit";
        
    private final ClaimSettlementAuditService claimSettlementAuditService;

    public ClaimSettlementAuditResource(ClaimSettlementAuditService claimSettlementAuditService) {
        this.claimSettlementAuditService = claimSettlementAuditService;
    }

    /**
     * POST  /claim-settlement-audits : Create a new claimSettlementAudit.
     *
     * @param claimSettlementAudit the claimSettlementAudit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new claimSettlementAudit, or with status 400 (Bad Request) if the claimSettlementAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/claim-settlement-audits")
    @Timed
    public ResponseEntity<ClaimSettlementAudit> createClaimSettlementAudit(@RequestBody ClaimSettlementAudit claimSettlementAudit) throws URISyntaxException {
        log.debug("REST request to save ClaimSettlementAudit : {}", claimSettlementAudit);
        if (claimSettlementAudit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new claimSettlementAudit cannot already have an ID")).body(null);
        }
        ClaimSettlementAudit result = claimSettlementAuditService.save(claimSettlementAudit);
        return ResponseEntity.created(new URI("/api/claim-settlement-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /claim-settlement-audits : Updates an existing claimSettlementAudit.
     *
     * @param claimSettlementAudit the claimSettlementAudit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated claimSettlementAudit,
     * or with status 400 (Bad Request) if the claimSettlementAudit is not valid,
     * or with status 500 (Internal Server Error) if the claimSettlementAudit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/claim-settlement-audits")
    @Timed
    public ResponseEntity<ClaimSettlementAudit> updateClaimSettlementAudit(@RequestBody ClaimSettlementAudit claimSettlementAudit) throws URISyntaxException {
        log.debug("REST request to update ClaimSettlementAudit : {}", claimSettlementAudit);
        if (claimSettlementAudit.getId() == null) {
            return createClaimSettlementAudit(claimSettlementAudit);
        }
        ClaimSettlementAudit result = claimSettlementAuditService.save(claimSettlementAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, claimSettlementAudit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /claim-settlement-audits : get all the claimSettlementAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of claimSettlementAudits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/claim-settlement-audits")
    @Timed
    public ResponseEntity<List<ClaimSettlementAudit>> getAllClaimSettlementAudits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ClaimSettlementAudits");
        Page<ClaimSettlementAudit> page = claimSettlementAuditService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/claim-settlement-audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /claim-settlement-audits/:id : get the "id" claimSettlementAudit.
     *
     * @param id the id of the claimSettlementAudit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the claimSettlementAudit, or with status 404 (Not Found)
     */
    @GetMapping("/claim-settlement-audits/{id}")
    @Timed
    public ResponseEntity<ClaimSettlementAudit> getClaimSettlementAudit(@PathVariable Long id) {
        log.debug("REST request to get ClaimSettlementAudit : {}", id);
        ClaimSettlementAudit claimSettlementAudit = claimSettlementAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(claimSettlementAudit));
    }

    /**
     * DELETE  /claim-settlement-audits/:id : delete the "id" claimSettlementAudit.
     *
     * @param id the id of the claimSettlementAudit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/claim-settlement-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteClaimSettlementAudit(@PathVariable Long id) {
        log.debug("REST request to delete ClaimSettlementAudit : {}", id);
        claimSettlementAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
