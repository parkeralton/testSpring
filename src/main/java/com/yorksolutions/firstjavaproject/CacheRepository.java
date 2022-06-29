package com.yorksolutions.firstjavaproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CacheRepository extends CrudRepository<Cache, Long> {
    Optional<Cache> findByInput(Integer input);
}
