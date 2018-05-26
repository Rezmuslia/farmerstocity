package ru.itpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account implements UserDetails {
  @Id
  @GeneratedValue
  private int id;
  private String username;
  private String password;
  @ElementCollection(fetch = FetchType.EAGER)
  private Collection<GrantedAuthority> authorities;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;
}
