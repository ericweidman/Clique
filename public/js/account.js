var jscon = {

    urls: {
        logout: '/logout',
        back: '/back',
        remove: '/remove'
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
        success: function(){
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
  if(r == true){
    user.password = prompt("Enter your password to delete your account. This cannot be undone.");
    remove(user);
  }else{
    alert("Thanks, jerk.");
  }
});
