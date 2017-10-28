package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DownPayment.
 */
@Entity
@Table(name = "down_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DownPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "receipt_money", precision=10, scale=2)
    private BigDecimal receiptMoney;

    @Column(name = "receipt_water")
    private String receiptWater;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "receipt")
    private String receipt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public DownPayment companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public DownPayment orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getReceiptMoney() {
        return receiptMoney;
    }

    public DownPayment receiptMoney(BigDecimal receiptMoney) {
        this.receiptMoney = receiptMoney;
        return this;
    }

    public void setReceiptMoney(BigDecimal receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    public String getReceiptWater() {
        return receiptWater;
    }

    public DownPayment receiptWater(String receiptWater) {
        this.receiptWater = receiptWater;
        return this;
    }

    public void setReceiptWater(String receiptWater) {
        this.receiptWater = receiptWater;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public DownPayment accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReceipt() {
        return receipt;
    }

    public DownPayment receipt(String receipt) {
        this.receipt = receipt;
        return this;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DownPayment downPayment = (DownPayment) o;
        if (downPayment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, downPayment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DownPayment{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", orderId='" + orderId + "'" +
            ", receiptMoney='" + receiptMoney + "'" +
            ", receiptWater='" + receiptWater + "'" +
            ", accountNumber='" + accountNumber + "'" +
            ", receipt='" + receipt + "'" +
            '}';
    }
}
