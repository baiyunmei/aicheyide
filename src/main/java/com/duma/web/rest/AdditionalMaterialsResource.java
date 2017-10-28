package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.AdditionalMaterials;
import com.duma.service.AdditionalMaterialsService;
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
 * REST controller for managing AdditionalMaterials.
 */
@RestController
@RequestMapping("/api")
public class AdditionalMaterialsResource {

    private final Logger log = LoggerFactory.getLogger(AdditionalMaterialsResource.class);

    private static final String ENTITY_NAME = "additionalMaterials";
        
    private final AdditionalMaterialsService additionalMaterialsService;

    public AdditionalMaterialsResource(AdditionalMaterialsService additionalMaterialsService) {
        this.additionalMaterialsService = additionalMaterialsService;
    }

    /**
     * POST  /additional-materials : Create a new additionalMaterials.
     *
     * @param additionalMaterials the additionalMaterials to create
     * @return the ResponseEntity with status 201 (Created) and with body the new additionalMaterials, or with status 400 (Bad Request) if the additionalMaterials has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/additional-materials")
    @Timed
    public ResponseEntity<AdditionalMaterials> createAdditionalMaterials(@RequestBody AdditionalMaterials additionalMaterials) throws URISyntaxException {
        log.debug("REST request to save AdditionalMaterials : {}", additionalMaterials);
        if (additionalMaterials.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new additionalMaterials cannot already have an ID")).body(null);
        }
        AdditionalMaterials result = additionalMaterialsService.save(additionalMaterials);
        return ResponseEntity.created(new URI("/api/additional-materials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /additional-materials : Updates an existing additionalMaterials.
     *
     * @param additionalMaterials the additionalMaterials to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated additionalMaterials,
     * or with status 400 (Bad Request) if the additionalMaterials is not valid,
     * or with status 500 (Internal Server Error) if the additionalMaterials couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/additional-materials")
    @Timed
    public ResponseEntity<AdditionalMaterials> updateAdditionalMaterials(@RequestBody AdditionalMaterials additionalMaterials) throws URISyntaxException {
        log.debug("REST request to update AdditionalMaterials : {}", additionalMaterials);
        if (additionalMaterials.getId() == null) {
            return createAdditionalMaterials(additionalMaterials);
        }
        AdditionalMaterials result = additionalMaterialsService.save(additionalMaterials);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, additionalMaterials.getId().toString()))
            .body(result);
    }

    /**
     * GET  /additional-materials : get all the additionalMaterials.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of additionalMaterials in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/additional-materials")
    @Timed
    public ResponseEntity<List<AdditionalMaterials>> getAllAdditionalMaterials(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AdditionalMaterials");
        Page<AdditionalMaterials> page = additionalMaterialsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/additional-materials");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /additional-materials/:id : get the "id" additionalMaterials.
     *
     * @param id the id of the additionalMaterials to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the additionalMaterials, or with status 404 (Not Found)
     */
    @GetMapping("/additional-materials/{id}")
    @Timed
    public ResponseEntity<AdditionalMaterials> getAdditionalMaterials(@PathVariable Long id) {
        log.debug("REST request to get AdditionalMaterials : {}", id);
        AdditionalMaterials additionalMaterials = additionalMaterialsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(additionalMaterials));
    }

    /**
     * DELETE  /additional-materials/:id : delete the "id" additionalMaterials.
     *
     * @param id the id of the additionalMaterials to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/additional-materials/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdditionalMaterials(@PathVariable Long id) {
        log.debug("REST request to delete AdditionalMaterials : {}", id);
        additionalMaterialsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
