package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.DownPayment;
import com.duma.service.DownPaymentService;
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
 * REST controller for managing DownPayment.
 */
@RestController
@RequestMapping("/api")
public class DownPaymentResource {

    private final Logger log = LoggerFactory.getLogger(DownPaymentResource.class);

    private static final String ENTITY_NAME = "downPayment";
        
    private final DownPaymentService downPaymentService;

    public DownPaymentResource(DownPaymentService downPaymentService) {
        this.downPaymentService = downPaymentService;
    }

    /**
     * POST  /down-payments : Create a new downPayment.
     *
     * @param downPayment the downPayment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new downPayment, or with status 400 (Bad Request) if the downPayment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/down-payments")
    @Timed
    public ResponseEntity<DownPayment> createDownPayment(@RequestBody DownPayment downPayment) throws URISyntaxException {
        log.debug("REST request to save DownPayment : {}", downPayment);
        if (downPayment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new downPayment cannot already have an ID")).body(null);
        }
        DownPayment result = downPaymentService.save(downPayment);
        return ResponseEntity.created(new URI("/api/down-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /down-payments : Updates an existing downPayment.
     *
     * @param downPayment the downPayment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated downPayment,
     * or with status 400 (Bad Request) if the downPayment is not valid,
     * or with status 500 (Internal Server Error) if the downPayment couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/down-payments")
    @Timed
    public ResponseEntity<DownPayment> updateDownPayment(@RequestBody DownPayment downPayment) throws URISyntaxException {
        log.debug("REST request to update DownPayment : {}", downPayment);
        if (downPayment.getId() == null) {
            return createDownPayment(downPayment);
        }
        DownPayment result = downPaymentService.save(downPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, downPayment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /down-payments : get all the downPayments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of downPayments in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/down-payments")
    @Timed
    public ResponseEntity<List<DownPayment>> getAllDownPayments(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DownPayments");
        Page<DownPayment> page = downPaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/down-payments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /down-payments/:id : get the "id" downPayment.
     *
     * @param id the id of the downPayment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the downPayment, or with status 404 (Not Found)
     */
    @GetMapping("/down-payments/{id}")
    @Timed
    public ResponseEntity<DownPayment> getDownPayment(@PathVariable Long id) {
        log.debug("REST request to get DownPayment : {}", id);
        DownPayment downPayment = downPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(downPayment));
    }

    /**
     * DELETE  /down-payments/:id : delete the "id" downPayment.
     *
     * @param id the id of the downPayment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/down-payments/{id}")
    @Timed
    public ResponseEntity<Void> deleteDownPayment(@PathVariable Long id) {
        log.debug("REST request to delete DownPayment : {}", id);
        downPaymentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
