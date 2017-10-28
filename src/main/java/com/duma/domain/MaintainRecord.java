package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A MaintainRecord.
 */
@Entity
@Table(name = "maintain_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MaintainRecord implements Serializable {

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

    @Column(name = "maintain_number")
    private String maintainNumber;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "maintain_time")
    private Long maintainTime;

    @Column(name = "money", precision=10, scale=2)
    private BigDecimal money;

    @Column(name = "repair_factory")
    private String repairFactory;

    @Column(name = "leave_factory_time")
    private Long leaveFactoryTime;

    @Column(name = "maintain_sky")
    private Integer maintainSky;

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

    public MaintainRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public MaintainRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public MaintainRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMaintainNumber() {
        return maintainNumber;
    }

    public MaintainRecord maintainNumber(String maintainNumber) {
        this.maintainNumber = maintainNumber;
        return this;
    }

    public void setMaintainNumber(String maintainNumber) {
        this.maintainNumber = maintainNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public MaintainRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public MaintainRecord driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getMaintainTime() {
        return maintainTime;
    }

    public MaintainRecord maintainTime(Long maintainTime) {
        this.maintainTime = maintainTime;
        return this;
    }

    public void setMaintainTime(Long maintainTime) {
        this.maintainTime = maintainTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public MaintainRecord money(BigDecimal money) {
        this.money = money;
        return this;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRepairFactory() {
        return repairFactory;
    }

    public MaintainRecord repairFactory(String repairFactory) {
        this.repairFactory = repairFactory;
        return this;
    }

    public void setRepairFactory(String repairFactory) {
        this.repairFactory = repairFactory;
    }

    public Long getLeaveFactoryTime() {
        return leaveFactoryTime;
    }

    public MaintainRecord leaveFactoryTime(Long leaveFactoryTime) {
        this.leaveFactoryTime = leaveFactoryTime;
        return this;
    }

    public void setLeaveFactoryTime(Long leaveFactoryTime) {
        this.leaveFactoryTime = leaveFactoryTime;
    }

    public Integer getMaintainSky() {
        return maintainSky;
    }

    public MaintainRecord maintainSky(Integer maintainSky) {
        this.maintainSky = maintainSky;
        return this;
    }

    public void setMaintainSky(Integer maintainSky) {
        this.maintainSky = maintainSky;
    }

    public String getRemark() {
        return remark;
    }

    public MaintainRecord remark(String remark) {
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
        MaintainRecord maintainRecord = (MaintainRecord) o;
        if (maintainRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maintainRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaintainRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", maintainNumber='" + maintainNumber + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", maintainTime='" + maintainTime + "'" +
            ", money='" + money + "'" +
            ", repairFactory='" + repairFactory + "'" +
            ", leaveFactoryTime='" + leaveFactoryTime + "'" +
            ", maintainSky='" + maintainSky + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
