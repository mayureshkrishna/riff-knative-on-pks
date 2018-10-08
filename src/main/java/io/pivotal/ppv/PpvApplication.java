package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpvApplication.class, args);
	}

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

	@Component
	class Initializer implements ApplicationRunner {

		private Log loggerInit = LogFactory.getLog(Initializer.class);

		@Autowired
		private final PpvRepository ppvRepository;

		Initializer(PpvRepository ppvRepository) {
			this.ppvRepository = ppvRepository;
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Stream.of("Khabib vs. McGregor", "Woodley vs. Till", "WWE: Evolution", "Soccer: Peru vs. Chile",
					"Dan Cummins: Don’t Wake The Bear")
					.forEach(name -> ppvRepository.save(new Ppv(null, name, "12.99", "10/10/2018")));

			ppvRepository.findAll().forEach(ppv -> loggerInit
					.info("Ppv Info: " + ppv.getName() + " ," + ppv.getPrice() + " ," + ppv.getDateOfShowing()));

		}

	}

}