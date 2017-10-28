package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.AuthorizationRecord;
import com.duma.service.AuthorizationRecordService;
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
 * REST controller for managing AuthorizationRecord.
 */
@RestController
@RequestMapping("/api")
public class AuthorizationRecordResource {

    private final Logger log = LoggerFactory.getLogger(AuthorizationRecordResource.class);

    private static final String ENTITY_NAME = "authorizationRecord";
        
    private final AuthorizationRecordService authorizationRecordService;

    public AuthorizationRecordResource(AuthorizationRecordService authorizationRecordService) {
        this.authorizationRecordService = authorizationRecordService;
    }

    /**
     * POST  /authorization-records : Create a new authorizationRecord.
     *
     * @param authorizationRecord the authorizationRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorizationRecord, or with status 400 (Bad Request) if the authorizationRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authorization-records")
    @Timed
    public ResponseEntity<AuthorizationRecord> createAuthorizationRecord(@RequestBody AuthorizationRecord authorizationRecord) throws URISyntaxException {
        log.debug("REST request to save AuthorizationRecord : {}", authorizationRecord);
        if (authorizationRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new authorizationRecord cannot already have an ID")).body(null);
        }
        AuthorizationRecord result = authorizationRecordService.save(authorizationRecord);
        return ResponseEntity.created(new URI("/api/authorization-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authorization-records : Updates an existing authorizationRecord.
     *
     * @param authorizationRecord the authorizationRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorizationRecord,
     * or with status 400 (Bad Request) if the authorizationRecord is not valid,
     * or with status 500 (Internal Server Error) if the authorizationRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authorization-records")
    @Timed
    public ResponseEntity<AuthorizationRecord> updateAuthorizationRecord(@RequestBody AuthorizationRecord authorizationRecord) throws URISyntaxException {
        log.debug("REST request to update AuthorizationRecord : {}", authorizationRecord);
        if (authorizationRecord.getId() == null) {
            return createAuthorizationRecord(authorizationRecord);
        }
        AuthorizationRecord result = authorizationRecordService.save(authorizationRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authorizationRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authorization-records : get all the authorizationRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of authorizationRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/authorization-records")
    @Timed
    public ResponseEntity<List<AuthorizationRecord>> getAllAuthorizationRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AuthorizationRecords");
        Page<AuthorizationRecord> page = authorizationRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authorization-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /authorization-records/:id : get the "id" authorizationRecord.
     *
     * @param id the id of the authorizationRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorizationRecord, or with status 404 (Not Found)
     */
    @GetMapping("/authorization-records/{id}")
    @Timed
    public ResponseEntity<AuthorizationRecord> getAuthorizationRecord(@PathVariable Long id) {
        log.debug("REST request to get AuthorizationRecord : {}", id);
        AuthorizationRecord authorizationRecord = authorizationRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(authorizationRecord));
    }

    /**
     * DELETE  /authorization-records/:id : delete the "id" authorizationRecord.
     *
     * @param id the id of the authorizationRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authorization-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthorizationRecord(@PathVariable Long id) {
        log.debug("REST request to delete AuthorizationRecord : {}", id);
        authorizationRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
