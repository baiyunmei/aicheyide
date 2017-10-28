package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.OutDangerRecord;
import com.duma.service.OutDangerRecordService;
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
 * REST controller for managing OutDangerRecord.
 */
@RestController
@RequestMapping("/api")
public class OutDangerRecordResource {

    private final Logger log = LoggerFactory.getLogger(OutDangerRecordResource.class);

    private static final String ENTITY_NAME = "outDangerRecord";
        
    private final OutDangerRecordService outDangerRecordService;

    public OutDangerRecordResource(OutDangerRecordService outDangerRecordService) {
        this.outDangerRecordService = outDangerRecordService;
    }

    /**
     * POST  /out-danger-records : Create a new outDangerRecord.
     *
     * @param outDangerRecord the outDangerRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new outDangerRecord, or with status 400 (Bad Request) if the outDangerRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/out-danger-records")
    @Timed
    public ResponseEntity<OutDangerRecord> createOutDangerRecord(@RequestBody OutDangerRecord outDangerRecord) throws URISyntaxException {
        log.debug("REST request to save OutDangerRecord : {}", outDangerRecord);
        if (outDangerRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new outDangerRecord cannot already have an ID")).body(null);
        }
        OutDangerRecord result = outDangerRecordService.save(outDangerRecord);
        return ResponseEntity.created(new URI("/api/out-danger-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /out-danger-records : Updates an existing outDangerRecord.
     *
     * @param outDangerRecord the outDangerRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated outDangerRecord,
     * or with status 400 (Bad Request) if the outDangerRecord is not valid,
     * or with status 500 (Internal Server Error) if the outDangerRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/out-danger-records")
    @Timed
    public ResponseEntity<OutDangerRecord> updateOutDangerRecord(@RequestBody OutDangerRecord outDangerRecord) throws URISyntaxException {
        log.debug("REST request to update OutDangerRecord : {}", outDangerRecord);
        if (outDangerRecord.getId() == null) {
            return createOutDangerRecord(outDangerRecord);
        }
        OutDangerRecord result = outDangerRecordService.save(outDangerRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, outDangerRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /out-danger-records : get all the outDangerRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of outDangerRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/out-danger-records")
    @Timed
    public ResponseEntity<List<OutDangerRecord>> getAllOutDangerRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OutDangerRecords");
        Page<OutDangerRecord> page = outDangerRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/out-danger-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /out-danger-records/:id : get the "id" outDangerRecord.
     *
     * @param id the id of the outDangerRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the outDangerRecord, or with status 404 (Not Found)
     */
    @GetMapping("/out-danger-records/{id}")
    @Timed
    public ResponseEntity<OutDangerRecord> getOutDangerRecord(@PathVariable Long id) {
        log.debug("REST request to get OutDangerRecord : {}", id);
        OutDangerRecord outDangerRecord = outDangerRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(outDangerRecord));
    }

    /**
     * DELETE  /out-danger-records/:id : delete the "id" outDangerRecord.
     *
     * @param id the id of the outDangerRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/out-danger-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteOutDangerRecord(@PathVariable Long id) {
        log.debug("REST request to delete OutDangerRecord : {}", id);
        outDangerRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
