var jscon = {

    urls: {
        logout: '/logout',
        back: '/back'
    }
};

$('#logout').click(function() {
    $.ajax({
        url: jscon.urls.logout,
        method: "POST",
        success: function(data) {
            window.location.replace("index.html");
        },
        error: function(error) {
            console.log(error);
            alert("Fail!");
        }
    });
})

$('#back').click(function() {
    $.ajax({
        success: function() {
            window.location.replace("home.html");
        },
        error: function(error) {
            console.log(error);
            alert("Fail!");
        }
    });
})
