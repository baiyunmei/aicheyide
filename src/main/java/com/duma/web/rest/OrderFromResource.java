package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.OrderFrom;
import com.duma.service.OrderFromService;
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
 * REST controller for managing OrderFrom.
 */
@RestController
@RequestMapping("/api")
public class OrderFromResource {

    private final Logger log = LoggerFactory.getLogger(OrderFromResource.class);

    private static final String ENTITY_NAME = "orderFrom";
        
    private final OrderFromService orderFromService;

    public OrderFromResource(OrderFromService orderFromService) {
        this.orderFromService = orderFromService;
    }

    /**
     * POST  /order-froms : Create a new orderFrom.
     *
     * @param orderFrom the orderFrom to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderFrom, or with status 400 (Bad Request) if the orderFrom has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-froms")
    @Timed
    public ResponseEntity<OrderFrom> createOrderFrom(@RequestBody OrderFrom orderFrom) throws URISyntaxException {
        log.debug("REST request to save OrderFrom : {}", orderFrom);
        if (orderFrom.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new orderFrom cannot already have an ID")).body(null);
        }
        OrderFrom result = orderFromService.save(orderFrom);
        return ResponseEntity.created(new URI("/api/order-froms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-froms : Updates an existing orderFrom.
     *
     * @param orderFrom the orderFrom to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderFrom,
     * or with status 400 (Bad Request) if the orderFrom is not valid,
     * or with status 500 (Internal Server Error) if the orderFrom couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-froms")
    @Timed
    public ResponseEntity<OrderFrom> updateOrderFrom(@RequestBody OrderFrom orderFrom) throws URISyntaxException {
        log.debug("REST request to update OrderFrom : {}", orderFrom);
        if (orderFrom.getId() == null) {
            return createOrderFrom(orderFrom);
        }
        OrderFrom result = orderFromService.save(orderFrom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderFrom.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-froms : get all the orderFroms.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderFroms in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/order-froms")
    @Timed
    public ResponseEntity<List<OrderFrom>> getAllOrderFroms(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OrderFroms");
        Page<OrderFrom> page = orderFromService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-froms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-froms/:id : get the "id" orderFrom.
     *
     * @param id the id of the orderFrom to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderFrom, or with status 404 (Not Found)
     */
    @GetMapping("/order-froms/{id}")
    @Timed
    public ResponseEntity<OrderFrom> getOrderFrom(@PathVariable Long id) {
        log.debug("REST request to get OrderFrom : {}", id);
        OrderFrom orderFrom = orderFromService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderFrom));
    }

    /**
     * DELETE  /order-froms/:id : delete the "id" orderFrom.
     *
     * @param id the id of the orderFrom to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-froms/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderFrom(@PathVariable Long id) {
        log.debug("REST request to delete OrderFrom : {}", id);
        orderFromService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
