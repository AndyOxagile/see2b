<!DOCTYPE html>
<html class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>See2B - Company Profile</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link id="theme-style" rel="stylesheet" href="../static/html/css/styles.css">
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
						<span class="text">${company?.contactName}</span>
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
				<li>
					<g:link uri="/companyDashboard/">
						<i class="fa  fa-fw fa-file-text"></i> Dashboard
					</g:link>
				</a>
				</li>
				<li class="nav-dropdown active">
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
			</ul>
		</nav>
	</aside>

	<section class="main-content-wrapper">
		<div class="pageheader">
			<h1>Edit Company Profile  <span style="color:green">${status}</span></h1>
		</div>

		<section id="main-content">
			<g:form action="saveprofile" controller="companyDashboard" method="post" autocomplete="off">
			<div class="row">
				<!--todo in future will need translate company -->
				<g:render template="/template/companyConference"/>

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
									<h1>${company?.contactName}</h1>
									<br/>

										<fieldset>
											<div class="form-group">
												<div class="fieldcontain ${hasErrors(bean: company, field: 'contactName', 'error')} form-group">
													<label for="contact-name">Contact Name</label>
													<g:textField name='contactName' value='${company?.contactName}' class="form-control" id="contact-name" maxlength="255"/>
													<g:renderErrors bean="${company}" as="list" field="contactName"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: company, field: 'name', 'error')} form-group">
													<label for="name-name">Company Name</label>
													<g:textField name='name' value='${company?.name}' class="form-control" id="name-name" maxlength="255"/>
													<g:renderErrors bean="${company}" as="list" field="name"/>
												</div>
												<div class="fieldcontain ${hasErrors(bean: company, field: 'location', 'error')} form-group">
													<label for="location-name">Location</label>
													<g:textField name='location' value='${company?.location}' class="form-control"  id="location-name" maxlength="255"/>
													<g:renderErrors bean="${company}" as="list" field="location"/>
												</div>

												<div class="fieldcontain ${hasErrors(bean: company, field: 'logo', 'error')} form-group">
													<label for="logo-name">Logo</label>
													<g:textField name='logo' value='${company?.logo}' class="form-control" id="logo-name" maxlength="255"/>
													<g:renderErrors bean="${company}" as="list" field="logo"/>
												</div>

												<div class="fieldcontain ${hasErrors(bean: company, field: 'website', 'error')} form-group">
													<label for="website-name">Your website</label>
													<g:textField name='website' value='${company?.website}' class="form-control" id="website-name"/>
													<g:renderErrors bean="${company}" as="list" field="website"/>
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
								<div class="fieldcontain ${hasErrors(bean: company, field: 'fbInfo', 'error')} form-group">
									<label for="fb-name">Facebook name</label>
									<g:textField name='fbInfo' value='${company?.fbInfo}' class="form-control" id="fb-name" maxlength="255"/>
									<g:renderErrors bean="${company}" as="list" field="fbInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: company, field: 'twInfo', 'error')} form-group">
									<label for="tw-name">Twitter name</label>
									<g:textField name='twInfo' value='${company?.twInfo}' class="form-control" id="tw-name" maxlength="255"/>
									<g:renderErrors bean="${company}" as="list" field="twInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: company, field: 'gInfo', 'error')} form-group">
									<label for="g-name">Google+ name</label>
									<g:textField name='gInfo' value='${company?.gInfo}' class="form-control" id="g-name" maxlength="255"/>
									<g:renderErrors bean="${company}" as="list" field="gInfo"/>
								</div>
								<div class="fieldcontain ${hasErrors(bean: company, field: 'ytInfo', 'error')} form-group">
									<label for="yt-name">YouTube name</label>
									<g:textField name='ytInfo' value='${company?.ytInfo}' class="form-control" id="yt-name" maxlength="255"/>
									<g:renderErrors bean="${company}" as="list" field="ytInfo"/>
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


