package com.yorksolutions.firstjavaproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValCacheRepository extends CrudRepository<ValCache, Long> {
    Optional<ValCache> findByInput(String input);
}
