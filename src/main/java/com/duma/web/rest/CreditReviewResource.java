package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.CreditReview;
import com.duma.service.CreditReviewService;
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
 * REST controller for managing CreditReview.
 */
@RestController
@RequestMapping("/api")
public class CreditReviewResource {

    private final Logger log = LoggerFactory.getLogger(CreditReviewResource.class);

    private static final String ENTITY_NAME = "creditReview";
        
    private final CreditReviewService creditReviewService;

    public CreditReviewResource(CreditReviewService creditReviewService) {
        this.creditReviewService = creditReviewService;
    }

    /**
     * POST  /credit-reviews : Create a new creditReview.
     *
     * @param creditReview the creditReview to create
     * @return the ResponseEntity with status 201 (Created) and with body the new creditReview, or with status 400 (Bad Request) if the creditReview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/credit-reviews")
    @Timed
    public ResponseEntity<CreditReview> createCreditReview(@RequestBody CreditReview creditReview) throws URISyntaxException {
        log.debug("REST request to save CreditReview : {}", creditReview);
        if (creditReview.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new creditReview cannot already have an ID")).body(null);
        }
        CreditReview result = creditReviewService.save(creditReview);
        return ResponseEntity.created(new URI("/api/credit-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /credit-reviews : Updates an existing creditReview.
     *
     * @param creditReview the creditReview to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated creditReview,
     * or with status 400 (Bad Request) if the creditReview is not valid,
     * or with status 500 (Internal Server Error) if the creditReview couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/credit-reviews")
    @Timed
    public ResponseEntity<CreditReview> updateCreditReview(@RequestBody CreditReview creditReview) throws URISyntaxException {
        log.debug("REST request to update CreditReview : {}", creditReview);
        if (creditReview.getId() == null) {
            return createCreditReview(creditReview);
        }
        CreditReview result = creditReviewService.save(creditReview);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, creditReview.getId().toString()))
            .body(result);
    }

    /**
     * GET  /credit-reviews : get all the creditReviews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of creditReviews in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/credit-reviews")
    @Timed
    public ResponseEntity<List<CreditReview>> getAllCreditReviews(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CreditReviews");
        Page<CreditReview> page = creditReviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/credit-reviews");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /credit-reviews/:id : get the "id" creditReview.
     *
     * @param id the id of the creditReview to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the creditReview, or with status 404 (Not Found)
     */
    @GetMapping("/credit-reviews/{id}")
    @Timed
    public ResponseEntity<CreditReview> getCreditReview(@PathVariable Long id) {
        log.debug("REST request to get CreditReview : {}", id);
        CreditReview creditReview = creditReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(creditReview));
    }

    /**
     * DELETE  /credit-reviews/:id : delete the "id" creditReview.
     *
     * @param id the id of the creditReview to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/credit-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteCreditReview(@PathVariable Long id) {
        log.debug("REST request to delete CreditReview : {}", id);
        creditReviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
