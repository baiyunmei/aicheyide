package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.GasRefit;
import com.duma.service.GasRefitService;
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
 * REST controller for managing GasRefit.
 */
@RestController
@RequestMapping("/api")
public class GasRefitResource {

    private final Logger log = LoggerFactory.getLogger(GasRefitResource.class);

    private static final String ENTITY_NAME = "gasRefit";
        
    private final GasRefitService gasRefitService;

    public GasRefitResource(GasRefitService gasRefitService) {
        this.gasRefitService = gasRefitService;
    }

    /**
     * POST  /gas-refits : Create a new gasRefit.
     *
     * @param gasRefit the gasRefit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gasRefit, or with status 400 (Bad Request) if the gasRefit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gas-refits")
    @Timed
    public ResponseEntity<GasRefit> createGasRefit(@RequestBody GasRefit gasRefit) throws URISyntaxException {
        log.debug("REST request to save GasRefit : {}", gasRefit);
        if (gasRefit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new gasRefit cannot already have an ID")).body(null);
        }
        GasRefit result = gasRefitService.save(gasRefit);
        return ResponseEntity.created(new URI("/api/gas-refits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gas-refits : Updates an existing gasRefit.
     *
     * @param gasRefit the gasRefit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gasRefit,
     * or with status 400 (Bad Request) if the gasRefit is not valid,
     * or with status 500 (Internal Server Error) if the gasRefit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gas-refits")
    @Timed
    public ResponseEntity<GasRefit> updateGasRefit(@RequestBody GasRefit gasRefit) throws URISyntaxException {
        log.debug("REST request to update GasRefit : {}", gasRefit);
        if (gasRefit.getId() == null) {
            return createGasRefit(gasRefit);
        }
        GasRefit result = gasRefitService.save(gasRefit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gasRefit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gas-refits : get all the gasRefits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gasRefits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/gas-refits")
    @Timed
    public ResponseEntity<List<GasRefit>> getAllGasRefits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of GasRefits");
        Page<GasRefit> page = gasRefitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gas-refits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gas-refits/:id : get the "id" gasRefit.
     *
     * @param id the id of the gasRefit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gasRefit, or with status 404 (Not Found)
     */
    @GetMapping("/gas-refits/{id}")
    @Timed
    public ResponseEntity<GasRefit> getGasRefit(@PathVariable Long id) {
        log.debug("REST request to get GasRefit : {}", id);
        GasRefit gasRefit = gasRefitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(gasRefit));
    }

    /**
     * DELETE  /gas-refits/:id : delete the "id" gasRefit.
     *
     * @param id the id of the gasRefit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gas-refits/{id}")
    @Timed
    public ResponseEntity<Void> deleteGasRefit(@PathVariable Long id) {
        log.debug("REST request to delete GasRefit : {}", id);
        gasRefitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
