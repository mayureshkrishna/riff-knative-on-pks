package io.pivotal.ppv;

import java.util.function.Function;


public class PpvFunction implements Function<Long, Ppv> {

	//private Log logger = LogFactory.getLog(PpvFunction.class);

/*	@Autowired
	private final PpvRepository ppvRepository;

	PpvFunction(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
	}*/

	public Ppv apply(Long id) {
		/*Optional<Ppv> ppv = ppvRepository.findById(id);
		if (ppv.isPresent()) {
			logger.info(" Retrieved PPV Info for following: " + ppv.get().getName());
			return ppv.get();
		} else {
			logger.info(" No PPV Info found for Id: " + id);
			return null;
		}*/
		
		Ppv ppv = new Ppv();
		ppv.setId(1L);
		ppv.setName("UFC:Khabib vs. McGregor");
		ppv.setPrice("65.00");
		ppv.setDateOfShowing("10/10/2018");
		return ppv;
		
	}
}