package com.haphazard.AlapOn.user;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

  public User fromTokenClaims(Map<String, Object> claims) {
    User user = new User();
    if (claims.containsKey("sub")) {
      user.setId(claims.get("sub")
                     .toString());
    }

    if (claims.containsKey("given_name")) {
      user.setFirstName(claims.get("given_name")
                            .toString());
    }
    else if (claims.containsKey("nick_name")) {
      user.setLastName(claims.get("nick_name")
                           .toString());
    }

    if (claims.containsKey("family_name")) {
      user.setEmail(claims.get("family_name")
                        .toString());
    }

    if (claims.containsKey("email")) {
      user.setEmail(claims.get("email")
                        .toString());
    }

    user.setLastSeen(LocalDateTime.now());
    return user;
  }
}
