package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SupplierRecord.
 */
@Entity
@Table(name = "supplier_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SupplierRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "mnemonic_code")
    private String mnemonicCode;

    @Column(name = "supplier_status")
    private Integer supplierStatus;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "open_bank")
    private String openBank;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "unit_address")
    private String unitAddress;

    @Column(name = "contact")
    private String contact;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "paytype")
    private Integer paytype;

    @Column(name = "founder")
    private String founder;

    @Column(name = "founder_time")
    private Long founderTime;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifier_time")
    private Long modifierTime;

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

    public SupplierRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public SupplierRecord mnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
        return this;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public Integer getSupplierStatus() {
        return supplierStatus;
    }

    public SupplierRecord supplierStatus(Integer supplierStatus) {
        this.supplierStatus = supplierStatus;
        return this;
    }

    public void setSupplierStatus(Integer supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public SupplierRecord supplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public SupplierRecord taxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
        return this;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPhone() {
        return phone;
    }

    public SupplierRecord phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenBank() {
        return openBank;
    }

    public SupplierRecord openBank(String openBank) {
        this.openBank = openBank;
        return this;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public SupplierRecord bankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public SupplierRecord unitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
        return this;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getContact() {
        return contact;
    }

    public SupplierRecord contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public SupplierRecord contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public SupplierRecord paytype(Integer paytype) {
        this.paytype = paytype;
        return this;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getFounder() {
        return founder;
    }

    public SupplierRecord founder(String founder) {
        this.founder = founder;
        return this;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getFounderTime() {
        return founderTime;
    }

    public SupplierRecord founderTime(Long founderTime) {
        this.founderTime = founderTime;
        return this;
    }

    public void setFounderTime(Long founderTime) {
        this.founderTime = founderTime;
    }

    public String getModifier() {
        return modifier;
    }

    public SupplierRecord modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierTime() {
        return modifierTime;
    }

    public SupplierRecord modifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
        return this;
    }

    public void setModifierTime(Long modifierTime) {
        this.modifierTime = modifierTime;
    }

    public String getRemark() {
        return remark;
    }

    public SupplierRecord remark(String remark) {
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
        SupplierRecord supplierRecord = (SupplierRecord) o;
        if (supplierRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, supplierRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SupplierRecord{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", mnemonicCode='" + mnemonicCode + "'" +
            ", supplierStatus='" + supplierStatus + "'" +
            ", supplierName='" + supplierName + "'" +
            ", taxNumber='" + taxNumber + "'" +
            ", phone='" + phone + "'" +
            ", openBank='" + openBank + "'" +
            ", bankAccount='" + bankAccount + "'" +
            ", unitAddress='" + unitAddress + "'" +
            ", contact='" + contact + "'" +
            ", contactPhone='" + contactPhone + "'" +
            ", paytype='" + paytype + "'" +
            ", founder='" + founder + "'" +
            ", founderTime='" + founderTime + "'" +
            ", modifier='" + modifier + "'" +
            ", modifierTime='" + modifierTime + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
