package com.charter.techassessment.model.datatransferobjects;

import com.charter.techassessment.model.transaction.Transaction;

import java.util.List;

public class TransactionsDTO {

    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
