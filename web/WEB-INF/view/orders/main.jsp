<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<TABLE>
    <tr>
        <th>User name</th>
        <th>Start order</th>
        <th>Finish order</th>
        <th>Total</th>
        <th></th>
        <%--<th>${cars.model}</th>--%>
    </tr>

    <c:forEach var="orders" items="${orders}" varStatus="status">
        <tr>
            <td>${user.name} ${user.surname}</td>
            <td>${orders.start}</td>
            <td>${orders.finish}</td>
            <td>${orders.total}</td>
        </tr>
    </c:forEach>
</TABLE>
