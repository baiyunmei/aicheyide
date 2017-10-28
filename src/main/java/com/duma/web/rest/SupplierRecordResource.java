package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.SupplierRecord;
import com.duma.service.SupplierRecordService;
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
 * REST controller for managing SupplierRecord.
 */
@RestController
@RequestMapping("/api")
public class SupplierRecordResource {

    private final Logger log = LoggerFactory.getLogger(SupplierRecordResource.class);

    private static final String ENTITY_NAME = "supplierRecord";
        
    private final SupplierRecordService supplierRecordService;

    public SupplierRecordResource(SupplierRecordService supplierRecordService) {
        this.supplierRecordService = supplierRecordService;
    }

    /**
     * POST  /supplier-records : Create a new supplierRecord.
     *
     * @param supplierRecord the supplierRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supplierRecord, or with status 400 (Bad Request) if the supplierRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/supplier-records")
    @Timed
    public ResponseEntity<SupplierRecord> createSupplierRecord(@RequestBody SupplierRecord supplierRecord) throws URISyntaxException {
        log.debug("REST request to save SupplierRecord : {}", supplierRecord);
        if (supplierRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new supplierRecord cannot already have an ID")).body(null);
        }
        SupplierRecord result = supplierRecordService.save(supplierRecord);
        return ResponseEntity.created(new URI("/api/supplier-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /supplier-records : Updates an existing supplierRecord.
     *
     * @param supplierRecord the supplierRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supplierRecord,
     * or with status 400 (Bad Request) if the supplierRecord is not valid,
     * or with status 500 (Internal Server Error) if the supplierRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/supplier-records")
    @Timed
    public ResponseEntity<SupplierRecord> updateSupplierRecord(@RequestBody SupplierRecord supplierRecord) throws URISyntaxException {
        log.debug("REST request to update SupplierRecord : {}", supplierRecord);
        if (supplierRecord.getId() == null) {
            return createSupplierRecord(supplierRecord);
        }
        SupplierRecord result = supplierRecordService.save(supplierRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supplierRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /supplier-records : get all the supplierRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of supplierRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/supplier-records")
    @Timed
    public ResponseEntity<List<SupplierRecord>> getAllSupplierRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SupplierRecords");
        Page<SupplierRecord> page = supplierRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/supplier-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /supplier-records/:id : get the "id" supplierRecord.
     *
     * @param id the id of the supplierRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supplierRecord, or with status 404 (Not Found)
     */
    @GetMapping("/supplier-records/{id}")
    @Timed
    public ResponseEntity<SupplierRecord> getSupplierRecord(@PathVariable Long id) {
        log.debug("REST request to get SupplierRecord : {}", id);
        SupplierRecord supplierRecord = supplierRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(supplierRecord));
    }

    /**
     * DELETE  /supplier-records/:id : delete the "id" supplierRecord.
     *
     * @param id the id of the supplierRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/supplier-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupplierRecord(@PathVariable Long id) {
        log.debug("REST request to delete SupplierRecord : {}", id);
        supplierRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
