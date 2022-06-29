package com.yorksolutions.firstjavaproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpCacheRepository extends CrudRepository<IpCache, Long> {
    Optional<IpCache> findByIp(String ip);
}
