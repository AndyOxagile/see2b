<!DOCTYPE html>
<html class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>See2B - User Profile</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link id="theme-style" rel="stylesheet" href="../static/html/css/styles.css">
	<r:require modules="jquery"/>
	<r:require modules="bootstrap"/>
	<r:require module="fileuploader" />
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
							<img class="img-circle" src="/seeb/upload/getAccountLogo"/>
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
				<li class="nav-dropdown active">
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
			<h1>Edit Profile  <span style="color:green">${status}</span></h1>
		</div>

		<g:if test="${accountInfo.needVerify}">
			<div style="border: 1px solid red">
				Please verify your account
			</div>
		</g:if>

		<g:render template="/template/userConference" model="[apiKey: tokData.apiKey, sessionId: tokData.sessionId, token: tokData.token]"/>

		<section id="main-content">
			<g:form action="saveprofile" controller="dashboard" method="post" autocomplete="off">
			<div class="row">
				<div class="col-md-6">
					<section class="panel">
						<div class="panel-body profile-wrapper">
							<div class="col-md-3">
								<div id="logoBlock">
									<g:if test="${accountInfo?.hasLogo}">
										<img style="width: 90%;" src="/seeb/upload/getAccountLogo"/>
									</g:if>
									<g:if test="${!accountInfo?.hasLogo}">
										No Logo
									</g:if>
								</div>
							</div>
							<div class="col-md-9">
								<div class="profile-info">
									<h1>${accountInfo?.firstName} ${accountInfo?.lastName}</h1>
									<br/>

										<fieldset>
											<div class="form-group">
												<div class="fieldcontain form-group">
													<label>Avatar</label>
													<uploader:uploader
															id="yourUploaderId" url="${[controller: 'upload', action: 'uploadAccountLogo']}"
															multiple="false">
														<uploader:onComplete>
															$("#logoBlock").html('<img style="width: 90%;" src="/seeb/upload/getAccountLogo"/>');
														</uploader:onComplete>
													</uploader:uploader>
												</div>

												<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'companyName', 'error')} form-group">
													<label for="company-name">Company</label>
													<g:textField name='companyName' value='${accountInfo?.companyName}' class="form-control" id="company-name" maxlength="255"/>
													<g:renderErrors bean="${accountInfo}" as="list" field="companyName"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'firstName', 'error')} form-group">
													<label for="first-name">First Name</label>
													<g:textField name='firstName' value='${accountInfo?.firstName}' class="form-control" id="first-name" maxlength="255"/>
													<g:renderErrors bean="${accountInfo}" as="list" field="firstName"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'lastName', 'error')} form-group">
													<label for="last-name">Last Name</label>
													<g:textField name='lastName' value='${accountInfo?.lastName}' class="form-control" id="last-name" maxlength="255"/>
													<g:renderErrors bean="${accountInfo}" as="list" field="lastName"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'location', 'error')} form-group">
													<label for="location-name">Location</label>
													<g:textField name='location' value='${accountInfo?.location}' class="form-control"  id="location-name" maxlength="255"/>
													<g:renderErrors bean="${accountInfo}" as="list" field="location"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'website', 'error')} form-group">
													<label for="website-name">Your website</label>
													<g:textField name='website' value='${accountInfo?.website}' class="form-control" id="website-name"/>
													<g:renderErrors bean="${accountInfo}" as="list" field="website"/>
												</div>
										</fieldset>
								</div>
							</div>
						</div>
					</section>
				</div>

				<div class="col-md-6">
					<section class="panel">
						<div class="panel-body">
							<div>
								<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'fbInfo', 'error')} form-group">
									<label for="fb-name">Facebook name</label>
									<g:textField name='fbInfo' value='${accountInfo?.fbInfo}' class="form-control" id="fb-name" maxlength="255"/>
									<g:renderErrors bean="${accountInfo}" as="list" field="fbInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'twInfo', 'error')} form-group">
									<label for="tw-name">Twitter name</label>
									<g:textField name='twInfo' value='${accountInfo?.twInfo}' class="form-control" id="tw-name" maxlength="255"/>
									<g:renderErrors bean="${accountInfo}" as="list" field="twInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'gInfo', 'error')} form-group">
									<label for="g-name">Google+ name</label>
									<g:textField name='gInfo' value='${accountInfo?.gInfo}' class="form-control" id="g-name" maxlength="255"/>
									<g:renderErrors bean="${accountInfo}" as="list" field="gInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: accountInfo, field: 'ytInfo', 'error')} form-group">
									<label for="yt-name">YouTube name</label>
									<g:textField name='ytInfo' value='${accountInfo?.ytInfo}' class="form-control" id="yt-name" maxlength="255"/>
									<g:renderErrors bean="${accountInfo}" as="list" field="ytInfo"/>
								</div>


							</div>
						</div>
					</section>

				</div>
				<div class="col-md-12">
					<g:submitButton name="Apply"  class="btn btn-block btn-cta-primary" style="width:200px"/>
				</div>
			</div>
			</g:form>
		</section>
	</section>
</section>
<r:layoutResources />
</body></html>


