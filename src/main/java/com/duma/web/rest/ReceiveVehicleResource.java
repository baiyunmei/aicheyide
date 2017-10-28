package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.ReceiveVehicle;
import com.duma.service.ReceiveVehicleService;
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
 * REST controller for managing ReceiveVehicle.
 */
@RestController
@RequestMapping("/api")
public class ReceiveVehicleResource {

    private final Logger log = LoggerFactory.getLogger(ReceiveVehicleResource.class);

    private static final String ENTITY_NAME = "receiveVehicle";
        
    private final ReceiveVehicleService receiveVehicleService;

    public ReceiveVehicleResource(ReceiveVehicleService receiveVehicleService) {
        this.receiveVehicleService = receiveVehicleService;
    }

    /**
     * POST  /receive-vehicles : Create a new receiveVehicle.
     *
     * @param receiveVehicle the receiveVehicle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new receiveVehicle, or with status 400 (Bad Request) if the receiveVehicle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/receive-vehicles")
    @Timed
    public ResponseEntity<ReceiveVehicle> createReceiveVehicle(@RequestBody ReceiveVehicle receiveVehicle) throws URISyntaxException {
        log.debug("REST request to save ReceiveVehicle : {}", receiveVehicle);
        if (receiveVehicle.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new receiveVehicle cannot already have an ID")).body(null);
        }
        ReceiveVehicle result = receiveVehicleService.save(receiveVehicle);
        return ResponseEntity.created(new URI("/api/receive-vehicles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /receive-vehicles : Updates an existing receiveVehicle.
     *
     * @param receiveVehicle the receiveVehicle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated receiveVehicle,
     * or with status 400 (Bad Request) if the receiveVehicle is not valid,
     * or with status 500 (Internal Server Error) if the receiveVehicle couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/receive-vehicles")
    @Timed
    public ResponseEntity<ReceiveVehicle> updateReceiveVehicle(@RequestBody ReceiveVehicle receiveVehicle) throws URISyntaxException {
        log.debug("REST request to update ReceiveVehicle : {}", receiveVehicle);
        if (receiveVehicle.getId() == null) {
            return createReceiveVehicle(receiveVehicle);
        }
        ReceiveVehicle result = receiveVehicleService.save(receiveVehicle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, receiveVehicle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /receive-vehicles : get all the receiveVehicles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of receiveVehicles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/receive-vehicles")
    @Timed
    public ResponseEntity<List<ReceiveVehicle>> getAllReceiveVehicles(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ReceiveVehicles");
        Page<ReceiveVehicle> page = receiveVehicleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receive-vehicles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /receive-vehicles/:id : get the "id" receiveVehicle.
     *
     * @param id the id of the receiveVehicle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the receiveVehicle, or with status 404 (Not Found)
     */
    @GetMapping("/receive-vehicles/{id}")
    @Timed
    public ResponseEntity<ReceiveVehicle> getReceiveVehicle(@PathVariable Long id) {
        log.debug("REST request to get ReceiveVehicle : {}", id);
        ReceiveVehicle receiveVehicle = receiveVehicleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(receiveVehicle));
    }

    /**
     * DELETE  /receive-vehicles/:id : delete the "id" receiveVehicle.
     *
     * @param id the id of the receiveVehicle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/receive-vehicles/{id}")
    @Timed
    public ResponseEntity<Void> deleteReceiveVehicle(@PathVariable Long id) {
        log.debug("REST request to delete ReceiveVehicle : {}", id);
        receiveVehicleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
