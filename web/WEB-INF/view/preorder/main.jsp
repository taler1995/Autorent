<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<TABLE>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>
    <tr>
        <td>Данные о автомобиле</td>
        <td>${cars.brand}</td>
        <td>${cars.model}</td>
        <td>${cars.price}</td>
    </tr>
    <tr>
        <td>Данные о пользователе</td>
    </tr>
    <tr>
        <td><label> <b><fmt:message bundle="${i18n}" key="order.start"/></b></label>
            <input type="text" name="email" maxlength="20"/></td>
    </tr>
    <tr>
        <td><label> <b><fmt:message bundle="${i18n}" key="order.finish"/></b></label>
        <input type="text" name="email" maxlength="20"/></td>
    </tr>
    <tr><a href="${pageContext.request.contextPath}/frontController?command=reg">
       <td> <input type="submit" value="<fmt:message bundle="${i18n}" key="preorder.butt"/>">
       </td>
    </a></tr>
</TABLE>
