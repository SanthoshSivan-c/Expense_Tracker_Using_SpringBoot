package expense_tracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import expense_tracker.model.Expenses;
import expense_tracker.model.Users;
import expense_tracker.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private PasswordEncoder passwordencoder;

	
	public Users register(Users user) {
		String encodepass = passwordencoder.encode(user.getPassword());
		user.setPassword(encodepass);
		return userrepo.save(user);
		
	}
	
	public Optional<Users> getUser(String email) {
		return userrepo.findByEmail(email);
	}
	
	public Users getUserById(Long id) {
		return userrepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
	}
	
	 public String deleteUser(Long id) {
	        Users users = userrepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
	        userrepo.deleteById(id);
	        return "User got Deleted";
	    }


}
