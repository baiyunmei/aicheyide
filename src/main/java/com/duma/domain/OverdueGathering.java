package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A OverdueGathering.
 */
@Entity
@Table(name = "overdue_gathering")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OverdueGathering implements Serializable {

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

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "repayment_time")
    private Long repaymentTime;

    @Column(name = "overdue_await", precision=10, scale=2)
    private BigDecimal overdueAwait;

    @Column(name = "overdue_interest", precision=10, scale=2)
    private BigDecimal overdueInterest;

    @Column(name = "overdue_data")
    private Integer overdueData;

    @Column(name = "total", precision=10, scale=2)
    private BigDecimal total;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "practical_money", precision=10, scale=2)
    private BigDecimal practicalMoney;

    @Column(name = "payment_way")
    private Integer paymentWay;

    @Column(name = "remark_1")
    private String remark1;

    @Column(name = "founder")
    private String founder;

    @Column(name = "founder_time")
    private Long founderTime;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifier_time")
    private Long modifierTime;

    @Column(name = "gathering")
    private Integer gathering;

    @Column(name = "gather_time")
    private Long gatherTime;

    @Column(name = "remark_2")
    private String remark2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public OverdueGathering driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OverdueGathering orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public OverdueGathering companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public OverdueGathering vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getRepaymentTime() {
        return repaymentTime;
    }

    public OverdueGathering repaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
        return this;
    }

    public void setRepaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getOverdueAwait() {
        return overdueAwait;
    }

    public OverdueGathering overdueAwait(BigDecimal overdueAwait) {
        this.overdueAwait = overdueAwait;
        return this;
    }

    public void setOverdueAwait(BigDecimal overdueAwait) {
        this.overdueAwait = overdueAwait;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public OverdueGathering overdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
        return this;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public Integer getOverdueData() {
        return overdueData;
    }

    public OverdueGathering overdueData(Integer overdueData) {
        this.overdueData = overdueData;
        return this;
    }

    public void setOverdueData(Integer overdueData) {
        this.overdueData = overdueData;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OverdueGathering total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public OverdueGathering receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public OverdueGathering receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public BigDecimal getPracticalMoney() {
        return practicalMoney;
    }

    public OverdueGathering practicalMoney(BigDecimal practicalMoney) {
        this.practicalMoney = practicalMoney;
        return this;
    }

    public void setPracticalMoney(BigDecimal practicalMoney) {
        this.practicalMoney = practicalMoney;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public OverdueGathering paymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
        return this;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getRemark1() {
        return remark1;
    }

    public OverdueGathering remark1(String remark1) {
        this.remark1 = remark1;
        return this;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getFounder() {
        return founder;
    }

    public OverdueGathering founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public OverdueGathering founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public OverdueGathering modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public OverdueGathering modifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
        return this;
    }

    public void setModifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
    }

    public Integer getGathering() {
        return gathering;
    }

    public OverdueGathering gathering(Integer gathering) {
        this.gathering = gathering;
        return this;
    }

    public void setGathering(Integer gathering) {
        this.gathering = gathering;
    }

    public Long getGatherTime() {
        return gatherTime;
    }

    public OverdueGathering gatherTime(Long gatherTime) {
        this.gatherTime = gatherTime;
        return this;
    }

    public void setGatherTime(Long gatherTime) {
        this.gatherTime = gatherTime;
    }

    public String getRemark2() {
        return remark2;
    }

    public OverdueGathering remark2(String remark2) {
        this.remark2 = remark2;
        return this;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OverdueGathering overdueGathering = (OverdueGathering) o;
        if (overdueGathering.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, overdueGathering.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OverdueGathering{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", orderId='" + orderId + "'" +
            ", companyId='" + companyId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", repaymentTime='" + repaymentTime + "'" +
            ", overdueAwait='" + overdueAwait + "'" +
            ", overdueInterest='" + overdueInterest + "'" +
            ", overdueData='" + overdueData + "'" +
            ", total='" + total + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", practicalMoney='" + practicalMoney + "'" +
            ", paymentWay='" + paymentWay + "'" +
            ", remark1='" + remark1 + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            ", gathering='" + gathering + "'" +
            ", gatherTime='" + gatherTime + "'" +
            ", remark2='" + remark2 + "'" +
            '}';
    }
}
