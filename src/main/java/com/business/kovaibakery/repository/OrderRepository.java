package com.business.kovaibakery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.business.kovaibakery.entity.OrderEntity;

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
