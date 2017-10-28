package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A ForcedSettle.
 */
@Entity
@Table(name = "forced_settle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ForcedSettle implements Serializable {

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

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "periods")
    private Integer periods;

    @Column(name = "sum_money", precision=10, scale=2)
    private BigDecimal sumMoney;

    @Column(name = "not_periods")
    private Integer notPeriods;

    @Column(name = "not_money", precision=10, scale=2)
    private BigDecimal notMoney;

    @Column(name = "settle_type")
    private String settleType;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "whether_recycle")
    private Integer whetherRecycle;

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

    public Long getDriverId() {
        return driverId;
    }

    public ForcedSettle driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public ForcedSettle vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ForcedSettle companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public ForcedSettle plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public ForcedSettle driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getPeriods() {
        return periods;
    }

    public ForcedSettle periods(Integer periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public ForcedSettle sumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
        return this;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getNotPeriods() {
        return notPeriods;
    }

    public ForcedSettle notPeriods(Integer notPeriods) {
        this.notPeriods = notPeriods;
        return this;
    }

    public void setNotPeriods(Integer notPeriods) {
        this.notPeriods = notPeriods;
    }

    public BigDecimal getNotMoney() {
        return notMoney;
    }

    public ForcedSettle notMoney(BigDecimal notMoney) {
        this.notMoney = notMoney;
        return this;
    }

    public void setNotMoney(BigDecimal notMoney) {
        this.notMoney = notMoney;
    }

    public String getSettleType() {
        return settleType;
    }

    public ForcedSettle settleType(String settleType) {
        this.settleType = settleType;
        return this;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public ForcedSettle receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public ForcedSettle receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getWhetherRecycle() {
        return whetherRecycle;
    }

    public ForcedSettle whetherRecycle(Integer whetherRecycle) {
        this.whetherRecycle = whetherRecycle;
        return this;
    }

    public void setWhetherRecycle(Integer whetherRecycle) {
        this.whetherRecycle = whetherRecycle;
    }

    public String getRemark() {
        return remark;
    }

    public ForcedSettle remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public ForcedSettle status(Integer status) {
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
        ForcedSettle forcedSettle = (ForcedSettle) o;
        if (forcedSettle.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, forcedSettle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ForcedSettle{" +
            "id=" + id +
            ", driverId='" + driverId + "'" +
            ", vehicleId='" + vehicleId + "'" +
            ", companyId='" + companyId + "'" +
            ", plateNumber='" + plateNumber + "'" +
            ", driverName='" + driverName + "'" +
            ", periods='" + periods + "'" +
            ", sumMoney='" + sumMoney + "'" +
            ", notPeriods='" + notPeriods + "'" +
            ", notMoney='" + notMoney + "'" +
            ", settleType='" + settleType + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", whetherRecycle='" + whetherRecycle + "'" +
            ", remark='" + remark + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
