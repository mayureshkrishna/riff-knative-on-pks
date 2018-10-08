package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class PpvFunction implements Function<Long, Ppv> {

	private Log logger = LogFactory.getLog(PpvFunction.class);

	@Autowired
	private final PpvRepository ppvRepository;

	PpvFunction(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
	}

	public Ppv apply(Long id) {
		Optional<Ppv> ppv = ppvRepository.findById(id);
		if (ppv.isPresent()) {
			logger.info(" Retrieved PPV Info for following: " + ppv.get().getName());
			return ppv.get();
		} else {
			logger.info(" No PPV Info found for Id: " + id);
			return null;
		}
	}
}