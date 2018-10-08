package io.pivotal.ppv;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PpvFunction implements Function<Long, Collection<Ppv>> {
	
	private static Log logger = LogFactory.getLog(PpvFunction.class);

	
	private PpvRepository ppvRepo;

	public Collection<Ppv> apply(Long id) {
		Collection<Ppv> ppv =  ppvRepo.findByPpvId(id);
		ppv.forEach(ppvInfo -> logger.info(" Retrieved PPV Info for following: " + ppvInfo.getName()));
		return ppv;

	}
}


/*@Component
class Initializer implements ApplicationRunner {
	
	private static Log logger = LogFactory.getLog(Initializer.class);

	private final PpvRepository ppvRepository;
	
	Initializer(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		Stream.of("Khabib vs. McGregor","Woodley vs. Till","WWE: Evolution","Soccer: Peru vs. Chile", "Dan Cummins: Don’t Wake The Bear")
		.forEach(name -> ppvRepository.save(new Ppv(null, name, Long.parseLong("12.99"),"10/10/2018")));
		
		ppvRepository.findAll().forEach(ppv -> logger.info("Ppv Info: "+ppv.getName()+" ,"+ppv.getPrice()+" ,"+ppv.getDateOfShowing()));
	}
	
}*/


interface PpvRepository extends JpaRepository<Ppv, Long> {

	Collection<Ppv> findByPpvId(Long id);

}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Ppv {
	@Id
	@GeneratedValue
	private Long id;

	private String Name;
	private Long price;
	private String dateOfShowing;

}