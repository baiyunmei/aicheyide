package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.SettleApply;
import com.duma.service.SettleApplyService;
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
 * REST controller for managing SettleApply.
 */
@RestController
@RequestMapping("/api")
public class SettleApplyResource {

    private final Logger log = LoggerFactory.getLogger(SettleApplyResource.class);

    private static final String ENTITY_NAME = "settleApply";
        
    private final SettleApplyService settleApplyService;

    public SettleApplyResource(SettleApplyService settleApplyService) {
        this.settleApplyService = settleApplyService;
    }

    /**
     * POST  /settle-applies : Create a new settleApply.
     *
     * @param settleApply the settleApply to create
     * @return the ResponseEntity with status 201 (Created) and with body the new settleApply, or with status 400 (Bad Request) if the settleApply has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/settle-applies")
    @Timed
    public ResponseEntity<SettleApply> createSettleApply(@RequestBody SettleApply settleApply) throws URISyntaxException {
        log.debug("REST request to save SettleApply : {}", settleApply);
        if (settleApply.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new settleApply cannot already have an ID")).body(null);
        }
        SettleApply result = settleApplyService.save(settleApply);
        return ResponseEntity.created(new URI("/api/settle-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /settle-applies : Updates an existing settleApply.
     *
     * @param settleApply the settleApply to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated settleApply,
     * or with status 400 (Bad Request) if the settleApply is not valid,
     * or with status 500 (Internal Server Error) if the settleApply couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/settle-applies")
    @Timed
    public ResponseEntity<SettleApply> updateSettleApply(@RequestBody SettleApply settleApply) throws URISyntaxException {
        log.debug("REST request to update SettleApply : {}", settleApply);
        if (settleApply.getId() == null) {
            return createSettleApply(settleApply);
        }
        SettleApply result = settleApplyService.save(settleApply);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, settleApply.getId().toString()))
            .body(result);
    }

    /**
     * GET  /settle-applies : get all the settleApplies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of settleApplies in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/settle-applies")
    @Timed
    public ResponseEntity<List<SettleApply>> getAllSettleApplies(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SettleApplies");
        Page<SettleApply> page = settleApplyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/settle-applies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /settle-applies/:id : get the "id" settleApply.
     *
     * @param id the id of the settleApply to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the settleApply, or with status 404 (Not Found)
     */
    @GetMapping("/settle-applies/{id}")
    @Timed
    public ResponseEntity<SettleApply> getSettleApply(@PathVariable Long id) {
        log.debug("REST request to get SettleApply : {}", id);
        SettleApply settleApply = settleApplyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(settleApply));
    }

    /**
     * DELETE  /settle-applies/:id : delete the "id" settleApply.
     *
     * @param id the id of the settleApply to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/settle-applies/{id}")
    @Timed
    public ResponseEntity<Void> deleteSettleApply(@PathVariable Long id) {
        log.debug("REST request to delete SettleApply : {}", id);
        settleApplyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
