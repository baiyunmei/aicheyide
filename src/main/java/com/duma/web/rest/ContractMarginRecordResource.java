package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.ContractMarginRecord;
import com.duma.service.ContractMarginRecordService;
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
 * REST controller for managing ContractMarginRecord.
 */
@RestController
@RequestMapping("/api")
public class ContractMarginRecordResource {

    private final Logger log = LoggerFactory.getLogger(ContractMarginRecordResource.class);

    private static final String ENTITY_NAME = "contractMarginRecord";
        
    private final ContractMarginRecordService contractMarginRecordService;

    public ContractMarginRecordResource(ContractMarginRecordService contractMarginRecordService) {
        this.contractMarginRecordService = contractMarginRecordService;
    }

    /**
     * POST  /contract-margin-records : Create a new contractMarginRecord.
     *
     * @param contractMarginRecord the contractMarginRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contractMarginRecord, or with status 400 (Bad Request) if the contractMarginRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contract-margin-records")
    @Timed
    public ResponseEntity<ContractMarginRecord> createContractMarginRecord(@RequestBody ContractMarginRecord contractMarginRecord) throws URISyntaxException {
        log.debug("REST request to save ContractMarginRecord : {}", contractMarginRecord);
        if (contractMarginRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contractMarginRecord cannot already have an ID")).body(null);
        }
        ContractMarginRecord result = contractMarginRecordService.save(contractMarginRecord);
        return ResponseEntity.created(new URI("/api/contract-margin-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contract-margin-records : Updates an existing contractMarginRecord.
     *
     * @param contractMarginRecord the contractMarginRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contractMarginRecord,
     * or with status 400 (Bad Request) if the contractMarginRecord is not valid,
     * or with status 500 (Internal Server Error) if the contractMarginRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contract-margin-records")
    @Timed
    public ResponseEntity<ContractMarginRecord> updateContractMarginRecord(@RequestBody ContractMarginRecord contractMarginRecord) throws URISyntaxException {
        log.debug("REST request to update ContractMarginRecord : {}", contractMarginRecord);
        if (contractMarginRecord.getId() == null) {
            return createContractMarginRecord(contractMarginRecord);
        }
        ContractMarginRecord result = contractMarginRecordService.save(contractMarginRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contractMarginRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contract-margin-records : get all the contractMarginRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of contractMarginRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/contract-margin-records")
    @Timed
    public ResponseEntity<List<ContractMarginRecord>> getAllContractMarginRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ContractMarginRecords");
        Page<ContractMarginRecord> page = contractMarginRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/contract-margin-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contract-margin-records/:id : get the "id" contractMarginRecord.
     *
     * @param id the id of the contractMarginRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contractMarginRecord, or with status 404 (Not Found)
     */
    @GetMapping("/contract-margin-records/{id}")
    @Timed
    public ResponseEntity<ContractMarginRecord> getContractMarginRecord(@PathVariable Long id) {
        log.debug("REST request to get ContractMarginRecord : {}", id);
        ContractMarginRecord contractMarginRecord = contractMarginRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contractMarginRecord));
    }

    /**
     * DELETE  /contract-margin-records/:id : delete the "id" contractMarginRecord.
     *
     * @param id the id of the contractMarginRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contract-margin-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteContractMarginRecord(@PathVariable Long id) {
        log.debug("REST request to delete ContractMarginRecord : {}", id);
        contractMarginRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
