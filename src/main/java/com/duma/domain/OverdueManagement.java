package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A OverdueManagement.
 */
@Entity
@Table(name = "overdue_management")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OverdueManagement implements Serializable {

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

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "repayment_time")
    private Long repaymentTime;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

    @Column(name = "overdue_data")
    private Integer overdueData;

    @Column(name = "overdue_interest", precision=10, scale=2)
    private BigDecimal overdueInterest;

    @Column(name = "next_money_data")
    private Long nextMoneyData;

    @Column(name = "postpone_data")
    private Integer postponeData;

    @Column(name = "inform_vehicle")
    private String informVehicle;

    @Column(name = "recycle_vehicle")
    private Long recycleVehicle;

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

    public OverdueManagement driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public OverdueManagement vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public OverdueManagement companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public OverdueManagement plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public OverdueManagement driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OverdueManagement orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRepaymentTime() {
        return repaymentTime;
    }

    public OverdueManagement repaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
        return this;
    }

    public void setRepaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public OverdueManagement money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getOverdueData() {
        return overdueData;
    }

    public OverdueManagement overdueData(Integer overdueData) {
        this.overdueData = overdueData;
        return this;
    }

    public void setOverdueData(Integer overdueData) {
        this.overdueData = overdueData;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public OverdueManagement overdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
        return this;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public Long getNextMoneyData() {
        return nextMoneyData;
    }

    public OverdueManagement nextMoneyData(Long nextMoneyData) {
        this.nextMoneyData = nextMoneyData;
        return this;
    }

    public void setNextMoneyData(Long nextMoneyData) {
        this.nextMoneyData = nextMoneyData;
    }

    public Integer getPostponeData() {
        return postponeData;
    }

    public OverdueManagement postponeData(Integer postponeData) {
        this.postponeData = postponeData;
        return this;
    }

    public void setPostponeData(Integer postponeData) {
        this.postponeData = postponeData;
    }

    public String getInformVehicle() {
        return informVehicle;
    }

    public OverdueManagement informVehicle(String informVehicle) {
        this.informVehicle = informVehicle;
        return this;
    }

    public void setInformVehicle(String informVehicle) {
        this.informVehicle = informVehicle;
    }

    public Long getRecycleVehicle() {
        return recycleVehicle;
    }

    public OverdueManagement recycleVehicle(Long recycleVehicle) {
        this.recycleVehicle = recycleVehicle;
        return this;
    }

    public void setRecycleVehicle(Long recycleVehicle) {
        this.recycleVehicle = recycleVehicle;
    }

    public String getRemark() {
        return remark;
    }

    public OverdueManagement remark(String remark) {
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
        OverdueManagement overdueManagement = (OverdueManagement) o;
        if (overdueManagement.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, overdueManagement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OverdueManagement{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", orderId='" + orderId + "'" +
            ", repaymentTime='" + repaymentTime + "'" +
            ", money='" + money + "'" +
            ", overdueData='" + overdueData + "'" +
            ", overdueInterest='" + overdueInterest + "'" +
            ", nextMoneyData='" + nextMoneyData + "'" +
            ", postponeData='" + postponeData + "'" +
            ", informVehicle='" + informVehicle + "'" +
            ", recycleVehicle='" + recycleVehicle + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
