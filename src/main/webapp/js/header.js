// $(document).ready(
document.addEventListener('DOMContentLoaded', function(){
    // function startFunction() {
    var language = window.navigator.language;

// set default language
    var selItem =  document.getElementById('sessionLanguage').value;
    if (selItem == "") {
        selItem = language.split('-')[1].toLowerCase();
        var searchText = $('#searchNameInput').val();
        var sortByCategory = $('#sortByCategory').val();
        var sortByPrice = $('#sortByPrice').val();
        var sortByName = $('#sortByName').val();
        $.get("home", {
            lang: selItem,
            searchText: searchText,
            sortByCategory: sortByCategory,
            sortByPrice: sortByPrice,
            sortByName: sortByName
        }, function (response) {
            // $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
            $(document.body).html(response);
        });
    }


    // localStorage.getItem("localesSession");
    $("#localesSession").val(selItem);
    $("#localesSession").change(function () {
        var selectedOption = $("#localesSession").val()
        if (selectedOption) {
            // localStorage.setItem("localesSession", selectedOption);
            var searchText = $('#searchNameInput').val();
            var sortByCategory = $('#sortByCategory').val();
            var sortByPrice = $('#sortByPrice').val();
            var sortByName = $('#sortByName').val();
            $.get("home", {
                lang: selectedOption,
                searchText: searchText,
                sortByCategory: sortByCategory,
                sortByPrice: sortByPrice,
                sortByName: sortByName
            }, function (response) {
                $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
            });
        }
    });
}
);


/*function searching by name, sorting by category, by name, by price from db */
/* with caching parameters */
function searchSortFunction() {
    var searchText = $('#searchNameInput').val();

    var searchText = $('#searchNameInput').val();
    var sortByCategory = $('#sortByCategory').val();
    var sortByPrice = $('#sortByPrice').val();
    var sortByName = $('#sortByName').val();
    $.get("home", {
        searchText: searchText,
        sortByCategory: sortByCategory,
        sortByPrice: sortByPrice,
        sortByName: sortByName
    }, function (response) {
        $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
        // $(document.body).html(response);
    });
}
