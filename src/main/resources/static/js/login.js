
var ownerLogin = function(){

   var ownerID = $("#id").val();
   var password = $("#password").val();

   var ownerLoginJson = JSON.stringify({
            ownerID: ownerID,
            ownerPassword: password
          });
   
  // cookie expire 2 hours later.
  var now = new Date();
  var time = now.getTime();
  time += 3600 * 1000 * 2;
  now.setTime(time);
  document.cookie="ownerID="+ownerID+"; expires="+now.toUTCString();
  
  // ajax
   $.ajax({
	  url: '/surepark-restful/owner/login',
      type: 'POST',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: ownerLoginJson,
      success: function (data)
      {
    	 // server's result
    	 var temp = JSON.stringify(data);
    	 var result = JSON.parse(temp);
    	 
    	 console.log(JSON.stringify(data));
    	 
         if (result.result == "fail") {
        	 window.alert("Wrong Passwords!");
         } else if (result.result == "success") {
        	 window.location = "twofactorpage.html";
         } else if (result.result == "unavailable") {
        	 window.alert("You failed log-in 3 times.\n" +
        	 		"Please contact maintenance.\n" +
        	 		"☏123-1234-1234");
         }
         // console.log("Page hostname is " + window.location.pathname);
      },
      error: function (data)
      {
    	  console.log(JSON.stringify(data));
      }
   });
}
