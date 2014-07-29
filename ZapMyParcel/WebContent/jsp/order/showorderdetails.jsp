<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../home/header.jsp"></jsp:include>

<!--container starts-->
<div id="container">
  <div class="raw">
    <jsp:include page="../home/left_panel.jsp"></jsp:include>
    <div id="middle_pnl">
   		 <div id="error"></div>
    	<center>
	 		<div id="ajax_loader">
					<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
						/> &nbsp; Processing...Please wait...
			</div>
		</center>
		<div id="order_accordion">
    		<div>
				<h2><a href="javascript:void(0);">Sender's Details</a></h2>
				<div>
				<c:choose>
					<c:when test="${fn:length(order_data) > 0 }">        
				    <c:forEach var="s" items="${order_data}">
						<p style="line-height: 20px;">${s.s_name}<br>
		                  ${s.s_addressLine1}<br>
		                  <c:if test="${s.s_addressLine2 != null and s.s_addressLine2 !=''}">
		                 	  ${s.s_addressLine2}<br>
		                  </c:if>
		                  <c:if test="${s.s_addressLine3 !=null and s.s_addressLine3 !=''}">
		                 	  ${s.s_addressLine3}<br>
		                  </c:if>
		                  ${s.s_postcode}<br>
		                  ${s.s_town}<br>
		                  ${s.s_city}<br>
		                  ${s.s_county}<br>
		                  <img style="vertical-align: middle;" src="images/phone_icon.gif">&nbsp; ${s.s_phone}<br>
		              	 <img style="vertical-align: middle;" src="images/email.gif">&nbsp; ${s.s_email}<br>	    
					</c:forEach>
					</c:when>
					<c:otherwise>
						<p>No Record found.</p>
					</c:otherwise>
				</c:choose>
				</div>
		    </div>
		    <div>
				<h2><a href="javascript:void(0);">Delivery Details</a></h2>
				<div>
				 <c:choose>
					<c:when test="${fn:length(delivery_addressList) > 0 }">        
				    <c:forEach var="d" items="${delivery_addressList}">	
				       <table width="100%" border="0" cellspacing="2" cellpadding="5" class="tabuler_forms">
				    	 <tr><th>Country :${d.country_code}:</th></tr>
				    	 <tr><td>
				    	 <p style="line-height: 20px;">${d.d_name}<br>
			                  ${d.d_addressLine1}<br>
			                  <c:if test="${d.d_addressLine2 != null and d.d_addressLine2 !=''}">
			                 	  ${d.d_addressLine2}<br>
			                  </c:if>
			                  <c:if test="${d.d_addressLine3 !=null and d.d_addressLine3 !=''}">
			                 	  ${d.d_addressLine3}<br>
			                  </c:if>
			                  ${d.d_postcode}<br>
			                  ${d.d_town}<br>
			                  ${d.d_city}<br>
		                  <img style="vertical-align: middle;" src="images/phone_icon.gif">&nbsp; ${d.d_phone}<br></p>
				    	 
				    	 </td></tr>
				    	</table>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<p>No Record found.</p>
					</c:otherwise>
				</c:choose>		
				</div>
		    </div>
		    <div>
				<h2><a href="javascript:void(0);">Parcel Details</a></h2>
				<div>
				<c:choose>
					<c:when test="${fn:length(order_data) > 0 }">        
				    <c:forEach var="pd" items="${order_data}">
				    <table width="100%" border="0" cellspacing="2" cellpadding="5" class="tabuler_forms">
				    	 <tr><th colspan="2">Parcel Details:</th>
				    	 </tr>	
						  <tr>
						  		<td align="right"><strong>Package Contains :</strong> </td>
						  		<td>${pd.package_content}</td>
						  </tr>
						    <tr>
						  		<td align="right"><strong>Value of Parcel :</strong></td>
						  		<td>&#163;${pd.value_parcel}</td>
						  </tr>
						   <tr>
						  		<td align="right"><strong>Insurance Cover :</strong></td>
						  		<td><c:if test="${pd.insurance_cover == '0.0' }">
						    			Free Standard Cover upto &#163; 50
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '3.75' }">
						    			Cover upto &#163; 100
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '7.5' }">
						    			Cover upto &#163; 200
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '9.38' }">
						    			 Cover upto &#163; 250
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '18.75' }">
						    			Cover upto &#163; 500
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '37.5' }">
						    			 Cover upto &#163; 1000
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '56.25' }">
						    			Cover upto &#163; 1500
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '75.0' }">
						    			 Cover upto &#163; 2000
						    		</c:if>
						    		<c:if test="${pd.insurance_cover == '93.75' }">
						    		 	 Cover upto &#163; 2500
						    		</c:if>
						    	</td>
						  </tr>
				    </table>
				    <br>
				    </c:forEach>
				    </c:when>
				    <c:otherwise>
						<p>No Record found.</p>
					</c:otherwise>
				 </c:choose>
				</div>
		    </div>
		    <div>
				<h2><a href="javascript:void(0);">Delivery Instructions </a></h2>
				<div>
				<c:choose>
					<c:when test="${fn:length(order_data) > 0 }">        
				    <c:forEach var="d" items="${order_data}">
							<p>${d.delivery_Instructions}</p>	
					</c:forEach>
					</c:when>    
					<c:otherwise>
						<p>No Record found.</p>
					</c:otherwise>
				  </c:choose>  
				</div>
		    </div>
		    <c:choose>
					<c:when test="${fn:length(agentDetails) > 0 }">
					 <div>
							<h2><a href="javascript:void(0);">Drop Off Address Details</a></h2>
							<div>
								<c:forEach var="d" items="${agentDetails}">	
							<p style="line-height: 20px;">${d.agent_name} [${d.agent_code }]<br>
			                  ${d.address1}<br>
			                  <c:if test="${d.address2 != null and d.address2 !=''}">
			                 	  ${d.address2}<br>
			                  </c:if>
			                  <c:if test="${d.address3 !=null and d.address3 !=''}">
			                 	  ${d.address3}<br>
			                  </c:if>
			                  ${d.Postcode}<br>
			                  ${d.town}<br>
			                  ${d.city}<br>
			                  ${d.County }<br>
			                  ${d.country_name} [${d.country_code}]<br>
		                  <img style="vertical-align: middle;" src="images/phone_icon.gif">&nbsp; ${d.Phone}<br>
		                  <img style="vertical-align: middle;" src="images/email.gif">&nbsp; ${d.Email}<br>	
					</c:forEach>
							</div>
		    		  </div>
		  	 		</c:when>
 			  	</c:choose>
		    
		</div>	
		 <div class="module" style="padding:5px; text-align:right;">
		 	<c:choose>
					<c:when test="${fn:length(order_data) > 0 }">        
				    <c:forEach var="d" items="${order_data}">
				   	 <table width="100%" border="0" cellspacing="2" cellpadding="2" class="tabuler_forms">
						 <tr>
					  		<td align="right" width="90%"><strong>Sub Total :</strong> </td>
						  		<td  align="left"><strong>&#163; ${d.sub_total}</strong></td>
						  </tr>
						  <tr>
						  		<td align="right" width="90%"><strong>Insurance :</strong> </td>
						  		<td  align="left">
								  			<c:if test="${d.insurance_cover == '0.0' }">
								    			<strong>&#163; 0.00</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '3.75' }">
								    			<strong>&#163; 03.75</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '7.5' }">
								    			<strong>&#163; 07.50</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '9.38' }">
								    			 <strong>&#163; 09.38</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '18.75' }">
								    			<strong>&#163; 18.75</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '37.5' }">
								    			 <strong>&#163; 37.50</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '56.25' }">
								    			<strong>&#163; 56.25</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '75.0' }">
								    			 <strong>&#163; 75.00</strong>
								    		</c:if>
								    		<c:if test="${d.insurance_cover == '93.75' }">
								    		 	 <strong>&#163; 93.75</strong>
								    		</c:if>
						     </td>
						  </tr>
			    		  <tr>
						  		<td align="right" width="90%"><strong>Vat@20% :</strong> </td>
						  		<td  align="left"><strong>&#163; ${d.vat}</strong></td>
						  </tr>
						   <tr>
						  		<td align="right" width="90%"><strong>Total :</strong> </td>
						  		<td  align="left"><strong>&#163; ${d.total_price}</strong></td>
						  </tr>
				    </table>
				    </c:forEach>
					</c:when>   
			</c:choose> 
			      
         </div> 
		<table border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;" width="100%">
		  <tr>
		    <td style="float: right;"><span class="orang_btn" ><a href="buymore.html">Buy&nbsp;More</a></span>
		    &nbsp;&nbsp;&nbsp;<span class="green_btn" style="line-height:24px;margin-left: 10px;"><a href="javascript:void(0);" onclick="showPayPayDetailForm();">Checkout</a></span>
		              </td>
		  </tr>
		</table>
		<input type="hidden" value="${itemInBasket}" id="basketsize" />
		<input type="hidden" value="${order_id}" id="order_id" />
		
    </div>
    <jsp:include page="../home/right_panel.jsp"></jsp:include>
    <div class="clear"></div>
  </div>
