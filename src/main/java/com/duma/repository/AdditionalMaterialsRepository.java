package com.duma.repository;

import com.duma.domain.AdditionalMaterials;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AdditionalMaterials entity.
 */
@SuppressWarnings("unused")
public interface AdditionalMaterialsRepository extends JpaRepository<AdditionalMaterials,Long> {

}
