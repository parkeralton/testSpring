package com.yorksolutions.firstjavaproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HeaderCacheRepository extends CrudRepository<HeaderCache, Long> {
    Optional<HeaderCache> findByHeader(String header);
}
