<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../home/header.jsp"></jsp:include>

<!--container starts-->
<div id="container">
  <div class="raw">
   <jsp:include page="../home/left_panel.jsp"></jsp:include>
    
    <div id="middle_pnl">
    	<div id="error"></div>
    	<div id="msg" style="display: none;">
						<div class="error1">
						</div>
					</div>
    	<center>
        	 <div id="ajax_loader">
				<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
					/> &nbsp; Processing...Please wait...
			 </div>
		</center>	
    		<div id="accordion">
    		<c:if test="${sessionScope.buymore == null}">
    		<div>
				<h2><a href="javascript:void(0);">Enter Sender's Details (Step 1 of 3)</a></h2>
				<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="3" class="tabuler_forms">
							<tr>
								<th colspan="2">Sender's Details</th>
							</tr>
							<tr>
								<td align="right" width="30%">Name:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="sname" id="sname"></td>
							</tr>
							<tr id="appendAddress">
								<td  align="right">Postcode:<strong style="color: red;">*</strong></td>
								<td>
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td style="padding-right: 10px;"><input type="text"
												name="spostcode" id="spostcode">
											</td>
											<td><span class="orang_btn"><a href="javascript:void(0);" onclick="findAddressByPostCode('s')">Find Address</a>
											</span>
											</td>
										</tr>
									</table></td>
							</tr>
							</tr>
							<tr>
								<td align="right">Address 1:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="saddress1" id="saddress1"></td>
							</tr>	
							<tr>
								<td align="right">Address 2:</td>
								<td><input type="text" name="saddress2" id="saddress2"></td>
							</tr>
							<tr>
								<td align="right">Address 3:</td>
								<td><input type="text" name="saddress3" id="saddress3"></td>
							</tr>
							<tr>
								<td align="right">Town:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="stown" id="stown"></td>
							</tr>
							<tr>
								<td align="right">City:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="scity" id="scity"></td>
							</tr>
							<tr>
								<td align="right">County:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="scounty" id="scounty"></td>
							</tr>
							<tr>
								<td align="right">Phone:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="sphone" id="sphone" onkeypress="return onlyIntegerNumber(event)" >
								</td>
							</tr>
							<tr>
								<td align="right">Email:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="semail" id="semail" >
								</td>
							</tr>
							<tr>
						    <td>&nbsp;</td>
						    <td><input type="button" value="Next" class="orang_btn" onclick="Screen2('1')"></td>
						  </tr>
						</table>
					</div>
			</div>
    		</c:if>

 			<div>
				<h2><a href="javascript:void(0);">Enter Delivery Details (Step 2 of 3)</a></h2>
				<div id="delivery_address_result">
				<div id="remaining_items">
						<input type="hidden" id="country_id" value="${country_id}" />
						<table width="100%" border="0" cellspacing="2" cellpadding="0" class="tabuler_forms">
						  <tr>
						    <th colspan="6">List of Completed Delivery Address  </th>
						    </tr>
						  <tr>
						  <tr>
							  <th align="center">Country</th>
				              <th align="center">Weight</th>
				              <th align="center">Quantity</th>
				              <th align="center">Price</th>
				              <th align="center">SubTotal</th>
				              <th align="center">Action</th>
				          </tr>
						<c:choose>
							<c:when test="${fn:length(completed_item) > 0}">  
										
								 	 <c:forEach var="c" items="${completed_item}">
								 	 	<tr>
								 	 	 		<td align="center">${c.country_code}</td>
								 	 		    <td align="center">${c.temp_weight}</td>
								 	 		    <td align="center">${c.temp_quantity}</td>
								 	 		    <td align="center">${c.temp_price}</td>
								 	 		    <td align="center">${c.temp_subtotal}</td>
								 	 		    <td align="center"><a href="javascript:void(0);" onclick="showDeliveryAddress('${c.country_id}');">Show Address</a></td>
								 		</tr>
								 	 </c:forEach>	
			   			    </c:when>
			   			    <c:otherwise>
			   			    	<tr><td align="center" colspan="6">No Record Found.</td></tr>
			   			    </c:otherwise>
					</c:choose>
					 </tr>
					 </table>
					 <br class="seperator_break">
				 </div>
				 <c:if test="${fn:length(current_itemDetails) > 0}">	
				  <div id="currentlyEnterdItem"> 
						<table width="100%" border="0" cellspacing="2" cellpadding="0" class="tabuler_forms">
						  <tr>
						    <th colspan="4">Currently Entering Delivery Details For Country : &nbsp; ${country_code} </th>
						    </tr>
						  <tr>
						<c:choose>
							<c:when test="${fn:length(current_itemDetails) > 0}">  
										<tr>
							              <th align="center">Weight</th>
							              <th align="center">Quantity</th>
							              <th align="center">Price</th>
							              <th align="center">SubTotal</th>
							            </tr>
								 	 <c:forEach var="c" items="${current_itemDetails}">
								 	 	<tr>
								 	 		 <td align="center">${c.temp_weight}</td>
								 	 		  <td align="center">${c.temp_quantity}</td>
								 	 		   <td align="center">${c.temp_price}</td>
								 	 		    <td align="center">${c.temp_subtotal}</td>
								 		</tr>
								 	 </c:forEach>	
								 	 
							<br class="seperator_break"> 
							<table width="100%" border="0" cellspacing="0" cellpadding="3" class="tabuler_forms">
									  <tr>
									    <th colspan="2">Enter Delivery Details For Country : &nbsp; ${country_code}</th>
									    </tr>
									  <tr>
										<td align="right" width="30%">Name:<strong style="color: red;">*</strong></td>
										<td><input type="text" name="dname" id="dname"></td>
									</tr>
									<tr id="appendAddress_delivery">
										<td  align="right">Postcode:<strong style="color: red;">*</strong></td>
										<td>
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td style="padding-right: 10px;"><input type="text"
														name="dpostcode" id="dpostcode">
													</td>
													<td><span class="orang_btn"><a href="javascript:void(0);" onclick="findAddressByPostCode('d')">Find Address</a>
													</span>
													</td>
												</tr>
											</table></td>
							</tr>
							<tr>
								<td align="right">Address 1:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="daddress1" id="daddress1"></td>
							</tr>	
							<tr>
								<td align="right">Address 2:</td>
								<td><input type="text" name="daddress2" id="daddress2"></td>
							</tr>
							<tr>
								<td align="right">Address 3:</td>
								<td><input type="text" name="daddress3" id="daddress3"></td>
							</tr>
							<tr>
								<td align="right">Town:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="dtown" id="dtown"></td>
							</tr>
							<tr>
								<td align="right">City:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="dcity" id="dcity"></td>
							</tr>
							<tr>
								<td align="right">Country:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="dcountry" id="dcountry" value="${country_code}" readonly="readonly"></td>
							</tr>
							<tr>
								<td align="right">Phone:<strong style="color: red;">*</strong></td>
								<td><input type="text" name="dphone" id="dphone" onkeypress="return onlyIntegerNumber(event);" >
								</td>
							</tr>
							<tr>
						    <td>&nbsp;</td>
						    <td><input type="button" value="Next" class="orang_btn" onclick="Screen3('2')"></td>
						  </tr>
						</table> 
						
		   			    </c:when>
		   			    <c:otherwise>
		   			    </c:otherwise>
					</c:choose>
					 </tr>
					 </table>
				 </div>
				 </c:if>
				 <br class="seperator_break">
				 <div id="remaining_items">
						<table width="100%" border="0" cellspacing="2" cellpadding="0" class="tabuler_forms">
						  <tr>
						    <th colspan="5">Remaining List of Items  </th>
						    </tr>
						  <tr>
									  <th align="center">Country</th>
						              <th align="center">Weight</th>
						              <th align="center">Quantity</th>
						              <th align="center">Price</th>
						              <th align="center">SubTotal</th>
						            </tr>
						<c:choose>
							<c:when test="${fn:length(remaining_item) > 0}">  
										
								 	 <c:forEach var="c" items="${remaining_item}">
								 	 	<tr>
								 	 	 		<td align="center">${c.country_code}</td>
								 	 		    <td align="center">${c.temp_weight}</td>
								 	 		    <td align="center">${c.temp_quantity}</td>
								 	 		    <td align="center">${c.temp_price}</td>
								 	 		    <td align="center">${c.temp_subtotal}</td>
								 		</tr>
								 	 </c:forEach>	
			   			    </c:when>
			   			    <c:otherwise>
			   			 	   <tr><td align="center" colspan="5">No Record Found.</td></tr>
			   			    </c:otherwise>
					</c:choose>
					 </table>
					 <br class="seperator_break">
				 </div>
				 
				 <c:if test="${fn:length(current_itemDetails) ==0}">
				 	<c:if test="${sessionScope.buymore == null}">
				 		<div align="center" style=" margin-left:45%;">
				 			<input type="button" id="lastbutton" onclick="changeScreen()" class="orang_btn" value="Next" ></div>
				 		 <div class="clear"></div>
				 	</c:if>
				 </c:if>
				 
				</div>
			</div>
			<c:if test="${sessionScope.buymore == null}">
			<div>
				<h2><a href="javascript:void(0);">Enter Parcel Details (Step 3 of 3)</a></h2>
				<div>
					<input type="hidden" value="" id="agentID" />
					<table width="100%" border="0" cellspacing="0" cellpadding="3" class="tabuler_forms">
						   <tr>
						    <th colspan="2">Parcel Details</th>
						    </tr>
						  <tr>
						   		<td  align="right" width="30%">Package Contents:<strong style="color: red;">*</strong></td>
						    	<td><input type="text" name="package_content" id="package_content"></td>
						    </tr>
						  <tr>
						    <td  align="right">Value of Parcel:<strong style="color: red;">*</strong> </td>
						    <td><input type="text" name="value_parcel" id="value_parcel" onkeypress="return isNumberKey(event);"></td>
						  </tr>
						  <tr>
						  	<td  align="right">No of    Parcel:<strong style="color: red;">*</strong></td>  
						  	<td><input type="text" name="num_parcel" id="num_parcel" readonly="readonly"  value="${itemInBasket}"></td>
						  </tr>  
						  <tr>
						    <td  align="right">Insurance Cover:<strong style="color: red;">*</strong></td>
						    <td><div class="styled-select">
					                  <select id="insurance_cover" onchange="showCartPlaceOrder();">
					                    		 <option value="0.00">Free Standard Cover upto &#163; 50</option>
					                    		 <option value="03.75">&#163;100  - 03.75   + VAT</option>
					                    		 <option value="07.50">&#163;200  - 07.50   + VAT</option>
					                    		 <option value="09.38">&#163;250  - 09.38   + VAT</option>
					                    		 <option value="18.75">&#163;500  - 18.75   + VAT</option>
					                    		 <option value="37.50">&#163;1000 - 37.50  + VAT</option>
					                    		 <option value="56.25">&#163;1500 - 56.25  + VAT</option>
					                    		 <option value="75.00">&#163;2000 - 75.00  + VAT</option>
					                    		 <option value="93.75">&#163;2500 - 93.75  + VAT</option>
					                  </select>
               				 </div></td>
						    </tr>
					     <tr>
						    <td align="right">Delivery Instructions :</td>
						    <td><input type="text" name="delivery_instruction" id="delivery_instruction"></td>
					    </tr>
					     <tr>
					     		 <td align="right" >&nbsp;</td>
					  		   	 <td style="padding-bottom: 5px;">
					  		   	  <input type="radio" onclick="selectAgent(this.value);" name="collection_type"  value="droptoagent" > &nbsp;Drop Your Parcel to Our Agent &nbsp; &nbsp;
					  		   	  <input type="radio" name="collection_type"  value="reversed" > &nbsp;Home    Collection
					  		   	 </td>
					    </tr>
					      <tr>
					    	<td>&nbsp;</td>
					    	<td> <span class="green_btn">
						              <a  href="info_dropoffagent.html" target=”_blank”>What is a Drop off Agent?</a> 
						         </span> &nbsp;
					  		   	 </td>
					    </tr>
					    <tr>
					  		   <td align="right" >&nbsp;</td>
					    		<td style="padding-bottom: 5px;"><input type="checkbox" id="prohibited_rest"/> My item isn't listed in the <a target="_blank" href="prohibited_items.html">Prohibited & restricted items</a> list</td>
					    </tr>
					    <tr>
					  		    <td align="right" >&nbsp;</td>
					    		<td style="padding-bottom: 5px;"><input  type="checkbox" id="term_condition"  /> I have understood and I agree to the <a target="_blank" href="terms_condition.html">Tems & Conditions</a></td>
					    </tr>
					    <tr>
						    <td align="right" >&nbsp;</td>
						    <td ><input type="button" id="finalbtn" value="Finish" class="orang_btn" onclick="finalScreen()"></td>
						  </tr>
						</table>
				</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.buymore != null}">
				<table width="100%" border="0" cellspacing="0" cellpadding="3" class="tabuler_forms">
						   <tr>
						    <th colspan="2">Parcel Details</th>
						    </tr>
						  <tr>
						   		<td  align="right" width="30%">Package Contents:<strong style="color: red;">*</strong></td>
						    	<td><input type="text" name="buy_package_content" id="buy_package_content"></td>
						    </tr>
						  <tr>
						    <td  align="right">Value of Parcel:<strong style="color: red;">*</strong> </td>
						    <td><input type="text" name="buy_value_parcel" id="buy_value_parcel" onkeypress="return isNumberKey(event);"></td>
						  </tr>
						  <tr>
						  	<td  align="right">No of    Parcel:<strong style="color: red;">*</strong></td>  
						  	<td><input type="text" name="buy_num_parcel" id="buy_num_parcel" value="${itemInBasket}"  readonly="readonly" ></td>
						  </tr>  
						  <tr>
						    <td  align="right">Insurance Cover:<strong style="color: red;">*</strong></td>
						    <td><div class="styled-select">
					                  <select id="buy_insurance_cover" onchange="buy_showCartPlaceOrder();">
					                    		 <option value="0.00">Free Standard Cover upto &#163; 50</option>
					                    		 <option value="03.75">&#163;100  - 03.75   + VAT</option>
					                    		 <option value="07.50">&#163;200  - 07.50   + VAT</option>
					                    		 <option value="09.38">&#163;250  - 09.38   + VAT</option>
					                    		 <option value="18.75">&#163;500  - 18.75   + VAT</option>
					                    		 <option value="37.50">&#163;1000 - 37.50  + VAT</option>
					                    		 <option value="56.25">&#163;1500 - 56.25  + VAT</option>
					                    		 <option value="75.00">&#163;2000 - 75.00  + VAT</option>
					                    		 <option value="93.75">&#163;2500 - 93.75  + VAT</option>
					                  </select>
               				 </div></td>
						    </tr>
					     <tr>
						    <td align="right">Delivery Instructions :</td>
						    <td><input type="text" name="buy_delivery_instruction" id="buy_delivery_instruction"></td>
					    </tr>
					     <tr>
					  		   <td align="right" >&nbsp;</td>
					    		<td style="padding-bottom: 5px;"><input type="checkbox" id="buy_prohibited_rest"/> My item isn't listed in the <a target="_blank" href="prohibited_items.html">Prohibited & restricted items</a> list</td>
					    </tr>
					    <tr>
					  		    <td align="right" >&nbsp;</td>
					    		<td style="padding-bottom: 5px;"><input  type="checkbox" id="buy_term_condition"  /> I have understood and I agree to the <a target="_blank" href="terms_condition.html">Tems & Conditions</a></td>
					    </tr>
					    <tr>
						    <td align="right" >&nbsp;</td>
						    <td ><input type="button" value="Order" class="orang_btn" onclick="buyMoreOrder()"></td>
						  </tr>
				</table>
			</c:if>
			 
		</div>
		
      <br class="seperator_break">
 		<div id="item_cart"></div>
    </div>
    <jsp:include page="../home/right_panel.jsp"></jsp:include>
    
    <div class="clear"></div>
  </div>
