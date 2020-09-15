package com.voll.snackmachine;

import com.voll.snackmachine.models.Account;
import com.voll.snackmachine.models.Company;
import com.voll.snackmachine.models.SellHistory;
import com.voll.snackmachine.models.User;
import com.voll.snackmachine.repositories.AccountRepository;
import com.voll.snackmachine.repositories.CompanyRepository;
import com.voll.snackmachine.repositories.SellHistoryRepository;
import com.voll.snackmachine.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SnackMachineApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	SellHistoryRepository sellHistoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SnackMachineApplication.class, args);
	}

	@Bean
	InitializingBean populateDatabase() {
		return () -> {
			Company companyVoll = new Company("1", "Voll", "USD", 50F, Collections.emptyList());
			Company company404 = new Company("2", "Senior", "BRL", 30F, Collections.emptyList());

			companyRepository.save(companyVoll);
			companyRepository.save(company404);

			List<User> users =
				Arrays.asList(
					new User("1", "Lucas Machado", "lucas.machado@govoll.com", companyVoll),
					new User("2", "Monalisa Vidigal", "monalisa.vidigal@govoll.com", companyVoll),
					new User("3", "Jack Barreto", "jack.barreto@404.com", company404)
				);

			users.forEach((user) -> {
				userRepository.save(user);
				accountRepository.save(new Account(null, user.getCompany().getBudgetPerUser(), user));
			});

			// Usuario com uma compra na data de hoje.
			User user = userRepository.save(new User("4", "Maria Joana", "maria.joana@404.com", company404));
			accountRepository.save(new Account(null, user.getCompany().getBudgetPerUser(), user));
			sellHistoryRepository.save(new SellHistory(null, user, LocalDate.now(), 2F));
		};
	}
}