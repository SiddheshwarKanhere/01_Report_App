package in.kanhere.runner;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.kanhere.entity.CitizenPlan;
import in.kanhere.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		citizenPlanRepository.deleteAll();

		// Cash Plan data
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("john");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(5000.00);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income..!");

		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("cathy");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(2));
		c3.setPlanEndDate(LocalDate.now().plusMonths(4));
		c3.setBenefitAmt(4000.00);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Employee");

		// food Plan data
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Kishor");
		c4.setGender("Male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmt(9000.00);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Ganesh");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Property Income getting..!");

		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Ankita");
		c6.setGender("Fe-Male");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(2));
		c6.setPlanEndDate(LocalDate.now().plusMonths(4));
		c6.setBenefitAmt(3000.00);
		c6.setTerminationDate(LocalDate.now());
		c6.setTerminationReason("Employee");

		// Medical Plan data
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Shyam");
		c7.setGender("Male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmt(8000.00);

		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Bharat");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Hotel Income getting..!");

		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Dhanshree");
		c9.setGender("Fe-Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(2));
		c9.setPlanEndDate(LocalDate.now().plusMonths(4));
		c9.setBenefitAmt(2000.00);
		c9.setTerminationDate(LocalDate.now());
		c9.setTerminationReason("Employee in hotel");

		// Employment Plan data
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("Ram");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmt(11000.00);

		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("Yogesh");
		c11.setGender("Male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Photoshop Income");

		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("Radha");
		c12.setGender("Fe-Male");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(2));
		c12.setPlanEndDate(LocalDate.now().plusMonths(4));
		c12.setBenefitAmt(32000.00);
		c12.setTerminationDate(LocalDate.now());
		c12.setTerminationReason("started farming");

		List<CitizenPlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
		citizenPlanRepository.saveAll(list);
		
	}

}
