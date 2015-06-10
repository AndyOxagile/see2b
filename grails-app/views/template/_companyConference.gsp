<div class="col-md-12" id="conference-div" style="display:none">
	<style>
		#customer_list.showAll .offline{
			display: block;
		}

		#customer_list .offline{
			display: none;
		}
	</style>
	<section class="panel">
		<div style="padding:10px">
			<link rel="stylesheet" href="../static/css/neu.css">
			<script src="../static/js/tokbox/TB.min.js"></script>
			<script src="../static/js/tokbox/companyPart.js"></script>
			<script>
				initUserStateTimer('${outerDivId}');
			</script>
			<div class="row">
				<div class="col-md-12">
					<section class="panel">
						<input type="button" value="stop" id="stopCall" style="display: none" onclick="stopCallSignal()"/>
						<g:if test="${outerDivId == null}">
							<input type="checkbox" onClick="showAllUsers(this.checked)"></input> Show all users
							<div id="customer_list">
							</div>
							<hr/>
						</g:if>
						<div id="video_content">
						</div>
					</section>
				</div>
			</div>
		</div>
	</section>
</div>





