package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.LogRecord;
import com.duma.service.LogRecordService;
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
 * REST controller for managing LogRecord.
 */
@RestController
@RequestMapping("/api")
public class LogRecordResource {

    private final Logger log = LoggerFactory.getLogger(LogRecordResource.class);

    private static final String ENTITY_NAME = "logRecord";
        
    private final LogRecordService logRecordService;

    public LogRecordResource(LogRecordService logRecordService) {
        this.logRecordService = logRecordService;
    }

    /**
     * POST  /log-records : Create a new logRecord.
     *
     * @param logRecord the logRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logRecord, or with status 400 (Bad Request) if the logRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/log-records")
    @Timed
    public ResponseEntity<LogRecord> createLogRecord(@RequestBody LogRecord logRecord) throws URISyntaxException {
        log.debug("REST request to save LogRecord : {}", logRecord);
        if (logRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new logRecord cannot already have an ID")).body(null);
        }
        LogRecord result = logRecordService.save(logRecord);
        return ResponseEntity.created(new URI("/api/log-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /log-records : Updates an existing logRecord.
     *
     * @param logRecord the logRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logRecord,
     * or with status 400 (Bad Request) if the logRecord is not valid,
     * or with status 500 (Internal Server Error) if the logRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/log-records")
    @Timed
    public ResponseEntity<LogRecord> updateLogRecord(@RequestBody LogRecord logRecord) throws URISyntaxException {
        log.debug("REST request to update LogRecord : {}", logRecord);
        if (logRecord.getId() == null) {
            return createLogRecord(logRecord);
        }
        LogRecord result = logRecordService.save(logRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /log-records : get all the logRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of logRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/log-records")
    @Timed
    public ResponseEntity<List<LogRecord>> getAllLogRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LogRecords");
        Page<LogRecord> page = logRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/log-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /log-records/:id : get the "id" logRecord.
     *
     * @param id the id of the logRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logRecord, or with status 404 (Not Found)
     */
    @GetMapping("/log-records/{id}")
    @Timed
    public ResponseEntity<LogRecord> getLogRecord(@PathVariable Long id) {
        log.debug("REST request to get LogRecord : {}", id);
        LogRecord logRecord = logRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(logRecord));
    }

    /**
     * DELETE  /log-records/:id : delete the "id" logRecord.
     *
     * @param id the id of the logRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/log-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogRecord(@PathVariable Long id) {
        log.debug("REST request to delete LogRecord : {}", id);
        logRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
