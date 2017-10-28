package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.DepositContract;
import com.duma.service.DepositContractService;
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
 * REST controller for managing DepositContract.
 */
@RestController
@RequestMapping("/api")
public class DepositContractResource {

    private final Logger log = LoggerFactory.getLogger(DepositContractResource.class);

    private static final String ENTITY_NAME = "depositContract";
        
    private final DepositContractService depositContractService;

    public DepositContractResource(DepositContractService depositContractService) {
        this.depositContractService = depositContractService;
    }

    /**
     * POST  /deposit-contracts : Create a new depositContract.
     *
     * @param depositContract the depositContract to create
     * @return the ResponseEntity with status 201 (Created) and with body the new depositContract, or with status 400 (Bad Request) if the depositContract has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/deposit-contracts")
    @Timed
    public ResponseEntity<DepositContract> createDepositContract(@RequestBody DepositContract depositContract) throws URISyntaxException {
        log.debug("REST request to save DepositContract : {}", depositContract);
        if (depositContract.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new depositContract cannot already have an ID")).body(null);
        }
        DepositContract result = depositContractService.save(depositContract);
        return ResponseEntity.created(new URI("/api/deposit-contracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /deposit-contracts : Updates an existing depositContract.
     *
     * @param depositContract the depositContract to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated depositContract,
     * or with status 400 (Bad Request) if the depositContract is not valid,
     * or with status 500 (Internal Server Error) if the depositContract couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/deposit-contracts")
    @Timed
    public ResponseEntity<DepositContract> updateDepositContract(@RequestBody DepositContract depositContract) throws URISyntaxException {
        log.debug("REST request to update DepositContract : {}", depositContract);
        if (depositContract.getId() == null) {
            return createDepositContract(depositContract);
        }
        DepositContract result = depositContractService.save(depositContract);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, depositContract.getId().toString()))
            .body(result);
    }

    /**
     * GET  /deposit-contracts : get all the depositContracts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of depositContracts in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/deposit-contracts")
    @Timed
    public ResponseEntity<List<DepositContract>> getAllDepositContracts(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DepositContracts");
        Page<DepositContract> page = depositContractService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/deposit-contracts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /deposit-contracts/:id : get the "id" depositContract.
     *
     * @param id the id of the depositContract to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the depositContract, or with status 404 (Not Found)
     */
    @GetMapping("/deposit-contracts/{id}")
    @Timed
    public ResponseEntity<DepositContract> getDepositContract(@PathVariable Long id) {
        log.debug("REST request to get DepositContract : {}", id);
        DepositContract depositContract = depositContractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(depositContract));
    }

    /**
     * DELETE  /deposit-contracts/:id : delete the "id" depositContract.
     *
     * @param id the id of the depositContract to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deposit-contracts/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepositContract(@PathVariable Long id) {
        log.debug("REST request to delete DepositContract : {}", id);
        depositContractService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