</div>
<!--container ends--> 

<!--payment starts-->
<jsp:include page="../home/payment.jsp"></jsp:include>
<!--payment ends--> 

<!--links starts-->
<jsp:include page="../home/links.jsp"></jsp:include>
<!--links ends-->

<!--footer starts-->
<jsp:include page="../home/footer.jsp"></jsp:include>
<!--footer ends-->
<!-- ui-dialog -->
		<div id=dialog_deliveryAddress title="Delivery Address Details"></div>
		<!-- <div id=dialog_dropoffagentList title="List of Drop Off Agent Details"></div> -->
		<div id=dialog_insurancecover title="Insurance Cover">
						<p>Your declared value of item is higher then the standard cover.</p>
						<br>
					    <p>It is automatically covered for up to &pound; 50.</p>
					    <br>
				   	    <p>You can cover the whole cost of your item by changing your insurance value.</p>
				   	    <br>
						<p>Do you wish to change the value of your insurance cover?	</p>
		</div>
	
		<div id=buy_dialog_insurancecover title="Insurance Cover">
							<p>Your declared value of item is higher then the standard cover.</p>
							<br>
						    <p>It is automatically covered for up to &pound; 50.</p>
						    <br>
					   	    <p>You can cover the whole cost of your item by changing your insurance value.</p>
					   	    <br>
							<p>Do you wish to change the value of your insurance cover?	</p>
			</div>
	
	

