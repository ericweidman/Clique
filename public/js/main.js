var jscon={

urls:{
  newUser: '/create-user'
  }
};

function newUser(user){
  $.ajax({
    url: jscon.urls.newUser,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    //dataType: 'json',
    data: JSON.stringify(user),
    success: function(data){
      console.log('user added!', data);
      alert("Success!");
      window.location.replace("home.html");

    },
    error: function(error){
      console.log("Add User", error);
      alert("Fail!");
    }
  });
}

$('#newUser').submit(function(event){
  event.preventDefault();
  var user = {};
  user.userName = $('input[name=userName]').val();
  user.email = $('input[name=email]').val();
  user.password = $('input[name=password]').val();
  user.firstName = $('input[name=firstName]').val();
  user.lastName = $('input[name=lastName]').val();
  newUser(user);
});
