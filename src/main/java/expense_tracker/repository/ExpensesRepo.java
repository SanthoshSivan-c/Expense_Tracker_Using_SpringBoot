package expense_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import expense_tracker.model.Expenses;

public interface ExpensesRepo extends JpaRepository<Expenses,Long>{
	List<Expenses> findByUsers_Id(Long userid);
}
