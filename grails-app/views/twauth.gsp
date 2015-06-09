<div>
	   !!




	   <oauth:connect provider="facebook" id="facebook-connect-link">Facebook</oauth:connect>
	   Logged with facebook?
	   <s2o:ifLoggedInWith provider="facebook">yes</s2o:ifLoggedInWith>
	   <s2o:ifNotLoggedInWith provider="facebook">no</s2o:ifNotLoggedInWith>

	    !!
</div>