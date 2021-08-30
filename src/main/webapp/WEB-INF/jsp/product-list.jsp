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
                    <th></th>
                </tr>
                <c:forEach var="tempProduct" items="${products}">
                    <tr>
                        <td> ${tempProduct.productName}</td>
                        <td> ${tempProduct.price}</td>
                        <td><input type="button" onclick="addToCart(id)" value="Add to cart"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <div>
            <c:url var="cartLink" value="shop/cart"></c:url>
            <a href="${cartLink}" id="cart">Cart</a>
        </div>
    </div>
</body>
<script>


    let listOfProductIds=[];
    let count = 0;

    document.getElementById("cart").innerHTML = "Cart " + count;

    function addToCart(id){

        listOfProductIds.push(id);

        count++;

    }
</script>
</html>