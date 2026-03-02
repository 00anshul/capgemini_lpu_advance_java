<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message</title>
</head>
<body>
    <c:forEach var="msg" items="${messages}">
        <h1>${msg} welcome to boot</h1>
    </c:forEach>
</body>
</html>