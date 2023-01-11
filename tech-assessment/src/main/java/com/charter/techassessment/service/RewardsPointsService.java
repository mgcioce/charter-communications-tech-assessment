package com.charter.techassessment.service;

import com.charter.techassessment.model.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RewardsPointsService {

    public static final BigInteger REWARDS_POINTS_LOWER_THRESHOLD = new BigInteger(String.valueOf(50));
    public static final BigInteger REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD = new BigInteger(String.valueOf(100));

    public static final BigInteger REWARDS_POINTS_MULTIPLIER = new BigInteger(String.valueOf(2));

    public BigInteger calculateRewardsPoints(Transaction transaction) {
        BigInteger rewardsPoints = new BigInteger(String.valueOf(0));
        BigInteger transactionAmount = transaction.getAmount().toBigInteger();
        if(REWARDS_POINTS_LOWER_THRESHOLD.compareTo(transactionAmount) >= 0) //amount isn't greater than 50, so return 0
            return rewardsPoints;
        if(REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD.compareTo(transactionAmount) < 0){ //amount is greater than 100, so return 50 + 2*(amount - 100)
            rewardsPoints.add(REWARDS_POINTS_LOWER_THRESHOLD);
            rewardsPoints.add(transactionAmount.subtract(REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD).multiply(REWARDS_POINTS_MULTIPLIER));
        } else { //amount is less than or equal to 100 and greater than 50, so return amount - 50
            rewardsPoints.add(transactionAmount.subtract(REWARDS_POINTS_LOWER_THRESHOLD));
        }
        return rewardsPoints;
    }
}
