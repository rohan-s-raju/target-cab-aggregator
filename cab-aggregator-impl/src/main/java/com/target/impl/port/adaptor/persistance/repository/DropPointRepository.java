package com.target.impl.port.adaptor.persistance.repository;

import com.target.impl.domain.entity.DropIds;
import com.target.impl.domain.entity.DropPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DropPointRepository extends JpaRepository<DropPoint, DropIds> {
    List<DropPoint> findByFrom(String dropPoint);
    Optional<DropPoint> findByFromAndTo(String from,String to);
}
