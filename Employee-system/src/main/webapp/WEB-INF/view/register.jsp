<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Employee Registration</title></head>
<body>
    <h2>Register New Employee</h2>
    <form action="create-account" method="post">
    Name: <input type="text" name="name" required /><br><br>
    Email: <input type="email" name="email" required /><br><br>
    Salary: <input type="number" name="salary" required /><br><br>
    
    Role: 
    <select name="role">
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select><br><br>
    
    <%-- Added Password Field --%>
    Password: <input type="password" name="password" required /><br><br>
    
    <button type="submit">Register Employee</button>
</form>
    <br>
    <a href="login">Already have an account? Login here</a>
</body>
</html>