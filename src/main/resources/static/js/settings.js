var parkingArr;


var ownerParkinglot = function(){

   var ownerID = getCookie(ownerID);

   $.ajax({
      url: '/surepark-restful/owners/'+ownerID+'/parkinglots',
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
         console.log(JSON.stringify(data));
         for(var i=0; i<data.count; i++) {
           var arr = '<option>'+data.parkignlot[i].parkingLotName+'</option>';
           parkingArr[i] = data.parkinglot[i].parkingLotID;
           $('#list').val(arr);
         }
      },
      error: function (data)
      {
         console.log(data)
      },
   });

}

var parkinglotinfo = function(select){

   var ownerID = getCookie(ownerID);
   var parkinglotID = parkingArr[select.selectedIndex];
   document.cookie="parkinglotID="+parkinglotID;

   $.ajax({
      url: '/surepark-restful/owners/'+ownerID+'/parkinglots/'+parkinglotID,
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
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
