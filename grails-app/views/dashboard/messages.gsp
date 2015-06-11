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
	<script src="../static/js/tokbox/generalPart.js"></script>
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
							<g:if test="${accountInfo?.hasLogo}">
								<img class="img-circle" src="/seeb/upload/getAccountLogo"/>
							</g:if>
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
				<g:if test="${accountInfo?.hasLogo}">
					<img class="img-circle profile-image" src="/seeb/upload/getAccountLogo"/>
				</g:if>
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
				<li>
					<g:link uri="/dashboard/conference">
						<i class="fa  fa-fw fa-file-text"></i> My Business
					</g:link>
				</li>
				<li class="nav-dropdown active">
					<g:link uri="/dashboard/messages">
						<i class="fa  fa-fw fa-file-text"></i> Messages
					</g:link>
				</li>
			</ul>
		</nav>
	</aside>

	<section class="main-content-wrapper">
		<div class="pageheader">
			<h1>Messages </h1>
		</div>

		<g:if test="${accountInfo.needVerify}">
			<div style="border: 1px solid red">
				Please verify your account
			</div>
		</g:if>

		<g:render template="/template/userConference" model="[apiKey: tokData.apiKey, sessionId: tokData.sessionId, token: tokData.token]"/>

		<section id="main-content">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<section class="panel" style="text-align: center;height: 300px" id="videoContainer">

					</section>
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-12">
					<section class="panel">
						<div style="padding:10px">
							<table border="1" cellpadding="5" style="width:100%">
								<tr>
									<th style="width:150px">
										Date
									</th>
									<th style="width:150px">
										Time
									</th>
									<th style="min-width:250px">
										User
									</th>
									<th style="min-width:250px">
										Company
									</th>
									<th  style="width:110px">
										State
									</th>
									<th style="min-width:100px">
										Action
									</th>
								</tr>
								<g:each in="${videoMessages}" var="message">
									<tr id="message_${message.id}">
										<td>${message.date}</td>
										<td>${message.time}</td>
										<td>${message.user}</td>
										<td>${message.company}</td>
										<td>${message.state}</td>
										<td>
											<g:if test="${message.url && !message.isProcessing && !message.isProcessing }">
												<button onClick="playVideo('${message.url}')" style="background-color: green;color:white; border-radius: 4px;border: none;outline:0">
													<span class="glyphicon glyphicon-play" ></span>
												</button>
											</g:if>
											<button onClick="removeVideo('${message.id}')" style="background-color: transparent;color: black; border-radius: 4px;border: none;outline:0">
												<span class="glyphicon glyphicon-trash" ></span>
											</button>
										</td>
									</tr>
								</g:each>
							</table>
						</div>
					</section>
				</div>

			</div>
		</section>
	</section>
</section>
<r:layoutResources />
</body></html>


