$(document).ready(() => {
    var productsList = JSON.parse(localStorage.getItem("productsList"));
    for (let cartProduct of productsList) {
        let tableRow = $("<tr id ='" + cartProduct.productId + "'>" +
            "<td>" + cartProduct.productName + "</td>" +
            "<td>" + cartProduct.price + "</td>" +
            "</tr>")
        $('#cartProducts').append(tableRow);
    }

    function formToJson() {
        let $form = $('#clientForm');
        let productIds = [];
        $.each(productsList, (i, p) => productIds.push(p.productId));
        return {
            client: {
                firstName: $form.find("#firstName").val(),
                lastName: $form.find("#lastName").val(),
                city: $form.find("#city").val(),
                street:$form.find("#street").val(),
                postalCode:$form.find("#postalCode").val(),
                country:$form.find("#country").val(),
            },
            orderProductIds: productIds.join()
        };
    }

    $('#order').click(() => {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/shop/order",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(formToJson()),
            success: (data) => {
                localStorage.setItem("order", JSON.stringify(data));
                window.location.href = "../html/orderList.html";
            }
        });
    });
});
