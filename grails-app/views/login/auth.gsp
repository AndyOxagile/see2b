<html lang="en"><!--<![endif]--><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>See2B Real Time Communication</title>
	<meta name="description" content="Maximize sales and delight customers with See2B's real time video communication platform" />
	<meta name="keywords" content="video, video chat, instant message, video call, skype, snapchat, appear, google hangouts, hangouts, phone, audio, screen share, real time video, communication, see2b, b2b, b2c, c2b, business support, customer service, customer support, youtube, twitch, live stream, live video" />
	<meta name="author" content="See2b">
	<meta name="robots" content="index, follow" />
	<!-- Meta -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="http://themes.3rdwavemedia.com/velocity/1.5.2/favicon.ico">
	<link href="../static/html/css/css" rel="stylesheet" type="text/css">
	<link href="../static/html/css/css(1)" rel="stylesheet" type="text/css">
	<!-- Global CSS -->
	<link rel="stylesheet" href="http://themes.3rdwavemedia.com/velocity/1.5.2/assets/plugins/bootstrap/css/bootstrap.min.css">
	<!-- Plugins CSS -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://themes.3rdwavemedia.com/velocity/1.5.2/assets/plugins/flexslider/flexslider.css">
	<!-- Theme CSS -->
	<link id="theme-style" rel="stylesheet" href="../static/html/css/styles.css">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style id="fit-vids-style">.fluid-width-video-wrapper{width:100%;position:relative;padding:0;}.fluid-width-video-wrapper iframe,.fluid-width-video-wrapper object,.fluid-width-video-wrapper embed {position:absolute;top:0;left:0;width:100%;height:100%;}</style></head>

<body class="login-page access-page has-full-screen-bg" data-twttr-rendered="true">
<div class="upper-wrapper">
	<!-- ******HEADER****** -->
	<header class="header">
		<div class="container">
			<h1 class="logo">
				<a href="../index.html"><span class="logo-icon"></span><span class="text">See2B</span></a>
			</h1><!--//logo-->

		</div><!--//container-->
	</header><!--//header-->

<!-- ******Signup Section****** -->
	<section class="signup-section access-section section">
		<div class="container">
			<div class="row">
				<div class="form-box col-md-8 col-sm-12 col-xs-12 col-md-offset-2 col-sm-offset-0 xs-offset-0">
					<div class="form-box-inner">
						<h2 class="title text-center">Log in to See2B</h2>
						<div class="row">
							<div class="form-container col-md-5 col-sm-12 col-xs-12">
								<form action='${postUrl}' method='POST' id='loginForm' class="login-form" autocomplete='off'>
									<div class="form-group email">
										<label class="sr-only" for="login-email">Email or username</label>
										<input type='text' class='form-control login-email' name='j_username' id='login-email' placeholder="Email or username"/>
									</div><!--//form-group-->
									<div class="form-group password">
										<label class="sr-only" for="login-password">Password</label>
										<input type='password' class="form-control login-password" placeholder="Password" name='j_password' id='login-password'/>
										<p class="forgot-password"><a href="http://themes.3rdwavemedia.com/velocity/1.5.2/reset-password.html">Forgot password?</a></p>
									</div><!--//form-group-->

									<input type='submit' id="submit" value='Log in' class="btn btn-block btn-cta-primary"/>

									<div class="checkbox remember">
										<label>
											<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
											Remember me
										</label>
									</div><!--//checkbox-->
									<p class="lead">Don't have a See2B account yet? <br><a class="signup-link" href="../signup.html">Create your account now</a></p>
								</form>

								<script type='text/javascript'>
									<!--
									(function() {
										document.forms['loginForm'].elements['j_username'].focus();
									})();
									// -->
								</script>

							</div><!--//form-container-->
							<div class="social-btns col-md-5 col-sm-12 col-xs-12 col-md-offset-1 col-sm-offset-0 col-sm-offset-0">
								<div class="divider"><span>Or</span></div>
								<ul class="list-unstyled social-login">

									<li><oauth:connect  class="facebook-btn btn" provider="facebook" id="facebook-connect-link"><i class="fa fa-facebook"></i>Log in with Facebook</oauth:connect></li>
									<li><oauth:connect  class="twitter-btn btn" provider="linkedin" id="linkedin-connect-link"><i class="fa fa-linkedin"></i>Log in with LinkedIn</oauth:connect></li>

									<li><oauth:connect  class="google-btn btn" provider="google" id="google-connect-link"><i class="fa fa-google-plus"></i>Log in with Google</oauth:connect></li>



								</ul>
								<p class="note">Don't worry, we won't post anything without your permission.</p>
							</div><!--//social-login-->
						</div><!--//row-->
					</div><!--//form-box-inner-->
				</div><!--//form-box-->
			</div><!--//row-->
		</div><!--//container-->
	</section><!--//signup-section-->
</div><!--//upper-wrapper-->

