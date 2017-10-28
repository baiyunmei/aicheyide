package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "engine")
    private String engine;

    @Column(name = "vehicle_shelf")
    private String vehicleShelf;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "vehicle_colour")
    private String vehicleColour;

    @Column(name = "status")
    private Integer status;

    @Column(name = "stop_location")
    private String stopLocation;

    @Column(name = "brand_type")
    private String brandType;

    @Column(name = "company")
    private String company;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Vehicle companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getEngine() {
        return engine;
    }

    public Vehicle engine(String engine) {
        this.engine = engine;
        return this;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getVehicleShelf() {
        return vehicleShelf;
    }

    public Vehicle vehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
        return this;
    }

    public void setVehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Vehicle plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getDriverId() {
        return driverId;
    }

    public Vehicle driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public Vehicle driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public Vehicle vehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
        return this;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public Integer getStatus() {
        return status;
    }

    public Vehicle status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public Vehicle stopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
        return this;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public String getBrandType() {
        return brandType;
    }

    public Vehicle brandType(String brandType) {
        this.brandType = brandType;
        return this;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getCompany() {
        return company;
    }

    public Vehicle company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Vehicle vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRemark() {
        return remark;
    }

    public Vehicle remark(String remark) {
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
        Vehicle vehicle = (Vehicle) o;
        if (vehicle.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", engine='" + engine + "'" +
            ", vehicleShelf='" + vehicleShelf + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverId='" + driverId + "'" +
            ", driverName='" + driverName + "'" +
            ", vehicleColour='" + vehicleColour + "'" +
            ", status='" + status + "'" +
            ", stopLocation='" + stopLocation + "'" +
            ", brandType='" + brandType + "'" +
            ", company='" + company + "'" +
            ", vehicleType='" + vehicleType + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
