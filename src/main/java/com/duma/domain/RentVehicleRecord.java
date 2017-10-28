package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A RentVehicleRecord.
 */
@Entity
@Table(name = "rent_vehicle_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RentVehicleRecord implements Serializable {

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

    @Column(name = "phone")
    private String phone;

    @Column(name = "contract_start_date")
    private Long contractStartDate;

    @Column(name = "contract_end_date")
    private Long contractEndDate;

    @Column(name = "monthly_rent", precision=10, scale=2)
    private BigDecimal monthlyRent;

    @Column(name = "cash_pledge", precision=10, scale=2)
    private BigDecimal cashPledge;

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

    public RentVehicleRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public RentVehicleRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public RentVehicleRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public RentVehicleRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public RentVehicleRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public RentVehicleRecord driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhone() {
        return phone;
    }

    public RentVehicleRecord phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getContractStartDate() {
        return contractStartDate;
    }

    public RentVehicleRecord contractStartDate(Long contractStartDate) {
        this.contractStartDate = contractStartDate;
        return this;
    }

    public void setContractStartDate(Long contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Long getContractEndDate() {
        return contractEndDate;
    }

    public RentVehicleRecord contractEndDate(Long contractEndDate) {
        this.contractEndDate = contractEndDate;
        return this;
    }

    public void setContractEndDate(Long contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public RentVehicleRecord monthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
        return this;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public RentVehicleRecord cashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
        return this;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Integer getStatus() {
        return status;
    }

    public RentVehicleRecord status(Integer status) {
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
        RentVehicleRecord rentVehicleRecord = (RentVehicleRecord) o;
        if (rentVehicleRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rentVehicleRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RentVehicleRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", phone='" + phone + "'" +
            ", contractStartDate='" + contractStartDate + "'" +
            ", contractEndDate='" + contractEndDate + "'" +
            ", monthlyRent='" + monthlyRent + "'" +
            ", cashPledge='" + cashPledge + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
