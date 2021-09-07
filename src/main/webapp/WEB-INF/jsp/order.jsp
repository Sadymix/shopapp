    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>Order</title>

</head>

<body>
<div>
    <div>
        <h2>Your Order</h2>
    </div>
</div>
<div>
    <div>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>City</th>
                <th>Street</th>
                <th>Postal Code</th>
                <th>Country</th>


            </tr>

                <tr>
                    <td> ${client.firstName}</td>
                    <td> ${client.lastName}</td>
                    <td> ${client.city}</td>
                    <td> ${client.street}</td>
                    <td> ${client.postalCode}</td>
                    <td> ${client.country}</td>

                </tr>
            <br><br>
                <tr>
                    <th>Product Name</th>
                    <th>Price PLN</th>
                </tr>
            <c:forEach var="tempProduct" items="${orderProducts}">
                <tr>
                    <td>${tempProduct.productName}</td>
                    <td>${tempProduct.price}</td>
                </tr>
            </c:forEach>
            <tr>


        </table>
    </div>
</div>
<div>
    <div>
        <button>Payment</button>
    </div>
</div>

</body>
</html>
