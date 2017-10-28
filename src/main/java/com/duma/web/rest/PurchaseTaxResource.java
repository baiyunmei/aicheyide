package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.PurchaseTax;
import com.duma.service.PurchaseTaxService;
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
 * REST controller for managing PurchaseTax.
 */
@RestController
@RequestMapping("/api")
public class PurchaseTaxResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseTaxResource.class);

    private static final String ENTITY_NAME = "purchaseTax";
        
    private final PurchaseTaxService purchaseTaxService;

    public PurchaseTaxResource(PurchaseTaxService purchaseTaxService) {
        this.purchaseTaxService = purchaseTaxService;
    }

    /**
     * POST  /purchase-taxes : Create a new purchaseTax.
     *
     * @param purchaseTax the purchaseTax to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseTax, or with status 400 (Bad Request) if the purchaseTax has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchase-taxes")
    @Timed
    public ResponseEntity<PurchaseTax> createPurchaseTax(@RequestBody PurchaseTax purchaseTax) throws URISyntaxException {
        log.debug("REST request to save PurchaseTax : {}", purchaseTax);
        if (purchaseTax.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new purchaseTax cannot already have an ID")).body(null);
        }
        PurchaseTax result = purchaseTaxService.save(purchaseTax);
        return ResponseEntity.created(new URI("/api/purchase-taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchase-taxes : Updates an existing purchaseTax.
     *
     * @param purchaseTax the purchaseTax to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseTax,
     * or with status 400 (Bad Request) if the purchaseTax is not valid,
     * or with status 500 (Internal Server Error) if the purchaseTax couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/purchase-taxes")
    @Timed
    public ResponseEntity<PurchaseTax> updatePurchaseTax(@RequestBody PurchaseTax purchaseTax) throws URISyntaxException {
        log.debug("REST request to update PurchaseTax : {}", purchaseTax);
        if (purchaseTax.getId() == null) {
            return createPurchaseTax(purchaseTax);
        }
        PurchaseTax result = purchaseTaxService.save(purchaseTax);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseTax.getId().toString()))
            .body(result);
    }

    /**
     * GET  /purchase-taxes : get all the purchaseTaxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of purchaseTaxes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/purchase-taxes")
    @Timed
    public ResponseEntity<List<PurchaseTax>> getAllPurchaseTaxes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PurchaseTaxes");
        Page<PurchaseTax> page = purchaseTaxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-taxes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchase-taxes/:id : get the "id" purchaseTax.
     *
     * @param id the id of the purchaseTax to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the purchaseTax, or with status 404 (Not Found)
     */
    @GetMapping("/purchase-taxes/{id}")
    @Timed
    public ResponseEntity<PurchaseTax> getPurchaseTax(@PathVariable Long id) {
        log.debug("REST request to get PurchaseTax : {}", id);
        PurchaseTax purchaseTax = purchaseTaxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(purchaseTax));
    }

    /**
     * DELETE  /purchase-taxes/:id : delete the "id" purchaseTax.
     *
     * @param id the id of the purchaseTax to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/purchase-taxes/{id}")
    @Timed
    public ResponseEntity<Void> deletePurchaseTax(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseTax : {}", id);
        purchaseTaxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
