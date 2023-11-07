package com.enmanuel.inditex.repository;

import com.enmanuel.inditex.entity.Brand;
import com.enmanuel.inditex.entity.Price;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {
}