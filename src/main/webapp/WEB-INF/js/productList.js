$(document).ready(() => {
    let listOfProductIds = [];

    $.get("http://localhost:8080/shop/products", function (data) {
        $.each(data, function (key, val) {
            let tableRow = $(("<tr id ='" + key + "'>" +
                "<td>" + val.productName + "</td>" +
                "<td>" + val.price + "</td>" +
                "<td><input class='idInput' type='button' value='Add to cart'/></td>" +
                "</tr>"));
            tableRow.find(".idInput").data("productId", val.productId);
            $('#products').append(tableRow);

        });
        $('.idInput').click(function () {
            listOfProductIds.push($(this).data("productId"));
            $('#addToCart').html("Cart (" + listOfProductIds.length + ")");
        });
    });

    $('#addToCart').click(() => {
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/shop/cart",
            contentType: "text/plain",
            data: listOfProductIds.join(),
            success: (data) =>{
                localStorage.setItem("productsList", JSON.stringify(data));
                window.location.href = "../html/cartProductsList.html";
            }
        });
    });
});
