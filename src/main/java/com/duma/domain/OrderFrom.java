package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A OrderFrom.
 */
@Entity
@Table(name = "order_from")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "market_id")
    private Long marketId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "type")
    private String type;

    @Column(name = "versions")
    private Integer versions;

    @Column(name = "year")
    private String year;

    @Column(name = "refit")
    private Integer refit;

    @Column(name = "sales_plan")
    private String salesPlan;

    @Column(name = "sales_plan_id")
    private String salesPlanId;

    @Column(name = "down_payment", precision=10, scale=2)
    private BigDecimal downPayment;

    @Column(name = "monthly", precision=10, scale=2)
    private BigDecimal monthly;

    @Column(name = "periods")
    private Integer periods;

    @Column(name = "cash_pledge", precision=10, scale=2)
    private BigDecimal cashPledge;

    @Column(name = "rent", precision=10, scale=2)
    private BigDecimal rent;

    @Column(name = "referrals_id_number")
    private String referralsIdNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "audit_status")
    private Integer auditStatus;

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

    public OrderFrom companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public OrderFrom marketId(Long marketId) {
        this.marketId = marketId;
        return this;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public OrderFrom driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getProductType() {
        return productType;
    }

    public OrderFrom productType(String productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public OrderFrom fuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBrandName() {
        return brandName;
    }

    public OrderFrom brandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getType() {
        return type;
    }

    public OrderFrom type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVersions() {
        return versions;
    }

    public OrderFrom versions(Integer versions) {
        this.versions = versions;
        return this;
    }

    public void setVersions(Integer versions) {
        this.versions = versions;
    }

    public String getYear() {
        return year;
    }

    public OrderFrom year(String year) {
        this.year = year;
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getRefit() {
        return refit;
    }

    public OrderFrom refit(Integer refit) {
        this.refit = refit;
        return this;
    }

    public void setRefit(Integer refit) {
        this.refit = refit;
    }

    public String getSalesPlan() {
        return salesPlan;
    }

    public OrderFrom salesPlan(String salesPlan) {
        this.salesPlan = salesPlan;
        return this;
    }

    public void setSalesPlan(String salesPlan) {
        this.salesPlan = salesPlan;
    }

    public String getSalesPlanId() {
        return salesPlanId;
    }

    public OrderFrom salesPlanId(String salesPlanId) {
        this.salesPlanId = salesPlanId;
        return this;
    }

    public void setSalesPlanId(String salesPlanId) {
        this.salesPlanId = salesPlanId;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public OrderFrom downPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
        return this;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public OrderFrom monthly(BigDecimal monthly) {
        this.monthly = monthly;
        return this;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Integer getPeriods() {
        return periods;
    }

    public OrderFrom periods(Integer periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public OrderFrom cashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
        return this;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public OrderFrom rent(BigDecimal rent) {
        this.rent = rent;
        return this;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getReferralsIdNumber() {
        return referralsIdNumber;
    }

    public OrderFrom referralsIdNumber(String referralsIdNumber) {
        this.referralsIdNumber = referralsIdNumber;
        return this;
    }

    public void setReferralsIdNumber(String referralsIdNumber) {
        this.referralsIdNumber = referralsIdNumber;
    }

    public String getPhone() {
        return phone;
    }

    public OrderFrom phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public OrderFrom name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public OrderFrom auditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
        return this;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderFrom status(Integer status) {
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
        OrderFrom orderFrom = (OrderFrom) o;
        if (orderFrom.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orderFrom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderFrom{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", marketId='" + marketId + "'" +
            ", driverId='" + driverId + "'" +
            ", productType='" + productType + "'" +
            ", fuelType='" + fuelType + "'" +
            ", brandName='" + brandName + "'" +
            ", type='" + type + "'" +
            ", versions='" + versions + "'" +
            ", year='" + year + "'" +
            ", refit='" + refit + "'" +
            ", salesPlan='" + salesPlan + "'" +
            ", salesPlanId='" + salesPlanId + "'" +
            ", downPayment='" + downPayment + "'" +
            ", monthly='" + monthly + "'" +
            ", periods='" + periods + "'" +
            ", cashPledge='" + cashPledge + "'" +
            ", rent='" + rent + "'" +
            ", referralsIdNumber='" + referralsIdNumber + "'" +
            ", phone='" + phone + "'" +
            ", name='" + name + "'" +
            ", auditStatus='" + auditStatus + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
