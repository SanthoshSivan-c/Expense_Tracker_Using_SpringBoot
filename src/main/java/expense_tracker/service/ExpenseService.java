package expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import expense_tracker.model.Expenses;
import expense_tracker.repository.ExpensesRepo;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpensesRepo exprepo;
	
	public  Expenses create(Expenses expenses) {
		return exprepo.save(expenses);
	}
	
	public List<Expenses> getExp(Long id) {
		return exprepo.findByUsers_Id(id);
	}
	
	public Expenses updateExp(Long id,Expenses expenses) {
		Expenses exp = exprepo.findById(id).orElseThrow(()->new RuntimeException("Expense Not Found"));
		exp.setCategory(expenses.getCategory());
		exp.setAmount(expenses.getAmount());
		exp.setDate(expenses.getDate());
		
		return exprepo.save(exp);
	}
	
	public String deleteExp(Long id) {
		exprepo.findById(id).orElseThrow(()->new RuntimeException("Expense Not Found"));
		exprepo.deleteById(id);
		return id+" Expense Got deleted";
	}
}
