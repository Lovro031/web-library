<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head></head>

<body>
<form:form method="POST" action="/persons" modelAttribute="person">
    <form:label path="firstName">First Name</form:label>
    <form:input path="firstName" />

    <form:label path="lastName">Last Name</form:label>
    <form:input path="lastName" />

    <form:label type="date" path="dateOfBirth">Date of birth</form:label>
    <form:input type="date" path="dateOfBirth" />

    <form:label path="contactNumbers">Contact Number #1</form:label>
    <form:input path="contactNumbers" />
    <form:label path="contactNumbers">Contact Number #2</form:label>
    <form:input path="contactNumbers" />
    <form:label path="contactNumbers">Contact Number #3</form:label>
    <form:input path="contactNumbers" />

    <form:label path="email">Email</form:label>
    <form:input path="email" />

    <form:label path="password">Password</form:label>
    <form:input path="password" />

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>