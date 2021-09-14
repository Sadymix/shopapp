$(document).ready(() => {
    var order = JSON.parse(localStorage.getItem("order"));

    let clientTableRow = $(("<tr id ='" + order.clientDTO.clientId + "'>" +
        "<td>" + order.clientDTO.firstName + "</td>" +
        "<td>" + order.clientDTO.lastName + "</td>" +
        "<td>" + order.clientDTO.city + "</td>" +
        "<td>" + order.clientDTO.street + "</td>" +
        "<td>" + order.clientDTO.postalCode + "</td>" +
        "<td>" + order.clientDTO.country + "</td>" +
        "</tr>"));
    $('#client').append(clientTableRow);

    for (let product of order.products) {
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
localStorage.clear();

