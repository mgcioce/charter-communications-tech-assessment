package com.charter.techassessment.model.rewards;

import java.math.BigInteger;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class RewardsCalculation {

    private Map<Month, BigInteger> monthlyPointsMap;
    private BigInteger total;

    private List<Long> invalidTransactions;

    public RewardsCalculation(){
        super();
    }
    public RewardsCalculation(Map<Month, BigInteger> monthlyPointsMap, BigInteger total, List<Long> invalidTransactions) {
        this.monthlyPointsMap = monthlyPointsMap;
        this.total = total;
        this.invalidTransactions = invalidTransactions;
    }

    public Map<Month, BigInteger> getMonthlyPointsMap() {
        return monthlyPointsMap;
    }

    public void setMonthlyPointsMap(Map<Month, BigInteger> monthlyPointsMap) {
        this.monthlyPointsMap = monthlyPointsMap;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public List<Long> getInvalidTransactions() {
        return invalidTransactions;
    }

    public void setInvalidTransactions(List<Long> invalidTransactions) {
        this.invalidTransactions = invalidTransactions;
    }

    public void calculateTotalPoints(){
        BigInteger sum = new BigInteger("0");
        for(Map.Entry<Month,BigInteger> entry: this.monthlyPointsMap.entrySet())
            sum = sum.add(entry.getValue());
        this.total = sum;
    }


}
