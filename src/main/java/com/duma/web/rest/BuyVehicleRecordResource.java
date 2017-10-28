package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.BuyVehicleRecord;
import com.duma.service.BuyVehicleRecordService;
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
 * REST controller for managing BuyVehicleRecord.
 */
@RestController
@RequestMapping("/api")
public class BuyVehicleRecordResource {

    private final Logger log = LoggerFactory.getLogger(BuyVehicleRecordResource.class);

    private static final String ENTITY_NAME = "buyVehicleRecord";
        
    private final BuyVehicleRecordService buyVehicleRecordService;

    public BuyVehicleRecordResource(BuyVehicleRecordService buyVehicleRecordService) {
        this.buyVehicleRecordService = buyVehicleRecordService;
    }

    /**
     * POST  /buy-vehicle-records : Create a new buyVehicleRecord.
     *
     * @param buyVehicleRecord the buyVehicleRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new buyVehicleRecord, or with status 400 (Bad Request) if the buyVehicleRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buy-vehicle-records")
    @Timed
    public ResponseEntity<BuyVehicleRecord> createBuyVehicleRecord(@RequestBody BuyVehicleRecord buyVehicleRecord) throws URISyntaxException {
        log.debug("REST request to save BuyVehicleRecord : {}", buyVehicleRecord);
        if (buyVehicleRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new buyVehicleRecord cannot already have an ID")).body(null);
        }
        BuyVehicleRecord result = buyVehicleRecordService.save(buyVehicleRecord);
        return ResponseEntity.created(new URI("/api/buy-vehicle-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buy-vehicle-records : Updates an existing buyVehicleRecord.
     *
     * @param buyVehicleRecord the buyVehicleRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated buyVehicleRecord,
     * or with status 400 (Bad Request) if the buyVehicleRecord is not valid,
     * or with status 500 (Internal Server Error) if the buyVehicleRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buy-vehicle-records")
    @Timed
    public ResponseEntity<BuyVehicleRecord> updateBuyVehicleRecord(@RequestBody BuyVehicleRecord buyVehicleRecord) throws URISyntaxException {
        log.debug("REST request to update BuyVehicleRecord : {}", buyVehicleRecord);
        if (buyVehicleRecord.getId() == null) {
            return createBuyVehicleRecord(buyVehicleRecord);
        }
        BuyVehicleRecord result = buyVehicleRecordService.save(buyVehicleRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, buyVehicleRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /buy-vehicle-records : get all the buyVehicleRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of buyVehicleRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/buy-vehicle-records")
    @Timed
    public ResponseEntity<List<BuyVehicleRecord>> getAllBuyVehicleRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BuyVehicleRecords");
        Page<BuyVehicleRecord> page = buyVehicleRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/buy-vehicle-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /buy-vehicle-records/:id : get the "id" buyVehicleRecord.
     *
     * @param id the id of the buyVehicleRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the buyVehicleRecord, or with status 404 (Not Found)
     */
    @GetMapping("/buy-vehicle-records/{id}")
    @Timed
    public ResponseEntity<BuyVehicleRecord> getBuyVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to get BuyVehicleRecord : {}", id);
        BuyVehicleRecord buyVehicleRecord = buyVehicleRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(buyVehicleRecord));
    }

    /**
     * DELETE  /buy-vehicle-records/:id : delete the "id" buyVehicleRecord.
     *
     * @param id the id of the buyVehicleRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buy-vehicle-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteBuyVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to delete BuyVehicleRecord : {}", id);
        buyVehicleRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
