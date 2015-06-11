var playVideo = function (url) {
	document.getElementById("videoContainer").innerHTML =
		"<video id=\"videoBox\" height=\"300\" src=\"" + url + "\" controls></video>";
	document.getElementById("videoBox").load();
	document.getElementById("videoBox").oncanplay = function() {
		document.getElementById("videoBox").play()
	};
};

var removeVideo = function (id) {
	$("#message_"+id).hide();
	jQuery.ajax({
		type: 'POST',
		url: '/seeb/remove/removeVideoMessage/'+id,
		success: function (data, textStatus) {

		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
		}});
};