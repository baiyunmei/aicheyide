package com.duma.repository;

import com.duma.domain.DataCollection;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DataCollection entity.
 */
@SuppressWarnings("unused")
public interface DataCollectionRepository extends JpaRepository<DataCollection,Long> {

}
