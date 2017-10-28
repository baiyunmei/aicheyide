package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A AiRecord.
 */
@Entity
@Table(name = "ai_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AiRecord implements Serializable {

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

    @Column(name = "ai_date")
    private Long aiDate;

    @Column(name = "next_ai_date")
    private Long nextAiDate;

    @Column(name = "ai_conductor")
    private String aiConductor;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

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

    public AiRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public AiRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public AiRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public AiRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public AiRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getAiDate() {
        return aiDate;
    }

    public AiRecord aiDate(Long aiDate) {
        this.aiDate = aiDate;
        return this;
    }

    public void setAiDate(Long aiDate) {
        this.aiDate = aiDate;
    }

    public Long getNextAiDate() {
        return nextAiDate;
    }

    public AiRecord nextAiDate(Long nextAiDate) {
        this.nextAiDate = nextAiDate;
        return this;
    }

    public void setNextAiDate(Long nextAiDate) {
        this.nextAiDate = nextAiDate;
    }

    public String getAiConductor() {
        return aiConductor;
    }

    public AiRecord aiConductor(String aiConductor) {
        this.aiConductor = aiConductor;
        return this;
    }

    public void setAiConductor(String aiConductor) {
        this.aiConductor = aiConductor;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public AiRecord money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public AiRecord remark(String remark) {
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
        AiRecord aiRecord = (AiRecord) o;
        if (aiRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, aiRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AiRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", aiDate='" + aiDate + "'" +
            ", nextAiDate='" + nextAiDate + "'" +
            ", aiConductor='" + aiConductor + "'" +
            ", money='" + money + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
