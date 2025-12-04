package com.haphazard.AlapOn.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  @Query(name = UserConstants.FIND_USER_BY_EMAIL)
  Optional<User> findByEmail(String email);
}
