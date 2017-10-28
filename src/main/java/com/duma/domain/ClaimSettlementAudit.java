package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A ClaimSettlementAudit.
 */
@Entity
@Table(name = "claim_settlement_audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimSettlementAudit implements Serializable {

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

    @Column(name = "claim_money", precision=10, scale=2)
    private BigDecimal claimMoney;

    @Column(name = "illegal_naam")
    private Integer illegalNaam;

    @Column(name = "illegal_number")
    private String illegalNumber;

    @Column(name = "illegal_accessory")
    private String illegalAccessory;

    @Column(name = "payment", precision=10, scale=2)
    private BigDecimal payment;

    @Column(name = "repair_accessory")
    private String repairAccessory;

    @Column(name = "debt", precision=10, scale=2)
    private BigDecimal debt;

    @Column(name = "debt_accessory")
    private String debtAccessory;

    @Column(name = "debt_remark")
    private String debtRemark;

    @Column(name = "actual_payment", precision=10, scale=2)
    private BigDecimal actualPayment;

    @Column(name = "payment_time")
    private Long paymentTime;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "payment_way")
    private Integer paymentWay;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "audit_status")
    private Integer auditStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public ClaimSettlementAudit driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public ClaimSettlementAudit orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ClaimSettlementAudit companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public ClaimSettlementAudit vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public BigDecimal getClaimMoney() {
        return claimMoney;
    }

    public ClaimSettlementAudit claimMoney(BigDecimal claimMoney) {
        this.claimMoney = claimMoney;
        return this;
    }

    public void setClaimMoney(BigDecimal claimMoney) {
        this.claimMoney = claimMoney;
    }

    public Integer getIllegalNaam() {
        return illegalNaam;
    }

    public ClaimSettlementAudit illegalNaam(Integer illegalNaam) {
        this.illegalNaam = illegalNaam;
        return this;
    }

    public void setIllegalNaam(Integer illegalNaam) {
        this.illegalNaam = illegalNaam;
    }

    public String getIllegalNumber() {
        return illegalNumber;
    }

    public ClaimSettlementAudit illegalNumber(String illegalNumber) {
        this.illegalNumber = illegalNumber;
        return this;
    }

    public void setIllegalNumber(String illegalNumber) {
        this.illegalNumber = illegalNumber;
    }

    public String getIllegalAccessory() {
        return illegalAccessory;
    }

    public ClaimSettlementAudit illegalAccessory(String illegalAccessory) {
        this.illegalAccessory = illegalAccessory;
        return this;
    }

    public void setIllegalAccessory(String illegalAccessory) {
        this.illegalAccessory = illegalAccessory;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public ClaimSettlementAudit payment(BigDecimal payment) {
        this.payment = payment;
        return this;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getRepairAccessory() {
        return repairAccessory;
    }

    public ClaimSettlementAudit repairAccessory(String repairAccessory) {
        this.repairAccessory = repairAccessory;
        return this;
    }

    public void setRepairAccessory(String repairAccessory) {
        this.repairAccessory = repairAccessory;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public ClaimSettlementAudit debt(BigDecimal debt) {
        this.debt = debt;
        return this;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public String getDebtAccessory() {
        return debtAccessory;
    }

    public ClaimSettlementAudit debtAccessory(String debtAccessory) {
        this.debtAccessory = debtAccessory;
        return this;
    }

    public void setDebtAccessory(String debtAccessory) {
        this.debtAccessory = debtAccessory;
    }

    public String getDebtRemark() {
        return debtRemark;
    }

    public ClaimSettlementAudit debtRemark(String debtRemark) {
        this.debtRemark = debtRemark;
        return this;
    }

    public void setDebtRemark(String debtRemark) {
        this.debtRemark = debtRemark;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public ClaimSettlementAudit actualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
        return this;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Long getPaymentTime() {
        return paymentTime;
    }

    public ClaimSettlementAudit paymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public ClaimSettlementAudit accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public ClaimSettlementAudit paymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
        return this;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ClaimSettlementAudit serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public ClaimSettlementAudit auditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
        return this;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClaimSettlementAudit claimSettlementAudit = (ClaimSettlementAudit) o;
        if (claimSettlementAudit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, claimSettlementAudit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClaimSettlementAudit{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", orderId='" + orderId + "'" +
            ", companyId='" + companyId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", claimMoney='" + claimMoney + "'" +
            ", illegalNaam='" + illegalNaam + "'" +
            ", illegalNumber='" + illegalNumber + "'" +
            ", illegalAccessory='" + illegalAccessory + "'" +
            ", payment='" + payment + "'" +
            ", repairAccessory='" + repairAccessory + "'" +
            ", debt='" + debt + "'" +
            ", debtAccessory='" + debtAccessory + "'" +
            ", debtRemark='" + debtRemark + "'" +
            ", actualPayment='" + actualPayment + "'" +
            ", paymentTime='" + paymentTime + "'" +
            ", accountNumber='" + accountNumber + "'" +
            ", paymentWay='" + paymentWay + "'" +
            ", serialNumber='" + serialNumber + "'" +
            ", auditStatus='" + auditStatus + "'" +
            '}';
    }
}
