let listOfProductIds = [];

function submitClientForm() {
    document.getElementById("clientForm").submit();

}

function getIdsValueToArr() {

    const collectionOf = document.getElementsByClassName("classValues");

    for (let i = 0; i < collectionOf.length; i++) {
        listOfProductIds.push(collectionOf.item(i).value);
    }
    document.getElementById("productIdsInput").setAttribute("value", listOfProductIds.join());
}
