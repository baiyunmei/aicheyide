package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A LogRecord.
 */
@Entity
@Table(name = "log_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operating_type")
    private String operatingType;

    @Column(name = "operator")
    private String operator;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "ip")
    private String ip;

    @Column(name = "role")
    private String role;

    @Column(name = "operating_date")
    private Long operatingDate;

    @Column(name = "remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatingType() {
        return operatingType;
    }

    public LogRecord operatingType(String operatingType) {
        this.operatingType = operatingType;
        return this;
    }

    public void setOperatingType(String operatingType) {
        this.operatingType = operatingType;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecord operator(String operator) {
        this.operator = operator;
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public LogRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public LogRecord departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public LogRecord operatorId(Long operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getIp() {
        return ip;
    }

    public LogRecord ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRole() {
        return role;
    }

    public LogRecord role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getOperatingDate() {
        return operatingDate;
    }

    public LogRecord operatingDate(Long operatingDate) {
        this.operatingDate = operatingDate;
        return this;
    }

    public void setOperatingDate(Long operatingDate) {
        this.operatingDate = operatingDate;
    }

    public String getRemark() {
        return remark;
    }

    public LogRecord remark(String remark) {
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
        LogRecord logRecord = (LogRecord) o;
        if (logRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, logRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LogRecord{" +
            "id=" + id +
            ", operatingType='" + operatingType + "'" +
            ", operator='" + operator + "'" +
            ", companyId='" + companyId + "'" +
            ", departmentId='" + departmentId + "'" +
            ", operatorId='" + operatorId + "'" +
            ", ip='" + ip + "'" +
            ", role='" + role + "'" +
            ", operatingDate='" + operatingDate + "'" +
            ", remark='" + remark + "'" +
            '}';
    }
}
