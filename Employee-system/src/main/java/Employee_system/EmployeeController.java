package Employee_system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo repo;
 // 1. Show the Register Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Looks for WEB-INF/view/register.jsp
    }

    // 2. Process the Registration
    @PostMapping("/create-account")
    public String register(@ModelAttribute Employee emp) {
        repo.save(emp); // Saves to DB
        return "redirect:/login"; // Sends user to the login page after success
    }

    // 1. You need a GET mapping to actually show the login page initially!
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 2. The unified login logic
    @PostMapping("/logincheck")
    public String logincheck(@ModelAttribute Employee emp, Model model) {
        // Logic: Find the user by both email and password
        Employee validUser = repo.findByEmailAndPassword(emp.getEmail(), emp.getPassword());

        if (validUser != null) {
            model.addAttribute("userName", validUser.getName());
            model.addAttribute("role", validUser.getRole());

            if ("admin".equalsIgnoreCase(validUser.getRole())) {
                // Fetch everyone for the admin table
                List<Employee> allEmployees = repo.findAll();
                model.addAttribute("empList", allEmployees); // Ensure this matches ${empList} in JSP
                return "admin-dashboard"; 
            } else {
                return "user-welcome";
            }
        }
        
        // If login fails, send them back with an error message
        model.addAttribute("error", "Invalid Credentials!");
        return "login"; 
    }
}