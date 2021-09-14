$(document).ready(() => {
    var client = JSON.parse(localStorage.getItem("client"));
    var orderProductsList = JSON.parse(localStorage.getItem("productList"));

    for (let product of orderProductsList) {
        let tableRow = $(("<tr id ='" + cartProduct.productId + "'>" +
            "<td>" + cartProduct.productName + "</td>" +
            "<td>" + cartProduct.price + "</td>" +
            "</tr>"));
        $('#orderProducts').append(tableRow);
    }

    let tableRow = $(("<tr id ='" + client.clientId + "'>" +
        "<td>" + client.firstName + "</td>" +
        "<td>" + client.lastName + "</td>" +
        "<td>" + client.city + "</td>" +
        "<td>" + client.street + "</td>" +
        "<td>" + client.postalCode + "</td>" +
        "<td>" + client.country + "</td>" +
        "</tr>"));
    $('#client').append(tableRow);
})

