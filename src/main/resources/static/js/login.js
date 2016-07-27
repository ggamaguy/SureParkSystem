
var ownerLogin = function(){

   var ownerID = "ckanstnzja";
   var password = "123456";
   var ownerLoginJson = JSON.stringify({
            ownerID: ownerID,
            ownerPassword: password,
          });

  // cookie expire 2 hours later.
  var now = new Date();
  var time = now.getTime();
  time += 3600 * 1000 * 2;
  now.setTime(time);
  document.cookie="ownerID="+ownerID+"; expires="+now.toUTCString();

  // ajax

   $.ajax({
      url: '/surepark-restful/owners/login',
      type: 'POST',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: ownerLoginJson,
      success: function (data)
      {
         console.log(JSON.stringify(data))
      },
      error: function (data)
      {
         console.log(data)
      },
   });

}
