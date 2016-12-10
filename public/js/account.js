var jscon = {

    urls: {
        logout: '/logout',
        back: '/back',
        remove: '/remove',
        addphoto: 'addphoto'
        accountload: 'account-load'
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

function remove(user) {
    $.ajax({
        url: jscon.urls.remove,
        method: "DELETE",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        success: function() {
            window.location.replace("index.html");
        },
        error: function(error) {
            alert("Fail!");
        }
    });
}

$('#remove').click(function() {
    event.preventDefault();
    var user = {};
    var r = confirm("Do you really wish to delete your account?")
    if (r == true) {
        user.password = prompt("Enter your password to delete your account. This cannot be undone.");
        remove(user);
    } else {
        alert("Thanks, jerk.");
    }
});

$('account-load').ready(function(){
  $.ajax({
    url:jscon.urls.accountload,
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
