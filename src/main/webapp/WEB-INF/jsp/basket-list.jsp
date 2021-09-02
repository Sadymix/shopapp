<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>Order</title>
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
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <div>
            <input type="button" value="Checkout">
        </div>
    </div>
</body>

</html>