package com.charter.techassessment.configuration;

import com.charter.techassessment.controller.RewardsPointsController;
import com.charter.techassessment.service.RewardsPointsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(value = {RewardsPointsControllerConfiguration.class})
public class RewardsPointControllerTestConfiguration {

    @Bean
    @Scope("singleton")
    public RewardsPointsController rewardPointsController(RewardsPointsService rewardsPointsService) {
        return new RewardsPointsController(rewardsPointsService);
    }
}
