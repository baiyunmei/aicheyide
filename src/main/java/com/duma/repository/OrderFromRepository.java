package com.duma.repository;

import com.duma.domain.OrderFrom;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrderFrom entity.
 */
@SuppressWarnings("unused")
public interface OrderFromRepository extends JpaRepository<OrderFrom,Long> {

}
