package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.OverdueGathering;
import com.duma.service.OverdueGatheringService;
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
 * REST controller for managing OverdueGathering.
 */
@RestController
@RequestMapping("/api")
public class OverdueGatheringResource {

    private final Logger log = LoggerFactory.getLogger(OverdueGatheringResource.class);

    private static final String ENTITY_NAME = "overdueGathering";
        
    private final OverdueGatheringService overdueGatheringService;

    public OverdueGatheringResource(OverdueGatheringService overdueGatheringService) {
        this.overdueGatheringService = overdueGatheringService;
    }

    /**
     * POST  /overdue-gatherings : Create a new overdueGathering.
     *
     * @param overdueGathering the overdueGathering to create
     * @return the ResponseEntity with status 201 (Created) and with body the new overdueGathering, or with status 400 (Bad Request) if the overdueGathering has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/overdue-gatherings")
    @Timed
    public ResponseEntity<OverdueGathering> createOverdueGathering(@RequestBody OverdueGathering overdueGathering) throws URISyntaxException {
        log.debug("REST request to save OverdueGathering : {}", overdueGathering);
        if (overdueGathering.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new overdueGathering cannot already have an ID")).body(null);
        }
        OverdueGathering result = overdueGatheringService.save(overdueGathering);
        return ResponseEntity.created(new URI("/api/overdue-gatherings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /overdue-gatherings : Updates an existing overdueGathering.
     *
     * @param overdueGathering the overdueGathering to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated overdueGathering,
     * or with status 400 (Bad Request) if the overdueGathering is not valid,
     * or with status 500 (Internal Server Error) if the overdueGathering couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/overdue-gatherings")
    @Timed
    public ResponseEntity<OverdueGathering> updateOverdueGathering(@RequestBody OverdueGathering overdueGathering) throws URISyntaxException {
        log.debug("REST request to update OverdueGathering : {}", overdueGathering);
        if (overdueGathering.getId() == null) {
            return createOverdueGathering(overdueGathering);
        }
        OverdueGathering result = overdueGatheringService.save(overdueGathering);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, overdueGathering.getId().toString()))
            .body(result);
    }

    /**
     * GET  /overdue-gatherings : get all the overdueGatherings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of overdueGatherings in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/overdue-gatherings")
    @Timed
    public ResponseEntity<List<OverdueGathering>> getAllOverdueGatherings(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OverdueGatherings");
        Page<OverdueGathering> page = overdueGatheringService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/overdue-gatherings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /overdue-gatherings/:id : get the "id" overdueGathering.
     *
     * @param id the id of the overdueGathering to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the overdueGathering, or with status 404 (Not Found)
     */
    @GetMapping("/overdue-gatherings/{id}")
    @Timed
    public ResponseEntity<OverdueGathering> getOverdueGathering(@PathVariable Long id) {
        log.debug("REST request to get OverdueGathering : {}", id);
        OverdueGathering overdueGathering = overdueGatheringService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(overdueGathering));
    }

    /**
     * DELETE  /overdue-gatherings/:id : delete the "id" overdueGathering.
     *
     * @param id the id of the overdueGathering to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/overdue-gatherings/{id}")
    @Timed
    public ResponseEntity<Void> deleteOverdueGathering(@PathVariable Long id) {
        log.debug("REST request to delete OverdueGathering : {}", id);
        overdueGatheringService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
