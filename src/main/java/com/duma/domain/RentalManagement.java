package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A RentalManagement.
 */
@Entity
@Table(name = "rental_management")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RentalManagement implements Serializable {

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

    @Column(name = "rent_time")
    private Long rentTime;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

    @Column(name = "overdue")
    private Integer overdue;

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

    @Column(name = "whether_gather")
    private Integer whetherGather;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public RentalManagement driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public RentalManagement orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public RentalManagement companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public RentalManagement vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public RentalManagement plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public RentalManagement driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getRentTime() {
        return rentTime;
    }

    public RentalManagement rentTime(Long rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public void setRentTime(Long rentTime) {
        this.rentTime = rentTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public RentalManagement money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public RentalManagement overdue(Integer overdue) {
        this.overdue = overdue;
        return this;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public String getRemark() {
        return remark;
    }

    public RentalManagement remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public RentalManagement receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public RentalManagement receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getNextRentTime() {
        return nextRentTime;
    }

    public RentalManagement nextRentTime(Long nextRentTime) {
        this.nextRentTime = nextRentTime;
        return this;
    }

    public void setNextRentTime(Long nextRentTime) {
        this.nextRentTime = nextRentTime;
    }

    public String getFounder() {
        return founder;
    }

    public RentalManagement founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public RentalManagement founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public RentalManagement modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public RentalManagement modifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
        return this;
    }

    public void setModifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
    }

    public Integer getWhetherGather() {
        return whetherGather;
    }

    public RentalManagement whetherGather(Integer whetherGather) {
        this.whetherGather = whetherGather;
        return this;
    }

    public void setWhetherGather(Integer whetherGather) {
        this.whetherGather = whetherGather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RentalManagement rentalManagement = (RentalManagement) o;
        if (rentalManagement.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rentalManagement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RentalManagement{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", orderId='" + orderId + "'" +
            ", companyId='" + companyId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", rentTime='" + rentTime + "'" +
            ", money='" + money + "'" +
            ", overdue='" + overdue + "'" +
            ", remark='" + remark + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", nextRentTime='" + nextRentTime + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            ", whetherGather='" + whetherGather + "'" +
            '}';
    }
}
