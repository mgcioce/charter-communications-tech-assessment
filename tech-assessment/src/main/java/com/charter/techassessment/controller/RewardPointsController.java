package com.charter.techassessment.controller;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.model.transaction.Transaction;
import com.charter.techassessment.security.TransactionSanitizer;
import com.charter.techassessment.service.RewardsPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/rewards/calculate"})
public class RewardPointsController {

    @Autowired
    private RewardsPointsService rewardsPointsService;

    @RequestMapping(method = RequestMethod.GET,consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RewardsCalculation> calculateRewards(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
                                                                @RequestBody TransactionsDTO payload) {
        List<Transaction> transactions = TransactionSanitizer.sanitizeTransactions(payload.getTransactions());

        return new ResponseEntity<>(new RewardsCalculation(),HttpStatus.ACCEPTED);
    }
}
