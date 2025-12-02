package expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import expense_tracker.model.Expenses;

public interface ExpensesRepo extends JpaRepository<Expenses,Long>{
	List<Expenses> findByUsers_Id(Long userid);
	
	
	@Query("SELECT SUM(e.amount) FROM Expenses e " +
		       "WHERE e.users.id = :userId " +
		       "AND e.date BETWEEN :fromDate AND CURRENT_DATE")
		Double totalExpense(@Param("userId") Long userId,
		                    @Param("fromDate") LocalDate fromDate);

}