<!-- user registation page  -->

	<div id=dialog_userRegistration title="Register User">
					<div id="error_loginmsg" style="display: none;">
						<div class="error2">
						</div>
					</div>
					<table width="100%" border="0" cellspacing="4" cellpadding="0">
			            <tr>
			                      <td align="right">Email ID :<strong style="color: red;">*</strong></td>
			                      <td><input type="text" name="remail" id="remail" ></td>
			                    </tr>
			            <tr>
			                      <td align="right">Password :<strong style="color: red;">*</strong></td>
			                      <td><input type="password" name="rpassword" id="rpassword"></td>
			                    </tr>
			            <tr>
			                      <td align="right">Confirm Password :<strong style="color: red;">*</strong></td>
			                      <td><input type="password" name="rcpassword" id="rcpassword"></td>
			                    </tr>
			          <!--   <tr>
			                      <td>&nbsp;</td>
			                      <td><input class="orang_btn" type="submit" name="button" id="button" value="Submit" /></td>
			                    </tr> -->
			          </table>
	</div>
<!-- for display order details form -->		
<form id="frm_showOrderDetails" name="frm_showOrderDetails" action="showorderdetails.html">
</form>

<script type="text/javascript">
			$(function(){
				 // Accordion
				$("#accordion").accordion({ header: "h2",
					 autoHeight: false,
					 navigation: true	
				});	
				 
				// Dialog
				$('#dialog_deliveryAddress').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() {
							$(this).dialog("close");
						}
					}
				}); 
				
				// Dialog
				$('#dialog_dropoffagentList').dialog({
					autoOpen: false,
					width: 900,
					buttons: {
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});
				// Dialog
				$('#dialog_insurancecover').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Change Insurance Value": function() {
							$(this).dialog("close");
						},
						"Keep Standard Cover": function() {
							$("#dialog_insurancecover").dialog("close");
							placeOrders();
							//userRegistration();
						}
					}
				}); 
			
				// Dialog for insurance cover for buy more 
				$('#buy_dialog_insurancecover').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Change Insurance Value": function() {
							$(this).dialog("close");
						},
						"Keep Standard Cover": function() {
							$(this).dialog("close");
							buyMoreOrderItems();
						}
					}
				}); 
				
				// Dialog user registratation
				$('#dialog_userRegistration').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Register": function() {
							validateRegistration();
						},
						"Cancel": function() {
						    $(this).dialog("close");
						}
					}
				}); 
			});
			
		    $(document).ready(function() {
		        //show cart.. details
		    	showCartPlaceOrder("");
		    });
</script>
</body>
</html>
