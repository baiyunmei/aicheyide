package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A InsurancePurchase.
 */
@Entity
@Table(name = "insurance_purchase")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InsurancePurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "insurance_money", precision=10, scale=2)
    private BigDecimal insuranceMoney;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "ibegin_date")
    private Long ibeginDate;

    @Column(name = "ifinish_date")
    private Long ifinishDate;

    @Column(name = "icompany_name")
    private String icompanyName;

    @Column(name = "commercial_company")
    private String commercialCompany;

    @Column(name = "commercial_money", precision=10, scale=2)
    private BigDecimal commercialMoney;

    @Column(name = "commercial_number")
    private String commercialNumber;

    @Column(name = "cbegin_date")
    private Long cbeginDate;

    @Column(name = "cfinish_date")
    private Long cfinishDate;

    @Column(name = "ccompany_name")
    private String ccompanyName;

    @Column(name = "remark")
    private String remark;

    @Column(name = "iphotograph")
    private String iphotograph;

    @Column(name = "cphotograph")
    private String cphotograph;

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

    public InsurancePurchase vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public InsurancePurchase insuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
        return this;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public BigDecimal getInsuranceMoney() {
        return insuranceMoney;
    }

    public InsurancePurchase insuranceMoney(BigDecimal insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
        return this;
    }

    public void setInsuranceMoney(BigDecimal insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public InsurancePurchase policyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
        return this;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Long getIbeginDate() {
        return ibeginDate;
    }

    public InsurancePurchase ibeginDate(Long ibeginDate) {
        this.ibeginDate = ibeginDate;
        return this;
    }

    public void setIbeginDate(Long ibeginDate) {
        this.ibeginDate = ibeginDate;
    }

    public Long getIfinishDate() {
        return ifinishDate;
    }

    public InsurancePurchase ifinishDate(Long ifinishDate) {
        this.ifinishDate = ifinishDate;
        return this;
    }

    public void setIfinishDate(Long ifinishDate) {
        this.ifinishDate = ifinishDate;
    }

    public String getIcompanyName() {
        return icompanyName;
    }

    public InsurancePurchase icompanyName(String icompanyName) {
        this.icompanyName = icompanyName;
        return this;
    }

    public void setIcompanyName(String icompanyName) {
        this.icompanyName = icompanyName;
    }

    public String getCommercialCompany() {
        return commercialCompany;
    }

    public InsurancePurchase commercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany;
        return this;
    }

    public void setCommercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany;
    }

    public BigDecimal getCommercialMoney() {
        return commercialMoney;
    }

    public InsurancePurchase commercialMoney(BigDecimal commercialMoney) {
        this.commercialMoney = commercialMoney;
        return this;
    }

    public void setCommercialMoney(BigDecimal commercialMoney) {
        this.commercialMoney = commercialMoney;
    }

    public String getCommercialNumber() {
        return commercialNumber;
    }

    public InsurancePurchase commercialNumber(String commercialNumber) {
        this.commercialNumber = commercialNumber;
        return this;
    }

    public void setCommercialNumber(String commercialNumber) {
        this.commercialNumber = commercialNumber;
    }

    public Long getCbeginDate() {
        return cbeginDate;
    }

    public InsurancePurchase cbeginDate(Long cbeginDate) {
        this.cbeginDate = cbeginDate;
        return this;
    }

    public void setCbeginDate(Long cbeginDate) {
        this.cbeginDate = cbeginDate;
    }

    public Long getCfinishDate() {
        return cfinishDate;
    }

    public InsurancePurchase cfinishDate(Long cfinishDate) {
        this.cfinishDate = cfinishDate;
        return this;
    }

    public void setCfinishDate(Long cfinishDate) {
        this.cfinishDate = cfinishDate;
    }

    public String getCcompanyName() {
        return ccompanyName;
    }

    public InsurancePurchase ccompanyName(String ccompanyName) {
        this.ccompanyName = ccompanyName;
        return this;
    }

    public void setCcompanyName(String ccompanyName) {
        this.ccompanyName = ccompanyName;
    }

    public String getRemark() {
        return remark;
    }

    public InsurancePurchase remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIphotograph() {
        return iphotograph;
    }

    public InsurancePurchase iphotograph(String iphotograph) {
        this.iphotograph = iphotograph;
        return this;
    }

    public void setIphotograph(String iphotograph) {
        this.iphotograph = iphotograph;
    }

    public String getCphotograph() {
        return cphotograph;
    }

    public InsurancePurchase cphotograph(String cphotograph) {
        this.cphotograph = cphotograph;
        return this;
    }

    public void setCphotograph(String cphotograph) {
        this.cphotograph = cphotograph;
    }

    public Long getPurchaseTime() {
        return purchaseTime;
    }

    public InsurancePurchase purchaseTime(Long purchaseTime) {
        this.purchaseTime = purchaseTime;
        return this;
    }

    public void setPurchaseTime(Long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getFounder() {
        return founder;
    }

    public InsurancePurchase founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public InsurancePurchase founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public InsurancePurchase modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public InsurancePurchase modifierTime(Long modifierTime) {
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
        InsurancePurchase insurancePurchase = (InsurancePurchase) o;
        if (insurancePurchase.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, insurancePurchase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InsurancePurchase{" +
            "id=" + id +
            ", vehicleId='" + vehicleId + "'" +
            ", insuranceCompany='" + insuranceCompany + "'" +
            ", insuranceMoney='" + insuranceMoney + "'" +
            ", policyNumber='" + policyNumber + "'" +
            ", ibeginDate='" + ibeginDate + "'" +
            ", ifinishDate='" + ifinishDate + "'" +
            ", icompanyName='" + icompanyName + "'" +
            ", commercialCompany='" + commercialCompany + "'" +
            ", commercialMoney='" + commercialMoney + "'" +
            ", commercialNumber='" + commercialNumber + "'" +
            ", cbeginDate='" + cbeginDate + "'" +
            ", cfinishDate='" + cfinishDate + "'" +
            ", ccompanyName='" + ccompanyName + "'" +
            ", remark='" + remark + "'" +
            ", iphotograph='" + iphotograph + "'" +
            ", cphotograph='" + cphotograph + "'" +
            ", purchaseTime='" + purchaseTime + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            '}';
    }
}
