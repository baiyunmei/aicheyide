package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.InsurancePurchase;
import com.duma.service.InsurancePurchaseService;
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
 * REST controller for managing InsurancePurchase.
 */
@RestController
@RequestMapping("/api")
public class InsurancePurchaseResource {

    private final Logger log = LoggerFactory.getLogger(InsurancePurchaseResource.class);

    private static final String ENTITY_NAME = "insurancePurchase";
        
    private final InsurancePurchaseService insurancePurchaseService;

    public InsurancePurchaseResource(InsurancePurchaseService insurancePurchaseService) {
        this.insurancePurchaseService = insurancePurchaseService;
    }

    /**
     * POST  /insurance-purchases : Create a new insurancePurchase.
     *
     * @param insurancePurchase the insurancePurchase to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insurancePurchase, or with status 400 (Bad Request) if the insurancePurchase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insurance-purchases")
    @Timed
    public ResponseEntity<InsurancePurchase> createInsurancePurchase(@RequestBody InsurancePurchase insurancePurchase) throws URISyntaxException {
        log.debug("REST request to save InsurancePurchase : {}", insurancePurchase);
        if (insurancePurchase.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new insurancePurchase cannot already have an ID")).body(null);
        }
        InsurancePurchase result = insurancePurchaseService.save(insurancePurchase);
        return ResponseEntity.created(new URI("/api/insurance-purchases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insurance-purchases : Updates an existing insurancePurchase.
     *
     * @param insurancePurchase the insurancePurchase to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insurancePurchase,
     * or with status 400 (Bad Request) if the insurancePurchase is not valid,
     * or with status 500 (Internal Server Error) if the insurancePurchase couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insurance-purchases")
    @Timed
    public ResponseEntity<InsurancePurchase> updateInsurancePurchase(@RequestBody InsurancePurchase insurancePurchase) throws URISyntaxException {
        log.debug("REST request to update InsurancePurchase : {}", insurancePurchase);
        if (insurancePurchase.getId() == null) {
            return createInsurancePurchase(insurancePurchase);
        }
        InsurancePurchase result = insurancePurchaseService.save(insurancePurchase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insurancePurchase.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insurance-purchases : get all the insurancePurchases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of insurancePurchases in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/insurance-purchases")
    @Timed
    public ResponseEntity<List<InsurancePurchase>> getAllInsurancePurchases(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of InsurancePurchases");
        Page<InsurancePurchase> page = insurancePurchaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/insurance-purchases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /insurance-purchases/:id : get the "id" insurancePurchase.
     *
     * @param id the id of the insurancePurchase to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insurancePurchase, or with status 404 (Not Found)
     */
    @GetMapping("/insurance-purchases/{id}")
    @Timed
    public ResponseEntity<InsurancePurchase> getInsurancePurchase(@PathVariable Long id) {
        log.debug("REST request to get InsurancePurchase : {}", id);
        InsurancePurchase insurancePurchase = insurancePurchaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(insurancePurchase));
    }

    /**
     * DELETE  /insurance-purchases/:id : delete the "id" insurancePurchase.
     *
     * @param id the id of the insurancePurchase to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insurance-purchases/{id}")
    @Timed
    public ResponseEntity<Void> deleteInsurancePurchase(@PathVariable Long id) {
        log.debug("REST request to delete InsurancePurchase : {}", id);
        insurancePurchaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
