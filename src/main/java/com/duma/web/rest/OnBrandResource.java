package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.OnBrand;
import com.duma.service.OnBrandService;
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
 * REST controller for managing OnBrand.
 */
@RestController
@RequestMapping("/api")
public class OnBrandResource {

    private final Logger log = LoggerFactory.getLogger(OnBrandResource.class);

    private static final String ENTITY_NAME = "onBrand";
        
    private final OnBrandService onBrandService;

    public OnBrandResource(OnBrandService onBrandService) {
        this.onBrandService = onBrandService;
    }

    /**
     * POST  /on-brands : Create a new onBrand.
     *
     * @param onBrand the onBrand to create
     * @return the ResponseEntity with status 201 (Created) and with body the new onBrand, or with status 400 (Bad Request) if the onBrand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/on-brands")
    @Timed
    public ResponseEntity<OnBrand> createOnBrand(@RequestBody OnBrand onBrand) throws URISyntaxException {
        log.debug("REST request to save OnBrand : {}", onBrand);
        if (onBrand.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new onBrand cannot already have an ID")).body(null);
        }
        OnBrand result = onBrandService.save(onBrand);
        return ResponseEntity.created(new URI("/api/on-brands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /on-brands : Updates an existing onBrand.
     *
     * @param onBrand the onBrand to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated onBrand,
     * or with status 400 (Bad Request) if the onBrand is not valid,
     * or with status 500 (Internal Server Error) if the onBrand couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/on-brands")
    @Timed
    public ResponseEntity<OnBrand> updateOnBrand(@RequestBody OnBrand onBrand) throws URISyntaxException {
        log.debug("REST request to update OnBrand : {}", onBrand);
        if (onBrand.getId() == null) {
            return createOnBrand(onBrand);
        }
        OnBrand result = onBrandService.save(onBrand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, onBrand.getId().toString()))
            .body(result);
    }

    /**
     * GET  /on-brands : get all the onBrands.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of onBrands in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/on-brands")
    @Timed
    public ResponseEntity<List<OnBrand>> getAllOnBrands(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OnBrands");
        Page<OnBrand> page = onBrandService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/on-brands");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /on-brands/:id : get the "id" onBrand.
     *
     * @param id the id of the onBrand to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the onBrand, or with status 404 (Not Found)
     */
    @GetMapping("/on-brands/{id}")
    @Timed
    public ResponseEntity<OnBrand> getOnBrand(@PathVariable Long id) {
        log.debug("REST request to get OnBrand : {}", id);
        OnBrand onBrand = onBrandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(onBrand));
    }

    /**
     * DELETE  /on-brands/:id : delete the "id" onBrand.
     *
     * @param id the id of the onBrand to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/on-brands/{id}")
    @Timed
    public ResponseEntity<Void> deleteOnBrand(@PathVariable Long id) {
        log.debug("REST request to delete OnBrand : {}", id);
        onBrandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
