package io.pivotal.ppv;

//@SpringBootApplication
public class PpvApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(PpvApplication.class, args);
	}*/
	
	
	/*
	//This initializer is just for adding dummy data
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

	}*/

}