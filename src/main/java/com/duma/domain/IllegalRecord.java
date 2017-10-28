package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A IllegalRecord.
 */
@Entity
@Table(name = "illegal_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IllegalRecord implements Serializable {

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

    @Column(name = "illegal_date")
    private Long illegalDate;

    @Column(name = "illegal_site")
    private String illegalSite;

    @Column(name = "detail")
    private String detail;

    @Column(name = "illegal_money", precision=10, scale=2)
    private BigDecimal illegalMoney;

    @Column(name = "illegal_deduct")
    private Integer illegalDeduct;

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

    public IllegalRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public IllegalRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public IllegalRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public IllegalRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getIllegalDate() {
        return illegalDate;
    }

    public IllegalRecord illegalDate(Long illegalDate) {
        this.illegalDate = illegalDate;
        return this;
    }

    public void setIllegalDate(Long illegalDate) {
        this.illegalDate = illegalDate;
    }

    public String getIllegalSite() {
        return illegalSite;
    }

    public IllegalRecord illegalSite(String illegalSite) {
        this.illegalSite = illegalSite;
        return this;
    }

    public void setIllegalSite(String illegalSite) {
        this.illegalSite = illegalSite;
    }

    public String getDetail() {
        return detail;
    }

    public IllegalRecord detail(String detail) {
        this.detail = detail;
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getIllegalMoney() {
        return illegalMoney;
    }

    public IllegalRecord illegalMoney(BigDecimal illegalMoney) {
        this.illegalMoney = illegalMoney;
        return this;
    }

    public void setIllegalMoney(BigDecimal illegalMoney) {
        this.illegalMoney = illegalMoney;
    }

    public Integer getIllegalDeduct() {
        return illegalDeduct;
    }

    public IllegalRecord illegalDeduct(Integer illegalDeduct) {
        this.illegalDeduct = illegalDeduct;
        return this;
    }

    public void setIllegalDeduct(Integer illegalDeduct) {
        this.illegalDeduct = illegalDeduct;
    }

    public Integer getStatus() {
        return status;
    }

    public IllegalRecord status(Integer status) {
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
        IllegalRecord illegalRecord = (IllegalRecord) o;
        if (illegalRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, illegalRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IllegalRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", illegalDate='" + illegalDate + "'" +
            ", illegalSite='" + illegalSite + "'" +
            ", detail='" + detail + "'" +
            ", illegalMoney='" + illegalMoney + "'" +
            ", illegalDeduct='" + illegalDeduct + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
