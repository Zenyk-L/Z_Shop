$(".togglePopup").on('click',function(){
    $("#popup").fadeToggle();
});

$(".cancel").on('click',function(){
    $("#popup").hide();//fadeToggle();
});




$('.message a').click(function () {
    loginRegisterSwitch();
});

function loginRegisterSwitch() {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}

