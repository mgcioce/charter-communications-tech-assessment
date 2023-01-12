package com.charter.techassessment.configuration;

import com.charter.techassessment.controller.RewardsPointsController;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.security.TransactionSanitizer;
import com.charter.techassessment.service.RewardsPointsService;
import com.charter.techassessment.service.RewardsPointsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.math.BigInteger;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RewardsPointsControllerConfiguration {

    @Bean
    @Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RewardsPointsService rewardsPointsService(TransactionSanitizer transactionSanitizer, RewardsCalculation rewardsCalculation){
        return new RewardsPointsServiceImpl(transactionSanitizer, rewardsCalculation);
    }

    @Bean
    public TransactionSanitizer transactionSanitizer() {
        return new TransactionSanitizer();
    }

    @Bean
    @Scope("prototype")
    public RewardsCalculation rewardsCalculation(Map<Month, BigInteger> monthlyPointsMap, BigInteger total, List<Long> invalidTransactions) {
        return new RewardsCalculation(monthlyPointsMap,total,invalidTransactions);
    }

    @Bean
    @Scope("prototype")
    public Map<Month,BigInteger> monthBigIntegerMap() {
        return new EnumMap<Month, BigInteger>(Month.class);
    }

    @Bean
    @Scope("prototype")
    public BigInteger bigInteger(){
        return new BigInteger("0");
    }

    @Bean
    @Scope("prototype")
    public List<Long> longList(){
        return new ArrayList<>();
    }

}
