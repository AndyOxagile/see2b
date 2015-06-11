(function() {
	var callFunctional = {

		tokObject: {},

		externalData: function () {
			seeb.jQuery.ajax({
					jsonp: "callback",
					dataType: "jsonp",
					url: seeb.baseScriptUrl + "dashboard/external",
					success: function (data, textStatus) {
						callFunctional.initTok(data.tokData);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}}
			);
		},

		register: function () {
			seeb.jQuery.ajax({
					type: 'GET',
					jsonp: "callback",
					dataType: "jsonp",
					url: seeb.baseScriptUrl + "localOAuth/createAndLoginExternalAccount",
					success: function (data, textStatus) {
						callFunctional.externalData()
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}}
			);
		},

		withoutCallbackTemplate: function (name) {
			seeb.jQuery.ajax({
					jsonp: "callback",
					dataType: "jsonp",
					url: seeb.baseScriptUrl + "dashboard/" + name,
					success: function (data, textStatus) {
//						console.log(name);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}}
			);
		},

		initTok: function (params) {
			callFunctional.tokObject.apiKey = params.apiKey;
			callFunctional.tokObject.sessionId = params.sessionId;
			callFunctional.tokObject.token = params.token;
			callFunctional.tokObject.isCallActive = false;
			callFunctional.tokObject.companyCall = false;
			seeb.jQuery("#seebActionBox #loading_user").hide();
			seeb.jQuery("#seebActionBox #startButton").show();
			callFunctional.initCheckUserStateTimer();
		},

		initCheckUserStateTimer: function () {
			setInterval(function () {
				seeb.jQuery.ajax({
					jsonp: "callback",
					dataType: "jsonp",
					url: seeb.baseScriptUrl + "dashboard/getSessionState",
					success: function (data, textStatus) {
						var showCall = false;
						if (data != undefined) {
							if (data.isAvailible) {
								seeb.jQuery("#seebActionBox #greenPoint").css({display: "inline-block"});
								seeb.jQuery("#seebActionBox #redPoint").hide();
							} else {
								seeb.jQuery("#seebActionBox #redPoint").css({display: "inline-block"});
								seeb.jQuery("#seebActionBox #greenPoint").hide();
							}
							if (data.companyCall != undefined && data.companyCall) {
								callFunctional.tokObject.companyCall = true;
								seeb.jQuery("#seebActionBox #startButton")[0].value = "Accept call";
								seeb.jQuery("#seebActionBox #declineButton").show();
								seeb.jQuery("#seebActionBox #leaveButton").hide();
								showCall = true;
							} else {
								callFunctional.tokObject.companyCall = false;
								seeb.jQuery("#seebActionBox #startButton")[0].value = "Call";
								seeb.jQuery("#seebActionBox #declineButton").hide();

								if (data.inCall != undefined && data.inCall) {
									showCall = true;
									if (!callFunctional.tokObject.isCallActive) {
										callFunctional.tokObject.autoInit = true;
										callFunctional.startSession()
									} else {
										if (callFunctional.tokObject.timeout != null) {
											callFunctional.clearTimers();
										}
									}
								} else if (callFunctional.tokObject.isCallActive && data.stop != undefined && data.stop) {
									callFunctional.stopSession(false);
									setTimeout(function () {
										console.log("Call ended");
									}, 200);
								}
							}
						}

						if (document.getElementById("conference-div") != undefined) {
							if (showCall) {
								document.getElementById("conference-div").style.display = "block";
							} else {
								document.getElementById("conference-div").style.display = "none";
							}
						}
					},

					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}});
			}, 500);
		},

		initAreaButton: function () {
			seeb.jQuery("#seebActionBox #startButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.startSession();
			});
			seeb.jQuery("#seebActionBox #declineButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.declineSession();
			});
			seeb.jQuery("#seebActionBox #stopCall").on("click", function (event) {
				event.preventDefault();
				callFunctional.stopSession(true);
			});
			seeb.jQuery("#seebActionBox #leaveButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.startRecord();
			});
			seeb.jQuery("#seebActionBox #stopRecordButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.stopRecord();
			});

			seeb.jQuery("#seebActionBox #stopRecordButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.stopRecord();
			});

			seeb.jQuery("#seebActionBox #openButton").on("click", function (event) {
				event.preventDefault();
				callFunctional.openButton();
			});


		},

		openButton: function() {
			console.log("openButton");
			seeb.jQuery("#seebActionBox").css({
//				width:"268px",
//				height:"458px"
				width:"160px",
				height:"100px"
			});
			seeb.jQuery("#seebActionBox #openButton").hide();
			seeb.jQuery("#contentContainer").show();
		},

		clearTimers: function(){
			seeb.jQuery("#timerArea").html("");
			clearTimeout(callFunctional.tokObject.timeout);
			clearTimeout(callFunctional.tokObject.timer);
		},

		startSession: function(){
			callFunctional.tokObject.isCallActive = true;

			seeb.jQuery("#seebActionBox #stopCall").show();
			seeb.jQuery("#seebActionBox #leaveButton").hide();

			if(!callFunctional.tokObject.autoInit) {
				if (!callFunctional.tokObject.companyCall) {
					callFunctional.withoutCallbackTemplate("preCall");
				} else {
					callFunctional.withoutCallbackTemplate("approveCall");
				}
			}

			document.getElementById("video_content").innerHTML =
				document.getElementById("video_content").innerHTML =
					"<div style=\"display: inline-block\"><div>You:</div><div id=\"publisher\" style=\"display:inline-block\"></div></div>" +
					"<div style=\"display: inline-block\"><div>Company:</div><div id=\"subscribers\" style=\"display:inline-block\"></div></div>";

			seeb.jQuery("#seebActionBox #startButton").hide();
			callFunctional.tokObject.session = TB.initSession(callFunctional.tokObject.sessionId);
			callFunctional.tokObject.publisher = TB.initPublisher(callFunctional.tokObject.apiKey, 'publisher');

			callFunctional.tokObject.session.on({
				sessionConnected: function (event) {
					callFunctional.tokObject.session.publish(callFunctional.tokObject.publisher);
				},
				streamCreated: function (event) {
					var subContainer = document.createElement('div');
					subContainer.id = 'stream-' + event.stream.streamId;

					//TODO stub
					document.getElementById('subscribers').innerHTML = "";

					document.getElementById('subscribers').appendChild(subContainer);
					callFunctional.tokObject.session.subscribe(event.stream, subContainer);
				}
			});

			callFunctional.tokObject.session.connect(callFunctional.tokObject.apiKey, callFunctional.tokObject.token);
			seeb.jQuery("#seebActionBox").css({
				width:"268px",
				height:"458px"
			});


			if(!callFunctional.tokObject.autoInit) {
				if (callFunctional.tokObject.timeout != null) {
					callFunctional.clearTimers();
				}

				var timerFunc = function() {
					callFunctional.tokObject.timer = setTimeout(timerFunc, 100);
					callFunctional.tokObject.delta-=100;
					seeb.jQuery("#timerArea").html(callFunctional.tokObject.delta/1000)

				};
				callFunctional.tokObject.delta = 30000;
				timerFunc();

				callFunctional.tokObject.timeout = setTimeout(function () {
					callFunctional.stopSession(true)
				}, 30000);
			}
			callFunctional.tokObject.autoInit = false;
		},

		declineSession: function(){
			seeb.jQuery.ajax({
				jsonp: "callback",
				dataType: "jsonp",
				url: seeb.baseScriptUrl + "dashboard/declineCall",
				success:function(data,textStatus){

				},
				error:function(XMLHttpRequest,textStatus,errorThrown){}});
		},

		stopSession: function(sendSignal){
			callFunctional.clearTimers();
			callFunctional.tokObject.isCallActive = false;
			callFunctional.tokObject.session.disconnect();
			document.getElementById("video_content").innerHTML = "";
			seeb.jQuery("#seebActionBox").css({
				width:"160px",
				height:"100px"
			});

			if(sendSignal){
				callFunctional.withoutCallbackTemplate("callTimeoutOver");
			}
			seeb.jQuery("#seebActionBox #leaveButton").show();
			seeb.jQuery("#seebActionBox #startButton").show();
			seeb.jQuery("#seebActionBox #stopCall").hide();
		},

		startRecord: function(){
			document.getElementById("video_content").innerHTML =
				"<div style=\"display: inline-block\"><div>You:</div><div id=\"publisher\" style=\"display:inline-block\"></div></div>";
			seeb.jQuery("#seebActionBox #startButton").hide();
			callFunctional.tokObject.session = TB.initSession(callFunctional.tokObject.sessionId);
			callFunctional.tokObject.publisher = TB.initPublisher(callFunctional.tokObject.apiKey, 'publisher');

			callFunctional.tokObject.session.on({
				sessionConnected: function (event) {
					callFunctional.tokObject.session.publish(callFunctional.tokObject.publisher);
				},
				streamCreated: function (event) {
					var subContainer = document.createElement('div');
					subContainer.id = 'stream-' + event.stream.streamId;
					document.getElementById('subscribers').appendChild(subContainer);
					callFunctional.tokObject.session.subscribe(event.stream, subContainer);
				}
			});
			callFunctional.tokObject.session.connect(callFunctional.tokObject.apiKey, callFunctional.tokObject.token);
			seeb.jQuery("#seebActionBox").css({
				width:"268px",
				height:"258px"
			});

			seeb.jQuery("#seebActionBox #leaveButton").hide();
			seeb.jQuery("#seebActionBox #stopRecordButton").show();
			callFunctional.withoutCallbackTemplate("recordMessage");
		},

		stopRecord: function(){
			callFunctional.tokObject.session.disconnect();
			document.getElementById("video_content").innerHTML = "";
			seeb.jQuery("#seebActionBox").css({
				width:"160px",
				height:"100px"
			});
			seeb.jQuery("#seebActionBox #startButton").show();
			seeb.jQuery("#seebActionBox #stopRecordButton").hide();
			seeb.jQuery("#seebActionBox #leaveButton").hide();
			callFunctional.withoutCallbackTemplate("stopRecordAndLeaveMessage");
		},

		initBeforeUnload: function(){
			window.addEventListener('beforeunload', function (event){
				jQuery.ajax({
					jsonp: "callback",
					dataType: "jsonp",
					url: seeb.baseScriptUrl + "dashboard/stopActiveCall",
					success:function(data,textStatus){

					},
					error:function(XMLHttpRequest,textStatus,errorThrown){}});
			});
		}
	};

	callFunctional.register();
	callFunctional.initAreaButton();
	callFunctional.initBeforeUnload();

}).call(this);
