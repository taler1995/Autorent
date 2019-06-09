<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form action="frontController?command=cars" method="post">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>
    <table>
        <tr>
        <td><h2>Заказ подтверждён</h2></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<fmt:message bundle="${i18n}" key="next.butt"/>">
            </td>
        </tr>
    </table>
</form>
