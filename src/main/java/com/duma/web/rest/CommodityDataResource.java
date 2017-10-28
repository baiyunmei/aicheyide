package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.CommodityData;
import com.duma.service.CommodityDataService;
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
 * REST controller for managing CommodityData.
 */
@RestController
@RequestMapping("/api")
public class CommodityDataResource {

    private final Logger log = LoggerFactory.getLogger(CommodityDataResource.class);

    private static final String ENTITY_NAME = "commodityData";
        
    private final CommodityDataService commodityDataService;

    public CommodityDataResource(CommodityDataService commodityDataService) {
        this.commodityDataService = commodityDataService;
    }

    /**
     * POST  /commodity-data : Create a new commodityData.
     *
     * @param commodityData the commodityData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commodityData, or with status 400 (Bad Request) if the commodityData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/commodity-data")
    @Timed
    public ResponseEntity<CommodityData> createCommodityData(@RequestBody CommodityData commodityData) throws URISyntaxException {
        log.debug("REST request to save CommodityData : {}", commodityData);
        if (commodityData.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new commodityData cannot already have an ID")).body(null);
        }
        CommodityData result = commodityDataService.save(commodityData);
        return ResponseEntity.created(new URI("/api/commodity-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commodity-data : Updates an existing commodityData.
     *
     * @param commodityData the commodityData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commodityData,
     * or with status 400 (Bad Request) if the commodityData is not valid,
     * or with status 500 (Internal Server Error) if the commodityData couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/commodity-data")
    @Timed
    public ResponseEntity<CommodityData> updateCommodityData(@RequestBody CommodityData commodityData) throws URISyntaxException {
        log.debug("REST request to update CommodityData : {}", commodityData);
        if (commodityData.getId() == null) {
            return createCommodityData(commodityData);
        }
        CommodityData result = commodityDataService.save(commodityData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commodityData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /commodity-data : get all the commodityData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of commodityData in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/commodity-data")
    @Timed
    public ResponseEntity<List<CommodityData>> getAllCommodityData(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CommodityData");
        Page<CommodityData> page = commodityDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/commodity-data");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /commodity-data/:id : get the "id" commodityData.
     *
     * @param id the id of the commodityData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commodityData, or with status 404 (Not Found)
     */
    @GetMapping("/commodity-data/{id}")
    @Timed
    public ResponseEntity<CommodityData> getCommodityData(@PathVariable Long id) {
        log.debug("REST request to get CommodityData : {}", id);
        CommodityData commodityData = commodityDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(commodityData));
    }

    /**
     * DELETE  /commodity-data/:id : delete the "id" commodityData.
     *
     * @param id the id of the commodityData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/commodity-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteCommodityData(@PathVariable Long id) {
        log.debug("REST request to delete CommodityData : {}", id);
        commodityDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
