package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.AiRecord;
import com.duma.service.AiRecordService;
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
 * REST controller for managing AiRecord.
 */
@RestController
@RequestMapping("/api")
public class AiRecordResource {

    private final Logger log = LoggerFactory.getLogger(AiRecordResource.class);

    private static final String ENTITY_NAME = "aiRecord";
        
    private final AiRecordService aiRecordService;

    public AiRecordResource(AiRecordService aiRecordService) {
        this.aiRecordService = aiRecordService;
    }

    /**
     * POST  /ai-records : Create a new aiRecord.
     *
     * @param aiRecord the aiRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aiRecord, or with status 400 (Bad Request) if the aiRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ai-records")
    @Timed
    public ResponseEntity<AiRecord> createAiRecord(@RequestBody AiRecord aiRecord) throws URISyntaxException {
        log.debug("REST request to save AiRecord : {}", aiRecord);
        if (aiRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new aiRecord cannot already have an ID")).body(null);
        }
        AiRecord result = aiRecordService.save(aiRecord);
        return ResponseEntity.created(new URI("/api/ai-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ai-records : Updates an existing aiRecord.
     *
     * @param aiRecord the aiRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aiRecord,
     * or with status 400 (Bad Request) if the aiRecord is not valid,
     * or with status 500 (Internal Server Error) if the aiRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ai-records")
    @Timed
    public ResponseEntity<AiRecord> updateAiRecord(@RequestBody AiRecord aiRecord) throws URISyntaxException {
        log.debug("REST request to update AiRecord : {}", aiRecord);
        if (aiRecord.getId() == null) {
            return createAiRecord(aiRecord);
        }
        AiRecord result = aiRecordService.save(aiRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aiRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ai-records : get all the aiRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of aiRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/ai-records")
    @Timed
    public ResponseEntity<List<AiRecord>> getAllAiRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AiRecords");
        Page<AiRecord> page = aiRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ai-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ai-records/:id : get the "id" aiRecord.
     *
     * @param id the id of the aiRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aiRecord, or with status 404 (Not Found)
     */
    @GetMapping("/ai-records/{id}")
    @Timed
    public ResponseEntity<AiRecord> getAiRecord(@PathVariable Long id) {
        log.debug("REST request to get AiRecord : {}", id);
        AiRecord aiRecord = aiRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aiRecord));
    }

    /**
     * DELETE  /ai-records/:id : delete the "id" aiRecord.
     *
     * @param id the id of the aiRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ai-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteAiRecord(@PathVariable Long id) {
        log.debug("REST request to delete AiRecord : {}", id);
        aiRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
