<div id="payment_opt">
	<div class="raw">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="68" align="left" valign="middle"
					style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FFF;">Payment
					Options<br>
					<a href="javascript:void(0);"><img src="images/paypal.png"></a>
					<a href="javascript:void(0);"><img src="images/master_card.png"></a>
					<a href="javascript:void(0);"><img src="images/mastereo.png"></a>
					<a href="javascript:void(0);"><img src="images/visa.png"></a>
				</td>
				<td align="right" valign="middle">
				<a href="http://www.investorsinpeople.co.uk/"><img src="images/accreditation_1.png"></a>
				<a href="http://www.iso-14001.org.uk/"><img	src="images/accreditation_2.png"></a>
				<a href="http://www.iso.org/"><img src="images/accreditation_3.png"></a>
				<a href="http://www.mhra.gov.uk/"><img src="images/accreditation_4.png"></a>
				<a href="http://www.dma.org.uk/"><img src="images/accreditation_5.png"></a>
				</td>
			</tr>
		</table>
	</div>
</div>


<!-- ui-dialog for track parcel -->
	<div id="dialog_trackParcel" title="Status Of Your Parcel"></div>

<!-- ui-dialog for dropoffagentList -->	
<div id=dialog_dropoffagentList title="List of Drop Off Agent Details"></div>	


<!-- ui-dialog for dropoffagentList -->	
<div id=dialog_cartDetails title="List Items in Cart"></div>	

	

<script type="text/javascript">
			$(function(){
				// Dialog for drop of agent
				$('#dialog_dropoffagentList').dialog({
					autoOpen: false,
					width: 900,
					buttons: {
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});					
				
				// Dialog for Track Parcel
				$('#dialog_trackParcel').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Cancel": function() {
							$(this).dialog("close");
						},
					}
				});
				
				// Dialog for dialog_cartDetails
				$('#dialog_cartDetails').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Cancel": function() {
							$(this).dialog("close");
						},
					}
				});
				
			});
</script>			