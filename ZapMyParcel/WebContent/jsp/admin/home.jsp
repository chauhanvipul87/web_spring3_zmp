<jsp:include page="../home/admin_header.jsp"></jsp:include>
<!--middle container starts-->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main_container">
	<div class="raws">
		<!--grid starts here-->
		<div align="center">
			From Date : <input type="text" name="fromDates" id="fromDates" value=""/>
			To Date : <input type="text" name="toDates" id="toDates" value=""/>
			<input type="button" onclick="getAdminReport();" name="adminReport" id="adminReport" value="View Report"/>
		</div><br/>
		<div id="search_div" style="display: none;">
		<form>
			<table width="100%" border="0" cellspacing="0" cellpadding="7" class="s_grid">
		  <tr>
		    <td><b>Date :</b>
		      <input style="float:right;" type="text" name="search_date" id="search_date" /></td>
		    <td><b>ZMP Ref : </b>
		      <input style="float:right;" type="text" name="search_zmp_ref" id="search_zmp_ref" /></td>
		    <td><b>Payment Status :</b>
		    <div style="float:right;width: 158px;" class="styled-select">
		    	<select id="search_paypal_status">
		    			<option value="0" >Select PayPal Status</option>
		    			<option value="Success">Success</option>
		    			<option value="Failure">Failure</option>
		    			<option value="SuccessWithWarning">SuccessWithWarning</option>
		    			<option value="FailureWithWarning">FailureWithWarning</option>
		    	</select>
		    </div>
		  </tr>
		  <tr>
		    <td><b>Name :</b>
		      <input style="float:right;" type="text" name="search_name" id="search_name" /></td>
		    <td><b>Tracking No :</b>
		      <input style="float:right;" type="text" name="search_tracking_no" id="search_tracking_no" /></td>
		    <td><b>Insurance :</b>
		      	<div style="float:right;width: 158px;" class="styled-select">
			    <select id="search_Insurance" name="search_Insurance">
			    	<option value="0" >Select Insurance Type</option>
		    			<option value="Yes">Yes</option>
		    			<option value="No">No</option>
			    </select>
			   </div> </td>  
		  </tr>
		  <tr>
		    <td><b>Order Status :</b>
		     <div style="float:right;width: 158px;" class="styled-select">
			    <select id="search_orderstatus" name="search_orderstatus">
			    
			 			   <option value="0" selected="selected">Select Order Status</option>
	              			<option value="To be Collected">To be Collected</option>
	               			<option value="notified">Notified</option>
	               			<option value="enroute">Enroute</option>
	               			<option value="delivered">Delivered</option>
	               			<!-- ADDED NEW START -->
		               			<option value="card_left">Card Left</option>
		               			<option value="check_address">Check Address</option>
		               			<option value="refused">Refused</option>
		               			<option value="rts">RTS (Return to Sender)</option>
	               			<!-- ADDED NEW END -->
				</select>
			</div></td>
			 <td><b>Carrier : </b>
		    <div style="float:right;width: 158px;" class="styled-select">
			    <select id="search_carrier" name="search_carrier">
			 			   <option value="0" selected="selected">Select Carrier</option>
				 			   <c:forEach var="c" items="${carriers}">
									<option value="${c.carrier_id}">${c.carrier_name }</option>				 			   		
				 			   </c:forEach>
				</select>
			</div></td>
			 <td><b>Type :</b>
		      <input style="float:right;" type="text" name="search_type" id="search_type" /></td> 	
		  </tr>
		  <tr>
		    <td colspan="3" align="center">
		     <input style="padding:5px; border-radius:4px;" class="btn" type="button" name="button4" id="button4" value="Search" 
		     	onclick="optimizedRecord();"/>
		     	  <input style="padding:5px; border-radius:4px;" class="btn" type="reset" name="button4" id="button4" value="Reset"/>
		    </td>
		  </tr>
          </table>
			<br />
			</form>
		</div>
	
		
		<div class="grid" id="reportResponse"></div>
		<div id="dialog" title="Order Details"></div>
	</div>
</div>
<!--middle container ends-->
<jsp:include page="../home/admin_footer.jsp"></jsp:include>
</div>
<script type="text/javascript" src="developerjs/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	now = new Date();
	var day = now.getDate();
	if (now.getMonth() == 0) {
	    before = new Date(now.getFullYear() - 1, 11, day);
	} else {
		before = new Date(now.getFullYear(), now.getMonth() - 1, day);
	}
	$("#fromDates").datepicker({
		dateFormat: "yy-mm-dd",
	});
	$("#fromDates").datepicker("setDate", before); 
	$("#toDates").datepicker({
		dateFormat: "yy-mm-dd",
	});
	$("#toDates").datepicker("setDate", now);
});

$(function(){

	// Dialog for view OrderDeails
	$('#dialog').dialog({
		autoOpen: false,
		width: 1000,
		buttons: {
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	
}); 

</script>
</body>
</html>
