package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.PurchaseVehicleRecord;
import com.duma.service.PurchaseVehicleRecordService;
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
 * REST controller for managing PurchaseVehicleRecord.
 */
@RestController
@RequestMapping("/api")
public class PurchaseVehicleRecordResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseVehicleRecordResource.class);

    private static final String ENTITY_NAME = "purchaseVehicleRecord";
        
    private final PurchaseVehicleRecordService purchaseVehicleRecordService;

    public PurchaseVehicleRecordResource(PurchaseVehicleRecordService purchaseVehicleRecordService) {
        this.purchaseVehicleRecordService = purchaseVehicleRecordService;
    }

    /**
     * POST  /purchase-vehicle-records : Create a new purchaseVehicleRecord.
     *
     * @param purchaseVehicleRecord the purchaseVehicleRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseVehicleRecord, or with status 400 (Bad Request) if the purchaseVehicleRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchase-vehicle-records")
    @Timed
    public ResponseEntity<PurchaseVehicleRecord> createPurchaseVehicleRecord(@RequestBody PurchaseVehicleRecord purchaseVehicleRecord) throws URISyntaxException {
        log.debug("REST request to save PurchaseVehicleRecord : {}", purchaseVehicleRecord);
        if (purchaseVehicleRecord.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new purchaseVehicleRecord cannot already have an ID")).body(null);
        }
        PurchaseVehicleRecord result = purchaseVehicleRecordService.save(purchaseVehicleRecord);
        return ResponseEntity.created(new URI("/api/purchase-vehicle-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchase-vehicle-records : Updates an existing purchaseVehicleRecord.
     *
     * @param purchaseVehicleRecord the purchaseVehicleRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseVehicleRecord,
     * or with status 400 (Bad Request) if the purchaseVehicleRecord is not valid,
     * or with status 500 (Internal Server Error) if the purchaseVehicleRecord couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/purchase-vehicle-records")
    @Timed
    public ResponseEntity<PurchaseVehicleRecord> updatePurchaseVehicleRecord(@RequestBody PurchaseVehicleRecord purchaseVehicleRecord) throws URISyntaxException {
        log.debug("REST request to update PurchaseVehicleRecord : {}", purchaseVehicleRecord);
        if (purchaseVehicleRecord.getId() == null) {
            return createPurchaseVehicleRecord(purchaseVehicleRecord);
        }
        PurchaseVehicleRecord result = purchaseVehicleRecordService.save(purchaseVehicleRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseVehicleRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /purchase-vehicle-records : get all the purchaseVehicleRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of purchaseVehicleRecords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/purchase-vehicle-records")
    @Timed
    public ResponseEntity<List<PurchaseVehicleRecord>> getAllPurchaseVehicleRecords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PurchaseVehicleRecords");
        Page<PurchaseVehicleRecord> page = purchaseVehicleRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-vehicle-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchase-vehicle-records/:id : get the "id" purchaseVehicleRecord.
     *
     * @param id the id of the purchaseVehicleRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the purchaseVehicleRecord, or with status 404 (Not Found)
     */
    @GetMapping("/purchase-vehicle-records/{id}")
    @Timed
    public ResponseEntity<PurchaseVehicleRecord> getPurchaseVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to get PurchaseVehicleRecord : {}", id);
        PurchaseVehicleRecord purchaseVehicleRecord = purchaseVehicleRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(purchaseVehicleRecord));
    }

    /**
     * DELETE  /purchase-vehicle-records/:id : delete the "id" purchaseVehicleRecord.
     *
     * @param id the id of the purchaseVehicleRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/purchase-vehicle-records/{id}")
    @Timed
    public ResponseEntity<Void> deletePurchaseVehicleRecord(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseVehicleRecord : {}", id);
        purchaseVehicleRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
