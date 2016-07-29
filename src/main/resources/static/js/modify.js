// onload로 할거

var parkingArr = [];

var modifyParkinglot = function(){

   var ownerID = getCookie("ownerID");
   var parkinglotID = getCookie("parkinglotID");

   $.ajax({
      url: '/surepark-restful/owner/'+ownerID+'/parkinglots',
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
    	// server's result
       	 var temp = JSON.stringify(data);
       	 var result = JSON.parse(temp);
       	 var res = JSON.parse(result);
       	 
          //console.log(res.parkinglot[0]);
          var index;
          
          for(var i=0; i<res.count; i++) {
        	  parkingArr[i] = res.parkinglot[i];
        	  console.log(parkingArr[i]);
         	 if (res.parkinglot[i].parkingLotID == parkinglotID) {
         		 index = i;
         	 }
          }
          
          var re = res.parkinglot[index];

     	 //console.log(re);

          $('#ptitle').empty().append("<h4>"+re.parkingLotName+"</h4>");
          $('#pid').empty().append(re.parkingLotID);
          $('#pname').empty().append("<input type='text' id='inputname' style='padding-left: 10px;' placeholder='"+re.parkingLotName+"'>");
          $('#pmap').empty().append("("+re.parkingLotLocationLatitude+","+re.parkingLotLocationLongitude+")");
          $('#paddress').empty().append(re.parkingLotAdress);
          $('#popen').empty().append("<input type='text' id='inputopen' style='padding-left: 10px;' placeholder='"+re.parkingLotStartTime+"'>");
          $('#pclose').empty().append("<input type='text' id='inputclose' style='padding-left: 10px;' placeholder='"+re.parkingLotEndTime+"'>");
          $('#pspots').empty().append(re.parkingLotMaximumCapacity);
          $('#pgrace').empty().append("<input type='integer' id='inputgrace' style='padding-left: 10px;' placeholder='"+re.parkingLotGracePeriod+"'>");
          $('#ppre').empty().append("<input type='integer' id='inputpre' style='padding-left: 10px;' placeholder='"+re.parkingLotPreResvationPeriod+"'>");
 
      },
      error: function (data)
      {
         console.log(data)
      },
   });

}


var updateParking = function(){

   var ownerID = getCookie("ownerID");
   var parkinglotID = getCookie("parkinglotID");
   var index;
   for(var i=0; i<parkingArr.length; i++) {
  	 if (parkingArr[i].parkingLotID == parkinglotID) {
  		 index = i;
  	   console.log(parkingArr[index]);
  	   
  	 }
   }

   var parkingLotName = $("#inputname").val();
   var parkingLotStartTime = $("#inputopen").val();
   var parkingLotEndTime = $("#inputclose").val();
   var parkingLotGracePeriod = $("#inputgrace").val();
   var parkingLotPreResvationPeriod = $("#inputpre").val();
   
   if (parkingLotName == undefined || parkingLotName == '') {
	   parkingLotName = parkingArr[index].parkingLotName;
   }
   if ( parkingLotStartTime == undefined || parkingLotStartTime == '') {
	   parkingLotStartTime = parkingArr[index].parkingLotStartTime;
   }
   if (parkingLotEndTime == undefined || parkingLotEndTime == '') {
	   parkingLotEndTime = parkingArr[index].parkingLotEndTime;
   }
   if ( parkingLotGracePeriod == undefined || parkingLotGracePeriod == '') {
	   parkingLotGracePeriod = parkingArr[index].parkingLotGracePeriod;
	   console.log(parkingArr[index].parkingLotGracePeriod);
   }
   if (parkingLotPreResvationPeriod == undefined || parkingLotPreResvationPeriod == '') {
	   parkingLotPreResvationPeriod = parkingArr[index].parkingLotPreResvationPeriod;
   }
   

   
   var updateParkinglot = JSON.stringify({
	   		parkingLotID: parkingArr[index].parkingLotID,
            parkingLotName: parkingLotName,
            parkingLotStartTime: parkingLotStartTime,
            parkingLotEndTime: parkingLotEndTime,
            parkingLotGracePeriod: parkingLotGracePeriod,
            parkingLotPreResvationPeriod: parkingLotPreResvationPeriod,
            parkingLotLocationLongitude: parkingArr[index].parkingLotLocationLongitude,
            parkingLotLocationLatitude: parkingArr[index].parkingLotLocationLatitude,
            parkingLotAdress: parkingArr[index].parkingLotAdress,
            parkingLotMaximumCapacity: parkingArr[index].parkingLotMaximumCapacity,
            ownerID: parkingArr[index].ownerID,
          });

   $.ajax({
      url: '/surepark-restful/owner/'+ownerID+'/parkinglots/'+parkinglotID,
      type: 'PUT',
      contentType: 'application/json',
      accept: 'application/json',
      dataType: 'json',
      data: updateParkinglot,
      success: function (data)
      {
         console.log(JSON.stringify(data));
         window.location = "settings.html";
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
