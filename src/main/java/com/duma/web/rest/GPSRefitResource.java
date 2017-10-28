package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.GPSRefit;
import com.duma.service.GPSRefitService;
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
 * REST controller for managing GPSRefit.
 */
@RestController
@RequestMapping("/api")
public class GPSRefitResource {

    private final Logger log = LoggerFactory.getLogger(GPSRefitResource.class);

    private static final String ENTITY_NAME = "gPSRefit";
        
    private final GPSRefitService gPSRefitService;

    public GPSRefitResource(GPSRefitService gPSRefitService) {
        this.gPSRefitService = gPSRefitService;
    }

    /**
     * POST  /g-ps-refits : Create a new gPSRefit.
     *
     * @param gPSRefit the gPSRefit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gPSRefit, or with status 400 (Bad Request) if the gPSRefit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/g-ps-refits")
    @Timed
    public ResponseEntity<GPSRefit> createGPSRefit(@RequestBody GPSRefit gPSRefit) throws URISyntaxException {
        log.debug("REST request to save GPSRefit : {}", gPSRefit);
        if (gPSRefit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new gPSRefit cannot already have an ID")).body(null);
        }
        GPSRefit result = gPSRefitService.save(gPSRefit);
        return ResponseEntity.created(new URI("/api/g-ps-refits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /g-ps-refits : Updates an existing gPSRefit.
     *
     * @param gPSRefit the gPSRefit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gPSRefit,
     * or with status 400 (Bad Request) if the gPSRefit is not valid,
     * or with status 500 (Internal Server Error) if the gPSRefit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/g-ps-refits")
    @Timed
    public ResponseEntity<GPSRefit> updateGPSRefit(@RequestBody GPSRefit gPSRefit) throws URISyntaxException {
        log.debug("REST request to update GPSRefit : {}", gPSRefit);
        if (gPSRefit.getId() == null) {
            return createGPSRefit(gPSRefit);
        }
        GPSRefit result = gPSRefitService.save(gPSRefit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gPSRefit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /g-ps-refits : get all the gPSRefits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gPSRefits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/g-ps-refits")
    @Timed
    public ResponseEntity<List<GPSRefit>> getAllGPSRefits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of GPSRefits");
        Page<GPSRefit> page = gPSRefitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/g-ps-refits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /g-ps-refits/:id : get the "id" gPSRefit.
     *
     * @param id the id of the gPSRefit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gPSRefit, or with status 404 (Not Found)
     */
    @GetMapping("/g-ps-refits/{id}")
    @Timed
    public ResponseEntity<GPSRefit> getGPSRefit(@PathVariable Long id) {
        log.debug("REST request to get GPSRefit : {}", id);
        GPSRefit gPSRefit = gPSRefitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(gPSRefit));
    }

    /**
     * DELETE  /g-ps-refits/:id : delete the "id" gPSRefit.
     *
     * @param id the id of the gPSRefit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/g-ps-refits/{id}")
    @Timed
    public ResponseEntity<Void> deleteGPSRefit(@PathVariable Long id) {
        log.debug("REST request to delete GPSRefit : {}", id);
        gPSRefitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
