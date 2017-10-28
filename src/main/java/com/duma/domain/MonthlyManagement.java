package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A MonthlyManagement.
 */
@Entity
@Table(name = "monthly_management")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonthlyManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "repayment_time")
    private Long repaymentTime;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

    @Column(name = "overdue")
    private Integer overdue;

    @Column(name = "periods")
    private Integer periods;

    @Column(name = "next_money", precision=10, scale=2)
    private BigDecimal nextMoney;

    @Column(name = "next_date")
    private Long nextDate;

    @Column(name = "periods_status")
    private Integer periodsStatus;

    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public MonthlyManagement driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public MonthlyManagement orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public MonthlyManagement companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDriverName() {
        return driverName;
    }

    public MonthlyManagement driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public MonthlyManagement bankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Long getRepaymentTime() {
        return repaymentTime;
    }

    public MonthlyManagement repaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
        return this;
    }

    public void setRepaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public MonthlyManagement money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public MonthlyManagement overdue(Integer overdue) {
        this.overdue = overdue;
        return this;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Integer getPeriods() {
        return periods;
    }

    public MonthlyManagement periods(Integer periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getNextMoney() {
        return nextMoney;
    }

    public MonthlyManagement nextMoney(BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
        return this;
    }

    public void setNextMoney(BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
    }

    public Long getNextDate() {
        return nextDate;
    }

    public MonthlyManagement nextDate(Long nextDate) {
        this.nextDate = nextDate;
        return this;
    }

    public void setNextDate(Long nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getPeriodsStatus() {
        return periodsStatus;
    }

    public MonthlyManagement periodsStatus(Integer periodsStatus) {
        this.periodsStatus = periodsStatus;
        return this;
    }

    public void setPeriodsStatus(Integer periodsStatus) {
        this.periodsStatus = periodsStatus;
    }

    public String getRemark() {
        return remark;
    }

    public MonthlyManagement remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonthlyManagement monthlyManagement = (MonthlyManagement) o;
        if (monthlyManagement.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, monthlyManagement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MonthlyManagement{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", orderId='" + orderId + "'" +
            ", companyId='" + companyId + "'" +
            ", driverName='" + driverName + "'" +
            ", bankAccount='" + bankAccount + "'" +
            ", repaymentTime='" + repaymentTime + "'" +
            ", money='" + money + "'" +
            ", overdue='" + overdue + "'" +
            ", periods='" + periods + "'" +
            ", nextMoney='" + nextMoney + "'" +
            ", nextDate='" + nextDate + "'" +
            ", periodsStatus='" + periodsStatus + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
