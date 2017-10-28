package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DriverMate.
 */
@Entity
@Table(name = "driver_mate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DriverMate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone")
    private String phone;

    @Column(name = "certificate_type")
    private String certificateType;

    @Column(name = "certificate_phone")
    private String certificatePhone;

    @Column(name = "location")
    private String location;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "residential_address")
    private String residentialAddress;

    @Column(name = "unit_address")
    private String unitAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public DriverMate driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public DriverMate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public DriverMate sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public DriverMate phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public DriverMate certificateType(String certificateType) {
        this.certificateType = certificateType;
        return this;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificatePhone() {
        return certificatePhone;
    }

    public DriverMate certificatePhone(String certificatePhone) {
        this.certificatePhone = certificatePhone;
        return this;
    }

    public void setCertificatePhone(String certificatePhone) {
        this.certificatePhone = certificatePhone;
    }

    public String getLocation() {
        return location;
    }

    public DriverMate location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnitName() {
        return unitName;
    }

    public DriverMate unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public DriverMate residentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
        return this;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public DriverMate unitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
        return this;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriverMate driverMate = (DriverMate) o;
        if (driverMate.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, driverMate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DriverMate{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", name='" + name + "'" +
            ", sex='" + sex + "'" +
            ", phone='" + phone + "'" +
            ", certificateType='" + certificateType + "'" +
            ", certificatePhone='" + certificatePhone + "'" +
            ", location='" + location + "'" +
            ", unitName='" + unitName + "'" +
            ", residentialAddress='" + residentialAddress + "'" +
            ", unitAddress='" + unitAddress + "'" +
            '}';
    }
}
