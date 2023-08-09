package com.tmf.partymgmtservices.api.controller;

import com.tmf.partymgmtservices.api.organization.domain.request.OrganizationRequest;
import com.tmf.partymgmtservices.api.organization.domain.response.OrganizationResponse;
import com.tmf.partymgmtservices.api.organization.services.OrganizationService;
import com.tmf.partymgmtservices.api.util.RequestUtil;
import com.tmf.partymgmtservices.api.util.SessionRequestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrganizationController {
    private static final Logger LOGGER = LogManager.getLogger(OrganizationController.class);

    private final OrganizationService organizationService;

    @Autowired
    OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping(value = "/organization",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResponse> createOrganization(@RequestBody OrganizationRequest organizationRequest) throws Exception{
        LOGGER.info(String.format("Call createOrganization %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(organizationService.saveOrganization(organizationRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/organization/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResponse> getOrganizationById(@PathVariable String id) throws Exception{
        LOGGER.info(String.format("Call getOrganizationById %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(organizationService.getOrganizationById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/organization",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrganizationResponse>> getPaginatedOrganization(@RequestParam Map<String, Object> requestParams) throws Exception{
        LOGGER.info(String.format("Call getOrganizationById %s", SessionRequestUtil.getSessionRequest()));

        int[] limitOffset = RequestUtil.validateRequestParams(requestParams);

        return new ResponseEntity<>(organizationService.getPaginatedOrganization(requestParams, limitOffset[0], limitOffset[1]), HttpStatus.OK);
    }
    @DeleteMapping(value = "/organization/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOrganizationById(@PathVariable String id) throws Exception{
        LOGGER.info(String.format("Call delete OrganizationById %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(organizationService.deleteOrganization(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/organization/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResponse> updateOrganization(@PathVariable String id, @RequestBody OrganizationRequest organizationRequest) throws Exception{
        LOGGER.info(String.format("Call createIndividual %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(organizationService.updateOrganization(id, organizationRequest), HttpStatus.OK);
    }
}
