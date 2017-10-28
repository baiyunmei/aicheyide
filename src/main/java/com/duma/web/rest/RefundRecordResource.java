package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.RefundRecord;
import com.duma.service.RefundRecordService;
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
 * REST controller for managing RefundRecord.
 */
@RestController
@RequestMapping("/api")
public class RefundRecordResource {

    private final Logger log = LoggerFactory.getLogger(RefundRecordResource.class);

    private static final String ENTITY_NAME = "refundRecord";
        
    private final RefundRecordService refundRecordService;

    public RefundRecordResource(RefundRecordService refundRecordService) {
        this.refundRecordService = refundRecordService;
    }

    /**
     * POST  /refund-records : Create a new refundRecord.
     *
     * @param refundRecord the refundRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refundRecord, or with status 400 (Bad Request) if the refundRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/refund-records")
    @Timed
    public ResponseEntity<RefundRecord> createRefundRecord(@RequestBody RefundRecord refundRecord) throws URISyntaxException {
        log.debug("REST request to save RefundRecord : {}", refundRecord);
        if (refundRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refundRecord cannot already have an ID")).body(null);
        }
        RefundRecord result = refundRecordService.save(refundRecord);
        return ResponseEntity.created(new URI("/api/refund-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /refund-records : Updates an existing refundRecord.
     *
     * @param refundRecord the refundRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refundRecord,
     * or with status 400 (Bad Request) if the refundRecord is not valid,
     * or with status 500 (Internal Server Error) if the refundRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/refund-records")
    @Timed
    public ResponseEntity<RefundRecord> updateRefundRecord(@RequestBody RefundRecord refundRecord) throws URISyntaxException {
        log.debug("REST request to update RefundRecord : {}", refundRecord);
        if (refundRecord.getId() == null) {
            return createRefundRecord(refundRecord);
        }
        RefundRecord result = refundRecordService.save(refundRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refundRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /refund-records : get all the refundRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refundRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/refund-records")
    @Timed
    public ResponseEntity<List<RefundRecord>> getAllRefundRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RefundRecords");
        Page<RefundRecord> page = refundRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/refund-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /refund-records/:id : get the "id" refundRecord.
     *
     * @param id the id of the refundRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refundRecord, or with status 404 (Not Found)
     */
    @GetMapping("/refund-records/{id}")
    @Timed
    public ResponseEntity<RefundRecord> getRefundRecord(@PathVariable Long id) {
        log.debug("REST request to get RefundRecord : {}", id);
        RefundRecord refundRecord = refundRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refundRecord));
    }

    /**
     * DELETE  /refund-records/:id : delete the "id" refundRecord.
     *
     * @param id the id of the refundRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/refund-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefundRecord(@PathVariable Long id) {
        log.debug("REST request to delete RefundRecord : {}", id);
        refundRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
