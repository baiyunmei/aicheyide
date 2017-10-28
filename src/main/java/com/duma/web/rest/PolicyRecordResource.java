package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.PolicyRecord;
import com.duma.service.PolicyRecordService;
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
 * REST controller for managing PolicyRecord.
 */
@RestController
@RequestMapping("/api")
public class PolicyRecordResource {

    private final Logger log = LoggerFactory.getLogger(PolicyRecordResource.class);

    private static final String ENTITY_NAME = "policyRecord";
        
    private final PolicyRecordService policyRecordService;

    public PolicyRecordResource(PolicyRecordService policyRecordService) {
        this.policyRecordService = policyRecordService;
    }

    /**
     * POST  /policy-records : Create a new policyRecord.
     *
     * @param policyRecord the policyRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new policyRecord, or with status 400 (Bad Request) if the policyRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/policy-records")
    @Timed
    public ResponseEntity<PolicyRecord> createPolicyRecord(@RequestBody PolicyRecord policyRecord) throws URISyntaxException {
        log.debug("REST request to save PolicyRecord : {}", policyRecord);
        if (policyRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new policyRecord cannot already have an ID")).body(null);
        }
        PolicyRecord result = policyRecordService.save(policyRecord);
        return ResponseEntity.created(new URI("/api/policy-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /policy-records : Updates an existing policyRecord.
     *
     * @param policyRecord the policyRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated policyRecord,
     * or with status 400 (Bad Request) if the policyRecord is not valid,
     * or with status 500 (Internal Server Error) if the policyRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/policy-records")
    @Timed
    public ResponseEntity<PolicyRecord> updatePolicyRecord(@RequestBody PolicyRecord policyRecord) throws URISyntaxException {
        log.debug("REST request to update PolicyRecord : {}", policyRecord);
        if (policyRecord.getId() == null) {
            return createPolicyRecord(policyRecord);
        }
        PolicyRecord result = policyRecordService.save(policyRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, policyRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /policy-records : get all the policyRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of policyRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/policy-records")
    @Timed
    public ResponseEntity<List<PolicyRecord>> getAllPolicyRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PolicyRecords");
        Page<PolicyRecord> page = policyRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/policy-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /policy-records/:id : get the "id" policyRecord.
     *
     * @param id the id of the policyRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the policyRecord, or with status 404 (Not Found)
     */
    @GetMapping("/policy-records/{id}")
    @Timed
    public ResponseEntity<PolicyRecord> getPolicyRecord(@PathVariable Long id) {
        log.debug("REST request to get PolicyRecord : {}", id);
        PolicyRecord policyRecord = policyRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(policyRecord));
    }

    /**
     * DELETE  /policy-records/:id : delete the "id" policyRecord.
     *
     * @param id the id of the policyRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/policy-records/{id}")
    @Timed
    public ResponseEntity<Void> deletePolicyRecord(@PathVariable Long id) {
        log.debug("REST request to delete PolicyRecord : {}", id);
        policyRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
