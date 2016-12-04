var jscon={

urls:{
  newMessage: '/save-message',
  target: '/email'
  }
};

function newMessage(message){
  $.ajax({
    url: jscon.urls.newMessage,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    data: JSON.stringify(message),
    success: function(data){
      console.log('message added!', data);
      alert("Success!");

    },
    error: function(error){
      console.log("Add User", error);
      alert("Fail!");
    }
  });
}

$('#newMessage').submit(function(event){
  event.preventDefault();
  var message = {};
  message.message1 = $('input[name=message]').val();
  message.user = 0;
  newMessage(message);
});


$( "#target" ).click(function() {
  $.ajax({
    url: jscon.urls.target,
    method: "GET",
    dataType: 'text',
    success: function(data){
      alert(data);
    },
    error: function(error){
      console.log(error);
      alert("Fail!");
    }
  });
});
