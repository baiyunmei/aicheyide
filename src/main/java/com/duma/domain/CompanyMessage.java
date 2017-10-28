package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CompanyMessage.
 */
@Entity
@Table(name = "company_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "company_principal_id")
    private Long companyPrincipalId;

    @Column(name = "company_principal")
    private String companyPrincipal;

    @Column(name = "company_principalphone")
    private String companyPrincipalphone;

    @Column(name = "site")
    private String site;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "opening_bank")
    private String openingBank;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "referrals")
    private String referrals;

    @Column(name = "referrals_phone")
    private String referralsPhone;

    @Column(name = "referrals_authorization_id")
    private Long referralsAuthorizationId;

    @Column(name = "authorization_id")
    private Long authorizationId;

    @Column(name = "authorization_company_name")
    private String authorizationCompanyName;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyMessage companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public CompanyMessage companyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
        return this;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public Long getCompanyPrincipalId() {
        return companyPrincipalId;
    }

    public CompanyMessage companyPrincipalId(Long companyPrincipalId) {
        this.companyPrincipalId = companyPrincipalId;
        return this;
    }

    public void setCompanyPrincipalId(Long companyPrincipalId) {
        this.companyPrincipalId = companyPrincipalId;
    }

    public String getCompanyPrincipal() {
        return companyPrincipal;
    }

    public CompanyMessage companyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal;
        return this;
    }

    public void setCompanyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal;
    }

    public String getCompanyPrincipalphone() {
        return companyPrincipalphone;
    }

    public CompanyMessage companyPrincipalphone(String companyPrincipalphone) {
        this.companyPrincipalphone = companyPrincipalphone;
        return this;
    }

    public void setCompanyPrincipalphone(String companyPrincipalphone) {
        this.companyPrincipalphone = companyPrincipalphone;
    }

    public String getSite() {
        return site;
    }

    public CompanyMessage site(String site) {
        this.site = site;
        return this;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAccountName() {
        return accountName;
    }

    public CompanyMessage accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public CompanyMessage openingBank(String openingBank) {
        this.openingBank = openingBank;
        return this;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyMessage phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public CompanyMessage taxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
        return this;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getReferrals() {
        return referrals;
    }

    public CompanyMessage referrals(String referrals) {
        this.referrals = referrals;
        return this;
    }

    public void setReferrals(String referrals) {
        this.referrals = referrals;
    }

    public String getReferralsPhone() {
        return referralsPhone;
    }

    public CompanyMessage referralsPhone(String referralsPhone) {
        this.referralsPhone = referralsPhone;
        return this;
    }

    public void setReferralsPhone(String referralsPhone) {
        this.referralsPhone = referralsPhone;
    }

    public Long getReferralsAuthorizationId() {
        return referralsAuthorizationId;
    }

    public CompanyMessage referralsAuthorizationId(Long referralsAuthorizationId) {
        this.referralsAuthorizationId = referralsAuthorizationId;
        return this;
    }

    public void setReferralsAuthorizationId(Long referralsAuthorizationId) {
        this.referralsAuthorizationId = referralsAuthorizationId;
    }

    public Long getAuthorizationId() {
        return authorizationId;
    }

    public CompanyMessage authorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
        return this;
    }

    public void setAuthorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    public String getAuthorizationCompanyName() {
        return authorizationCompanyName;
    }

    public CompanyMessage authorizationCompanyName(String authorizationCompanyName) {
        this.authorizationCompanyName = authorizationCompanyName;
        return this;
    }

    public void setAuthorizationCompanyName(String authorizationCompanyName) {
        this.authorizationCompanyName = authorizationCompanyName;
    }

    public Integer getStatus() {
        return status;
    }

    public CompanyMessage status(Integer status) {
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
        CompanyMessage companyMessage = (CompanyMessage) o;
        if (companyMessage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, companyMessage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompanyMessage{" +
            "id=" + id +
            ", companyName='" + companyName + "'" +
            ", companyLogo='" + companyLogo + "'" +
            ", companyPrincipalId='" + companyPrincipalId + "'" +
            ", companyPrincipal='" + companyPrincipal + "'" +
            ", companyPrincipalphone='" + companyPrincipalphone + "'" +
            ", site='" + site + "'" +
            ", accountName='" + accountName + "'" +
            ", openingBank='" + openingBank + "'" +
            ", phone='" + phone + "'" +
            ", taxNumber='" + taxNumber + "'" +
            ", referrals='" + referrals + "'" +
            ", referralsPhone='" + referralsPhone + "'" +
            ", referralsAuthorizationId='" + referralsAuthorizationId + "'" +
            ", authorizationId='" + authorizationId + "'" +
            ", authorizationCompanyName='" + authorizationCompanyName + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
