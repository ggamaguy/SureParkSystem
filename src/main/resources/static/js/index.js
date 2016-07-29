
var ownerMain = function(){

   var ownerID = getCookie("ownerID");

   $.ajax({
      url: '/surepark-restful/owner/'+ownerID,
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
    	// server's result
     	 var temp = JSON.stringify(data);
     	 var result = JSON.parse(temp);
     	 var re = JSON.parse(result);
     	 
         console.log(re);
         
         $("#oid").append(re.ownerID);
         $("#oname").append(re.ownerName);
         $("#oemail").append(re.ownerEmail);
         $("#ophone").append(re.ownerPhoneNumber);
      },
      error: function (data)
      {
         console.log(data)
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
