<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>Order</title>

    <script src="<c:url value="/js/basket-list.js"/>"></script>
</head>

<body>
    <div>
        <div>
            <h2>Your Basket</h2>
        </div>
    </div>
    <div>
        <div>
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Price PLN</th>

                </tr>
                <c:forEach var="basketProduct" items="${basketProducts}">
                    <tr>
                        <td> ${basketProduct.productName}</td>
                        <td> ${basketProduct.price}</td>
                        <input class="classValues" type="hidden" value="${basketProduct.productId}">
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <div>
            <form:form id="clientForm" method="post" modelAttribute="clientWrapper" action="/shop/order">
                <label>First Name: </label>
                <input type="text" name="client.firstName" id="firstName">
                <br><br>
                <label>Last Name: </label>
                <input type="text" name="client.lastName" id="lastName">
                <br><br>
                <label>City: </label>
                <input type="text" name="client.city" id="city">
                <br><br>
                <label>Street: </label>
                <input type="text" name="client.street" id="street">
                <br><br>
                <label>Postal Code: </label>
                <input type="text" name="client.postalCode" id="postalCode">
                <br><br>
                <label>Country: </label>
                <input type="text" name="client.country" id="country">
                <input type="hidden" id="productIdsInput" name="orderProductIds">
                <br><br>
                <input type="submit" id="order" value="Order"/>
            </form:form>

        </div>
    </div>
    <script>
        getIdsValueToArr();
    </script>
</body>
</html>