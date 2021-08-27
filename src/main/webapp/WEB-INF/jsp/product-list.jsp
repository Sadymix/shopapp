<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <title>Shop Application</title>
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
                    <th>Add To Basket</th>
                </tr>
                <c:forEach var="tempProduct" items="${products}">
                    <tr>
                        <td> ${tempProduct.productName}</td>
                        <td> ${tempProduct.price}</td>
                        <td>add</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>