package com.charter.techassessment.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Long transactionId;
    private BigDecimal amount;
    private LocalDate transactionDate;

    private Boolean valid;

    public Transaction(Long transactionId, BigDecimal amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
