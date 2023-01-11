package com.charter.techassessment.model.rewards;

import java.math.BigDecimal;
import java.util.Map;

public class RewardsCalculation {

    private Map<String, BigDecimal> monthlyPointsMap;
    private BigDecimal total;

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
}
