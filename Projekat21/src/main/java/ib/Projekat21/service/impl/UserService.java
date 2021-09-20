package ib.Projekat21.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ib.Projekat21.model.User;
import ib.Projekat21.repository.UserRepository;



@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(username);
		if(userOptional.isPresent()) {			
			return userOptional.get();
		}else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
		
	}

}
