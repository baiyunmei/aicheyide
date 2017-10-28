package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PostponeRecord.
 */
@Entity
@Table(name = "postpone_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PostponeRecord implements Serializable {

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

    @Column(name = "operation_time")
    private Long operationTime;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "postpone_data")
    private Integer postponeData;

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

    public PostponeRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public PostponeRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public PostponeRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public PostponeRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public PostponeRecord operationTime(Long operationTime) {
        this.operationTime = operationTime;
        return this;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public PostponeRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public PostponeRecord driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getPostponeData() {
        return postponeData;
    }

    public PostponeRecord postponeData(Integer postponeData) {
        this.postponeData = postponeData;
        return this;
    }

    public void setPostponeData(Integer postponeData) {
        this.postponeData = postponeData;
    }

    public String getRemark() {
        return remark;
    }

    public PostponeRecord remark(String remark) {
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
        PostponeRecord postponeRecord = (PostponeRecord) o;
        if (postponeRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, postponeRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PostponeRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", operationTime='" + operationTime + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", postponeData='" + postponeData + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
