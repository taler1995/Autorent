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
    <form action="frontController?command=reg" method="post">
        <p>
            <fmt:setLocale value="${sessionScope.locale}"/>
            <fmt:setBundle basename="messages" var="i18n"/>
            <label><b><fmt:message bundle="${i18n}" key="user.login"/></b></label>
            <input type="text" name="login" maxlength="20"/>
        </p>
        <p>
            <label><b><fmt:message bundle="${i18n}" key="user.password"/></b></label>
            <input type="password" name="password" maxlength="20"/>
        </p>
        <br>
        <br>
        <p>
            <label><b><fmt:message bundle="${i18n}" key="user.name"/></b></label>
            <input type="text" name="name" maxlength="20"/>
        </p>
        <p>
            <label><b><fmt:message bundle="${i18n}" key="user.surname"/></b></label>
            <input type="text" name="surname" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.birthday"/></b></label>
            <input type="text" name="birthday" maxlength="20"/>
        </p>
        <p>
            <label><b><fmt:message bundle="${i18n}" key="user.passport_lett"/></b></label>
            <input type="text" name="passport_lett" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.passport_id"/></b></label>
            <input type="text" name="passport_id" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.country"/></b></label>
            <input type="text" name="country" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.identif"/></b></label>
            <input type="text" name="identification_number" maxlength="20"/>
        </p>
        <p>
            <label><b><fmt:message bundle="${i18n}" key="user.drive"/></b></label>
            <input type="text" name="driving_experience" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.phone"/></b></label>
            <input type="text" name="number_of_phone" maxlength="20"/>
        </p>
        <p>
            <label> <b><fmt:message bundle="${i18n}" key="user.email"/></b></label>
            <input type="text" name="email" maxlength="20"/>
        </p>
        <input type="submit" value="<fmt:message bundle="${i18n}" key="reg.title"/>">

    </form>
    <%--<input type="submit" value="<fmt:message bundle="${i18n}" key="reg.title"/>">--%>
</div>
