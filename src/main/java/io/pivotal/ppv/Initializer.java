package io.pivotal.ppv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class Initializer implements ApplicationRunner {

	private Log loggerInit = LogFactory.getLog(Initializer.class);
	
	@Autowired
	private PpvRepository ppvRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ppvRepository.save(new Ppv(null,"UFC-1","UFC: Khabib vs. McGregor","64.99", "10/10/2018"));
		ppvRepository.save(new Ppv(null,"UFC-2","UFC: Woodley vs. Till","54.99", "10/17/2018"));
		ppvRepository.save(new Ppv(null,"WWE-1","WWE: Evolution","44.99", "10/13/2018"));
		ppvRepository.save(new Ppv(null,"SOCCER-1","SOCCER: Peru vs. Chile","24.99", "10/12/2018"));
		ppvRepository.save(new Ppv(null,"STANDUP-1","STANDUP: Dan Cummins: Don’t Wake The Bear","7.99", "10/15/2018"));
		
		
		ppvRepository.findAll().forEach(ppv -> loggerInit
				.info("Ppv Info: " + ppv.getId() + ", " + ppv.getName() + " ," + ppv.getPrice() + " ," + ppv.getDateOfShowing()));
	}
}