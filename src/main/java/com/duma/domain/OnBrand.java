package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OnBrand.
 */
@Entity
@Table(name = "on_brand")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "gpsdevice")
    private String gpsdevice;

    @Column(name = "remark")
    private String remark;

    @Column(name = "gps_install")
    private String gpsInstall;

    @Column(name = "install_time")
    private Long installTime;

    @Column(name = "founder")
    private String founder;

    @Column(name = "founder_time")
    private Long founderTime;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifier_time")
    private Long modifierTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public OnBrand vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getGpsdevice() {
        return gpsdevice;
    }

    public OnBrand gpsdevice(String gpsdevice) {
        this.gpsdevice = gpsdevice;
        return this;
    }

    public void setGpsdevice(String gpsdevice) {
        this.gpsdevice = gpsdevice;
    }

    public String getRemark() {
        return remark;
    }

    public OnBrand remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGpsInstall() {
        return gpsInstall;
    }

    public OnBrand gpsInstall(String gpsInstall) {
        this.gpsInstall = gpsInstall;
        return this;
    }

    public void setGpsInstall(String gpsInstall) {
        this.gpsInstall = gpsInstall;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public OnBrand installTime(Long installTime) {
        this.installTime = installTime;
        return this;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public String getFounder() {
        return founder;
    }

    public OnBrand founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public OnBrand founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public OnBrand modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public OnBrand modifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
        return this;
    }

    public void setModifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnBrand onBrand = (OnBrand) o;
        if (onBrand.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, onBrand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OnBrand{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + "'" +
            ", gpsdevice='" + gpsdevice + "'" +
            ", remark='" + remark + "'" +
            ", gpsInstall='" + gpsInstall + "'" +
            ", installTime='" + installTime + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            '}';
    }
}