</div>
<!--container ends--> 

<!-- dialog_cardTips start -->

<div id="dialog_cardTips" title="What is Security Code?">
	<center>
		<br/>
			<p style="line-height: 20px;">For your safety and security, zapmyparcel.com requires that you enter your card verification value (CVV) code when paying via credit card. The CVV code is a three-digit number which appears on the back of all Visa, Mastercard and Maestro Card. See below for samples. Credit card orders that do not include CVV codes will not be processed.</p>
	    <br/>
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/visa_cvv.png" ></td>
            <td><img src="images/maestro_cvv.png" ></td>
            <td><img src="images/master_card_cvv.gif"></td>
          </tr>
        </table>	
	
	</center>

</div>
<!-- dialog_cardTips end -->

<!-- ui-dialog -->
		<div id="dialog" title="Please Enter Your Card Details">
			<center>
				<br/>
		          <table width="100%" border="0" cellspacing="0" cellpadding="3">
		            <tr>
		              <td align="right"><strong>Card Type : <b>*</b></strong></td>
		              <td><div class="styled-select">
		                  <select id="CardType">
		                  		  <option value="0">Select Card Type</option>
								  <option value="Visa">Visa</option>
								  <option value="Discover">Discover</option>
								  <option value="Maestro">Maestro</option>
								  <option value="MasterCard">MasterCard</option>
								  <option value="Solo">Solo</option>
								  <option value="Switch">Switch</option>
								  <option value="VisaElectron">Visa Electron</option>
							</select>
		                </div></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Credit Card Number : <b>*</b></strong></td>
		              <td><input type="text" name="card_no" id="card_no" value="" onkeypress="return onlyIntegerNumber(event)"></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>First Name : <b>*</b></strong></td>
		              <td><input type="text" name="fname" id="fname" value=""></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Last Name : <b>*</b></strong></td>
		              <td><input type="text" name="lname" id="lname" value=""></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Start Date : <b>*</b></strong></td>
		              <td><input size="6" type="text" name="smonth" id="smonth" value="MM" onfocus="if (this.value == 'MM') {this.value = '';}" onblur="if (this.value == '') {this.value = 'MM';}" onkeypress="return onlyIntegerNumber(event)" >
		                <input size="6" type="text" name="syear" id="syear" value="YYYY" onfocus="if (this.value == 'YYYY') {this.value = '';}" onblur="if (this.value == '') {this.value = 'YYYY';}" onkeypress="return onlyIntegerNumber(event)"></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Expiration Date : <b>*</b></strong></td>
		              <td><input size="6" type="text" name="emonth" id="emonth" value="MM" onfocus="if (this.value == 'MM') {this.value = '';}" onblur="if (this.value == '') {this.value = 'MM';}" onkeypress="return onlyIntegerNumber(event)">
		                <input  size="6" type="text" name="eyear" id="eyear"  value="YYYY" onfocus="if (this.value == 'YYYY') {this.value = '';}" onblur="if (this.value == '') {this.value = 'YYYY';}" onkeypress="return onlyIntegerNumber(event)"></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Security Code : <b>*</b></strong></td>
		              <td><input type="text" name="cvv_no" id="cvv_no" value="" onkeypress="return onlyIntegerNumber(event)">&nbsp;<a href="javascript:void(0);" onclick="showCardTips();">What's this?</a></td>
		            </tr>
		            <tr>
		              <td align="right"><strong>Issue No :</strong></td>
		              <td><input type="text" name="issue_no" id="issue_no"></td>
		            </tr>
		          </table>
			</center>
		</div>

