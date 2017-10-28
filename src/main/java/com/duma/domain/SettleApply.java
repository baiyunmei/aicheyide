package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A SettleApply.
 */
@Entity
@Table(name = "settle_apply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SettleApply implements Serializable {

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

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "settle_type")
    private String settleType;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "deduct_marks")
    private Integer deductMarks;

    @Column(name = "fine", precision=10, scale=2)
    private BigDecimal fine;

    @Column(name = "pending", precision=10, scale=2)
    private BigDecimal pending;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public SettleApply driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public SettleApply vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public SettleApply companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public SettleApply plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public SettleApply driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSettleType() {
        return settleType;
    }

    public SettleApply settleType(String settleType) {
        this.settleType = settleType;
        return this;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public SettleApply receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public SettleApply receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getDeductMarks() {
        return deductMarks;
    }

    public SettleApply deductMarks(Integer deductMarks) {
        this.deductMarks = deductMarks;
        return this;
    }

    public void setDeductMarks(Integer deductMarks) {
        this.deductMarks = deductMarks;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public SettleApply fine(BigDecimal fine) {
        this.fine = fine;
        return this;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public BigDecimal getPending() {
        return pending;
    }

    public SettleApply pending(BigDecimal pending) {
        this.pending = pending;
        return this;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }

    public String getRemark() {
        return remark;
    }

    public SettleApply remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public SettleApply status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SettleApply settleApply = (SettleApply) o;
        if (settleApply.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, settleApply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SettleApply{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", settleType='" + settleType + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", deductMarks='" + deductMarks + "'" +
            ", fine='" + fine + "'" +
            ", pending='" + pending + "'" +
            ", remark='" + remark + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
