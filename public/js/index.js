var jscon={

urls:{
  newUser: '/create-user',
  login: 'login'
  }
};

function newUser(user){
  $.ajax({
    url: jscon.urls.newUser,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(user),
    success: function(data){
      console.log('user added!', data);
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


function login(user){
  $.ajax({
    url: jscon.urls.login,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(user),
    success: function(data){
      console.log('user in', data);
      window.location.replace("home.html");

    },
    error: function(error){
      alert("Fail!");
    }
  });
}

$('#login').submit(function(event){
  event.preventDefault();
  var user = {};
  user.userName = $('input[name=userName]').val();
  user.password = $('input[name=password]').val();
  newUser(user);
});
