package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.RepaymentRecord;
import com.duma.service.RepaymentRecordService;
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
 * REST controller for managing RepaymentRecord.
 */
@RestController
@RequestMapping("/api")
public class RepaymentRecordResource {

    private final Logger log = LoggerFactory.getLogger(RepaymentRecordResource.class);

    private static final String ENTITY_NAME = "repaymentRecord";
        
    private final RepaymentRecordService repaymentRecordService;

    public RepaymentRecordResource(RepaymentRecordService repaymentRecordService) {
        this.repaymentRecordService = repaymentRecordService;
    }

    /**
     * POST  /repayment-records : Create a new repaymentRecord.
     *
     * @param repaymentRecord the repaymentRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new repaymentRecord, or with status 400 (Bad Request) if the repaymentRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/repayment-records")
    @Timed
    public ResponseEntity<RepaymentRecord> createRepaymentRecord(@RequestBody RepaymentRecord repaymentRecord) throws URISyntaxException {
        log.debug("REST request to save RepaymentRecord : {}", repaymentRecord);
        if (repaymentRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new repaymentRecord cannot already have an ID")).body(null);
        }
        RepaymentRecord result = repaymentRecordService.save(repaymentRecord);
        return ResponseEntity.created(new URI("/api/repayment-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /repayment-records : Updates an existing repaymentRecord.
     *
     * @param repaymentRecord the repaymentRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated repaymentRecord,
     * or with status 400 (Bad Request) if the repaymentRecord is not valid,
     * or with status 500 (Internal Server Error) if the repaymentRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/repayment-records")
    @Timed
    public ResponseEntity<RepaymentRecord> updateRepaymentRecord(@RequestBody RepaymentRecord repaymentRecord) throws URISyntaxException {
        log.debug("REST request to update RepaymentRecord : {}", repaymentRecord);
        if (repaymentRecord.getId() == null) {
            return createRepaymentRecord(repaymentRecord);
        }
        RepaymentRecord result = repaymentRecordService.save(repaymentRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, repaymentRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /repayment-records : get all the repaymentRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of repaymentRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/repayment-records")
    @Timed
    public ResponseEntity<List<RepaymentRecord>> getAllRepaymentRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RepaymentRecords");
        Page<RepaymentRecord> page = repaymentRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/repayment-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /repayment-records/:id : get the "id" repaymentRecord.
     *
     * @param id the id of the repaymentRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the repaymentRecord, or with status 404 (Not Found)
     */
    @GetMapping("/repayment-records/{id}")
    @Timed
    public ResponseEntity<RepaymentRecord> getRepaymentRecord(@PathVariable Long id) {
        log.debug("REST request to get RepaymentRecord : {}", id);
        RepaymentRecord repaymentRecord = repaymentRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(repaymentRecord));
    }

    /**
     * DELETE  /repayment-records/:id : delete the "id" repaymentRecord.
     *
     * @param id the id of the repaymentRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/repayment-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRepaymentRecord(@PathVariable Long id) {
        log.debug("REST request to delete RepaymentRecord : {}", id);
        repaymentRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
