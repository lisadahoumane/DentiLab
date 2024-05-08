package com.batchdentilab.batchdentilab.repository;

import com.batchdentilab.batchdentilab.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentiLabRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT f.id FROM factory f")
    List<Long> findFactoryId();
}
