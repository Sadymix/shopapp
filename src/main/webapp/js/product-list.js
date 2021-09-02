let listOfProductIds = [];

function addToCart(id) {
    listOfProductIds.push(id);
    document.getElementById("addToCart").innerHTML = "Cart " + listOfProductIds.length;
}

function submitForm() {
    document.getElementById("idInput").setAttribute("value",listOfProductIds.join());
    document.getElementById("idForm").submit();
}