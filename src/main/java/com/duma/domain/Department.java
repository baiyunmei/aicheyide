package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "department")
    private String department;

    @Column(name = "department_number")
    private Integer departmentNumber;

    @Column(name = "superior_department_id")
    private Long superiorDepartmentId;

    @Column(name = "department_heads")
    private String departmentHeads;

    @Column(name = "department_heads_id")
    private Long departmentHeadsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Department companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDepartment() {
        return department;
    }

    public Department department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getDepartmentNumber() {
        return departmentNumber;
    }

    public Department departmentNumber(Integer departmentNumber) {
        this.departmentNumber = departmentNumber;
        return this;
    }

    public void setDepartmentNumber(Integer departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public Long getSuperiorDepartmentId() {
        return superiorDepartmentId;
    }

    public Department superiorDepartmentId(Long superiorDepartmentId) {
        this.superiorDepartmentId = superiorDepartmentId;
        return this;
    }

    public void setSuperiorDepartmentId(Long superiorDepartmentId) {
        this.superiorDepartmentId = superiorDepartmentId;
    }

    public String getDepartmentHeads() {
        return departmentHeads;
    }

    public Department departmentHeads(String departmentHeads) {
        this.departmentHeads = departmentHeads;
        return this;
    }

    public void setDepartmentHeads(String departmentHeads) {
        this.departmentHeads = departmentHeads;
    }

    public Long getDepartmentHeadsId() {
        return departmentHeadsId;
    }

    public Department departmentHeadsId(Long departmentHeadsId) {
        this.departmentHeadsId = departmentHeadsId;
        return this;
    }

    public void setDepartmentHeadsId(Long departmentHeadsId) {
        this.departmentHeadsId = departmentHeadsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department department = (Department) o;
        if (department.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, department.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", department='" + department + "'" +
            ", departmentNumber='" + departmentNumber + "'" +
            ", superiorDepartmentId='" + superiorDepartmentId + "'" +
            ", departmentHeads='" + departmentHeads + "'" +
            ", departmentHeadsId='" + departmentHeadsId + "'" +
            '}';
    }
}
