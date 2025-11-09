package expense_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import expense_tracker.model.Users;

public interface UserRepo extends JpaRepository<Users,Long>{
	Optional<Users> findByEmail(String email);
}
