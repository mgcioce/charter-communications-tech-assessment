package com.charter.techassessment.controller;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.service.RewardsPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path = {"/rewards/calculate"})
public class RewardsPointsController {
    @Autowired
    private RewardsPointsService rewardsPointsService;

    @RequestMapping(method = RequestMethod.GET,consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RewardsCalculation> calculateRewards(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
                                                                @RequestBody TransactionsDTO payload) {
        if(acceptHeader != null && !acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE))
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        RewardsCalculation rewardsCalculation = rewardsPointsService.calculateRewardsPoints(payload);
        return new ResponseEntity<>(rewardsCalculation,HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<String> runtimeException(RuntimeException e, WebRequest request){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    public RewardsPointsController(RewardsPointsService rewardsPointsService) {
        this.rewardsPointsService = rewardsPointsService;
    }
}
