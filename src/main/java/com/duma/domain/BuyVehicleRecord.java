package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A BuyVehicleRecord.
 */
@Entity
@Table(name = "buy_vehicle_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BuyVehicleRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "buy_vehicle_date")
    private Long buyVehicleDate;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "vehicle_shelf")
    private String vehicleShelf;

    @Column(name = "engine")
    private String engine;

    @Column(name = "down_payment", precision=10, scale=2)
    private BigDecimal downPayment;

    @Column(name = "cash_pledge", precision=10, scale=2)
    private BigDecimal cashPledge;

    @Column(name = "periods")
    private Integer periods;

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "not_periods")
    private Integer notPeriods;

    @Column(name = "not_amount", precision=10, scale=2)
    private BigDecimal notAmount;

    @Column(name = "postpone_time")
    private Integer postponeTime;

    @Column(name = "overdue_tiem")
    private Integer overdueTiem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public BuyVehicleRecord driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public BuyVehicleRecord vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public BuyVehicleRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public BuyVehicleRecord receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getBuyVehicleDate() {
        return buyVehicleDate;
    }

    public BuyVehicleRecord buyVehicleDate(Long buyVehicleDate) {
        this.buyVehicleDate = buyVehicleDate;
        return this;
    }

    public void setBuyVehicleDate(Long buyVehicleDate) {
        this.buyVehicleDate = buyVehicleDate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public BuyVehicleRecord plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleShelf() {
        return vehicleShelf;
    }

    public BuyVehicleRecord vehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
        return this;
    }

    public void setVehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
    }

    public String getEngine() {
        return engine;
    }

    public BuyVehicleRecord engine(String engine) {
        this.engine = engine;
        return this;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public BuyVehicleRecord downPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
        return this;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public BuyVehicleRecord cashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
        return this;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Integer getPeriods() {
        return periods;
    }

    public BuyVehicleRecord periods(Integer periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BuyVehicleRecord amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getNotPeriods() {
        return notPeriods;
    }

    public BuyVehicleRecord notPeriods(Integer notPeriods) {
        this.notPeriods = notPeriods;
        return this;
    }

    public void setNotPeriods(Integer notPeriods) {
        this.notPeriods = notPeriods;
    }

    public BigDecimal getNotAmount() {
        return notAmount;
    }

    public BuyVehicleRecord notAmount(BigDecimal notAmount) {
        this.notAmount = notAmount;
        return this;
    }

    public void setNotAmount(BigDecimal notAmount) {
        this.notAmount = notAmount;
    }

    public Integer getPostponeTime() {
        return postponeTime;
    }

    public BuyVehicleRecord postponeTime(Integer postponeTime) {
        this.postponeTime = postponeTime;
        return this;
    }

    public void setPostponeTime(Integer postponeTime) {
        this.postponeTime = postponeTime;
    }

    public Integer getOverdueTiem() {
        return overdueTiem;
    }

    public BuyVehicleRecord overdueTiem(Integer overdueTiem) {
        this.overdueTiem = overdueTiem;
        return this;
    }

    public void setOverdueTiem(Integer overdueTiem) {
        this.overdueTiem = overdueTiem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuyVehicleRecord buyVehicleRecord = (BuyVehicleRecord) o;
        if (buyVehicleRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, buyVehicleRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BuyVehicleRecord{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", buyVehicleDate='" + buyVehicleDate + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", vehicleShelf='" + vehicleShelf + "'" +
            ", engine='" + engine + "'" +
            ", downPayment='" + downPayment + "'" +
            ", cashPledge='" + cashPledge + "'" +
            ", periods='" + periods + "'" +
            ", amount='" + amount + "'" +
            ", notPeriods='" + notPeriods + "'" +
            ", notAmount='" + notAmount + "'" +
            ", postponeTime='" + postponeTime + "'" +
            ", overdueTiem='" + overdueTiem + "'" +
            '}';
    }
}
