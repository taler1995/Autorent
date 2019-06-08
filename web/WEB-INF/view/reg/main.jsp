<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    form {
        display: table
    }

    p {
        display: table-row;
    }

    label {
        display: table-cell;
    }

    input {
        display: table-cell;
    }
</style>
<div class="container text-left">
    <form action="frontController?command=reg" method="get">
        <table>
            <fmt:setLocale value="${sessionScope.locale}"/>
            <fmt:setBundle basename="messages" var="i18n"/>
           <tr> <td><label><b><fmt:message bundle="${i18n}" key="user.login"/></b></label></td>
              <td> <input type="text" name="login" maxlength="20"/></tr></td>
        <tr>
            <td> <label><b><fmt:message bundle="${i18n}" key="user.password"/></b></label></td>
          <td>  <input type="password" name="password" maxlength="20"/></td>
        </tr>
        <br>
        <br>
        <tr>
            <td>  <label><b><fmt:message bundle="${i18n}" key="user.name"/></b></label></td>
            <td>  <input type="text" name="name" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label><b><fmt:message bundle="${i18n}" key="user.surname"/></b></label></td>
            <td> <input type="text" name="surname" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label> <b><fmt:message bundle="${i18n}" key="user.birthday"/></b></label></td>
            <td>  <input type="text" name="birthday" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label><b><fmt:message bundle="${i18n}" key="user.passport_lett"/></b></label></td>
            <td> <input type="text" name="passport_lett" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label> <b><fmt:message bundle="${i18n}" key="user.passport_id"/></b></label></td>
            <td> <input type="text" name="passport_id" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label> <b><fmt:message bundle="${i18n}" key="user.country"/></b></label></td>
            <td> <input type="text" name="country" maxlength="20"/></td>
        </tr>
        <p>
            <td>  <label> <b><fmt:message bundle="${i18n}" key="user.identif"/></b></label></td>
            <td> <input type="text" name="identification_number" maxlength="20"/></td>
        </p>
        <tr>
            <td> <label><b><fmt:message bundle="${i18n}" key="user.drive"/></b></label></td>
            <td>  <input type="text" name="driving_experience" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label> <b><fmt:message bundle="${i18n}" key="user.phone"/></b></label></td>
            <td>  <input type="text" name="number_of_phone" maxlength="20"/></td>
        </tr>
        <tr>
            <td> <label> <b><fmt:message bundle="${i18n}" key="user.email"/></b></label></td>
            <td>  <input type="text" name="email" maxlength="20"/></td>
        </tr>
            <td> <input type="submit" value="<fmt:message bundle="${i18n}" key="reg.title"/>"></td>
        </table>
    </form>

    <%--<input type="submit" value="<fmt:message bundle="${i18n}" key="reg.title"/>">--%>
</div>
