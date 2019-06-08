<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form action="frontController?command=accept&id=${cars.id}" method="post">
    <TABLE>
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="messages" var="i18n"/>

        <tr>
            <th>Данные о автомобиле:</th>
            <th>Данные о пользователе:</th>

        </tr>
        <tr>
            <td>${cars.brand} ${cars.model}</td>
            <td>${user.name} ${user.surname}</td>

        </tr>
        <tr>
            <td>${cars.plateNumber}</td>
            <td>${user.birthday}</td>
        </tr>
        <tr>
            <td>${cars.colour}</td>
            <td>${user.pasportLett} ${user.passportId}</td>
        </tr>
        <tr>
            <td>${cars.capacity}</td>
            <td>${user.country}</td>
        </tr>
        <tr>
            <td>${cars.yearOfEdition}</td>
            <td>${user.identification}</td>
        </tr>
        <tr>
            <td>${cars.typeOfEngine}</td>
            <td>${user.drivingExp}</td>
        </tr>
        <tr>
            <td>${cars.gearbox}</td>
            <td>${user.phoneNumber}</td>
        </tr>
        <tr>
            <td>${cars.price}</td>

        </tr>
        <tr>
            <td><label> <b><fmt:message bundle="${i18n}" key="order.start"/></b></label>
                <input type="text" name="date_start_order" maxlength="20"/></td>
            <td><label> <b><fmt:message bundle="${i18n}" key="order.finish"/></b></label>
                <input type="text" name="date_finish_order" maxlength="20"/></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="<fmt:message bundle="${i18n}" key="preorder.butt"/>">
            </td>
        </tr>
    </TABLE>
</form>
