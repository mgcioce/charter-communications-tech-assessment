package com.charter.techassessment.security;

import com.charter.techassessment.model.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionSanitizer {

    public static final Long ZEROINT = 0L;
    public static final BigDecimal ZERO_DOUBLE= new BigDecimal(0.0);

    public  List<Transaction> sanitizeTransactions(List<Transaction> transactions) {
        Boolean atLeastOneValid = Boolean.FALSE;
        if(transactions == null || transactions.isEmpty())
            throw new IllegalArgumentException("empty request payload");
        List<Transaction> returnList = new ArrayList<>();
        for(Transaction trx: transactions){
            if( trx != null && transactionIdIsSanitized(trx.getTransactionId())
                            && transactionAmountIsSanitized(trx.getAmount())
                            && transactionDateIsSanitized(trx.getTransactionDate())){
                trx.setValid(Boolean.TRUE);
                atLeastOneValid = Boolean.TRUE;
            } else
                trx.setValid(Boolean.FALSE);
            returnList.add(trx);
        }
        if(atLeastOneValid)
            return returnList;
        else
            throw new IllegalArgumentException("no valid transactions");
    }

    protected boolean transactionIdIsSanitized(Long id){
        return (id == null || ZEROINT.compareTo(id) >= 0) ? false : true;
    }

    protected boolean transactionAmountIsSanitized(BigDecimal amount){
        return (amount == null || ZERO_DOUBLE.compareTo(amount) >= 0) ? false : true;
    }

    protected boolean transactionDateIsSanitized(LocalDate date) {
        return (date == null || date.isAfter(LocalDate.now())) ? false : true;
    }

}
