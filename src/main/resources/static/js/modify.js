// onload로 할거
var modifyParkinglot = function(){

   var ownerID = getCookie(ownerID);
   var parkinglotID = getCookie(parkinglotID);

   $.ajax({
      url: '/surepark-restful/owners/'+ownerID+'/parkinglots/'+parkinglotID,
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
         console.log(JSON.stringify(data));
         $('#ptitle').val("<h4>"+data.parkingLotName+"</h4>");
         $('#pid').val(data.parkingLotID);
         $('#pname').val("<input type='text' id='inputname' style='padding-left: 10px;' placeholder='"+data.parkingLotName+"'>");
         $('#pmap').val(data.parkingLotLocation);
         $('#paddress').val(data.parkingLotAdress);
         $('#popen').val("<input type='date' id='inputopen' style='padding-left: 10px;' placeholder='"+data.parkingLotStartTime+"'>");
         $('#pclose').val("<input type='date' id='inputclose' style='padding-left: 10px;' placeholder='"+data.parkingLotEndTime+"'>");
         $('#pspots').val(data.parkingLotMaximumCapacity);
         $('#pgrace').val("<input type='date' id='inputgrace' style='padding-left: 10px;' placeholder='"+data.parkingLotGracePeriod+"'>");
         $('#ppre').val("<input type='date' id='inputpre' style='padding-left: 10px;' placeholder='"+data.parkingLotPreResvationPeriod+"'>");
      },
      error: function (data)
      {
         console.log(data)
      },
   });

}

// put!!!!! 제대로 안해놨음!!!!!! data 어떻게 보내는지 그런 거 신경 안썼음!!!
var updateParking = function(){

   var ownerID = getCookie(ownerID);
   var parkinglotID = getCookie(parkinglotID);

   var ownerLoginJson = JSON.stringify({
            ownerID: ownerID,
            ownerPassword: password,
          });

   $.ajax({
      url: '/surepark-restful/owners/'+ownerID+'/parkinglots/'+parkinglotID,
      type: 'PUT',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: ownerLoginJson,
      success: function (data)
      {
         console.log(JSON.stringify(data));
         $('#pid').val(data.parkingLotID);
         $('#pname').val(data.parkingLotName);
         $('#pmap').val(data.parkingLotLocation);
         $('#paddress').val(data.parkingLotAdress);
         $('#popen').val(data.parkingLotStartTime);
         $('#pclose').val(data.parkingLotEndTime);
         $('#pspots').val(data.parkingLotMaximumCapacity);
         $('#pgrace').val(data.parkingLotGracePeriod);
         $('#ppre').val(data.parkingLotPreResvationPeriod);
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
