<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <h1>Login Successful!</h1>
    
    <c:choose>
        <%-- If the role passed to the model is admin --%>
        <c:when test="${role == 'admin'}">
            <h3>Admin Panel - All Employee Records</h3>
            <table border="1" cellpadding="10">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Salary</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="emp" items="${allEmps}">
                    <tr>
                        <td>${emp.name}</td>
                        <td>${emp.email}</td>
                        <td>${emp.salary}</td>
                        <td>${emp.role}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        
        <%-- If the role is just a user --%>
        <c:otherwise>
            <h3>Welcome, ${userName}!</h3>
            <p>You are logged in as a standard Employee.</p>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="login">Logout</a>
</body>
</html>