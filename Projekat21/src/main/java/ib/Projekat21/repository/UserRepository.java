package ib.Projekat21.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ib.Projekat21.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findByEmail(String email);
	 List<User> findAll();
	 List<User> findByActiveTrue();

}
