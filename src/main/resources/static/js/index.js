
var ownerMain = function(){

   var ownerID = getCookie(ownerID);

   $.ajax({
      url: '/surepark-restful/owners/'+ownerID,
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
         console.log(JSON.stringify(data));
         $('#oid').val(data.ownerID);
         $('#oname').val(data.ownerName);
         $('#oemail').val(data.ownerEmail);
         $('#ophone').val(data.ownerPhoneNumber);
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