<!-- ******FOOTER****** -->
<footer class="footer">
	<div class="footer-content">
		<div class="container">
			<div class="row">
				<div class="footer-col links col-md-2 col-sm-4 col-xs-12">
					<div class="footer-col-inner">
						<h3 class="title">About us</h3>
						<ul class="list-unstyled">
							<li><a href="../about.html"><i class="fa fa-caret-right"></i>Who we are</a></li>
							<li><a href="../about.html"><i class="fa fa-caret-right"></i>Press</a></li>
							<li><a href="../about.html"><i class="fa fa-caret-right"></i>Blog</a></li>
							<li><a href="../about.html"><i class="fa fa-caret-right"></i>Jobs</a></li>
							<li><a href="../about.html"><i class="fa fa-caret-right"></i>Contact us</a></li>
						</ul>
					</div><!--//footer-col-inner-->
				</div><!--//foooter-col-->
				<div class="footer-col links col-md-2 col-sm-4 col-xs-12">
					<div class="footer-col-inner">
						<h3 class="title">Product</h3>
						<ul class="list-unstyled">
							<li><a href="../features.html"><i class="fa fa-caret-right"></i>How it works</a></li>
							<li><a href="../features.html"><i class="fa fa-caret-right"></i>API</a></li>
							<li><a href="../features.html"><i class="fa fa-caret-right"></i>Download Apps</a></li>
							<li><a href="../features.html"><i class="fa fa-caret-right"></i>Pricing</a></li>
						</ul>
					</div><!--//footer-col-inner-->
				</div><!--//foooter-col-->
				<div class="footer-col links col-md-2 col-sm-4 col-xs-12 sm-break">
					<div class="footer-col-inner">
						<h3 class="title">Support</h3>
						<ul class="list-unstyled">
							<li><a href="../index.html"><i class="fa fa-caret-right"></i>Help</a></li>
							<li><a href="../index.html"><i class="fa fa-caret-right"></i>Documentation</a></li>
							<li><a href="../index.html"><i class="fa fa-caret-right"></i>Terms of services</a></li>
							<li><a href="../index.html"><i class="fa fa-caret-right"></i>Privacy</a></li>
						</ul>
					</div><!--//footer-col-inner-->
				</div><!--//foooter-col-->
				<div class="footer-col connect col-md-6 col-sm-12 col-xs-12">
					<div class="footer-col-inner">
						<ul class="social list-inline">
							<li><a href="http://twitter.com" target="_blank"><i class="fa fa-twitter"></i></a></li>
							<li><a href="http://facebook.com"><i class="fa fa-facebook"></i></a></li>
							<li><a href="http://google.com"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="http://instagram.com"><i class="fa fa-instagram"></i></a></li>
						</ul>
						<div class="form-container">
							<p class="intro">Stay up to date with the latest news and offers from See2B</p>
							<form class="signup-form navbar-form">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Enter your email address">
								</div>
								<button type="submit" class="btn btn-cta btn-cta-primary">Subscribe Now</button>
							</form>
						</div><!--//subscription-form-->
					</div><!--//footer-col-inner-->
				</div><!--//foooter-col-->
				<div class="clearfix"></div>
			</div><!--//row-->
			<div class="row has-divider">
				<div class="footer-col download col-md-6 col-sm-12 col-xs-12">
					<div class="footer-col-inner">
						<h3 class="title">Mobile apps</h3>
						<ul class="list-unstyled download-list">
							<li><a class="btn btn-ghost" href="#"><i class="fa fa-apple"></i><span class="text">Download for iOS</span> </a></li>
							<li><a class="btn btn-ghost" href="#"><i class="fa fa-android"></i><span class="text">Download for Android</span></a></li>
							<li><a class="btn btn-ghost" href="#"><i class="fa fa-windows"></i><span class="text">Windows coming soon...</span></a></li>
						</ul>
					</div><!--//footer-col-inner-->
				</div><!--//download-->
				<div class="footer-col contact col-md-6 col-sm-12 col-xs-12">
					<div class="footer-col-inner">
						<h3 class="title">Contact us</h3>
						<p class="adr clearfix">
							<i class="fa fa-map-marker pull-left"></i>
							<span class="adr-group pull-left">
								<span class="street-address">417 N. 8th St. #201</span><br>
								<span class="region">Philadelphia, PA. 19123</span><br>
							</span>
						</p>
						<p class="tel"><i class="fa fa-phone"></i>215-396-8577</p>
						<p class="email"><i class="fa fa-envelope-o"></i><a href="mailto:support@see2b.com">support@see2b.com</a></p>


					</div><!--//footer-col-inner-->
				</div><!--//contact-->
			</div>
		</div><!--//container-->
	</div><!--//footer-content-->
	<div class="bottom-bar">
		<div class="container">
			<small class="copyright">Copyright @ 2015 <a href="http://see2b.com/" target="_blank">See2B LLC.</a></small>
		</div><!--//container-->
	</div><!--//bottom-bar-->
</footer><!--//footer-->

<!-- Video Modal -->
<div class="modal modal-video" id="modal-video" tabindex="-1" role="dialog" aria-labelledby="videoModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 id="videoModalLabel" class="modal-title sr-only">Video Tour</h4>
			</div>
			<div class="modal-body">

			</div><!--//modal-body-->
		</div><!--//modal-content-->
	</div><!--//modal-dialog-->
</div><!--//modal-->


<!-- Javascript -->
<script type="text/javascript" src="../static/html/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../static/html/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="../static/html/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../static/html/js/bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript" src="../static/html/js/back-to-top.js"></script>
<script type="text/javascript" src="../static/html/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="../static/html/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="../static/html/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="../static/html/js/main.js"></script>

<!-- Vimeo video API -->
<script src="../static/html/js/froogaloop2.min.js"></script>
<script type="text/javascript" src="../static/html/js/vimeo.js"></script>





<div id="topcontrol" title="Scroll Back to Top" style="position: fixed; bottom: 5px; right: 5px; opacity: 0; cursor: pointer;"><i class="fa fa-angle-up"></i></div></body></html>

