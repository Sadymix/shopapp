$(document).ready(() => {
    var order = JSON.parse(localStorage.getItem("order"));

    let clientTableRow = $(("<tr id ='" + order.client.clientId + "'>" +
        "<td>" + order.client.firstName + "</td>" +
        "<td>" + order.client.lastName + "</td>" +
        "<td>" + order.client.city + "</td>" +
        "<td>" + order.client.street + "</td>" +
        "<td>" + order.client.postalCode + "</td>" +
        "<td>" + order.client.country + "</td>" +
        "</tr>"));
    $('#client').append(clientTableRow);

    for (let product of order.productList) {
        let tableRow = $("<tr id ='" + product.productId + "'>" +
            "<td>" + product.productName + "</td>" +
            "<td>" + product.price + "</td>" +
            "</tr>");
        $('#orderProducts').append(tableRow);
    }
    let cartTotalPrice = $("<br><a>" +
        "TOTAL PRICE " + order.totalPrice + " PLN" +
        "</a>");
    $('#totalPrice').append(cartTotalPrice);
})

