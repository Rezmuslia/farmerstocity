package ru.itpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.domain.Account;
import ru.itpark.repository.AccountRepository;

import java.util.List;

@SpringBootApplication
public class SpringBootWebApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringBootWebApplication.class, args);
		var encoder = context.getBean(PasswordEncoder.class);
		var repository = context.getBean(AccountRepository.class);

		repository.saveAll(
				List.of(
						new Account(
								0,
								"admin",
								encoder.encode("admin"),
								List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
								true,
								true,
								true,
								true
						),
						new Account(
								0,
								"user",
								encoder.encode("user"),
								List.of(new SimpleGrantedAuthority("ROLE_USER")),
								true,
								true,
								true,
								true
						)
				)
		);
	}
}
