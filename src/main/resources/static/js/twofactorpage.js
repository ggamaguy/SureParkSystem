
var ownerTwofactor = function(){

   var ownerID = "ckanstnzja";
   var ownerTwofactorPassword = "12345";
   var ownerTwofactorJson = JSON.stringify({
            ownerID: ownerID,
            ownerTwofactorPassword: ownerTwofactorPassword,
          });


   $.ajax({
      url: '/surepark-restful/owners/login/twofactor',
      type: 'POST',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: ownerTwofactorJson,
      success: function (data)
      {
         console.log(JSON.stringify(data));
         // change cookie
         document.cookie="accessToken="+data.accessToken;
      },
      error: function (data)
      {
         console.log(data);
      },
   });

}
