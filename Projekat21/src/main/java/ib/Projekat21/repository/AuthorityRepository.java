package ib.Projekat21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ib.Projekat21.model.Authority;



public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	Optional<Authority> findByName(String name);

}
