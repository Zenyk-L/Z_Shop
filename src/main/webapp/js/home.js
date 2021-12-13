/**
 * Confirm message before delete product
 * */
function deleteProduct(productId, productName){

    if (window.confirm("Do you really want to delete "+ productName + " ?")) {
        window.open("/deleteProduct?productId=" + productId, "_self");
    }

}

/**
 * Pagination function
 * */
function getPage(getPageNumber) {
    var searchText = $('#searchNameInput').val();

    var searchText = $('#searchNameInput').val();
    var sortByCategory = $('#sortByCategory').val();
    var sortByPrice = $('#sortByPrice').val();
    var sortByName = $('#sortByName').val();
    $.get("home", {
        searchText: searchText,
        sortByCategory: sortByCategory,
        sortByPrice: sortByPrice,
        sortByName: sortByName,
        page: getPageNumber
    }, function (response) {
        $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
        // $(document.body).html(response);
    });
}