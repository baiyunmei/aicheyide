package com.duma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PurchaseVehicleRecord.
 */
@Entity
@Table(name = "purchase_vehicle_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseVehicleRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseVehicleRecord purchaseVehicleRecord = (PurchaseVehicleRecord) o;
        if (purchaseVehicleRecord.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, purchaseVehicleRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PurchaseVehicleRecord{" +
            "id=" + id +
            '}';
    }
}
