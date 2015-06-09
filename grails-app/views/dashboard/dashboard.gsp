<!DOCTYPE html>
<html class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>See2B - User Dashboard</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<r:require modules="jquery"/>
	<r:require modules="bootstrap"/>
	<r:layoutResources />

	<!-- Custom styles for this theme -->
	<link rel="stylesheet" href="../static/css/neu.css">
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
						<span class="text">${accountInfo.firstName} ${accountInfo.lastName}</span>
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
				<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><h4>${accountInfo.firstName} ${accountInfo.lastName}<span class="caret"></span></h4></a>


				<ul class="dropdown-menu animated fadeInRight" role="menu">
					<li class="profile-progress">
						<g:link uri="/logout">Logout</g:link>
					</li>
				</ul>
			</div>
		</div>

		<nav>
			<ul class="nav nav-pills nav-stacked">
				<li class="nav-dropdown active">
					<g:link uri="/dashboard/">
						<i class="fa  fa-fw fa-file-text"></i> Dashboard
					</g:link>
					</a>
				</li>
				<li class="nav-dropdown">
					<g:link uri="/dashboard/editprofile">
						<i class="fa  fa-fw fa-file-text"></i> Edit Profile
					</g:link>
				</li>
				<li>
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
			<h1>Dashboard</h1>
		</div>
		<g:if test="${accountInfo.needVerify}">
			<div style="border: 1px solid red">
				Please verify your account
			</div>
		</g:if>
		<g:if test="${accountInfo.alreadyVerify}">
			<div style="border: 1px solid blue">
				Account was verified successfully
			</div>
		</g:if>

		<g:render template="/template/userConference" model="[apiKey: tokData.apiKey, sessionId: tokData.sessionId, token: tokData.token]"/>

		<section id="main-content">
			<div class="row">
				<div class="col-md-6">
					<section class="panel">
						<div class="panel-body profile-wrapper">
							<div class="col-md-3">
								<div class="profile-pic text-center">
									<img src="../static/images/dashboard/avatar2.png" alt="" class="img-circle">
								</div>
							</div>
							<div class="col-md-9">
								<div class="profile-info">
									<h1>${accountInfo.firstName} ${accountInfo.lastName}</h1>
									<br/>
									Company : ${accountInfo.companyName}<br/>
									First Name : ${accountInfo.firstName}<br/>
									Last Name : ${accountInfo.lastName}<br/>
									Location : ${accountInfo.location}<br/>
									Photo url : ${accountInfo.profilePhotoUrl}<br/>

								</div>
							</div>
						</div>
					</section>
				</div>

				<div class="col-md-6">
					<section class="panel">
						<div class="panel-body">
							<div>
								FB username : ${accountInfo.fbInfo}<br/>
								Tw username : ${accountInfo.twInfo}<br/>
								G+ username : ${accountInfo.gInfo}<br/>
								youTube username : ${accountInfo.ytInfo}<br/>
								                                         <!--
								Welcom !
								Login? true<br/>
								Sign up facebook?
								<s2o:ifLoggedInWith provider="facebook">yes</s2o:ifLoggedInWith>
								<s2o:ifNotLoggedInWith provider="facebook">no</s2o:ifNotLoggedInWith>
								<br>
								Sign up linkedin?
								<s2o:ifLoggedInWith provider="linkedin">yes</s2o:ifLoggedInWith>
								<s2o:ifNotLoggedInWith provider="linkedin">no</s2o:ifNotLoggedInWith>

								<br>
								Sign up local?
								<s2o:ifLoggedInWith provider="local">yes</s2o:ifLoggedInWith>
								<s2o:ifNotLoggedInWith provider="local">no</s2o:ifNotLoggedInWith>
																		     -->
							</div>
						</div>
					</section>

				</div>
			</div>
		</section>
	</section>
</section>
<r:layoutResources />
</body></html>


