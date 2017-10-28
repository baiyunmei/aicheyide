package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.IllegalRecord;
import com.duma.service.IllegalRecordService;
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
 * REST controller for managing IllegalRecord.
 */
@RestController
@RequestMapping("/api")
public class IllegalRecordResource {

    private final Logger log = LoggerFactory.getLogger(IllegalRecordResource.class);

    private static final String ENTITY_NAME = "illegalRecord";
        
    private final IllegalRecordService illegalRecordService;

    public IllegalRecordResource(IllegalRecordService illegalRecordService) {
        this.illegalRecordService = illegalRecordService;
    }

    /**
     * POST  /illegal-records : Create a new illegalRecord.
     *
     * @param illegalRecord the illegalRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new illegalRecord, or with status 400 (Bad Request) if the illegalRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/illegal-records")
    @Timed
    public ResponseEntity<IllegalRecord> createIllegalRecord(@RequestBody IllegalRecord illegalRecord) throws URISyntaxException {
        log.debug("REST request to save IllegalRecord : {}", illegalRecord);
        if (illegalRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new illegalRecord cannot already have an ID")).body(null);
        }
        IllegalRecord result = illegalRecordService.save(illegalRecord);
        return ResponseEntity.created(new URI("/api/illegal-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /illegal-records : Updates an existing illegalRecord.
     *
     * @param illegalRecord the illegalRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated illegalRecord,
     * or with status 400 (Bad Request) if the illegalRecord is not valid,
     * or with status 500 (Internal Server Error) if the illegalRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/illegal-records")
    @Timed
    public ResponseEntity<IllegalRecord> updateIllegalRecord(@RequestBody IllegalRecord illegalRecord) throws URISyntaxException {
        log.debug("REST request to update IllegalRecord : {}", illegalRecord);
        if (illegalRecord.getId() == null) {
            return createIllegalRecord(illegalRecord);
        }
        IllegalRecord result = illegalRecordService.save(illegalRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, illegalRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /illegal-records : get all the illegalRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of illegalRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/illegal-records")
    @Timed
    public ResponseEntity<List<IllegalRecord>> getAllIllegalRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of IllegalRecords");
        Page<IllegalRecord> page = illegalRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/illegal-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /illegal-records/:id : get the "id" illegalRecord.
     *
     * @param id the id of the illegalRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the illegalRecord, or with status 404 (Not Found)
     */
    @GetMapping("/illegal-records/{id}")
    @Timed
    public ResponseEntity<IllegalRecord> getIllegalRecord(@PathVariable Long id) {
        log.debug("REST request to get IllegalRecord : {}", id);
        IllegalRecord illegalRecord = illegalRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(illegalRecord));
    }

    /**
     * DELETE  /illegal-records/:id : delete the "id" illegalRecord.
     *
     * @param id the id of the illegalRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/illegal-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteIllegalRecord(@PathVariable Long id) {
        log.debug("REST request to delete IllegalRecord : {}", id);
        illegalRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
