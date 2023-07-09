package com.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketplace.entity.*;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long>{

}
