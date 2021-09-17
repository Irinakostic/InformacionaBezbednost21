package ib.Projekat21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ib.Projekat21.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
