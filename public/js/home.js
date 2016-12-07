var jscon = {

    urls: {
        newMessage: '/save-message',
        target: '/getname',
        logout: '/logout',
        account: '/account-load'
    }
};

function newMessage(message) {
    $.ajax({
        url: jscon.urls.newMessage,
        method: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(message),
        success: function(data) {
            console.log('message added!', data);
            alert("Success!");

        },
        error: function(error) {
            console.log("Add User", error);
            alert("Fail!");
        }
    });
}

$('#newMessage').submit(function(event) {
    event.preventDefault();
    var message = {};
    message.message1 = $('input[name=message]').val();
    message.user = 0;
    newMessage(message);
});

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

$("#target").click(function() {
    $.ajax({
        url: jscon.urls.target,
        method: "GET",
        dataType: 'text',
        success: function(data) {
            alert(data)
        },
        error: function(error) {
            console.log(error);
            alert("Fail!");
        }
    });
});

$("#account").click(function() {
    $.ajax({
        url: jscon.urls.account,
        method: "GET",
        dataType: "json",
        success: function(data) {
            window.location.replace("account.html");
        },
        error: function(data) {
            console.log(error);
            alert("Fail!");
        }
    });
});
