<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>
    <h1>Welcome Admin, ${adminName}!</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Salary</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${empList}">
                <tr>
                    <td>${e.name}</td>
                    <td>${e.email}</td>
                    <td>${e.salary}</td>
                    <td>${e.role}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>