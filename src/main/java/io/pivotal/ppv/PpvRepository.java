package io.pivotal.ppv;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.jpa.repository.JpaRepository;

@ConditionalOnBean
public interface PpvRepository extends JpaRepository<Ppv, Long> {

}
