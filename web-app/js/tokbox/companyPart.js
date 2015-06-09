var userStates = {};

var initUserStateTimer =  function(){
	//TODO need release interval after some signal
	setInterval( function(){
		jQuery.ajax({
			type:'POST',
			url:'/seeb/companyDashboard/getCustomersStateList',
			success:function(data,textStatus){
				showUserState(data);},
			error:function(XMLHttpRequest,textStatus,errorThrown){}});
	},500);
};

var makeShortMap = function(){
	userStates.prevShortMap = userStates.shortMap;
	userStates.prevMapLength = userStates.mapLength;
	userStates.shortMap = {};
	userStates.mapLength = 0;
	userStates.needUpdate = false;
	userStates.companyCall = false;

	$.each(userStates.data.map, function(key,val){
		userStates.mapLength++;
		userStates.shortMap[val.id] = {state: val.state, isAvailible: val.isAvailible, name: val.name};

		if(userStates.prevShortMap == undefined
				|| userStates.prevShortMap[val.id] == undefined
				|| userStates.prevShortMap[val.id].state != val.state
				|| userStates.prevShortMap[val.id].isAvailible != val.isAvailible
				|| userStates.prevShortMap[val.id].name != val.name) {
			userStates.needUpdate = true;
		}

		if (val.state == "COMPANY_CALL") {
			userStates.companyCall = true;
		}
		if(userStates.activeId == val.id) {
			if(val.state != "IN_CALL" && val.state != "CLIENT_CALL") {
				console.log(val.state);
				stopCall();
				setTimeout(function(){
					console.log("Call ended");
				},200);
			}
		}
		if(userStates.activeId == undefined && val.state == "IN_CALL"){
			acceptCall(val.id, false);
		}
	});

	if(userStates.prevShortMap == undefined || userStates.prevMapLength != userStates.mapLength){
		userStates.needUpdate = true;
	}
};

var showUserState = function(data){
	userStates.data = data;
	makeShortMap();
	if (userStates.needUpdate) {
		var redPoint = "<div style=\"border:4px solid red; width:0; height:0;border-radius: 4px;display:inline-block\"></div>";
		var greenPoint = "<div style=\"border:4px solid green; width:0; height:0;border-radius: 4px;display:inline-block\"></div>";
//		var out = "<table>";
		var out = "";
		var showConference = false;
		$.each(data.map, function(key,val){
			showConference = true;
			out += "<div class=\"" + (val.isAvailible?"":"offline") + "\">" + (val.isAvailible?greenPoint:redPoint);

			if (val.state == "CLIENT_CALL"){
				out += " User <b>" + val.name + "</b> calls your. Please <a style=\"color:green\" href=\"javascript:acceptCall('" + val.id + "', true);\">Accept</a> or <a style=\"color:red\" href=\"javascript:declineCall('" + val.id + "');\">Decline</a></br>";
			} else if (val.state == "INACTIVE"){
				if(val.isAvailible) {
					out += " <b>" + val.name + "</b>. <a style=\"color:green\" href=\"javascript:initCall('" + val.id + "');\">Call</a><br/>";
				} else {
					out += " <b>" + val.name + "</b>.<br/>";
				}
			} else if (val.state == "COMPANY_CALL"){
				out += " <b>" + val.name + "</b>. Calling... <a style=\"color:red\" href=\"javascript:cancelCall('" + val.id + "');\">Cancel</a><br/>";
			} else if (val.state == "IN_CALL"){
				out += " <b>" + val.name + "</b> is calling with you.<br/>";
			}

			out += "</div>";
		});

		if(showConference ){
			document.getElementById("conference-div").style.display = "block";
		} else {
			document.getElementById("conference-div").style.display = "none";
		}

		document.getElementById("customer_list").innerHTML = out;
	}
};

var declineCall = function(id) {
	jQuery.ajax({
			type:'POST',
			url:'/seeb/companyDashboard/declineCall/' + id,
			success:function(data,textStatus){

			},
			error:function(XMLHttpRequest,textStatus,errorThrown){}}
	);
};

var initCall = function(id) {
	if(userStates.session != undefined){
		stopCallSignal();
	}
	jQuery.ajax({
			type:'POST',
			url:'/seeb/companyDashboard/initCall/' + id,
			success:function(data,textStatus){

			},
			error:function(XMLHttpRequest,textStatus,errorThrown){}}
	);
};

var cancelCall = function(id) {
	jQuery.ajax({
			type:'POST',
			url:'/seeb/companyDashboard/cancelCall/' + id,
			success:function(data,textStatus){

			},
			error:function(XMLHttpRequest,textStatus,errorThrown){}}
	);
};

var acceptCall = function(id, sendSignal) {
	if(userStates.session != undefined){
		stopCallSignal();
	}

	document.getElementById("stopCall").style.display = "block";

	userStates.activeId = id;
	document.getElementById("video_content").innerHTML =
		"<div id=\"publisher\" style=\"display:inline-block\"></div><div id=\"subscribers\" style=\"display:inline-block;margin-left: 20px;\"></div>";

//	document.getElementById("startButton").style.display="none";
	userStates.session = TB.initSession(userStates.data.map[id].sessionId);
	userStates.publisher = TB.initPublisher(userStates.data.apiKey, 'publisher');

	userStates.session.on({
		sessionConnected: function (event) {
			userStates.session.publish(userStates.publisher);
		},
		streamCreated: function (event) {
			var subContainer = document.createElement('div');
			subContainer.id = 'stream-' + event.stream.streamId;

			//TODO stub
			document.getElementById('subscribers').innerHTML = "";

			document.getElementById('subscribers').appendChild(subContainer);
			userStates.session.subscribe(event.stream, subContainer);
		}
	});
	userStates.session.connect(userStates.data.apiKey, userStates.data.map[id].companyToken);

	//TODo show stop button
	//todo up state on bd
	if(sendSignal) {
		jQuery.ajax({
				type:'POST',
				url:'/seeb/companyDashboard/acceptCall/'+userStates.activeId,
				success:function(data,textStatus){

				},
				error:function(XMLHttpRequest,textStatus,errorThrown){}}
		);
	}
};


var stopCallSignal = function() {
	jQuery.ajax({
		type: 'POST',
		url: '/seeb/companyDashboard/endCall/' + userStates.activeId,
		success: function (data, textStatus) {
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
		}}
	);
};


var stopCall = function() {
	document.getElementById("stopCall").style.display = "none";
	userStates.session.disconnect();
	userStates.session = undefined;
	userStates.activeId = undefined;
};

var playVideo = function (url) {
	document.getElementById("videoContainer").innerHTML =
		"<video id=\"videoBox\" height=\"300\" src=\"" + url + "\" controls></video>";
	document.getElementById("videoBox").load();
	document.getElementById("videoBox").oncanplay = function() {
		document.getElementById("videoBox").play()
	};
};

window.addEventListener('beforeunload', function (event){
	if(true != userStates.unload) {
		userStates.unload = true;
		jQuery.ajax({
			type: 'POST',
			url: '/seeb/companyDashboard/stopActiveCall',
			success: function (data, textStatus) {

			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}});
	}
});


var showAllUsers = function(isShow) {
	if (isShow) {
		$("#customer_list").addClass("showAll");
	}else{
		$("#customer_list").removeClass("showAll");
	}
}


