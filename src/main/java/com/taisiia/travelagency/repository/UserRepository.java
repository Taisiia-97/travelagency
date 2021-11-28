package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String emil);

    User getByEmailIgnoreCase(String emil);

    List<User> findAllByIsActive(Boolean status);


}
