package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DepositContract.
 */
@Entity
@Table(name = "deposit_contract")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepositContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "audit_result")
    private String auditResult;

    @Column(name = "audit")
    private String audit;

    @Column(name = "audit_id")
    private Long auditId;

    @Column(name = "audit_time")
    private Long auditTime;

    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public DepositContract orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public DepositContract driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public DepositContract auditResult(String auditResult) {
        this.auditResult = auditResult;
        return this;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAudit() {
        return audit;
    }

    public DepositContract audit(String audit) {
        this.audit = audit;
        return this;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public Long getAuditId() {
        return auditId;
    }

    public DepositContract auditId(Long auditId) {
        this.auditId = auditId;
        return this;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public DepositContract auditTime(Long auditTime) {
        this.auditTime = auditTime;
        return this;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public DepositContract remark(String remark) {
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
        DepositContract depositContract = (DepositContract) o;
        if (depositContract.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, depositContract.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepositContract{" +
            "id=" + id +
            ", orderId='" + orderId + "'" +
            ", driverId='" + driverId + "'" +
            ", auditResult='" + auditResult + "'" +
            ", audit='" + audit + "'" +
            ", auditId='" + auditId + "'" +
            ", auditTime='" + auditTime + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
