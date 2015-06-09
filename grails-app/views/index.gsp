<!DOCTYPE html>
<html>
	<head>
	</head>
	<r:require modules="bootstrap"/>
	<r:layoutResources />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'styles.css')}" type="text/css">
	<body>
		%{--<g:render template="/template/user/header"/>--}%



		springSecurityService.isLoggedIn()
	main
	<oauth:connect provider="facebook" id="facebook-connect-link">Facebook</oauth:connect>
	Logged with facebook?
	<s2o:ifLoggedInWith provider="facebook">yes</s2o:ifLoggedInWith>
	<s2o:ifNotLoggedInWith provider="facebook">no</s2o:ifNotLoggedInWith>

	Local
	-<s2o:ifLoggedInWith provider="local">yes</s2o:ifLoggedInWith>
-	<s2o:ifNotLoggedInWith provider="local">no</s2o:ifNotLoggedInWith>

		<r:layoutResources />
	</body>
</html>
