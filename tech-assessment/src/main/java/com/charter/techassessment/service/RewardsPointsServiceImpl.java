package com.charter.techassessment.service;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.model.transaction.Transaction;
import com.charter.techassessment.security.TransactionSanitizer;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Service
public class RewardsPointsServiceImpl implements RewardsPointsService{

    private TransactionSanitizer transactionSanitizer;

    private RewardsCalculation rewardsCalculation;
    public RewardsCalculation calculateRewardsPoints(TransactionsDTO transactionsDTO) {
        List<Transaction> transactions = transactionSanitizer.sanitizeTransactions(transactionsDTO.getTransactions());
        Month trxMonth;
        BigInteger points;
        Map<Month,BigInteger> monthlyPointsMap = rewardsCalculation.getMonthlyPointsMap();
        for(Transaction trx: transactions){
            if(!trx.getValid()){
                rewardsCalculation.getInvalidTransactions().add(trx.getTransactionId());
            } else {
                trxMonth = trx.getTransactionDate().getMonth();
                points = calculateRewardsPoints(trx);
                if(monthlyPointsMap.containsKey(trxMonth)){
                    points = points.add(monthlyPointsMap.get(trxMonth));
                    monthlyPointsMap.put(trxMonth,points);
                } else {
                    monthlyPointsMap.put(trxMonth,points);
                }
            }
        }
        rewardsCalculation.calculateTotalPoints();
        return rewardsCalculation;
    }

    public static BigInteger calculateRewardsPoints(Transaction transaction) {
        BigInteger rewardsPoints = new BigInteger(String.valueOf(0));
        BigInteger transactionAmount = transaction.getAmount().toBigInteger();
        if(REWARDS_POINTS_LOWER_THRESHOLD.compareTo(transactionAmount) >= 0) //amount isn't greater than 50, so return 0
            return rewardsPoints;
        if(REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD.compareTo(transactionAmount) < 0){ //amount is greater than 100, so return 50 + 2*(amount - 100)
            rewardsPoints = rewardsPoints.add(REWARDS_POINTS_LOWER_THRESHOLD);
            rewardsPoints = rewardsPoints.add(transactionAmount.subtract(REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD).multiply(REWARDS_POINTS_MULTIPLIER));
        } else { //amount is less than or equal to 100 and greater than 50, so return amount - 50
            rewardsPoints = rewardsPoints.add(transactionAmount.subtract(REWARDS_POINTS_LOWER_THRESHOLD));
        }
        return rewardsPoints;
    }

    public RewardsPointsServiceImpl(TransactionSanitizer transactionSanitizer, RewardsCalculation rewardsCalculation) {
        this.transactionSanitizer = transactionSanitizer;
        this.rewardsCalculation = rewardsCalculation;
    }


}
