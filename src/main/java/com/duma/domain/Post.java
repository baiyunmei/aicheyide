package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "post_name")
    private String postName;

    @Column(name = "staffing")
    private String staffing;

    @Column(name = "in_employee")
    private Integer inEmployee;

    @Column(name = "from_employee")
    private Integer fromEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Post companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Post departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getPostName() {
        return postName;
    }

    public Post postName(String postName) {
        this.postName = postName;
        return this;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getStaffing() {
        return staffing;
    }

    public Post staffing(String staffing) {
        this.staffing = staffing;
        return this;
    }

    public void setStaffing(String staffing) {
        this.staffing = staffing;
    }

    public Integer getInEmployee() {
        return inEmployee;
    }

    public Post inEmployee(Integer inEmployee) {
        this.inEmployee = inEmployee;
        return this;
    }

    public void setInEmployee(Integer inEmployee) {
        this.inEmployee = inEmployee;
    }

    public Integer getFromEmployee() {
        return fromEmployee;
    }

    public Post fromEmployee(Integer fromEmployee) {
        this.fromEmployee = fromEmployee;
        return this;
    }

    public void setFromEmployee(Integer fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if (post.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", companyId='" + companyId + "'" +
            ", departmentId='" + departmentId + "'" +
            ", postName='" + postName + "'" +
            ", staffing='" + staffing + "'" +
            ", inEmployee='" + inEmployee + "'" +
            ", fromEmployee='" + fromEmployee + "'" +
            '}';
    }
}
