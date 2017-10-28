package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.ForcedSettle;
import com.duma.service.ForcedSettleService;
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
 * REST controller for managing ForcedSettle.
 */
@RestController
@RequestMapping("/api")
public class ForcedSettleResource {

    private final Logger log = LoggerFactory.getLogger(ForcedSettleResource.class);

    private static final String ENTITY_NAME = "forcedSettle";
        
    private final ForcedSettleService forcedSettleService;

    public ForcedSettleResource(ForcedSettleService forcedSettleService) {
        this.forcedSettleService = forcedSettleService;
    }

    /**
     * POST  /forced-settles : Create a new forcedSettle.
     *
     * @param forcedSettle the forcedSettle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new forcedSettle, or with status 400 (Bad Request) if the forcedSettle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/forced-settles")
    @Timed
    public ResponseEntity<ForcedSettle> createForcedSettle(@RequestBody ForcedSettle forcedSettle) throws URISyntaxException {
        log.debug("REST request to save ForcedSettle : {}", forcedSettle);
        if (forcedSettle.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new forcedSettle cannot already have an ID")).body(null);
        }
        ForcedSettle result = forcedSettleService.save(forcedSettle);
        return ResponseEntity.created(new URI("/api/forced-settles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /forced-settles : Updates an existing forcedSettle.
     *
     * @param forcedSettle the forcedSettle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated forcedSettle,
     * or with status 400 (Bad Request) if the forcedSettle is not valid,
     * or with status 500 (Internal Server Error) if the forcedSettle couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/forced-settles")
    @Timed
    public ResponseEntity<ForcedSettle> updateForcedSettle(@RequestBody ForcedSettle forcedSettle) throws URISyntaxException {
        log.debug("REST request to update ForcedSettle : {}", forcedSettle);
        if (forcedSettle.getId() == null) {
            return createForcedSettle(forcedSettle);
        }
        ForcedSettle result = forcedSettleService.save(forcedSettle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, forcedSettle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /forced-settles : get all the forcedSettles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of forcedSettles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/forced-settles")
    @Timed
    public ResponseEntity<List<ForcedSettle>> getAllForcedSettles(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ForcedSettles");
        Page<ForcedSettle> page = forcedSettleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/forced-settles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /forced-settles/:id : get the "id" forcedSettle.
     *
     * @param id the id of the forcedSettle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the forcedSettle, or with status 404 (Not Found)
     */
    @GetMapping("/forced-settles/{id}")
    @Timed
    public ResponseEntity<ForcedSettle> getForcedSettle(@PathVariable Long id) {
        log.debug("REST request to get ForcedSettle : {}", id);
        ForcedSettle forcedSettle = forcedSettleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(forcedSettle));
    }

    /**
     * DELETE  /forced-settles/:id : delete the "id" forcedSettle.
     *
     * @param id the id of the forcedSettle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/forced-settles/{id}")
    @Timed
    public ResponseEntity<Void> deleteForcedSettle(@PathVariable Long id) {
        log.debug("REST request to delete ForcedSettle : {}", id);
        forcedSettleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
