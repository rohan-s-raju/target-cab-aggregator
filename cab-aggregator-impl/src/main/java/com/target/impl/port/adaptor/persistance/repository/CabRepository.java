package com.target.impl.port.adaptor.persistance.repository;

import com.target.impl.domain.entity.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Long> {

}
