<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    tr:not(:nth-child(1)):hover {
        background: lightblue;
        cursor: pointer;
        color: #802a3c;
    }
</style>
<script>
    jQuery(function ($) {
        $('tbody tr[data-href]').addClass('clickable').click(function () {
            window.location = $(this).attr('data-href');
        });
    });
</script>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    <div class="container-fluid">
        <div class="col-md-12">Автопрокат</div>
        <table class="table">
            <tr>
                <div class="col-md-8">
                    <th class="col-md-1">Status</th>
                    <th class="col-md-4">Price</th>
                    <th class="col-md-1">Model</th>
                    <th class="col-md-1">Brand</th>
                    <th class="col-md-1">Plate number</th>
                    <th class="col-md-1">Colour</th>
                    <th class="col-md-1">Capacity</th>
                    <th class="col-md-1">Type of body</th>
                    <th class="col-md-1">Type of engine</th>
                    <th class="col-md-1">Year of edition</th>
                    <th class="col-md-1">Gearbox</th>
                </div>
            </tr>
            <c:forEach var="car" items="${cars}" varStatus="status">
                <tr class="info"
                    data-href="${pageContext.request.contextPath}/frontController?command=preorder&id=${car.id}">
                    <td class="col-md-4">${car.status}</td>
                    <div class="col-md-8">
                        <td class="col-md-4">${car.price} </td>
                        <td class="col-md-1">${car.model}</td>
                        <td class="col-md-1">${car.brand}</td>
                        <td class="col-md-1">${car.plateNumber}</td>
                        <td class="col-md-1">${car.colour}</td>
                        <td class="col-md-1">${car.capacity}</td>
                        <td class="col-md-1">${car.typeOfBody}</td>
                        <td class="col-md-1">${car.typeOfEngine}</td>
                        <td class="col-md-1">${car.yearOfEdition}</td>
                        <td class="col-md-1">${car.gearbox}</td>
                            <%--<td class="col-md-1"><input id="${car.id}" class="btn-primary addProductBtn" type="button" title="Добавить в корзину" value="+"/></td>--%>
                            <%--<td class="col-md-1"><input id="${car.id}" class="btn-primary reduceProductBtn" type="button" title="Удалить 1 из корзину" value="-"/></td>--%>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>




