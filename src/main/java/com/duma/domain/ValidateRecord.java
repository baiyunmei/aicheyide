package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ValidateRecord.
 */
@Entity
@Table(name = "validate_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValidateRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "operating_date")
    private Long operatingDate;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "validate_time")
    private Long validateTime;

    @Column(name = "kilometre")
    private Integer kilometre;

    @Column(name = "damage")
    private Integer damage;

    @Column(name = "describes")
    private String describes;

    @Column(name = "damagep_icture")
    private String damagepIcture;

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

    public ValidateRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ValidateRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public ValidateRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public ValidateRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public ValidateRecord receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getOperatingDate() {
        return operatingDate;
    }

    public ValidateRecord operatingDate(Long operatingDate) {
        this.operatingDate = operatingDate;
        return this;
    }

    public void setOperatingDate(Long operatingDate) {
        this.operatingDate = operatingDate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public ValidateRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getValidateTime() {
        return validateTime;
    }

    public ValidateRecord validateTime(Long validateTime) {
        this.validateTime = validateTime;
        return this;
    }

    public void setValidateTime(Long validateTime) {
        this.validateTime = validateTime;
    }

    public Integer getKilometre() {
        return kilometre;
    }

    public ValidateRecord kilometre(Integer kilometre) {
        this.kilometre = kilometre;
        return this;
    }

    public void setKilometre(Integer kilometre) {
        this.kilometre = kilometre;
    }

    public Integer getDamage() {
        return damage;
    }

    public ValidateRecord damage(Integer damage) {
        this.damage = damage;
        return this;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public String getDescribes() {
        return describes;
    }

    public ValidateRecord describes(String describes) {
        this.describes = describes;
        return this;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getDamagepIcture() {
        return damagepIcture;
    }

    public ValidateRecord damagepIcture(String damagepIcture) {
        this.damagepIcture = damagepIcture;
        return this;
    }

    public void setDamagepIcture(String damagepIcture) {
        this.damagepIcture = damagepIcture;
    }

    public String getRemark() {
        return remark;
    }

    public ValidateRecord remark(String remark) {
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
        ValidateRecord validateRecord = (ValidateRecord) o;
        if (validateRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, validateRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ValidateRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", companyId='" + companyId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", operatingDate='" + operatingDate + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", validateTime='" + validateTime + "'" +
            ", kilometre='" + kilometre + "'" +
            ", damage='" + damage + "'" +
            ", describes='" + describes + "'" +
            ", damagepIcture='" + damagepIcture + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
