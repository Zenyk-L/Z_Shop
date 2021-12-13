/**
 * In bucket change order quantity product, check did order quantity less present product quantity
 * */
function changeQuantity(selectedProductId, selectedBucketId ){
    var inputValue = Number(document.getElementById('quantity'+selectedProductId).value);
    var maxValue = Number(document.getElementById('quantity'+selectedProductId).getAttribute("max"));
    if (maxValue + 1  > inputValue) {

        $.post("bucket", {productId: selectedProductId, quantity: inputValue}, function (response) {
            $(document.body).html(response);
            document.getElementById('quantity' + selectedProductId).focus();

            document.getElementById('buy' + selectedBucketId).setAttribute("href","/buyProduct?bucketId="+selectedBucketId );
            // document.getElementById('buyAll').setAttribute("href", "/buyAllProduct");
        });
    }else {
        // document.getElementById('buy' + selectedBucketId).removeAttribute("href");
        // document.getElementById('buyAll').removeAttribute("href");
        document.getElementById('quantity'+selectedProductId).value = maxValue;
        document.getElementById('quantity' + selectedProductId).focus();
        if (inputValue != 0) {
        alert("We haven't so quantity. We have only " + maxValue);
        }
    }
}