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
            <td>Модель: ${cars.brand} ${cars.model}</td>
            <td>Имя пользователя: ${user.name} ${user.surname}</td>

        </tr>
        <tr>
            <td>Номер: ${cars.plateNumber}</td>
            <td>День рождения: ${user.birthday}</td>
        </tr>
        <tr>
            <td>Цвет: ${cars.colour}</td>
            <td>Паспортные данные: ${user.pasportLett} ${user.passportId}</td>
        </tr>
        <tr>
            <td>Вместимость: ${cars.capacity}</td>
            <td>Страна: ${user.country}</td>
        </tr>
        <tr>
            <td>Год: ${cars.yearOfEdition}</td>
            <td>Идентификационный номер: ${user.identification}</td>
        </tr>
        <tr>
            <td>Тип двигателя: ${cars.typeOfEngine}</td>
            <td>Опыт вождения: ${user.drivingExp}</td>
        </tr>
        <tr>
            <td>Тип коробки: ${cars.gearbox}</td>
        </tr>
        <tr>
            <td>Цена за день: ${cars.price}</td>
        </tr>
        <tr>
            <td><label> <b><fmt:message bundle="${i18n}" key="order.start"/></b></label>
                <input type="date" name="date_start_order" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" maxlength="20" /></td>
            <td><label> <b><fmt:message bundle="${i18n}" key="order.finish"/></b></label>
                <input type="date" name="date_finish_order" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" maxlength="20"/></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="<fmt:message bundle="${i18n}" key="preorder.butt"/>">
            </td>
        </tr>
    </TABLE>
</form>
