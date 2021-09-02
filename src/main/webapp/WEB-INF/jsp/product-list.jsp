<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>Shop Application</title>

    <script src="<c:url value="/js/product-list.js"/>"></script>
</head>

<body>
<div>
    <div>
        <h2>Shop</h2>
    </div>
</div>
<div>
    <div>
        <table>
            <tr>
                <th>Product Name</th>
                <th>Price PLN</th>
                <th></th>
            </tr>
            <c:forEach var="tempProduct" items="${products}">
                <tr>
                    <td> ${tempProduct.productName}</td>
                    <td> ${tempProduct.price}</td>
                    <td>
                        <input type="button" onclick="addToCart(${tempProduct.productId})" value="Add to cart"/>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
<div>
    <div>
        <button id="addToCart" onclick="submitForm()">Cart</button>
    </div>
</div>
<form:form id="idForm" method="post" modelAttribute="formIdList" action="/shop/cart">
    <input type="hidden" id="idInput" name="productIds">
</form:form>
</body>
</html>