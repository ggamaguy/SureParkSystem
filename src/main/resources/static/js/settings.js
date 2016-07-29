var parkingArr = [];
var parkingInfoArr = [];

var ownerParkinglot = function(){

   var ownerID = getCookie("ownerID");

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
      	 var re = JSON.parse(result);
      	 
      	console.log(re);
         
         for(var i=0; i<re.count; i++) {
           var arr = '<option>'+re.parkinglot[i].parkingLotName+'</option>';
           parkingArr[i+1] = re.parkinglot[i].parkingLotID;
           $('#list').append(arr);
           parkingInfoArr[re.parkinglot[i].parkingLotID] = re.parkinglot[i];
         }
      },
      error: function (data)
      {
         console.log(data);
      },
   });

}

var parkinglotinfo = function(select){

   var ownerID = getCookie("ownerID");
   var parkinglotID = parkingArr[select.selectedIndex];
   
   document.cookie="parkinglotID="+parkinglotID;

   $.ajax({
      url: '/surepark-restful/owner/'+ownerID+'/parkinglots',
      type: 'GET',
      contentType: 'application/json',
      accept: 'application/json',
      success: function (data)
      {
    	// server's result   
    	 var re = parkingInfoArr[parkinglotID];
    	 
    	 console.log(re);
    	 
    	 $('#pid').empty().append("<br/>");
         $('#pname').empty().append("<br/>");
         $('#pmap').empty().append("<br/>");
         $('#paddress').empty().append("<br/>");
         $('#popen').empty().append("<br/>");
         $('#pclose').empty().append("<br/>");
         $('#pspots').empty().append("<br/>");
         $('#pgrace').empty().append("<br/>");
         $('#ppre').empty().append("<br/>");
         
         $('#pid').empty().append(re.parkingLotID);
         $('#pname').empty().append(re.parkingLotName);
         $('#pmap').empty().append("("+re.parkingLotLocationLatitude+","+re.parkingLotLocationLongitude+")");
         $('#paddress').empty().append(re.parkingLotAdress);
         $('#popen').empty().append(re.parkingLotStartTime);
         $('#pclose').empty().append(re.parkingLotEndTime);
         $('#pspots').empty().append(re.parkingLotMaximumCapacity);
         $('#pgrace').empty().append(re.parkingLotGracePeriod);
         $('#ppre').empty().append(re.parkingLotPreResvationPeriod);
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
