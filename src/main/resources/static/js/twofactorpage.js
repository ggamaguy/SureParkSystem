
var ownerTwofactor = function(){

   var ownerID = getCookie("ownerID");
   var ownerSecondPassword = $("#key").val();
   var ownerTwofactorJson = JSON.stringify({
            ownerID: ownerID,
            ownerSecondPassword: ownerSecondPassword,
          });
   
   //window.alert(ownerID+"/"+ownerSecondPassword);
   
   $.ajax({
      url: '/surepark-restful/owner/login/second',
      type: 'POST',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: ownerTwofactorJson,
      success: function (data)
      {
    	// server's result
     	 var temp = JSON.stringify(data);
     	 var result = JSON.parse(temp);
     	 
     	 console.log(JSON.stringify(data));
     	 
     	if (result.result == "fail") {
       	 window.alert("Wrong Passwords!");
        } else if (result.result == "success") {
        	// change cookie
            document.cookie="accessToken="+data.accessToken;
            window.location = "index.html";
        } else if (result.result == "unavailable") {
       	 window.alert("You failed log-in 3 times.\n" +
       	 		"Please contact maintenance.\n" +
       	 		"☏123-1234-1234");
        }
      },
      error: function (data)
      {
         console.log(data);
      },
   });

}

function getCookie(val) {
  var name = val+"=";
  var temp = document.cookie.split(';');
  for (var i = 0; i < temp.length; i++) {
    var c = temp[i];
    while (c.charAt(0)==' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length,c.length);
    }
  }
  return "";
}
