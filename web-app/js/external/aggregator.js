(function() {
	console.log("Start:"+Date.now());
	window.seeb = {
		initedTime: Date.now(),

		//TODO backend url
//		baseScriptUrl : "http://localhost:8080/seeb/",
		baseScriptUrl : "http://beta.see2b.com:8080/seeb/",

		jQueryPath : null,
		jQuery: null,


		init: function() {
			seeb.jQueryPath = seeb.baseScriptUrl + "static/plugins/jquery-1.10.2.2/js/jquery/jquery-1.10.2.min.js";
			seeb.tokBoxScriptPath = seeb.baseScriptUrl + "static/js/tokbox/TB.min.js";
			seeb.externalScriptPath = seeb.baseScriptUrl + "static/js/external/clientPart.js";

			seeb.jQueryLoad();

		},

		loadScript : function (scriptSrc, callback) {
			var script = document.createElement("SCRIPT");
			script.type = 'text/javascript';
			script.src = scriptSrc;
			script.onload = function () {
				if (typeof callback !== "undefined") {
					callback();
				}
			};
			document.head.appendChild(script);
			return script;
		},

		jQueryLoad: function () {
			var jQueryVersionToLoad = "1.10.2";
			var skipJQuery = false;

			if (typeof jQuery !== "undefined") {
				var jQueryVersion = jQuery.fn.jquery;
				if (jQueryVersion !== null) {
					console.log("Found jQuery version " + jQueryVersion);
					var compareResult = seeb.compareVersions(jQueryVersion, jQueryVersionToLoad);
					if (compareResult >= 0) {
						console.log("jQuery version is same or newer; skipping loading of jQuery.");
						skipJQuery = true;
					}
				}
			}
			if (!skipJQuery) {
				seeb.loadScript(seeb.jQueryPath, seeb.setupJQuery);
			} else {
				console.log("jQuery scripts already loaded.");
				seeb.setupJQuery(jQuery);
			}
		},

		compareVersions : function(vStr1, vStr2) {
			var vArr1 = seeb.getVersionArray(vStr1);
			var vArr2 = seeb.getVersionArray(vStr2);
			var vLen = (vArr2.length > vArr1.length) ? vArr2.length : vArr1.length;
			for (var i = 0; i < vLen; i++) {
				var vNum1 = (i < vArr1.length) ? vArr1[i] : 0;
				var vNum2 = (i < vArr2.length) ? vArr2[i] : 0;
				if (vNum1 < vNum2)
					return -1;
				else if (vNum1 > vNum2)
					return 1;
			}
			return 0;
		},

		getVersionArray : function(versionStr) {
			var vStrArr = versionStr.split(".");
			var vArr = [];
			for (var i = 0; i < vStrArr.length; i++) {
				vArr[i] = parseInt(vStrArr[i]);
			}
			return vArr;
		},


		setupJQuery: function(preLoadedjQuery){
			console.log("JQuery loaded:"+(Date.now() - seeb.initedTime));
			var jQuery = preLoadedjQuery || jQuery || $;
			seeb.jQuery = jQuery;
			seeb.jQuery.noConflict();
			seeb.generateTemplate();
			seeb.appendAllExternalScripts();
		},

		appendAllExternalScripts: function(){
			seeb.loadScript(seeb.tokBoxScriptPath, function () {
				console.log("tokBox script loaded:" + (Date.now() - seeb.initedTime));
				seeb.loadScript(seeb.externalScriptPath, function () {
					console.log("application script loaded:" + (Date.now() - seeb.initedTime));
				});
			});
		},

		generateTemplate: function(){
			seeb.jQuery('<style>' +
				'#seebActionBox{padding:2px;border:1px solid black;width:268px;height:458px; position: fixed;right: 0;top: 20%;background-color:white;z-index:1000000}' +
				'</style>').appendTo(seeb.jQuery('head'));

			seeb.jQuery("<div id='seebActionBox'>" +
				"<div id=\"loading_user\">Loading...</div>" +
				"Company No 1 <span id=\"callYour\" style=\"display: none\"> is calling you...</span>" +
				"<div style=\"width:12px;height:10px;display:inline-block;\"><div style=\"border:5px solid red; width:0; height:0;border-radius: 5px;display:none;\" id=\"redPoint\"></div>" +
				"<div style=\"border:5px solid green; width:0; height:0;border-radius: 5px;display:none;\" id=\"greenPoint\"></div></div>" +
				"<input type=\"button\" value=\"Call\" id=\"startButton\" style=\"display: none;margin-right:5px;\"/>" +
				"<input type=\"button\" value=\"Decline call\" id=\"declineButton\" style=\"display: none;margin-right:5px;\"/>" +
				"<input type=\"button\" value=\"stop\" id=\"stopCall\" style=\"display: none;margin-right:5px;\"/>" +
				"<input type=\"button\" value=\"leave message\" id=\"leaveButton\" style=\"display:none;margin-right:5px;\"/>" +
				"<input type=\"button\" value=\"stop record\" id=\"stopRecordButton\" style=\"display:none;margin-right:5px;\"/>" +
				"<div id=\"video_content\"></div>" +
				"</div>").appendTo(seeb.jQuery('body'));



//			seeb.jQuery("<div id='wcetOverlay' style='display: none;z-index:1000000'></div>").appendTo(wcet.jQuery('body'));
//			seeb.jQuery("<canvas></canvas>").appendTo(wcet.jQuery("#wcetOverlay"));

//			seeb.jQuery("<div id='wcetMousePosition' style='display: none;z-index:1000000'></div>").appendTo(wcet.jQuery('body'));
//			seeb.jQuery("<div></div>").appendTo(wcet.jQuery("#wcetMousePosition"));
//			seeb.jQuery("<span>Agent</span>").appendTo(wcet.jQuery("#wcetMousePosition"));


		}
	}
	seeb.init();
}).call(this);