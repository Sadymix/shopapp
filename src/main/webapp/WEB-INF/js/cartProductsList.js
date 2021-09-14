$(document).ready(() => {
    var productsList = JSON.parse(localStorage.getItem("productsList"));
    for (let cartProduct of productsList) {
        let tableRow = $("<tr id ='" + cartProduct.productId + "'>" +
            "<td>" + cartProduct.productName + "</td>" +
            "<td>" + cartProduct.price + "</td>" +
            "</tr>")
            .data("productId", cartProduct.productId);
        $('#cartProducts').append(tableRow);
    }

    function formToJson() {
        let $form = $('#clientForm');
        let productIds = $('.productIdsInput').data("productId")
        return {
            client: {
                firstName: $form.find("#firstName").val()
            },
            orderProductIds: productIds.join()
        };
    }

    $('#order').click(() => {
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/shop/order",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(formToJson()),
            success: (data) => {
                localStorage.setItem("client", JSON.stringify(data));
                window.location.href = "../html/orderList.html";
            }
        });
    });
});
