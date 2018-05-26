package ru.itpark.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itpark.repository.AccountRepository;

@Primary // используй мой сервис при любых конфликтах
@Service("accountService") // чтобы из view обращаться по @accountService
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public boolean isAuthenticated() {
    // https://docs.spring.io/spring-security/site/docs/current/reference/html/anonymous.html
    return !hasRole("ANONYMOUS");
  }

  @Override
  public boolean hasRole(String role) {
    // README: всю информацию о пользователе мы можем получать из контекста SecurityContextHolder (и вынести код из Account)
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        .stream()
        .anyMatch(
            e -> e.getAuthority().equals("ROLE_" + role)
        );
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return accountRepository
        .findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(username + " not found or password invalid")
        )
        ;
  }
}
