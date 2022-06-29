package com.yorksolutions.firstjavaproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MD5CacheRepository extends CrudRepository<MD5Cache, Long> {
    Optional<MD5Cache> findByInput(String input);
}
