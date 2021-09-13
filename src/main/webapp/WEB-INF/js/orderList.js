$(document).ready(()=> {
    $.get("http://localhost:8080/shop/cart", function (data) {
        $.each(data, function (key, val){
            let tableRow = $(("<tr id ='" + key + "'>" +
                "<td>" + val.firstName + "</td>" +
                "<td>" + val.lastName+ "</td>" +
                "<td>" + val.city+ "</td>" +
                "<td>" + val.street+ "</td>" +
                "<td>" + val.postalCode+ "</td>" +
                "<td>" + val.country+ "</td>" +
                "</tr>"));
            $('#client').append(tableRow)
        })

    })
    $.get("http://localhost:8080/shop/products", function (data) {
        $.each(data, function (key, val) {
            let tableRow = $(("<tr id ='" + key + "'>" +
                "<td>" + val.productName + "</td>" +
                "<td>" + val.price + "</td>" +
                "</tr>"));
            $('#orderProducts').append(tableRow);
        });
    })
})

