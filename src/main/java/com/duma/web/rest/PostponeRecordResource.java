package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.PostponeRecord;
import com.duma.service.PostponeRecordService;
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
 * REST controller for managing PostponeRecord.
 */
@RestController
@RequestMapping("/api")
public class PostponeRecordResource {

    private final Logger log = LoggerFactory.getLogger(PostponeRecordResource.class);

    private static final String ENTITY_NAME = "postponeRecord";
        
    private final PostponeRecordService postponeRecordService;

    public PostponeRecordResource(PostponeRecordService postponeRecordService) {
        this.postponeRecordService = postponeRecordService;
    }

    /**
     * POST  /postpone-records : Create a new postponeRecord.
     *
     * @param postponeRecord the postponeRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new postponeRecord, or with status 400 (Bad Request) if the postponeRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/postpone-records")
    @Timed
    public ResponseEntity<PostponeRecord> createPostponeRecord(@RequestBody PostponeRecord postponeRecord) throws URISyntaxException {
        log.debug("REST request to save PostponeRecord : {}", postponeRecord);
        if (postponeRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new postponeRecord cannot already have an ID")).body(null);
        }
        PostponeRecord result = postponeRecordService.save(postponeRecord);
        return ResponseEntity.created(new URI("/api/postpone-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /postpone-records : Updates an existing postponeRecord.
     *
     * @param postponeRecord the postponeRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated postponeRecord,
     * or with status 400 (Bad Request) if the postponeRecord is not valid,
     * or with status 500 (Internal Server Error) if the postponeRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/postpone-records")
    @Timed
    public ResponseEntity<PostponeRecord> updatePostponeRecord(@RequestBody PostponeRecord postponeRecord) throws URISyntaxException {
        log.debug("REST request to update PostponeRecord : {}", postponeRecord);
        if (postponeRecord.getId() == null) {
            return createPostponeRecord(postponeRecord);
        }
        PostponeRecord result = postponeRecordService.save(postponeRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, postponeRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /postpone-records : get all the postponeRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of postponeRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/postpone-records")
    @Timed
    public ResponseEntity<List<PostponeRecord>> getAllPostponeRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PostponeRecords");
        Page<PostponeRecord> page = postponeRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/postpone-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /postpone-records/:id : get the "id" postponeRecord.
     *
     * @param id the id of the postponeRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the postponeRecord, or with status 404 (Not Found)
     */
    @GetMapping("/postpone-records/{id}")
    @Timed
    public ResponseEntity<PostponeRecord> getPostponeRecord(@PathVariable Long id) {
        log.debug("REST request to get PostponeRecord : {}", id);
        PostponeRecord postponeRecord = postponeRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(postponeRecord));
    }

    /**
     * DELETE  /postpone-records/:id : delete the "id" postponeRecord.
     *
     * @param id the id of the postponeRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/postpone-records/{id}")
    @Timed
    public ResponseEntity<Void> deletePostponeRecord(@PathVariable Long id) {
        log.debug("REST request to delete PostponeRecord : {}", id);
        postponeRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
