<div class="col-md-12" id="conference-div" style="display:none">
	<section class="panel">
		<div style="padding:10px">
			<r:require modules="jquery"/>
			<r:require modules="bootstrap"/>
			<r:layoutResources />
			<link rel="stylesheet" href="../static/css/neu.css">
			<script src="../static/js/tokbox/TB.min.js"></script>
			<script src="../static/js/tokbox/clientPart.js"></script>

			<div class="row">
				<div class="col-md-12">
					<section class="panel"  id="seebActionBox">

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
		</div>
	</section>
	<r:layoutResources />
	<script type="text/javascript">
		callFunctional.initTok({apiKey:'${ apiKey }', sessionId:'${ sessionId }', token:'${ token }'});
		callFunctional.initAreaButton();
		callFunctional.initBeforeUnload();
	</script>
</div>





