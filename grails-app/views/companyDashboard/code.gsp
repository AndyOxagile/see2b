<!DOCTYPE html>
<html class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>See2B - Code portal</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<r:require modules="jquery"/>
	<r:require modules="bootstrap"/>
	<r:layoutResources />

	<!-- Custom styles for this theme -->
	<link rel="stylesheet" href="../static/css/neu.css">
	<script src="../static/js/tokbox/companyPart.js"></script>
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
						<span class="text">${company.contactName}</span>
						<span class="caret"></span>
					</span>
				</a>
				<ul class="dropdown-menu animated fadeInRight" role="menu">
					<li>
						<g:link uri="/">Logout</g:link>
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
				<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><h4>${company?.contactName} <span class="caret"></span></h4></a>
				<h3>${company?.name}</h3>

				<ul class="dropdown-menu animated fadeInRight" role="menu">
					<li class="profile-progress">
						<g:link uri="/">Logout</g:link>
					</li>
				</ul>
			</div>
		</div>

		<nav>
			<ul class="nav nav-pills nav-stacked">
				<li class="nav-dropdown">
					<g:link uri="/companyDashboard/">
						<i class="fa  fa-fw fa-file-text"></i> Dashboard
					</g:link>
					</a>
				</li>
				<li class="nav-dropdown">
					<g:link uri="/companyDashboard/editprofile">
						<i class="fa  fa-fw fa-file-text"></i> Edit Company Profile
					</g:link>
				</li>
				<li class="nav-dropdown">
					<g:link uri="/companyDashboard/customers">
						<i class="fa  fa-fw fa-file-text"></i> Customers
					</g:link>
				</li>
				<li class="nav-dropdown">
					<g:link uri="/companyDashboard/messages">
						<i class="fa  fa-fw fa-file-text"></i> Messages
					</g:link>
				</li>
				<li class="nav-dropdown active">
					<g:link uri="/companyDashboard/code">
						<i class="fa  fa-fw fa-file-text"></i> Code Portal
					</g:link>
				</li>
			</ul>
		</nav>
	</aside>

	<section class="main-content-wrapper">
		<div class="pageheader">
			<h1>Code Portal </h1>
		</div>

		<section id="main-content">
			<div class="row">
				<g:render template="/template/companyConference"/>

				<div class="col-md-12">
					<span>Copy and Paste the following code on every webpage you'd like have
					Video Calling enabled. Just be sure to place it before the &lt;/body&gt tag.
					</span>
				</div>
				<br/><br/>
				<div class="col-md-12">

					<section class="panel">
						<div style="padding:10px">
							&lt;script src=\"http://beta.see2b.com:8080/seeb/static/js/external/aggregator.js\" type=\"text/javascript\"&gt;&lt;/script&gt;
						</div>
					</section>
				</div>

			</div>
		</section>
	</section>
</section>
<r:layoutResources />
</body></html>


