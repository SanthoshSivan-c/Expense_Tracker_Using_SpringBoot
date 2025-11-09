package expense_tracker.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expense_tracker.model.Expenses;
import expense_tracker.model.Users;
import expense_tracker.service.ExpenseService;
import expense_tracker.service.UserService;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
	
	@Autowired
	private ExpenseService expservice;
	@Autowired
	private UserService userservice;
	
	@PostMapping("/{id}/exp")
	public ResponseEntity<?> addexp(@PathVariable Long id,@RequestBody Expenses expenses){
		Users users = userservice.getUserById(id);
		expenses.setUsers(users);
		Expenses exp = expservice.create(expenses);
		
		return new ResponseEntity<>(exp,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getexp(@PathVariable Long id){
		Users users = userservice.getUserById(id);
		List<Expenses> exp = users.getExpenses();
		return new ResponseEntity<>(exp,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/exp")
	public ResponseEntity<?> updateExp(@PathVariable Long id,@RequestBody Expenses expenses){
		Expenses exp = expservice.updateExp(id, expenses);
		return new ResponseEntity<>(exp,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExp(@PathVariable Long id){
		String exp= expservice.deleteExp(id);
		return new ResponseEntity<>(exp,HttpStatus.OK);
		
	}
	

}
