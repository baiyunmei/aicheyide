package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A GasRefit.
 */
@Entity
@Table(name = "gas_refit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GasRefit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "refit")
    private Integer refit;

    @Column(name = "remark")
    private String remark;

    @Column(name = "refit_record")
    private String refitRecord;

    @Column(name = "refit_time")
    private Long refitTime;

    @Column(name = "refit_unit")
    private String refitUnit;

    @Column(name = "phone")
    private String phone;

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

    public GasRefit vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getRefit() {
        return refit;
    }

    public GasRefit refit(Integer refit) {
        this.refit = refit;
        return this;
    }

    public void setRefit(Integer refit) {
        this.refit = refit;
    }

    public String getRemark() {
        return remark;
    }

    public GasRefit remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefitRecord() {
        return refitRecord;
    }

    public GasRefit refitRecord(String refitRecord) {
        this.refitRecord = refitRecord;
        return this;
    }

    public void setRefitRecord(String refitRecord) {
        this.refitRecord = refitRecord;
    }

    public Long getRefitTime() {
        return refitTime;
    }

    public GasRefit refitTime(Long refitTime) {
        this.refitTime = refitTime;
        return this;
    }

    public void setRefitTime(Long refitTime) {
        this.refitTime = refitTime;
    }

    public String getRefitUnit() {
        return refitUnit;
    }

    public GasRefit refitUnit(String refitUnit) {
        this.refitUnit = refitUnit;
        return this;
    }

    public void setRefitUnit(String refitUnit) {
        this.refitUnit = refitUnit;
    }

    public String getPhone() {
        return phone;
    }

    public GasRefit phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFounder() {
        return founder;
    }

    public GasRefit founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public GasRefit founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public GasRefit modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public GasRefit modifierTime(Long modifierTime) {
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
        GasRefit gasRefit = (GasRefit) o;
        if (gasRefit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, gasRefit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GasRefit{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + "'" +
            ", refit='" + refit + "'" +
            ", remark='" + remark + "'" +
            ", refitRecord='" + refitRecord + "'" +
            ", refitTime='" + refitTime + "'" +
            ", refitUnit='" + refitUnit + "'" +
            ", phone='" + phone + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            '}';
    }
}
