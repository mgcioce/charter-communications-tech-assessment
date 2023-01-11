package com.charter.techassessment.service;

import com.charter.techassessment.model.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RewardsPointsService {

    public static final BigInteger rewardsPointsLowerThreshold = new BigInteger(String.valueOf(50));
    public static final BigInteger rewardsPointsDoublePointsThreshold = new BigInteger(String.valueOf(100));

    public static final BigInteger rewardsPointsMultiplier = new BigInteger(String.valueOf(2));

    public BigInteger calculateRewardsPoints(Transaction transaction) {
        BigInteger rewardsPoints = new BigInteger(String.valueOf(0));
        BigInteger transactionAmount = transaction.getAmount().toBigInteger();
        if(rewardsPointsLowerThreshold.compareTo(transactionAmount) >= 0) //amount isn't greater than 50, so return 0
            return rewardsPoints;
        if(rewardsPointsDoublePointsThreshold.compareTo(transactionAmount) < 0){ //amount is greater than 100, so return 50 + 2*(amount - 100)
            rewardsPoints.add(rewardsPointsLowerThreshold);
            rewardsPoints.add(transactionAmount.subtract(rewardsPointsDoublePointsThreshold).multiply(rewardsPointsMultiplier));
        } else { //amount is less than or equal to 100 and greater than 50, so return amount - 50
            rewardsPoints.add(transactionAmount.subtract(rewardsPointsLowerThreshold));
        }
        return rewardsPoints;
    }
}
