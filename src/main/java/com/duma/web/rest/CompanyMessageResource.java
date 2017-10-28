package com.duma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.duma.domain.CompanyMessage;
import com.duma.service.CompanyMessageService;
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
 * REST controller for managing CompanyMessage.
 */
@RestController
@RequestMapping("/api")
public class CompanyMessageResource {

    private final Logger log = LoggerFactory.getLogger(CompanyMessageResource.class);

    private static final String ENTITY_NAME = "companyMessage";
        
    private final CompanyMessageService companyMessageService;

    public CompanyMessageResource(CompanyMessageService companyMessageService) {
        this.companyMessageService = companyMessageService;
    }

    /**
     * POST  /company-messages : Create a new companyMessage.
     *
     * @param companyMessage the companyMessage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companyMessage, or with status 400 (Bad Request) if the companyMessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/company-messages")
    @Timed
    public ResponseEntity<CompanyMessage> createCompanyMessage(@RequestBody CompanyMessage companyMessage) throws URISyntaxException {
        log.debug("REST request to save CompanyMessage : {}", companyMessage);
        if (companyMessage.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new companyMessage cannot already have an ID")).body(null);
        }
        CompanyMessage result = companyMessageService.save(companyMessage);
        return ResponseEntity.created(new URI("/api/company-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /company-messages : Updates an existing companyMessage.
     *
     * @param companyMessage the companyMessage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companyMessage,
     * or with status 400 (Bad Request) if the companyMessage is not valid,
     * or with status 500 (Internal Server Error) if the companyMessage couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/company-messages")
    @Timed
    public ResponseEntity<CompanyMessage> updateCompanyMessage(@RequestBody CompanyMessage companyMessage) throws URISyntaxException {
        log.debug("REST request to update CompanyMessage : {}", companyMessage);
        if (companyMessage.getId() == null) {
            return createCompanyMessage(companyMessage);
        }
        CompanyMessage result = companyMessageService.save(companyMessage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companyMessage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /company-messages : get all the companyMessages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of companyMessages in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/company-messages")
    @Timed
    public ResponseEntity<List<CompanyMessage>> getAllCompanyMessages(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CompanyMessages");
        Page<CompanyMessage> page = companyMessageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/company-messages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /company-messages/:id : get the "id" companyMessage.
     *
     * @param id the id of the companyMessage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMessage, or with status 404 (Not Found)
     */
    @GetMapping("/company-messages/{id}")
    @Timed
    public ResponseEntity<CompanyMessage> getCompanyMessage(@PathVariable Long id) {
        log.debug("REST request to get CompanyMessage : {}", id);
        CompanyMessage companyMessage = companyMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(companyMessage));
    }

    /**
     * DELETE  /company-messages/:id : delete the "id" companyMessage.
     *
     * @param id the id of the companyMessage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/company-messages/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanyMessage(@PathVariable Long id) {
        log.debug("REST request to delete CompanyMessage : {}", id);
        companyMessageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
