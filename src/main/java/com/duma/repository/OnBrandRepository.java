package com.duma.repository;

import com.duma.domain.OnBrand;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OnBrand entity.
 */
@SuppressWarnings("unused")
public interface OnBrandRepository extends JpaRepository<OnBrand,Long> {

}
