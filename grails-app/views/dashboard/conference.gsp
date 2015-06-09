<!DOCTYPE html>
<html class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>See2B - Call</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link id="theme-style" rel="stylesheet" href="../static/html/css/styles.css">
	<r:require modules="jquery"/>
	<r:require modules="bootstrap"/>
	<r:layoutResources />

	<!-- Custom styles for this theme -->
	<link rel="stylesheet" href="../static/css/neu.css">

	<script src="../static/js/tokbox/TB.min.js"></script>
	<script src="../static/js/tokbox/clientPart.js"></script>
</head>

<body class=" pace-done">
<section id="main-wrapper" class="theme-default">

	<header id="header">
		<div class="brand">
			<h1 class="logo">
				<a href="../"><span class="logo-icon"></span><span class="text">See2B</span></a>
			</h1>
		</div>

		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown profile hidden-xs">
				<a class="dropdown-toggle" data-toggle="dropdown">
					<span class="meta">
						<span class="avatar">
							<img src="../static/images/dashboard/profile.jpg" class="img-circle" alt="">
						</span>
						<span class="text">${accountInfo?.firstName} ${accountInfo?.lastName}</span>
						<span class="caret"></span>
					</span>
				</a>
				<ul class="dropdown-menu animated fadeInRight" role="menu">
					<li>
						<g:link uri="/logout">Logout</g:link>
					</li>
				</ul>
			</li>
		</ul>
	</header>

	<aside class="sidebar sidebar-left">
		<div class="sidebar-profile">

			<div class="avatar">
				<img class="img-circle profile-image" src="../static/images/dashboard/profile.jpg" alt="profile">
			</div>

			<div class="profile-body dropdown">
				<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><h4>${accountInfo?.firstName} ${accountInfo?.lastName} <span class="caret"></span></h4></a>


				<ul class="dropdown-menu animated fadeInRight" role="menu">
					<li class="profile-progress">
						<g:link uri="/logout">Logout</g:link>
					</li>
				</ul>
			</div>
		</div>

		<nav>
			<ul class="nav nav-pills nav-stacked">
				<li class="nav-dropdown">
					<g:link uri="/dashboard/">
						<i class="fa  fa-fw fa-file-text"></i> Dashboard
					</g:link>
				</a>
				</li>
				<li>
					<g:link uri="/dashboard/editprofile">
						<i class="fa  fa-fw fa-file-text"></i> Edit Profile
					</g:link>
				</li>
				<li class="nav-dropdown active">
					<g:link uri="/dashboard/conference">
						<i class="fa  fa-fw fa-file-text"></i> My Business
					</g:link>
				</li>
				<li>
					<g:link uri="/dashboard/messages">
						<i class="fa  fa-fw fa-file-text"></i> Messages
					</g:link>
				</li>
			</ul>
		</nav>
	</aside>

	<section class="main-content-wrapper">
		<div class="pageheader">
			<h1>My Business </h1>
		</div>
		<g:if test="${accountInfo.needVerify}">
			<div style="border: 1px solid red">
				Please verify your account
			</div>
		</g:if>

		<section id="main-content">

			<div class="row">
				<div class="col-md-12">
					<section class="panel" id="seebActionBox">
						<div style="border:5px solid red; width:0; height:0;border-radius: 5px;display:none;" id="redPoint"></div>
						<div style="border:5px solid green; width:0; height:0;border-radius: 5px;display:none;" id="greenPoint"></div>
						Company No 1
						<span id="callYour" style="display: none"> is calling you...</span>

						<button id="startButton" ><span class="glyphicon glyphicon-earphone"></span></button>
						<button id="declineButton" style="display:none"><span class="glyphicon glyphicon-earphone"></span></button>

						<button id="stopCall" style="display:none" ><span class="glyphicon glyphicon-stop"></span></button>

						<button id="leaveButton" style="display:none" ><span class="glyphicon glyphicon-record"></span></button>
						<button id="stopRecordButton" style="display:none" ><span class="glyphicon glyphicon-stop"></span></button>


						<div id="video_content">
						</div>
					</section>
				</div>

			</div>
		</section>
	</section>
</section>
<r:layoutResources />
<script type="text/javascript">
	callFunctional.initTok({apiKey:'${ apiKey }', sessionId:'${ sessionId }', token:'${ token }'});
	callFunctional.initAreaButton();
	callFunctional.initBeforeUnload();
</script>

</body></html>


