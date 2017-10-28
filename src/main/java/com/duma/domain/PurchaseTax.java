package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A PurchaseTax.
 */
@Entity
@Table(name = "purchase_tax")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseTax implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "purchase_taxmoney", precision=10, scale=2)
    private BigDecimal purchaseTaxmoney;

    @Column(name = "remark")
    private String remark;

    @Column(name = "invoice_picture")
    private String invoicePicture;

    @Column(name = "purchase_time")
    private Long purchaseTime;

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

    public PurchaseTax vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public BigDecimal getPurchaseTaxmoney() {
        return purchaseTaxmoney;
    }

    public PurchaseTax purchaseTaxmoney(BigDecimal purchaseTaxmoney) {
        this.purchaseTaxmoney = purchaseTaxmoney;
        return this;
    }

    public void setPurchaseTaxmoney(BigDecimal purchaseTaxmoney) {
        this.purchaseTaxmoney = purchaseTaxmoney;
    }

    public String getRemark() {
        return remark;
    }

    public PurchaseTax remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInvoicePicture() {
        return invoicePicture;
    }

    public PurchaseTax invoicePicture(String invoicePicture) {
        this.invoicePicture = invoicePicture;
        return this;
    }

    public void setInvoicePicture(String invoicePicture) {
        this.invoicePicture = invoicePicture;
    }

    public Long getPurchaseTime() {
        return purchaseTime;
    }

    public PurchaseTax purchaseTime(Long purchaseTime) {
        this.purchaseTime = purchaseTime;
        return this;
    }

    public void setPurchaseTime(Long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getFounder() {
        return founder;
    }

    public PurchaseTax founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public PurchaseTax founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public PurchaseTax modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public PurchaseTax modifierTime(Long modifierTime) {
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
        PurchaseTax purchaseTax = (PurchaseTax) o;
        if (purchaseTax.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, purchaseTax.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PurchaseTax{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + "'" +
            ", purchaseTaxmoney='" + purchaseTaxmoney + "'" +
            ", remark='" + remark + "'" +
            ", invoicePicture='" + invoicePicture + "'" +
            ", purchaseTime='" + purchaseTime + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            '}';
    }
}
