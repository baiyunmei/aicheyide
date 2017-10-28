package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A RepaymentRecord.
 */
@Entity
@Table(name = "repayment_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RepaymentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

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

    @Column(name = "remark")
    private String remark;

    @Column(name = "operation_time")
    private Long operationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public RepaymentRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public RepaymentRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public RepaymentRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public RepaymentRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public RepaymentRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public RepaymentRecord driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getRepaymentTime() {
        return repaymentTime;
    }

    public RepaymentRecord repaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
        return this;
    }

    public void setRepaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public RepaymentRecord money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public RepaymentRecord overdue(Integer overdue) {
        this.overdue = overdue;
        return this;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Integer getPeriods() {
        return periods;
    }

    public RepaymentRecord periods(Integer periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getNextMoney() {
        return nextMoney;
    }

    public RepaymentRecord nextMoney(BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
        return this;
    }

    public void setNextMoney(BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
    }

    public Long getNextDate() {
        return nextDate;
    }

    public RepaymentRecord nextDate(Long nextDate) {
        this.nextDate = nextDate;
        return this;
    }

    public void setNextDate(Long nextDate) {
        this.nextDate = nextDate;
    }

    public String getRemark() {
        return remark;
    }

    public RepaymentRecord remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public RepaymentRecord operationTime(Long operationTime) {
        this.operationTime = operationTime;
        return this;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepaymentRecord repaymentRecord = (RepaymentRecord) o;
        if (repaymentRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, repaymentRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RepaymentRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", repaymentTime='" + repaymentTime + "'" +
            ", money='" + money + "'" +
            ", overdue='" + overdue + "'" +
            ", periods='" + periods + "'" +
            ", nextMoney='" + nextMoney + "'" +
            ", nextDate='" + nextDate + "'" +
            ", remark='" + remark + "'" +
            ", operationTime='" + operationTime + "'" +
            '}';
    }
}
