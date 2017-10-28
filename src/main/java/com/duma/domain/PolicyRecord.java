package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PolicyRecord.
 */
@Entity
@Table(name = "policy_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PolicyRecord implements Serializable {

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

    @Column(name = "vi_begin_date")
    private Long viBeginDate;

    @Column(name = "vi_finish_date")
    private Long viFinishDate;

    @Column(name = "ci_begin_date")
    private Long ciBeginDate;

    @Column(name = "ci_finish_date")
    private Long ciFinishDate;

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

    public PolicyRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public PolicyRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public PolicyRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public PolicyRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public PolicyRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getViBeginDate() {
        return viBeginDate;
    }

    public PolicyRecord viBeginDate(Long viBeginDate) {
        this.viBeginDate = viBeginDate;
        return this;
    }

    public void setViBeginDate(Long viBeginDate) {
        this.viBeginDate = viBeginDate;
    }

    public Long getViFinishDate() {
        return viFinishDate;
    }

    public PolicyRecord viFinishDate(Long viFinishDate) {
        this.viFinishDate = viFinishDate;
        return this;
    }

    public void setViFinishDate(Long viFinishDate) {
        this.viFinishDate = viFinishDate;
    }

    public Long getCiBeginDate() {
        return ciBeginDate;
    }

    public PolicyRecord ciBeginDate(Long ciBeginDate) {
        this.ciBeginDate = ciBeginDate;
        return this;
    }

    public void setCiBeginDate(Long ciBeginDate) {
        this.ciBeginDate = ciBeginDate;
    }

    public Long getCiFinishDate() {
        return ciFinishDate;
    }

    public PolicyRecord ciFinishDate(Long ciFinishDate) {
        this.ciFinishDate = ciFinishDate;
        return this;
    }

    public void setCiFinishDate(Long ciFinishDate) {
        this.ciFinishDate = ciFinishDate;
    }

    public String getRemark() {
        return remark;
    }

    public PolicyRecord remark(String remark) {
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
        PolicyRecord policyRecord = (PolicyRecord) o;
        if (policyRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, policyRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PolicyRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", viBeginDate='" + viBeginDate + "'" +
            ", viFinishDate='" + viFinishDate + "'" +
            ", ciBeginDate='" + ciBeginDate + "'" +
            ", ciFinishDate='" + ciFinishDate + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
