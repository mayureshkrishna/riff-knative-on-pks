package io.pivotal.ppv;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PpvRepository extends JpaRepository<Ppv, Long>{
	Collection<Ppv> findByName(String name);


}
