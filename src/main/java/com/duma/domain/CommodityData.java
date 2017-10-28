package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CommodityData.
 */
@Entity
@Table(name = "commodity_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CommodityData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "mnemonic_code")
    private String mnemonicCode;

    @Column(name = "brand")
    private String brand;

    @Column(name = "sales_name")
    private String salesName;

    @Column(name = "colour")
    private String colour;

    @Column(name = "size")
    private Integer size;

    @Column(name = "commodity_type")
    private String commodityType;

    @Column(name = "normal_price")
    private Double normalPrice;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public CommodityData companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public CommodityData mnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
        return this;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getBrand() {
        return brand;
    }

    public CommodityData brand(String brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSalesName() {
        return salesName;
    }

    public CommodityData salesName(String salesName) {
        this.salesName = salesName;
        return this;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getColour() {
        return colour;
    }

    public CommodityData colour(String colour) {
        this.colour = colour;
        return this;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getSize() {
        return size;
    }

    public CommodityData size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public CommodityData commodityType(String commodityType) {
        this.commodityType = commodityType;
        return this;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public Double getNormalPrice() {
        return normalPrice;
    }

    public CommodityData normalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
        return this;
    }

    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public CommodityData remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public CommodityData status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommodityData commodityData = (CommodityData) o;
        if (commodityData.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, commodityData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CommodityData{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", mnemonicCode='" + mnemonicCode + "'" +
            ", brand='" + brand + "'" +
            ", salesName='" + salesName + "'" +
            ", colour='" + colour + "'" +
            ", size='" + size + "'" +
            ", commodityType='" + commodityType + "'" +
            ", normalPrice='" + normalPrice + "'" +
            ", remark='" + remark + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
