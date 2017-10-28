package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DriverEmergencyContact.
 */
@Entity
@Table(name = "driver_emergency_contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DriverEmergencyContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_1")
    private String name1;

    @Column(name = "residential_address_1")
    private String residentialAddress1;

    @Column(name = "phone_1")
    private String phone1;

    @Column(name = "relation_1")
    private String relation1;

    @Column(name = "name_2")
    private String name2;

    @Column(name = "residential_address_2")
    private String residentialAddress2;

    @Column(name = "phone_2")
    private String phone2;

    @Column(name = "relation_2")
    private String relation2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public DriverEmergencyContact name1(String name1) {
        this.name1 = name1;
        return this;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getResidentialAddress1() {
        return residentialAddress1;
    }

    public DriverEmergencyContact residentialAddress1(String residentialAddress1) {
        this.residentialAddress1 = residentialAddress1;
        return this;
    }

    public void setResidentialAddress1(String residentialAddress1) {
        this.residentialAddress1 = residentialAddress1;
    }

    public String getPhone1() {
        return phone1;
    }

    public DriverEmergencyContact phone1(String phone1) {
        this.phone1 = phone1;
        return this;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getRelation1() {
        return relation1;
    }

    public DriverEmergencyContact relation1(String relation1) {
        this.relation1 = relation1;
        return this;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1;
    }

    public String getName2() {
        return name2;
    }

    public DriverEmergencyContact name2(String name2) {
        this.name2 = name2;
        return this;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getResidentialAddress2() {
        return residentialAddress2;
    }

    public DriverEmergencyContact residentialAddress2(String residentialAddress2) {
        this.residentialAddress2 = residentialAddress2;
        return this;
    }

    public void setResidentialAddress2(String residentialAddress2) {
        this.residentialAddress2 = residentialAddress2;
    }

    public String getPhone2() {
        return phone2;
    }

    public DriverEmergencyContact phone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getRelation2() {
        return relation2;
    }

    public DriverEmergencyContact relation2(String relation2) {
        this.relation2 = relation2;
        return this;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriverEmergencyContact driverEmergencyContact = (DriverEmergencyContact) o;
        if (driverEmergencyContact.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, driverEmergencyContact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DriverEmergencyContact{" +
            "id=" + id +
            ", name1='" + name1 + "'" +
            ", residentialAddress1='" + residentialAddress1 + "'" +
            ", phone1='" + phone1 + "'" +
            ", relation1='" + relation1 + "'" +
            ", name2='" + name2 + "'" +
            ", residentialAddress2='" + residentialAddress2 + "'" +
            ", phone2='" + phone2 + "'" +
            ", relation2='" + relation2 + "'" +
            '}';
    }
}
