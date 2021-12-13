
document.addEventListener('DOMContentLoaded', function(){

    /**
     * On start session set default language getted from browser default
     * */
    var language = window.navigator.language;
    var selItem =  document.getElementById('sessionLanguage').value;
    if (selItem == "") {
        selItem = language.split('-')[1].toLowerCase();
        var searchText = $('#searchNameInput').val();
        var sortByCategory = $('#sortByCategory').val();
        var sortByPrice = $('#sortByPrice').val();
        var sortByName = $('#sortByName').val();
        // $.get("home", {
        $.get("languageSwitcherServlet", {
            lang: selItem,
            searchText: searchText,
            sortByCategory: sortByCategory,
            sortByPrice: sortByPrice,
            sortByName: sortByName
        }, function (response) {
            // $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
            // $(document.body).html(response);
            location.reload();
        });
    }


/**
 * switch session according to language selector
 * */
    $("#localesSession").val(selItem);
    $("#localesSession").change(function () {
        var selectedOption = $("#localesSession").val()
        if (selectedOption) {
            // localStorage.setItem("localesSession", selectedOption);
            var searchText = $('#searchNameInput').val();
            var sortByCategory = $('#sortByCategory').val();
            var sortByPrice = $('#sortByPrice').val();
            var sortByName = $('#sortByName').val();
            // $.get("home", {
            $.get("languageSwitcherServlet", {
                lang: selectedOption,
                searchText: searchText,
                sortByCategory: sortByCategory,
                sortByPrice: sortByPrice,
                sortByName: sortByName
            }, function (response) {
                // $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
                // $(".categoryLink").html($($.parseHTML(response)).find(".categoryLink").html());
                location.reload();

            });
        }
    });
}
);


/** Function searching by name, sorting by category, by name, by price from db
* with caching parameters
 * */
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
