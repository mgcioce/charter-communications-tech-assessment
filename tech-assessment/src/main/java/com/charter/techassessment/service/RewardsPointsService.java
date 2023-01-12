package com.charter.techassessment.service;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;

import java.math.BigInteger;

public interface RewardsPointsService {
    public static final BigInteger REWARDS_POINTS_LOWER_THRESHOLD = new BigInteger(String.valueOf(50));
    public static final BigInteger REWARDS_POINTS_DOUBLE_POINTS_THRESHOLD = new BigInteger(String.valueOf(100));

    public static final BigInteger REWARDS_POINTS_MULTIPLIER = new BigInteger(String.valueOf(2));

    public RewardsCalculation calculateRewardsPoints(TransactionsDTO transactionsDTO);
}
