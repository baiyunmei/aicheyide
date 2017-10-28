package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A PleasePayeeAudit.
 */
@Entity
@Table(name = "please_payee_audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PleasePayeeAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private Long receiptDate;

    @Column(name = "please_payee_id")
    private Long pleasePayeeId;

    @Column(name = "please_payee_name")
    private String pleasePayeeName;

    @Column(name = "please_payee_money", precision=10, scale=2)
    private BigDecimal pleasePayeeMoney;

    @Column(name = "reason")
    private String reason;

    @Column(name = "gathering_name")
    private String gatheringName;

    @Column(name = "gathering_number")
    private String gatheringNumber;

    @Column(name = "use_time")
    private Long useTime;

    @Column(name = "remark_1")
    private String remark1;

    @Column(name = "accessory")
    private String accessory;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remark_2")
    private String remark2;

    @Column(name = "remit")
    private String remit;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "establish_time")
    private Long establishTime;

    @Column(name = "amend_time")
    private Long amendTime;

    @Column(name = "amend_person")
    private String amendPerson;

    @Column(name = "submitter")
    private String submitter;

    @Column(name = "submit_time")
    private Long submitTime;

    @Column(name = "auditor")
    private String auditor;

    @Column(name = "auditor_time")
    private Long auditorTime;

    @Column(name = "conditions")
    private Integer conditions;

    @Column(name = "please_payee_type")
    private String pleasePayeeType;

    @Column(name = "vehicle_shelf")
    private String vehicleShelf;

    @Column(name = "engine")
    private String engine;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "driver_name")
    private String driverName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public PleasePayeeAudit companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PleasePayeeAudit orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public PleasePayeeAudit receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getReceiptDate() {
        return receiptDate;
    }

    public PleasePayeeAudit receiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public void setReceiptDate(Long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getPleasePayeeId() {
        return pleasePayeeId;
    }

    public PleasePayeeAudit pleasePayeeId(Long pleasePayeeId) {
        this.pleasePayeeId = pleasePayeeId;
        return this;
    }

    public void setPleasePayeeId(Long pleasePayeeId) {
        this.pleasePayeeId = pleasePayeeId;
    }

    public String getPleasePayeeName() {
        return pleasePayeeName;
    }

    public PleasePayeeAudit pleasePayeeName(String pleasePayeeName) {
        this.pleasePayeeName = pleasePayeeName;
        return this;
    }

    public void setPleasePayeeName(String pleasePayeeName) {
        this.pleasePayeeName = pleasePayeeName;
    }

    public BigDecimal getPleasePayeeMoney() {
        return pleasePayeeMoney;
    }

    public PleasePayeeAudit pleasePayeeMoney(BigDecimal pleasePayeeMoney) {
        this.pleasePayeeMoney = pleasePayeeMoney;
        return this;
    }

    public void setPleasePayeeMoney(BigDecimal pleasePayeeMoney) {
        this.pleasePayeeMoney = pleasePayeeMoney;
    }

    public String getReason() {
        return reason;
    }

    public PleasePayeeAudit reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getGatheringName() {
        return gatheringName;
    }

    public PleasePayeeAudit gatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
        return this;
    }

    public void setGatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public PleasePayeeAudit gatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
        return this;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public Long getUseTime() {
        return useTime;
    }

    public PleasePayeeAudit useTime(Long useTime) {
        this.useTime = useTime;
        return this;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getRemark1() {
        return remark1;
    }

    public PleasePayeeAudit remark1(String remark1) {
        this.remark1 = remark1;
        return this;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getAccessory() {
        return accessory;
    }

    public PleasePayeeAudit accessory(String accessory) {
        this.accessory = accessory;
        return this;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public Integer getStatus() {
        return status;
    }

    public PleasePayeeAudit status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark2() {
        return remark2;
    }

    public PleasePayeeAudit remark2(String remark2) {
        this.remark2 = remark2;
        return this;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemit() {
        return remit;
    }

    public PleasePayeeAudit remit(String remit) {
        this.remit = remit;
        return this;
    }

    public void setRemit(String remit) {
        this.remit = remit;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public PleasePayeeAudit serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getEstablishTime() {
        return establishTime;
    }

    public PleasePayeeAudit establishTime(Long establishTime) {
        this.establishTime = establishTime;
        return this;
    }

    public void setEstablishTime(Long establishTime) {
        this.establishTime = establishTime;
    }

    public Long getAmendTime() {
        return amendTime;
    }

    public PleasePayeeAudit amendTime(Long amendTime) {
        this.amendTime = amendTime;
        return this;
    }

    public void setAmendTime(Long amendTime) {
        this.amendTime = amendTime;
    }

    public String getAmendPerson() {
        return amendPerson;
    }

    public PleasePayeeAudit amendPerson(String amendPerson) {
        this.amendPerson = amendPerson;
        return this;
    }

    public void setAmendPerson(String amendPerson) {
        this.amendPerson = amendPerson;
    }

    public String getSubmitter() {
        return submitter;
    }

    public PleasePayeeAudit submitter(String submitter) {
        this.submitter = submitter;
        return this;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public PleasePayeeAudit submitTime(Long submitTime) {
        this.submitTime = submitTime;
        return this;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public PleasePayeeAudit auditor(String auditor) {
        this.auditor = auditor;
        return this;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Long getAuditorTime() {
        return auditorTime;
    }

    public PleasePayeeAudit auditorTime(Long auditorTime) {
        this.auditorTime = auditorTime;
        return this;
    }

    public void setAuditorTime(Long auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getConditions() {
        return conditions;
    }

    public PleasePayeeAudit conditions(Integer conditions) {
        this.conditions = conditions;
        return this;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    public String getPleasePayeeType() {
        return pleasePayeeType;
    }

    public PleasePayeeAudit pleasePayeeType(String pleasePayeeType) {
        this.pleasePayeeType = pleasePayeeType;
        return this;
    }

    public void setPleasePayeeType(String pleasePayeeType) {
        this.pleasePayeeType = pleasePayeeType;
    }

    public String getVehicleShelf() {
        return vehicleShelf;
    }

    public PleasePayeeAudit vehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
        return this;
    }

    public void setVehicleShelf(String vehicleShelf) {
        this.vehicleShelf = vehicleShelf;
    }

    public String getEngine() {
        return engine;
    }

    public PleasePayeeAudit engine(String engine) {
        this.engine = engine;
        return this;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Long getDriverId() {
        return driverId;
    }

    public PleasePayeeAudit driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public PleasePayeeAudit driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PleasePayeeAudit pleasePayeeAudit = (PleasePayeeAudit) o;
        if (pleasePayeeAudit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pleasePayeeAudit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PleasePayeeAudit{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", orderId='" + orderId + "'" +
            ", receiptNumber='" + receiptNumber + "'" +
            ", receiptDate='" + receiptDate + "'" +
            ", pleasePayeeId='" + pleasePayeeId + "'" +
            ", pleasePayeeName='" + pleasePayeeName + "'" +
            ", pleasePayeeMoney='" + pleasePayeeMoney + "'" +
            ", reason='" + reason + "'" +
            ", gatheringName='" + gatheringName + "'" +
            ", gatheringNumber='" + gatheringNumber + "'" +
            ", useTime='" + useTime + "'" +
            ", remark1='" + remark1 + "'" +
            ", accessory='" + accessory + "'" +
            ", status='" + status + "'" +
            ", remark2='" + remark2 + "'" +
            ", remit='" + remit + "'" +
            ", serialNumber='" + serialNumber + "'" +
            ", establishTime='" + establishTime + "'" +
            ", amendTime='" + amendTime + "'" +
            ", amendPerson='" + amendPerson + "'" +
            ", submitter='" + submitter + "'" +
            ", submitTime='" + submitTime + "'" +
            ", auditor='" + auditor + "'" +
            ", auditorTime='" + auditorTime + "'" +
            ", conditions='" + conditions + "'" +
            ", pleasePayeeType='" + pleasePayeeType + "'" +
            ", vehicleShelf='" + vehicleShelf + "'" +
            ", engine='" + engine + "'" +
            ", driverId='" + driverId + "'" +
            ", driverName='" + driverName + "'" +
            '}';
    }
}
