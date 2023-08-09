package com.tmf.partymgmtservices.api.controller;

import com.tmf.partymgmtservices.api.individual.domain.request.IndividualRequest;
import com.tmf.partymgmtservices.api.individual.domain.response.IndividualResponse;
import com.tmf.partymgmtservices.api.individual.services.IndividualService;
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
public class IndividualController {
    private static final Logger LOGGER = LogManager.getLogger(IndividualController.class);

    private final IndividualService individualService;

    @Autowired
    IndividualController(IndividualService individualService){
        this.individualService = individualService;
    }

    @PostMapping(value = "/individual",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IndividualResponse> createIndividual(@RequestBody IndividualRequest individualRequest) throws Exception{
        LOGGER.info(String.format("Call createIndividual %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(individualService.saveIndividual(individualRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/individual/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IndividualResponse> getIndividualById(@PathVariable String id) throws Exception{
        LOGGER.info(String.format("Call getIndividualById %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(individualService.getIndividualById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/individual",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IndividualResponse>> getPaginatedIndividual(@RequestParam Map<String, Object> requestParams) throws Exception{
        LOGGER.info(String.format("Call getIndividualById %s", SessionRequestUtil.getSessionRequest()));

        int[] limitOffset = RequestUtil.validateRequestParams(requestParams);

        return new ResponseEntity<>(individualService.getPaginatedIndividual(requestParams, limitOffset[0], limitOffset[1]), HttpStatus.OK);
    }
    @PatchMapping(value = "/individual/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IndividualResponse> updateIndividual(@PathVariable String id, @RequestBody IndividualRequest individualRequest) throws Exception{
        LOGGER.info(String.format("Call createIndividual %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(individualService.updateIndividual(id, individualRequest), HttpStatus.OK);
    }
    @DeleteMapping(value = "/individual/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteIndividualById(@PathVariable String id) throws Exception{
        LOGGER.info(String.format("Call deleteIndividualById %s", SessionRequestUtil.getSessionRequest()));
        return new ResponseEntity<>(individualService.deleteIndividual(id), HttpStatus.NO_CONTENT);
    }
}
