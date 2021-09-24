package ib.Projekat21.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ib.Projekat21.model.Authority;
import ib.Projekat21.model.User;
import ib.Projekat21.repository.AuthorityRepository;
import ib.Projekat21.repository.UserRepository;



@RestController
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	
	@PostMapping(path = "users/register")
    public @ResponseBody ResponseEntity<?> registrovaniUser(@RequestParam String email, @RequestParam String password) {
    	Authority authority = authorityRepository.findByName("REGULAR").get();
		
		if(email==null | password == null | email.equals("") | password.equals("") ) {
			return new ResponseEntity("Polja ne mogu biti prazna", HttpStatus.BAD_REQUEST);
		}else {
			Optional<User> userSaProslijedjenimEmailom = userRepository.findByEmail(email);
			if(userSaProslijedjenimEmailom.isPresent()) {
				return new ResponseEntity("Vec postoji korisnik sa tim mejlom", HttpStatus.BAD_REQUEST);
			}else {
				User user = new User();
				user.setActive(false);
				user.setEmail(email);
				user.setPassword(bCryptPasswordEncoder.encode(password));
				user.setCertificate("");
				user.getUserAuthorities().add(authority);
									
				userRepository.save(user);
				
				return new ResponseEntity("Uspesna registracija.", HttpStatus.OK);
				
			}
		}
    }

}
