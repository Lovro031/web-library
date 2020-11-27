<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head></head>
    <body>
        <form method="POST" action="/loans">
            <c:forEach items="${persons}" var="person">
                ${person.firstName} ${person.lastName} <input type="radio" name="personId" value="${person.id}"/>
                <br>
            </c:forEach>

            Loan Date   <input type="date" name="loanDate" />
            <br>

            <c:forEach items="${books}" var="book">
                ${book.title} <input type="radio" name="bookId" value="${book.id}"/>
                <br>
            </c:forEach>

            <input type="submit" value="Submit" />
        </form>
    </body>
</html>