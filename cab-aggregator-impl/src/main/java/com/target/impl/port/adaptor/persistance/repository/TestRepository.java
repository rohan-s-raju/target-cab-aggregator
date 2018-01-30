package com.target.impl.port.adaptor.persistance.repository;

import com.target.impl.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

	@Query("select t from Test t where t.testId = :testId")
	Optional<Test> findByTestId(@Param("testId") Long testId);
}
