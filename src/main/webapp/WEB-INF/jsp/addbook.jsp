<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head></head>

    <body>
    <form:form method="POST" action="/books" modelAttribute="book">
        <form:label path="title">Title</form:label>
        <form:input path="title" />

        <input type="submit" value="Submit" />
    </form:form>
    </body>
</html>