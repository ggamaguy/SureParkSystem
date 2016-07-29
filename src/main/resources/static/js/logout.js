
var ownerLogout = function(){
  
  // ajax
   $.ajax({
	  url: '/surepark-restful/owner/logout',
      type: 'POST',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
    	  window.location = "login.html";
      },
      error: function (data)
      {
    	  console.log(JSON.stringify(data));
      }
   });
}
