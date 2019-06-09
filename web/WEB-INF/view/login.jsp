<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="error">${errorMsg}</div>
<div class="container text-center">
    <form action="frontController?command=login" method="post">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="messages" var="i18n"/>
        <b><fmt:message bundle="${i18n}" key="login.login"/></b>
        <input type="text" name="login" maxlength="30"/>
        <b><fmt:message bundle="${i18n}" key="login.password"/></b>
        <input type="password" name="password" maxlength="20"/><br/>
        <input type="submit" value="<fmt:message bundle="${i18n}" key="login.submit"/>">
    </form>
    <a href="${pageContext.request.contextPath}/frontController?command=reg">
        <input type="submit" value="<fmt:message bundle="${i18n}" key="reg.title"/>">
    </a>

</div>
