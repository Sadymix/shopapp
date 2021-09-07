let listOfProductIds = [];

function submitClientForm() {
    document.getElementById("clientForm").submit();

}

function getIdsValueToArr() {

    const arr = document.getElementsByClassName("classValues");

    for (let i = 0; i < arr.length; i++) {
        listOfProductIds.push(arr.item(i).value);
    }
    document.getElementById("productIdsInput").setAttribute("value", listOfProductIds.join());
}
