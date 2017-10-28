package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuthorizationRecord.
 */
@Entity
@Table(name = "authorization_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AuthorizationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_numbering")
    private String contractNumbering;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "purchase_date")
    private Long purchaseDate;

    @Column(name = "authorization_start_date")
    private Long authorizationStartDate;

    @Column(name = "authorization_finish_date")
    private Long authorizationFinishDate;

    @Column(name = "authorization_type")
    private Integer authorizationType;

    @Column(name = "remark")
    private String remark;

    @Column(name = "authorization_id")
    private Long authorizationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumbering() {
        return contractNumbering;
    }

    public AuthorizationRecord contractNumbering(String contractNumbering) {
        this.contractNumbering = contractNumbering;
        return this;
    }

    public void setContractNumbering(String contractNumbering) {
        this.contractNumbering = contractNumbering;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public AuthorizationRecord companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPurchaseDate() {
        return purchaseDate;
    }

    public AuthorizationRecord purchaseDate(Long purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public void setPurchaseDate(Long purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getAuthorizationStartDate() {
        return authorizationStartDate;
    }

    public AuthorizationRecord authorizationStartDate(Long authorizationStartDate) {
        this.authorizationStartDate = authorizationStartDate;
        return this;
    }

    public void setAuthorizationStartDate(Long authorizationStartDate) {
        this.authorizationStartDate = authorizationStartDate;
    }

    public Long getAuthorizationFinishDate() {
        return authorizationFinishDate;
    }

    public AuthorizationRecord authorizationFinishDate(Long authorizationFinishDate) {
        this.authorizationFinishDate = authorizationFinishDate;
        return this;
    }

    public void setAuthorizationFinishDate(Long authorizationFinishDate) {
        this.authorizationFinishDate = authorizationFinishDate;
    }

    public Integer getAuthorizationType() {
        return authorizationType;
    }

    public AuthorizationRecord authorizationType(Integer authorizationType) {
        this.authorizationType = authorizationType;
        return this;
    }

    public void setAuthorizationType(Integer authorizationType) {
        this.authorizationType = authorizationType;
    }

    public String getRemark() {
        return remark;
    }

    public AuthorizationRecord remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAuthorizationId() {
        return authorizationId;
    }

    public AuthorizationRecord authorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
        return this;
    }

    public void setAuthorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizationRecord authorizationRecord = (AuthorizationRecord) o;
        if (authorizationRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, authorizationRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AuthorizationRecord{" +
            "id=" + id +
            ", contractNumbering='" + contractNumbering + "'" +
            ", companyId='" + companyId + "'" +
            ", purchaseDate='" + purchaseDate + "'" +
            ", authorizationStartDate='" + authorizationStartDate + "'" +
            ", authorizationFinishDate='" + authorizationFinishDate + "'" +
            ", authorizationType='" + authorizationType + "'" +
            ", remark='" + remark + "'" +
            ", authorizationId='" + authorizationId + "'" +
            '}';
    }
}
