package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.DriverMessage;
import com.duma.service.DriverMessageService;
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
 * REST controller for managing DriverMessage.
 */
@RestController
@RequestMapping("/api")
public class DriverMessageResource {

    private final Logger log = LoggerFactory.getLogger(DriverMessageResource.class);

    private static final String ENTITY_NAME = "driverMessage";
        
    private final DriverMessageService driverMessageService;

    public DriverMessageResource(DriverMessageService driverMessageService) {
        this.driverMessageService = driverMessageService;
    }

    /**
     * POST  /driver-messages : Create a new driverMessage.
     *
     * @param driverMessage the driverMessage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new driverMessage, or with status 400 (Bad Request) if the driverMessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/driver-messages")
    @Timed
    public ResponseEntity<DriverMessage> createDriverMessage(@RequestBody DriverMessage driverMessage) throws URISyntaxException {
        log.debug("REST request to save DriverMessage : {}", driverMessage);
        if (driverMessage.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new driverMessage cannot already have an ID")).body(null);
        }
        DriverMessage result = driverMessageService.save(driverMessage);
        return ResponseEntity.created(new URI("/api/driver-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /driver-messages : Updates an existing driverMessage.
     *
     * @param driverMessage the driverMessage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated driverMessage,
     * or with status 400 (Bad Request) if the driverMessage is not valid,
     * or with status 500 (Internal Server Error) if the driverMessage couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/driver-messages")
    @Timed
    public ResponseEntity<DriverMessage> updateDriverMessage(@RequestBody DriverMessage driverMessage) throws URISyntaxException {
        log.debug("REST request to update DriverMessage : {}", driverMessage);
        if (driverMessage.getId() == null) {
            return createDriverMessage(driverMessage);
        }
        DriverMessage result = driverMessageService.save(driverMessage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, driverMessage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /driver-messages : get all the driverMessages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of driverMessages in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/driver-messages")
    @Timed
    public ResponseEntity<List<DriverMessage>> getAllDriverMessages(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DriverMessages");
        Page<DriverMessage> page = driverMessageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/driver-messages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /driver-messages/:id : get the "id" driverMessage.
     *
     * @param id the id of the driverMessage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the driverMessage, or with status 404 (Not Found)
     */
    @GetMapping("/driver-messages/{id}")
    @Timed
    public ResponseEntity<DriverMessage> getDriverMessage(@PathVariable Long id) {
        log.debug("REST request to get DriverMessage : {}", id);
        DriverMessage driverMessage = driverMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(driverMessage));
    }

    /**
     * DELETE  /driver-messages/:id : delete the "id" driverMessage.
     *
     * @param id the id of the driverMessage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/driver-messages/{id}")
    @Timed
    public ResponseEntity<Void> deleteDriverMessage(@PathVariable Long id) {
        log.debug("REST request to delete DriverMessage : {}", id);
        driverMessageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
