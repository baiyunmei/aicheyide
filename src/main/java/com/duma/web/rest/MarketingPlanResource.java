package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.MarketingPlan;
import com.duma.service.MarketingPlanService;
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
 * REST controller for managing MarketingPlan.
 */
@RestController
@RequestMapping("/api")
public class MarketingPlanResource {

    private final Logger log = LoggerFactory.getLogger(MarketingPlanResource.class);

    private static final String ENTITY_NAME = "marketingPlan";
        
    private final MarketingPlanService marketingPlanService;

    public MarketingPlanResource(MarketingPlanService marketingPlanService) {
        this.marketingPlanService = marketingPlanService;
    }

    /**
     * POST  /marketing-plans : Create a new marketingPlan.
     *
     * @param marketingPlan the marketingPlan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new marketingPlan, or with status 400 (Bad Request) if the marketingPlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/marketing-plans")
    @Timed
    public ResponseEntity<MarketingPlan> createMarketingPlan(@RequestBody MarketingPlan marketingPlan) throws URISyntaxException {
        log.debug("REST request to save MarketingPlan : {}", marketingPlan);
        if (marketingPlan.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new marketingPlan cannot already have an ID")).body(null);
        }
        MarketingPlan result = marketingPlanService.save(marketingPlan);
        return ResponseEntity.created(new URI("/api/marketing-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /marketing-plans : Updates an existing marketingPlan.
     *
     * @param marketingPlan the marketingPlan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated marketingPlan,
     * or with status 400 (Bad Request) if the marketingPlan is not valid,
     * or with status 500 (Internal Server Error) if the marketingPlan couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/marketing-plans")
    @Timed
    public ResponseEntity<MarketingPlan> updateMarketingPlan(@RequestBody MarketingPlan marketingPlan) throws URISyntaxException {
        log.debug("REST request to update MarketingPlan : {}", marketingPlan);
        if (marketingPlan.getId() == null) {
            return createMarketingPlan(marketingPlan);
        }
        MarketingPlan result = marketingPlanService.save(marketingPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, marketingPlan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /marketing-plans : get all the marketingPlans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of marketingPlans in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/marketing-plans")
    @Timed
    public ResponseEntity<List<MarketingPlan>> getAllMarketingPlans(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MarketingPlans");
        Page<MarketingPlan> page = marketingPlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/marketing-plans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /marketing-plans/:id : get the "id" marketingPlan.
     *
     * @param id the id of the marketingPlan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the marketingPlan, or with status 404 (Not Found)
     */
    @GetMapping("/marketing-plans/{id}")
    @Timed
    public ResponseEntity<MarketingPlan> getMarketingPlan(@PathVariable Long id) {
        log.debug("REST request to get MarketingPlan : {}", id);
        MarketingPlan marketingPlan = marketingPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(marketingPlan));
    }

    /**
     * DELETE  /marketing-plans/:id : delete the "id" marketingPlan.
     *
     * @param id the id of the marketingPlan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/marketing-plans/{id}")
    @Timed
    public ResponseEntity<Void> deleteMarketingPlan(@PathVariable Long id) {
        log.debug("REST request to delete MarketingPlan : {}", id);
        marketingPlanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
