package Employee_system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
    Employee findByEmail(String email);

	Employee findByEmailAndPassword(String email, String password);
}