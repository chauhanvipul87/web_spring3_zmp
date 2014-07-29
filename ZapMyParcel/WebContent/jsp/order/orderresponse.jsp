<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:choose>
	<c:when test="${ErrorMsg != null}">
		<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${ErrorMsg}');</script></div> 
	</c:when>
	<c:otherwise>
		<c:choose>
				<c:when test="${option == 'loadaddress' and prefix =='s'}">
							<tr>
								<td align="right" width="30%">Select Address:</td>
								<td><div class="styled-select">
                 		  			 <select onchange="setAddressFields('s')"   id="addressFinder">
									<option value="0">--Select Address ---</option>
									 <c:choose>
										<c:when test="${fn:length(addressList) > 0 }">    
											<c:forEach var="address" items="${addressList }">
													<option value="${address}">${address}</option>
											</c:forEach>
										</c:when>
										</c:choose>    
						  </select>
                		</div></td>
							</tr>
				</c:when>
				
				<c:when test="${option =='loadaddress' and prefix =='d'}">
							<tr>
								<td align="right" width="30%">Select Address:</td>
								<td><div class="styled-select">
                 		  			<select id="addressFinder_Delivery" onchange="setAddressFields('d')">
									<option value="0">--Select Address ---</option>
										<c:choose>
											<c:when test="${fn:length(addressList) > 0 }">    
												<c:forEach var="address" items="${addressList }">
													<option value="${address}">${address}</option>
												</c:forEach>
											</c:when>
										</c:choose>    
							         </select>
                					</div></td>
							</tr>
				</c:when>
				
				<c:when test="${option =='load_delivery_address'}">
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
					 </tr>
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

				</c:when>
				<c:when test="${option == 'showdeliveryaddress_country' }">
				<center>
				<c:if test="${fn:length(delivery_addressDetails) >0}">
				<c:forEach var="c" items="${delivery_addressDetails }">
					<table class="tabuler_forms" border="0"  cellspacing="3" width="100%"> 
						<tr>
							<th colspan="2">Delivery Address Details For Country :${c.country_code}  </th>
						</tr>
						<tr>
							<td width="30%" >Name :</td>
							<td>${c.d_name }</td>
						</tr>
						<tr>
							<td >PostCode :</td>
							<td>${c.d_postcode }</td>
						</tr>
						<tr>
							<td >Address Line 1 :</td>
							<td>${c.d_addressLine1 }</td>
						</tr>
						<tr>
							<td >Address Line 2 :</td>
							<td>${c.d_addressLine3 }</td>
						</tr>
						<tr>
							<td >Address Line 3 :</td>
							<td>${c.d_addressLine3}</td>
						</tr>
						<tr>
							<td >Town :</td>
							<td>${c.d_town }</td>
						</tr>
						<tr>
							<td >City :</td>
							<td>${c.d_city }</td>
						</tr>
						<tr>
							<td >Country :</td>
							<td>${c.country_code}</td>
						</tr>
						<tr>
							<td >Phone :</td>
							<td>${c.d_phone }</td>
						</tr>
					</table>
					</c:forEach>	
				</c:if>
			</center>
			</c:when>
			
			<c:when test="${option =='dropToAgentDetails' }">
			
				<table width="100%" cellspacing="3" border="0" class="tabuler_forms" cellpadding="3"> 
						<c:if test="${action == null }">
							<tr>
								<th colspan="12">Drop Off Agent Details </th>
							</tr>
						</c:if>
						<c:if test="${action != null }">
							<tr>
								<th colspan="11">Drop Off Agent Details </th>
							</tr>
						</c:if>
						<tr>
								<th align="center">Name</th>
								<th align="center">Code</th>
								<th align="center">PostCode</th>
								<th align="center">Address 1</th>
								<th align="center">Address 2</th>
								<th align="center">Address 3</th>
								<th align="center">Town </th>
								<th align="center">City </th>
								<th align="center">County </th>
								<th align="center">Country</th>
								<th align="center">Phone </th>
								<c:if test="${action == null }">
										<th align="center">Action</th>
								</c:if>
								
						</tr>
						
					<c:forEach var="a" items="${agentDeails }">
						<tr>
							<td align="left" >${a.agent_name }</td>
							<td align="left" >${a.agent_code }</td>
							<td align="left" >${a.postcode }</td>
							<td align="left" >${a.address1 }</td>
							<td align="left" >${a.address2 }</td>
							<td align="left" >${a.address3 }</td>
							<td align="left" >${a.town }</td>
							<td align="left" >${a.city }</td>
							<td align="left" >${a.County }</td>
							<td align="left" >${a.country_code }</td>
							<td align="left" >${a.Phone }</td>
							<c:if test="${action == null }">
								<td align="left" ><span class="green_btn">
							              	<a onclick="setAgent('${a.agent_id}')" href="javascript:void(0);">Select</a>
							            </span>
								</td>
							</c:if>
						</tr>
					</c:forEach>	
					</table>
			
			</c:when>
			<c:when test="${option =='userRegistration' }">
						${resmsg}				
			</c:when>	
			<c:when test="${option == 'track_order'}">
			<table width="100%" cellspacing="1" border="0" class="tabuler_forms" cellpadding="5"> 
						<tr>	<th>Order Date</th>
								<th>Order ID</th>
								<th>Order Status</th>
								<th>Signature</th>
						</tr>
			  <c:choose>
					<c:when test="${fn:length(getOrderDetails) > 0 }">  
						<c:forEach var="op" items="${getOrderDetails}">
								<tr><td align="center">${op.order_date}</td>
									<td align="center">${op.order_id}</td>
									<c:choose>
										<c:when test="${op.order_status == 'ordered' }">
											<td align="center">To be Collected</td>			
										</c:when>
										<c:when test="${op.order_status == 'card_left' }">
											<td align="center">Delivery Attempted, Recipient Not In</td>			
										</c:when>
										<c:when test="${op.order_status == 'check_address' }">
											<td align="center">Need More Address Detail</td>			
										</c:when>
										<c:when test="${op.order_status == 'refused' }">
											<td align="center">Recipient Refused to Take Delivery</td>			
										</c:when>
										<c:when test="${op.order_status == 'rts' }">
											<td align="center">Delivery Returned to Sender</td>			
										</c:when>
										<c:otherwise>
											<td align="center">${op.order_status}</td>	
										</c:otherwise>
								    </c:choose>
								    <c:choose>
								    	<c:when test="${op.signature != null }">
								    			<td align="center">${op.signature}</td>
								    	</c:when>
								    	<c:otherwise>
								    			<td align="center">-</td>	
								    	</c:otherwise>
								    	
								    </c:choose>
									<%--<td align="center">${op.total_price}</td>
									 <c:choose>
										<c:when test="${op.transactionid == null }">
											<td align="center">-</td>	
										</c:when>
										<c:otherwise>
											<td align="center">${op.transactionid}</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${op.status == 'SuccessWithWarning' or op.status == 'FailureWithWarning' or op.status =='Failure' or op.status =='Success'}">
											<td align="center">${op.status}</td>	
										</c:when>
										<c:otherwise>
											<td align="center">-</td>
										</c:otherwise>
									</c:choose>	 --%>
								</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
							<tr><td colspan="6" align="center">No Record Found.</td></tr>					
					</c:otherwise>
				</c:choose>
				</table>
				 <br class="seperator_break">
				 <c:choose>
							<c:when test="${fn:length(errorDetails) > 0 }">  	
								<table width="100%" cellspacing="1" border="0" class="tabuler_forms" cellpadding="5"> 
									<tr><th>Message</th></tr>
									<tr><td><ol style="margin:0 0 0 20px; padding: 0px;color: #ff0000; font-size: 12px;">
						 			<c:forEach var="e" items="${errorDetails}" >
						 					<li style="margin-bottom: 8px;" >${e.long_errormsg}</li>
						 			</c:forEach>
									</ol></td></tr>
							</table>
							</c:when>
							
				</c:choose>
				
			</c:when>
			
			<c:when test="${option == 'getcartdetails'}">
					<c:choose>
							<c:when test="${fn:length(cartList) > 0 }">
									<table width="100%" cellspacing="1" border="0" class="tabuler_forms" cellpadding="2"> 
										<tr>
										<th>Weight</th>
										<th>Quantity</th>
										<th>Price</th>
										<th>SubTotal</th>
										</tr>
										<c:forEach items="${cartList}" var="c">
											<tr>
												<td align="center">${c.temp_weight}</td>
												<td align="center">${c.temp_quantity }</td>
												<td align="center">${c.temp_price }</td>
												<td align="center">${c.temp_subtotal}</td>
											</tr>
										</c:forEach>
									</table>
							</c:when>
							<c:otherwise>
								<table width="100%" cellspacing="1" border="0" class="tabuler_forms" cellpadding="5"> 
									<tr><td align="center">Please add item into cart first.</td></tr>
								</table>
							</c:otherwise> 
					</c:choose>
			
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>	