package com.duma.repository;

import com.duma.domain.DriverMessage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DriverMessage entity.
 */
@SuppressWarnings("unused")
public interface DriverMessageRepository extends JpaRepository<DriverMessage,Long> {

}
