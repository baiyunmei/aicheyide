package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.RentVehicleRecord;
import com.duma.service.RentVehicleRecordService;
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
 * REST controller for managing RentVehicleRecord.
 */
@RestController
@RequestMapping("/api")
public class RentVehicleRecordResource {

    private final Logger log = LoggerFactory.getLogger(RentVehicleRecordResource.class);

    private static final String ENTITY_NAME = "rentVehicleRecord";
        
    private final RentVehicleRecordService rentVehicleRecordService;

    public RentVehicleRecordResource(RentVehicleRecordService rentVehicleRecordService) {
        this.rentVehicleRecordService = rentVehicleRecordService;
    }

    /**
     * POST  /rent-vehicle-records : Create a new rentVehicleRecord.
     *
     * @param rentVehicleRecord the rentVehicleRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rentVehicleRecord, or with status 400 (Bad Request) if the rentVehicleRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rent-vehicle-records")
    @Timed
    public ResponseEntity<RentVehicleRecord> createRentVehicleRecord(@RequestBody RentVehicleRecord rentVehicleRecord) throws URISyntaxException {
        log.debug("REST request to save RentVehicleRecord : {}", rentVehicleRecord);
        if (rentVehicleRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rentVehicleRecord cannot already have an ID")).body(null);
        }
        RentVehicleRecord result = rentVehicleRecordService.save(rentVehicleRecord);
        return ResponseEntity.created(new URI("/api/rent-vehicle-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rent-vehicle-records : Updates an existing rentVehicleRecord.
     *
     * @param rentVehicleRecord the rentVehicleRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rentVehicleRecord,
     * or with status 400 (Bad Request) if the rentVehicleRecord is not valid,
     * or with status 500 (Internal Server Error) if the rentVehicleRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rent-vehicle-records")
    @Timed
    public ResponseEntity<RentVehicleRecord> updateRentVehicleRecord(@RequestBody RentVehicleRecord rentVehicleRecord) throws URISyntaxException {
        log.debug("REST request to update RentVehicleRecord : {}", rentVehicleRecord);
        if (rentVehicleRecord.getId() == null) {
            return createRentVehicleRecord(rentVehicleRecord);
        }
        RentVehicleRecord result = rentVehicleRecordService.save(rentVehicleRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rentVehicleRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rent-vehicle-records : get all the rentVehicleRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rentVehicleRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/rent-vehicle-records")
    @Timed
    public ResponseEntity<List<RentVehicleRecord>> getAllRentVehicleRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RentVehicleRecords");
        Page<RentVehicleRecord> page = rentVehicleRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rent-vehicle-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rent-vehicle-records/:id : get the "id" rentVehicleRecord.
     *
     * @param id the id of the rentVehicleRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rentVehicleRecord, or with status 404 (Not Found)
     */
    @GetMapping("/rent-vehicle-records/{id}")
    @Timed
    public ResponseEntity<RentVehicleRecord> getRentVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to get RentVehicleRecord : {}", id);
        RentVehicleRecord rentVehicleRecord = rentVehicleRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rentVehicleRecord));
    }

    /**
     * DELETE  /rent-vehicle-records/:id : delete the "id" rentVehicleRecord.
     *
     * @param id the id of the rentVehicleRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rent-vehicle-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRentVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to delete RentVehicleRecord : {}", id);
        rentVehicleRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
