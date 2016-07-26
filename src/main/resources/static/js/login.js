/**
 * 
 */

var oauthToken = function(){

	var number = "2222232222";
	var key ="surepark"
	var drive = JSON.stringify({
			   phoneNumber: number,
			   secretKey: key,
	       });
	
	$.ajax({
		url: '/surepark-restful/drivers',
		type: 'POST',
		contentType: 'application/x-www-form-urlencoded',
		accept: 'application/json',
		dataType: 'json',
		data: drive,
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
	


