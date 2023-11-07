package com.enmanuel.inditex.repository;

import com.enmanuel.inditex.entity.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {
}