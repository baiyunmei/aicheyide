package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A OutDangerRecord.
 */
@Entity
@Table(name = "out_danger_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OutDangerRecord implements Serializable {

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

    @Column(name = "come_time")
    private Long comeTime;

    @Column(name = "responsible_party")
    private Long responsibleParty;

    @Column(name = "one_money", precision=10, scale=2)
    private BigDecimal oneMoney;

    @Column(name = "three_money", precision=10, scale=2)
    private BigDecimal threeMoney;

    @Column(name = "repair_factory")
    private String repairFactory;

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

    public OutDangerRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public OutDangerRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public OutDangerRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public OutDangerRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public OutDangerRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public OutDangerRecord driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getComeTime() {
        return comeTime;
    }

    public OutDangerRecord comeTime(Long comeTime) {
        this.comeTime = comeTime;
        return this;
    }

    public void setComeTime(Long comeTime) {
        this.comeTime = comeTime;
    }

    public Long getResponsibleParty() {
        return responsibleParty;
    }

    public OutDangerRecord responsibleParty(Long responsibleParty) {
        this.responsibleParty = responsibleParty;
        return this;
    }

    public void setResponsibleParty(Long responsibleParty) {
        this.responsibleParty = responsibleParty;
    }

    public BigDecimal getOneMoney() {
        return oneMoney;
    }

    public OutDangerRecord oneMoney(BigDecimal oneMoney) {
        this.oneMoney = oneMoney;
        return this;
    }

    public void setOneMoney(BigDecimal oneMoney) {
        this.oneMoney = oneMoney;
    }

    public BigDecimal getThreeMoney() {
        return threeMoney;
    }

    public OutDangerRecord threeMoney(BigDecimal threeMoney) {
        this.threeMoney = threeMoney;
        return this;
    }

    public void setThreeMoney(BigDecimal threeMoney) {
        this.threeMoney = threeMoney;
    }

    public String getRepairFactory() {
        return repairFactory;
    }

    public OutDangerRecord repairFactory(String repairFactory) {
        this.repairFactory = repairFactory;
        return this;
    }

    public void setRepairFactory(String repairFactory) {
        this.repairFactory = repairFactory;
    }

    public String getRemark() {
        return remark;
    }

    public OutDangerRecord remark(String remark) {
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
        OutDangerRecord outDangerRecord = (OutDangerRecord) o;
        if (outDangerRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, outDangerRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OutDangerRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", comeTime='" + comeTime + "'" +
            ", responsibleParty='" + responsibleParty + "'" +
            ", oneMoney='" + oneMoney + "'" +
            ", threeMoney='" + threeMoney + "'" +
            ", repairFactory='" + repairFactory + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
