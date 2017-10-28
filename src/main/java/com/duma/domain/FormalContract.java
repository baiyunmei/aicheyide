package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FormalContract.
 */
@Entity
@Table(name = "formal_contract")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FormalContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "contract_numbering")
    private String contractNumbering;

    @Column(name = "video")
    private String video;

    @Column(name = "credit")
    private String credit;

    @Column(name = "apply")
    private String apply;

    @Column(name = "special")
    private String special;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public FormalContract orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getContractNumbering() {
        return contractNumbering;
    }

    public FormalContract contractNumbering(String contractNumbering) {
        this.contractNumbering = contractNumbering;
        return this;
    }

    public void setContractNumbering(String contractNumbering) {
        this.contractNumbering = contractNumbering;
    }

    public String getVideo() {
        return video;
    }

    public FormalContract video(String video) {
        this.video = video;
        return this;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCredit() {
        return credit;
    }

    public FormalContract credit(String credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getApply() {
        return apply;
    }

    public FormalContract apply(String apply) {
        this.apply = apply;
        return this;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getSpecial() {
        return special;
    }

    public FormalContract special(String special) {
        this.special = special;
        return this;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormalContract formalContract = (FormalContract) o;
        if (formalContract.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, formalContract.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FormalContract{" +
            "id=" + id +
            ", orderId='" + orderId + "'" +
            ", contractNumbering='" + contractNumbering + "'" +
            ", video='" + video + "'" +
            ", credit='" + credit + "'" +
            ", apply='" + apply + "'" +
            ", special='" + special + "'" +
            '}';
    }
}
