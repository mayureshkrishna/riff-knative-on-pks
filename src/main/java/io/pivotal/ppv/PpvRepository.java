package io.pivotal.ppv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PpvRepository extends JpaRepository<PayPerView, Long> {
	List<PayPerView> findByName (String name);
	
}
