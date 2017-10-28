package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DriverMessage.
 */
@Entity
@Table(name = "driver_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DriverMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "certificate_type")
    private String certificateType;

    @Column(name = "certificate_phone")
    private String certificatePhone;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "borm_date")
    private String bormDate;

    @Column(name = "sex")
    private String sex;

    @Column(name = "marriage_status")
    private Integer marriageStatus;

    @Column(name = "phone")
    private String phone;

    @Column(name = "education_degree")
    private String educationDegree;

    @Column(name = "registered")
    private String registered;

    @Column(name = "census_register")
    private String censusRegister;

    @Column(name = "housing_type")
    private String housingType;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "house_loan")
    private Double houseLoan;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "monthly")
    private String monthly;

    @Column(name = "residential_address")
    private String residentialAddress;

    @Column(name = "dwell_time")
    private Long dwellTime;

    @Column(name = "identity_front")
    private String identityFront;

    @Column(name = "identity_reverse")
    private String identityReverse;

    @Column(name = "driving_front")
    private String drivingFront;

    @Column(name = "driving_reverse")
    private String drivingReverse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public DriverMessage companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public DriverMessage certificateType(String certificateType) {
        this.certificateType = certificateType;
        return this;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificatePhone() {
        return certificatePhone;
    }

    public DriverMessage certificatePhone(String certificatePhone) {
        this.certificatePhone = certificatePhone;
        return this;
    }

    public void setCertificatePhone(String certificatePhone) {
        this.certificatePhone = certificatePhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public DriverMessage driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBormDate() {
        return bormDate;
    }

    public DriverMessage bormDate(String bormDate) {
        this.bormDate = bormDate;
        return this;
    }

    public void setBormDate(String bormDate) {
        this.bormDate = bormDate;
    }

    public String getSex() {
        return sex;
    }

    public DriverMessage sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMarriageStatus() {
        return marriageStatus;
    }

    public DriverMessage marriageStatus(Integer marriageStatus) {
        this.marriageStatus = marriageStatus;
        return this;
    }

    public void setMarriageStatus(Integer marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getPhone() {
        return phone;
    }

    public DriverMessage phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public DriverMessage educationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
        return this;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getRegistered() {
        return registered;
    }

    public DriverMessage registered(String registered) {
        this.registered = registered;
        return this;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getCensusRegister() {
        return censusRegister;
    }

    public DriverMessage censusRegister(String censusRegister) {
        this.censusRegister = censusRegister;
        return this;
    }

    public void setCensusRegister(String censusRegister) {
        this.censusRegister = censusRegister;
    }

    public String getHousingType() {
        return housingType;
    }

    public DriverMessage housingType(String housingType) {
        this.housingType = housingType;
        return this;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public DriverMessage contactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Double getHouseLoan() {
        return houseLoan;
    }

    public DriverMessage houseLoan(Double houseLoan) {
        this.houseLoan = houseLoan;
        return this;
    }

    public void setHouseLoan(Double houseLoan) {
        this.houseLoan = houseLoan;
    }

    public String getDeadline() {
        return deadline;
    }

    public DriverMessage deadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getMonthly() {
        return monthly;
    }

    public DriverMessage monthly(String monthly) {
        this.monthly = monthly;
        return this;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public DriverMessage residentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
        return this;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Long getDwellTime() {
        return dwellTime;
    }

    public DriverMessage dwellTime(Long dwellTime) {
        this.dwellTime = dwellTime;
        return this;
    }

    public void setDwellTime(Long dwellTime) {
        this.dwellTime = dwellTime;
    }

    public String getIdentityFront() {
        return identityFront;
    }

    public DriverMessage identityFront(String identityFront) {
        this.identityFront = identityFront;
        return this;
    }

    public void setIdentityFront(String identityFront) {
        this.identityFront = identityFront;
    }

    public String getIdentityReverse() {
        return identityReverse;
    }

    public DriverMessage identityReverse(String identityReverse) {
        this.identityReverse = identityReverse;
        return this;
    }

    public void setIdentityReverse(String identityReverse) {
        this.identityReverse = identityReverse;
    }

    public String getDrivingFront() {
        return drivingFront;
    }

    public DriverMessage drivingFront(String drivingFront) {
        this.drivingFront = drivingFront;
        return this;
    }

    public void setDrivingFront(String drivingFront) {
        this.drivingFront = drivingFront;
    }

    public String getDrivingReverse() {
        return drivingReverse;
    }

    public DriverMessage drivingReverse(String drivingReverse) {
        this.drivingReverse = drivingReverse;
        return this;
    }

    public void setDrivingReverse(String drivingReverse) {
        this.drivingReverse = drivingReverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriverMessage driverMessage = (DriverMessage) o;
        if (driverMessage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, driverMessage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DriverMessage{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", certificateType='" + certificateType + "'" +
            ", certificatePhone='" + certificatePhone + "'" +
            ", driverName='" + driverName + "'" +
            ", bormDate='" + bormDate + "'" +
            ", sex='" + sex + "'" +
            ", marriageStatus='" + marriageStatus + "'" +
            ", phone='" + phone + "'" +
            ", educationDegree='" + educationDegree + "'" +
            ", registered='" + registered + "'" +
            ", censusRegister='" + censusRegister + "'" +
            ", housingType='" + housingType + "'" +
            ", contactAddress='" + contactAddress + "'" +
            ", houseLoan='" + houseLoan + "'" +
            ", deadline='" + deadline + "'" +
            ", monthly='" + monthly + "'" +
            ", residentialAddress='" + residentialAddress + "'" +
            ", dwellTime='" + dwellTime + "'" +
            ", identityFront='" + identityFront + "'" +
            ", identityReverse='" + identityReverse + "'" +
            ", drivingFront='" + drivingFront + "'" +
            ", drivingReverse='" + drivingReverse + "'" +
            '}';
    }
}