<!-- for display success message with payment  details -->		
<form id="frm_paymentConfrim" name="frm_paymentConfrim" action="paymentconfirm.html">
</form>


<!--payment starts-->
<jsp:include page="../home/payment.jsp"></jsp:include>
<!--payment ends--> 

<!--links starts-->
<jsp:include page="../home/links.jsp"></jsp:include>
<!--links ends-->

<!--footer starts-->
<jsp:include page="../home/footer.jsp"></jsp:include>
<!--footer ends-->
<script type="text/javascript" src="developerjs/creditcard.js"></script>

<script type="text/javascript">
			$(function(){
				 // Accordion
				$("#order_accordion").accordion({ header: "h2",
					 autoHeight: false,
					 navigation: true	
				});
				 
				 
				// Dialog for paypay details
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Submit": function() {
							ValidatePaymentDetails();
						},
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});
				
				
				// Dialog for paypay details
				$('#dialog_cardTips').dialog({
					autoOpen: false,
					width: 650,
					buttons: {
						"OK": function() {
							$(this).dialog("close");
						}
					}
				});
				
				
			}); 
			
			
			
			 $(document).ready(function() {
			        //show cart.. details
			        $("#itemInBasket").html("Items&nbsp;in&nbsp;Basket&nbsp;("+$("#basketsize").val()+")");
			    	
			    });
			
</script>
</body>
</html>
