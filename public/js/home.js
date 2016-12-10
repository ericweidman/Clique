var jscon = {

    urls: {
        newMessage: '/save-message',
        logout: '/logout',
        account: '/account-load',
        pageload: 'page-load',
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

$('#account').click(function() {
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

$('page-load').ready(function(){
  $.ajax({
    url:jscon.urls.pageload,
    method: "GET",
    dataType: 'json',
    success:function(data){
      console.log(data);
      var user = data;
      var myDiv = document.getElementById('fixedheader');
      console.log(myDiv);
      myDiv.innerHTML = "Clique - Chat For " + user.firstName + " " + user.lastName;
    },
    error: function(data){
      console.log(error);
      console.log(data);
      alert('Fail!');
    }
  });
});
