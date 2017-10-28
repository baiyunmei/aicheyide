package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.MaintainRecord;
import com.duma.service.MaintainRecordService;
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
 * REST controller for managing MaintainRecord.
 */
@RestController
@RequestMapping("/api")
public class MaintainRecordResource {

    private final Logger log = LoggerFactory.getLogger(MaintainRecordResource.class);

    private static final String ENTITY_NAME = "maintainRecord";
        
    private final MaintainRecordService maintainRecordService;

    public MaintainRecordResource(MaintainRecordService maintainRecordService) {
        this.maintainRecordService = maintainRecordService;
    }

    /**
     * POST  /maintain-records : Create a new maintainRecord.
     *
     * @param maintainRecord the maintainRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new maintainRecord, or with status 400 (Bad Request) if the maintainRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/maintain-records")
    @Timed
    public ResponseEntity<MaintainRecord> createMaintainRecord(@RequestBody MaintainRecord maintainRecord) throws URISyntaxException {
        log.debug("REST request to save MaintainRecord : {}", maintainRecord);
        if (maintainRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new maintainRecord cannot already have an ID")).body(null);
        }
        MaintainRecord result = maintainRecordService.save(maintainRecord);
        return ResponseEntity.created(new URI("/api/maintain-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /maintain-records : Updates an existing maintainRecord.
     *
     * @param maintainRecord the maintainRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated maintainRecord,
     * or with status 400 (Bad Request) if the maintainRecord is not valid,
     * or with status 500 (Internal Server Error) if the maintainRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/maintain-records")
    @Timed
    public ResponseEntity<MaintainRecord> updateMaintainRecord(@RequestBody MaintainRecord maintainRecord) throws URISyntaxException {
        log.debug("REST request to update MaintainRecord : {}", maintainRecord);
        if (maintainRecord.getId() == null) {
            return createMaintainRecord(maintainRecord);
        }
        MaintainRecord result = maintainRecordService.save(maintainRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, maintainRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /maintain-records : get all the maintainRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of maintainRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/maintain-records")
    @Timed
    public ResponseEntity<List<MaintainRecord>> getAllMaintainRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MaintainRecords");
        Page<MaintainRecord> page = maintainRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/maintain-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /maintain-records/:id : get the "id" maintainRecord.
     *
     * @param id the id of the maintainRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the maintainRecord, or with status 404 (Not Found)
     */
    @GetMapping("/maintain-records/{id}")
    @Timed
    public ResponseEntity<MaintainRecord> getMaintainRecord(@PathVariable Long id) {
        log.debug("REST request to get MaintainRecord : {}", id);
        MaintainRecord maintainRecord = maintainRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(maintainRecord));
    }

    /**
     * DELETE  /maintain-records/:id : delete the "id" maintainRecord.
     *
     * @param id the id of the maintainRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/maintain-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteMaintainRecord(@PathVariable Long id) {
        log.debug("REST request to delete MaintainRecord : {}", id);
        maintainRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
