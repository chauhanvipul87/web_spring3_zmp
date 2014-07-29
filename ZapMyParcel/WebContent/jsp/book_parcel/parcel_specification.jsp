<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param_country =='' }">
		<jsp:forward page="/index.html"></jsp:forward>
</c:if> 

<jsp:include page="../home/header.jsp"></jsp:include>

<!--container starts-->
<div id="container">
  <div class="raw">
    <jsp:include page="../home/left_panel.jsp"></jsp:include>
    <div id="middle_pnl">
      <div class="module">
      	<div id="error"></div>
       <h5>Sending to (${param_country})</h5>
       <center>
         <div id="ajax_loader">
				<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
					/> &nbsp; Processing...Please wait...
		</div>
		</center>
       <div class="mo_container">
       <input type="hidden" name="country_id" id="country_id" value="${country_id}"/>
       <input type="hidden" name="country_name" id="country_name" value="${param_country}"/>
       <input type="hidden" name="user_key" id="user_key" value=""/>
       <%-- <input type="hidden" name="price" id="price" value="${param_price}"/> --%>
       
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
 						 <tr>
							<td colspan="3" align="center">
								<table border="0" cellpadding="3" >
									<c:choose>
											<c:when test="${param_weight !=''}">
												<tr>
													<td colspan="3" align="right">Weight :</td><td align="left"><input type="text" readonly="readonly" value="${param_weight}" id="weight" /> Kg</td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="3" align="right">Weight: <strong style="color: red;">*</strong></td><td align="left">
													<input type="text"  value="1" onfocus="if (this.value == '1') {this.value = '';}" onblur="if (this.value == '') {this.value = '1';}" onkeypress="return isNumberKey(event)"  id="weight" /> Kg
													</td>
												</tr>
											</c:otherwise>
									</c:choose>
									<c:if test="${param_price !=''}" >
										<tr>
											<td colspan="3" align="right">Price:</td><td align="left"><input type="text" value="${param_price}" readonly="readonly" id="main_price" /></td>
										</tr>
									</c:if>
									<tr>
										<td colspan="3" align="right">Quantity :<strong style="color: red;">*</strong></td>
										
										<td align="left" ><input type="text" value="1" id="quantity" onfocus="if (this.value == '1') {this.value = '';}" onblur="if (this.value == '') {this.value = '1';}" onkeypress="return onlyIntegerNumber(event)" /></td>
									</tr>
									<tr>
										<td colspan="3">&nbsp;</td><td align="left"><input class="orang_btn" type="button" onclick="addToCart();" value="Add to Cart" /></td>
									</tr>
								</table></td>
						</tr>
        </table>
        
       </div>
      </div>
      
      <br class="seperator_break">
      
      <div class="module">
        <h5>${param_country}</h5>
        <div class="mo_container">
          <p>${country_description}</p>
        </div>
      </div>
      
      <br class="seperator_break">
      
     <!--  <div class="module">
        <div class="mo_container">
          <p><input name="" type="checkbox" value=""> I have read and agree with zap my parcel <a href="#">terms and conditions</a></p>
          <p><input name="" type="checkbox" value=""> I would like to drip off the parcel to my <a href="#">nearest drop off point</a></p>
        </div>
      </div> -->
      
       <div id="item_cart"></div>
    </div>
    <jsp:include page="../home/right_panel.jsp"></jsp:include>
    <div class="clear"></div>
  </div>
</div>
<!--container ends--> 
<!-- ui-dialog -->
		<div id="dialog" title="Request Quote">
				<div id="msg" style="display: none;">
						<div class="error1">
						</div>
					</div>
			<center>
			
				<p style="line-height: 20px;" >
					The weight/dimensions you have selected are too big and we can't provide you the service right now but 
					you can request for the quote.Please fill in the form below and someone from our team will get
					 in touch  or call us on 0265 3928697 for a quick response. 
				</p>
				<br/>
				<table class="tabuler_forms" border="0"  cellspacing="3" width="100%"> 
					<tr>
						<th colspan="2">Please fill Request Quote Form </th>
					</tr>
					<tr>
						<td style="float: right;">Name :<strong style="color: red;">*</strong></td>
						<td><input type="text" name="quote_name" id="quote_name"  /></td>
					</tr>
					<tr>
						<td style="float: right;">Email :<strong style="color: red;">*</strong></td>
						<td><input type="text" name="quote_email" id="quote_email" /></td>
					</tr>
					<tr>
						<td style="float: right;">Contact No :<strong style="color: red;">*</strong></td>
						<td><input type="text" name="quote_contant_no" id="quote_contant_no"   onkeypress="return isNumberKey(event)"/></td>
					</tr>
					<tr>
						<td style="float: right;">Destination Country :<strong style="color: red;">*</strong></td>
						<td><input type="text" name="quote_destination_country" id="quote_destination_country" /></td>
					</tr>
					<tr>
						<td style="float: right;">Comments :</td>
						<td><textarea cols="20" rows="3" id="quote_comments"></textarea></td>
					</tr>
				</table>
			</center>
		</div>

<!--payment starts-->
<jsp:include page="../home/payment.jsp"></jsp:include>
<!--payment ends--> 

<!--links starts-->
<jsp:include page="../home/links.jsp"></jsp:include>
<!--links ends-->

<!--footer starts-->
<jsp:include page="../home/footer.jsp"></jsp:include>
<!--footer ends-->
<script type="text/javascript">
			$(function(){
				
				// Dialog
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Submit": function() {
							validateQuoteRequest();
						},
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});
				
			});
			
		    $(document).ready(function() {
		        //show cart.. details
		    	showCart("");
		    });


			
</script>


</body>
</html>
