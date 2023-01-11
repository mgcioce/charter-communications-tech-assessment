package com.charter.techassessment.controller;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.model.transaction.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/rewards/calculate"})
public class RewardPointsController {

    @RequestMapping(method = RequestMethod.GET,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RewardsCalculation> calculateRewards(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
                                                                @RequestBody TransactionsDTO transactions) {
        return new ResponseEntity<>(new RewardsCalculation(),HttpStatus.ACCEPTED);
    }
}
