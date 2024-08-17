<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page isELIgnored="false" %>



<html>
<body>

<h1>You entered:</h1>
<p>Your first name: <%= request.getParameter("first_name")%></p>
<p>Your last name: <%= request.getParameter("last_name")%></p>
<p>Your age: <%= request.getParameter("age")%></p>

</body>
</html>
