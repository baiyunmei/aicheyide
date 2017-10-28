package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A RetreatDeposit.
 */
@Entity
@Table(name = "retreat_deposit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RetreatDeposit implements Serializable {

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

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "refund_time")
    private Long refundTime;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

    @Column(name = "source")
    private String source;

    @Column(name = "pledge_status")
    private Integer pledgeStatus;

    @Column(name = "remark")
    private String remark;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "next_rent_time")
    private Long nextRentTime;

    @Column(name = "founder")
    private String founder;

    @Column(name = "founder_time")
    private Long founderTime;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifier_time")
    private Long modifierTime;

    @Column(name = "whether_refund")
    private Integer whetherRefund;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public RetreatDeposit driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public RetreatDeposit orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public RetreatDeposit companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public RetreatDeposit vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public RetreatDeposit plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public RetreatDeposit driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getRefundTime() {
        return refundTime;
    }

    public RetreatDeposit refundTime(Long refundTime) {
        this.refundTime = refundTime;
        return this;
    }

    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public RetreatDeposit money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSource() {
        return source;
    }

    public RetreatDeposit source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getPledgeStatus() {
        return pledgeStatus;
    }

    public RetreatDeposit pledgeStatus(Integer pledgeStatus) {
        this.pledgeStatus = pledgeStatus;
        return this;
    }

    public void setPledgeStatus(Integer pledgeStatus) {
        this.pledgeStatus = pledgeStatus;
    }

    public String getRemark() {
        return remark;
    }

    public RetreatDeposit remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public RetreatDeposit receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public RetreatDeposit receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getNextRentTime() {
        return nextRentTime;
    }

    public RetreatDeposit nextRentTime(Long nextRentTime) {
        this.nextRentTime = nextRentTime;
        return this;
    }

    public void setNextRentTime(Long nextRentTime) {
        this.nextRentTime = nextRentTime;
    }

    public String getFounder() {
        return founder;
    }

    public RetreatDeposit founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public RetreatDeposit founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public RetreatDeposit modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public RetreatDeposit modifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
        return this;
    }

    public void setModifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
    }

    public Integer getWhetherRefund() {
        return whetherRefund;
    }

    public RetreatDeposit whetherRefund(Integer whetherRefund) {
        this.whetherRefund = whetherRefund;
        return this;
    }

    public void setWhetherRefund(Integer whetherRefund) {
        this.whetherRefund = whetherRefund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RetreatDeposit retreatDeposit = (RetreatDeposit) o;
        if (retreatDeposit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, retreatDeposit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RetreatDeposit{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", orderId='" + orderId + "'" +
            ", companyId='" + companyId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", refundTime='" + refundTime + "'" +
            ", money='" + money + "'" +
            ", source='" + source + "'" +
            ", pledgeStatus='" + pledgeStatus + "'" +
            ", remark='" + remark + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", nextRentTime='" + nextRentTime + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            ", whetherRefund='" + whetherRefund + "'" +
            '}';
    }
}
