package expense_tracker.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expense_tracker.jwtutil.JwtUtil;
import expense_tracker.model.Users;
import expense_tracker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users users){
		String email=users.getEmail();
		if(userservice.getUser(email).isPresent()) {
			return new ResponseEntity<>("Email Already Exists",HttpStatus.CONFLICT);
		}
		
		Users save=userservice.register(users);
		if(save==null) {
			return new ResponseEntity<>("User not Saved",HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("User saved Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users users){
		String email=users.getEmail();
		String pass=users.getPassword();
		
		Optional<Users> user = userservice.getUser(email);
		if(user.isEmpty()) {
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
		Users dbuser = user.get();
		if(user.isEmpty()) {
			return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
		}
		
		if(!passwordencoder.matches(pass, dbuser.getPassword())) {
			return new ResponseEntity<>("Invlid Credentials",HttpStatus.UNAUTHORIZED);
		}
		String token = jwtutil.generateToken(email);
		return new ResponseEntity<>(
		    "{\"token\":\"" + token + "\", \"userId\":\"" + dbuser.getId() + "\"}",
		    HttpStatus.OK
		);

		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
	    try {
	        String message = userservice.deleteUser(id);
	        return new ResponseEntity<>(message, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}

	

}
