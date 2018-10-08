package io.pivotal.ppv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PpvRepository extends JpaRepository<Ppv, Long> {

}
