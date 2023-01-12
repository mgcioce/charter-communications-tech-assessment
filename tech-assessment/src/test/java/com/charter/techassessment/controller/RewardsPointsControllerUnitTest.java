package com.charter.techassessment.controller;

import com.charter.techassessment.model.datatransferobjects.TransactionsDTO;
import com.charter.techassessment.model.rewards.RewardsCalculation;
import com.charter.techassessment.service.RewardsPointsService;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class RewardsPointsControllerUnitTest {

    @Mock
    private RewardsPointsService rewardsPointsService;

    @InjectMocks
    private RewardsPointsController rewardsPointsController;

    @Test
    public void calculateRewards_AcceptHeaderProvidesNonJson_return406(){
        ResponseEntity<RewardsCalculation> response = rewardsPointsController.calculateRewards("application/xml", Mockito.mock(TransactionsDTO.class));
        Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void calculateRewards_payloadArgumentIsNull_throwIllegalArgumentException(){
        Mockito.doThrow(new IllegalArgumentException("empty request payload")).when(rewardsPointsService).calculateRewardsPoints(null);
        try{
            rewardsPointsController.calculateRewards("application/json",null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("empty request payload",e.getMessage());
        }

    }

    @Test
    public void calculateRewards_payloadDTOisEmpty_throwIllegalArgumentException(){
        TransactionsDTO trx = new TransactionsDTO();
        Mockito.doThrow(new IllegalArgumentException("no valid transactions")).when(rewardsPointsService).calculateRewardsPoints(trx);
        try{
            rewardsPointsController.calculateRewards("application/json",trx);
        } catch (IllegalArgumentException e){
            Assert.assertEquals("no valid transactions",e.getMessage());
        }
    }
}
