package com.charter.techassessment.model.rewards;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RewardsCalculation {

    private Map<String, BigDecimal> monthlyPointsMap;
    private BigDecimal total;

    private List<Long> invalidTransactions;

    public Map<String, BigDecimal> getMonthlyPointsMap() {
        return monthlyPointsMap;
    }

    public void setMonthlyPointsMap(Map<String, BigDecimal> monthlyPointsMap) {
        this.monthlyPointsMap = monthlyPointsMap;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Long> getInvalidTransactions() {
        return invalidTransactions;
    }

    public void setInvalidTransactions(List<Long> invalidTransactions) {
        this.invalidTransactions = invalidTransactions;
    }
}
