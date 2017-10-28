package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CreditReview.
 */
@Entity
@Table(name = "credit_review")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CreditReview implements Serializable {

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

    public CreditReview orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public CreditReview driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public CreditReview auditResult(String auditResult) {
        this.auditResult = auditResult;
        return this;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAudit() {
        return audit;
    }

    public CreditReview audit(String audit) {
        this.audit = audit;
        return this;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public Long getAuditId() {
        return auditId;
    }

    public CreditReview auditId(Long auditId) {
        this.auditId = auditId;
        return this;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public CreditReview auditTime(Long auditTime) {
        this.auditTime = auditTime;
        return this;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public CreditReview remark(String remark) {
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
        CreditReview creditReview = (CreditReview) o;
        if (creditReview.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, creditReview.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CreditReview{" +
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
