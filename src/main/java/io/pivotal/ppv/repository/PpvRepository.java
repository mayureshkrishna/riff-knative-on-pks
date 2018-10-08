package io.pivotal.ppv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.pivotal.ppv.Ppv;

@Repository
public interface PpvRepository extends JpaRepository<Ppv, Long> {

}
