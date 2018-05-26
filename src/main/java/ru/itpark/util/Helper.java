package ru.itpark.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class Helper {
  public static boolean hasRole(Collection<SimpleGrantedAuthority> authorities, String role) {
    for (SimpleGrantedAuthority authority : authorities) {
      if (authority.getAuthority().equals("ROLE_" + role)) {
        return true;
      }
    }
    return false;
  }
}
