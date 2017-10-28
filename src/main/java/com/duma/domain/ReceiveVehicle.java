package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ReceiveVehicle.
 */
@Entity
@Table(name = "receive_vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReceiveVehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "inform_vehicle_time")
    private Long informVehicleTime;

    @Column(name = "inform_data")
    private String informData;

    @Column(name = "inform_vehicle_date")
    private Long informVehicleDate;

    @Column(name = "remark")
    private String remark;

    @Column(name = "park_warehouse")
    private String parkWarehouse;

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

    public ReceiveVehicle vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getInformVehicleTime() {
        return informVehicleTime;
    }

    public ReceiveVehicle informVehicleTime(Long informVehicleTime) {
        this.informVehicleTime = informVehicleTime;
        return this;
    }

    public void setInformVehicleTime(Long informVehicleTime) {
        this.informVehicleTime = informVehicleTime;
    }

    public String getInformData() {
        return informData;
    }

    public ReceiveVehicle informData(String informData) {
        this.informData = informData;
        return this;
    }

    public void setInformData(String informData) {
        this.informData = informData;
    }

    public Long getInformVehicleDate() {
        return informVehicleDate;
    }

    public ReceiveVehicle informVehicleDate(Long informVehicleDate) {
        this.informVehicleDate = informVehicleDate;
        return this;
    }

    public void setInformVehicleDate(Long informVehicleDate) {
        this.informVehicleDate = informVehicleDate;
    }

    public String getRemark() {
        return remark;
    }

    public ReceiveVehicle remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParkWarehouse() {
        return parkWarehouse;
    }

    public ReceiveVehicle parkWarehouse(String parkWarehouse) {
        this.parkWarehouse = parkWarehouse;
        return this;
    }

    public void setParkWarehouse(String parkWarehouse) {
        this.parkWarehouse = parkWarehouse;
    }

    public String getFounder() {
        return founder;
    }

    public ReceiveVehicle founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public ReceiveVehicle founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public ReceiveVehicle modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public ReceiveVehicle modifierTime(Long modifierTime) {
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
        ReceiveVehicle receiveVehicle = (ReceiveVehicle) o;
        if (receiveVehicle.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, receiveVehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReceiveVehicle{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + "'" +
            ", informVehicleTime='" + informVehicleTime + "'" +
            ", informData='" + informData + "'" +
            ", informVehicleDate='" + informVehicleDate + "'" +
            ", remark='" + remark + "'" +
            ", parkWarehouse='" + parkWarehouse + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            '}';
    }
}
