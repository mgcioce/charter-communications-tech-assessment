package com.charter.techassessment.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(value = {RewardsPointsControllerConfiguration.class})
public class RewardsPointControllerTestConfiguration {

}
