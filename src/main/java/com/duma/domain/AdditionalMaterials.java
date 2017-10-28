package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AdditionalMaterials.
 */
@Entity
@Table(name = "additional_materials")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdditionalMaterials implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "registered")
    private String registered;

    @Column(name = "marriage")
    private String marriage;

    @Column(name = "site")
    private String site;

    @Column(name = "communicate")
    private String communicate;

    @Column(name = "agreement")
    private String agreement;

    @Column(name = "bank_card")
    private String bankCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public AdditionalMaterials orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRegistered() {
        return registered;
    }

    public AdditionalMaterials registered(String registered) {
        this.registered = registered;
        return this;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getMarriage() {
        return marriage;
    }

    public AdditionalMaterials marriage(String marriage) {
        this.marriage = marriage;
        return this;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getSite() {
        return site;
    }

    public AdditionalMaterials site(String site) {
        this.site = site;
        return this;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCommunicate() {
        return communicate;
    }

    public AdditionalMaterials communicate(String communicate) {
        this.communicate = communicate;
        return this;
    }

    public void setCommunicate(String communicate) {
        this.communicate = communicate;
    }

    public String getAgreement() {
        return agreement;
    }

    public AdditionalMaterials agreement(String agreement) {
        this.agreement = agreement;
        return this;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getBankCard() {
        return bankCard;
    }

    public AdditionalMaterials bankCard(String bankCard) {
        this.bankCard = bankCard;
        return this;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdditionalMaterials additionalMaterials = (AdditionalMaterials) o;
        if (additionalMaterials.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, additionalMaterials.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AdditionalMaterials{" +
            "id=" + id +
            ", orderId='" + orderId + "'" +
            ", registered='" + registered + "'" +
            ", marriage='" + marriage + "'" +
            ", site='" + site + "'" +
            ", communicate='" + communicate + "'" +
            ", agreement='" + agreement + "'" +
            ", bankCard='" + bankCard + "'" +
            '}';
    }
}
