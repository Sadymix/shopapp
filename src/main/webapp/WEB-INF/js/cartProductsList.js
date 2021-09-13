$(document).ready(() => {
    var productsList = JSON.parse(localStorage.getItem("productsList"));
    var cartProductsList = productsList;
    console.log(cartProductsList);
    for (let cartProduct of cartProductsList) {
        let tableRow = $(("<tr id ='" + cartProduct.productId + "'>" +
            "<td>" + cartProduct.productName + "</td>" +
            "<td>" + cartProduct.price + "</td>" +
            "</tr>"));
        tableRow.find(".productIdsInput").data("productId", cartProduct.productId);
        $('#cartProducts').append(tableRow);
    }


    var myForm = $('#clientForm').serialize();
    $('#order').click(() =>{
        $.ajax({
          type: 'POST',
          url: "http://localhost:8080/shop/order",
          contentType: "text/plain",
          data: myForm,
          success: (data) => {
              localStorage.setItem("")
              window.location.href = "../html/orderList.html";
          }
        });
    });


});
