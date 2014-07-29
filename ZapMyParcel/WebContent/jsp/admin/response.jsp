<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${ErrorMsg != null}">
	<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage_Admin('${ErrorMsg}');</script></div>
	<c:if test="${display == 'adminReportError' }">
	<table width="100%" border="0" cellspacing="0" cellpadding="7" class="grid">
		<tr>
			<th align="center">Date</th>
			<th align="center">ZMP Ref</th>
			<th align="center">Transaction Id</th>
			<th align="center">Payment Id</th>
			<th align="center">PayPal Status</th>
			<th align="center">Name</th>
			<th align="center">Carrier</th>
			<th align="center">Tracking No</th>
			<th align="center">Type</th>
			<th align="center">Parcels</th>
			<th align="center">Price</th>
			<th align="center">Insurance</th>
			<th align="center">Order Status</th>
			<th align="center">Signature</th>
		</tr>
		<tr>
			<td colspan="14" align="center"><b>No Record Found !!!</b></td>
		</tr>
	</table>
	</c:if>
</c:when>
<c:otherwise>
	<c:if test="${display == 'adminReport' }">
	<div id="error"></div>
	<table width="100%" border="0" cellspacing="0" cellpadding="7" class="grid">
		<tr>
			<th align="center">Date</th>
			<th align="center">ZMP Ref</th>
			<th align="center">Transaction Id</th>
			<th align="center">Payment Id</th>
			<th align="center">PayPal Status</th>
			<th align="center">Name</th>
			<th align="center">Carrier</th>
			<th align="center">Tracking No</th>
			<th align="center">Type</th>
			<th align="center">Parcels</th>
			<th align="center">Price</th>
			<th align="center">Insurance</th>
			<th align="center">Order Status</th>
			<th align="center">Signature</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(reportData) > 0 }">
			<c:forEach var="r" items="${reportData}">
			<tr id="response_${r.order_id}">
				<td align="center">${r.orderDate}</td>
				<td align="center"><input type="button" onclick="viewFullOrderDetails('${r.order_id}');" value="${r.zapRef}"  class="btn"></td>
				
				
				
				<td align="center">
					<c:choose>
						<c:when test="${r.paymentStatus == 'Success' or r.paymentStatus == 'SuccessWithWarning'}">${r.transactionId}</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${r.paymentStatus == 'Success' or r.paymentStatus == 'SuccessWithWarning'}">${r.paymentId}</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td align="center">${r.paymentStatus}</td>
				<td align="center">${r.name}</td>
				<td align="center">
					<c:choose>
						<c:when test="${r.paymentStatus == 'Success' or r.paymentStatus == 'SuccessWithWarning'}">
						<input type="hidden" name="carrierId_${r.order_id}" id="carrierId_${r.order_id}" value="${r.carrierId}"/>
						<div id="editDiv1_${r.order_id}" align="center">
							${r.carrierName}
						</div>
						<div id="updateDiv1_${r.order_id}" class="hiddenField" align="center">
							<select name="newCarrierId_${r.order_id}" id="newCarrierId_${r.order_id}">
								<c:forEach items="${carriers}" var="c">
								<c:choose>
		           					<c:when test="${r.carrierId == c.carrier_id }">
		           						<option selected="selected" value="${c.carrier_id}">${c.carrier_name}</option>
		           					</c:when>
		            				<c:otherwise>
		            					<option value="${c.carrier_id}">${c.carrier_name}</option>
		            				</c:otherwise>
		           				</c:choose> 
								</c:forEach>
							</select>
						</div>
						</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${r.paymentStatus == 'Success' or r.paymentStatus == 'SuccessWithWarning'}">
						<div id="editDiv2_${r.order_id}" align="center">
							${r.trackingNo}
							<input class="btn" type="button" name="editbutton_${r.order_id}" id="editbutton_${r.order_id}" value="Edit" onclick="editOrderDetail('${r.order_id}');"/>
						</div>
						<div id="updateDiv2_${r.order_id}" class="hiddenField" align="center">
							<input type="text" name="newTrackingNo_${r.order_id}" id="newTrackingNo_${r.order_id}" value="${r.trackingNo}"/>
							<select name="newStatus_${r.order_id}" id="newStatus_${r.order_id}" style="margin: 5px 0 5px 0;">
								<option value="${enroute}">Enroute</option>
								<option value="${delivered}">Delivered</option>
								<!-- ADDED NEW START -->
								<option value="${card_left}">Card Left</option>
								<option value="${check_address}">Check Address</option>
								<option value="${refused}">Refused </option>
								<option value="${rts}">RTS (Return to Sender)</option>
								
								<!-- ADDED NEW END-->
							</select><br/>
							<input class="btn" type="button" name="updatebutton_${r.order_id}" id="updatebutton_${r.order_id}" value="Update" onclick="updateOrderDetail('${r.order_id}');"/>
							<input class="btn" type="button" name="cancelbutton_${r.order_id}" id="cancelbutton_${r.order_id}" value="Cancel" onclick="calcelOrderDetail('${r.order_id}');"/>
						</div>
						</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td align="center">${r.collectionType}</td>
				<td align="center">${r.parcel}</td>
				<td align="right">${r.totalPrice}</td>
				<td align="center">${r.insurance}</td>
				<td align="center">
					${r.orderStatus}
					<input type="hidden" name="orderStatus_${r.order_id}" id="orderStatus_${r.order_id}" value="${r.orderStatus}"/>
				</td>
				<td align="center">
						<div id="editDiv3_${r.order_id}" align="center">
							${r.signature}
							<input class="btn" type="button" name="editSingnature_${r.order_id}" id="editSingnature_${r.order_id}" value="Edit" onclick="editSignature('${r.order_id}');"/>
						</div>
						<div id="updateSignatureDiv3_${r.order_id}" align="center" class="hiddenField">
						   <input type="text" value="${r.signature}" name="txt_signature_${r.order_id}" id="txt_signature_${r.order_id}" />
						   	<input class="btn" type="button" name="updatebutton_${r.order_id}" id="updatebutton_${r.order_id}" value="Update" onclick="updateSignature('${r.order_id}');"/>
							<input class="btn" type="button" name="cancelbutton_${r.order_id}" id="cancelbutton_${r.order_id}" value="Cancel" onclick="cancelSignarure('${r.order_id}');"/>
						</div>
				</td>
			</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
			<tr>
				<td colspan="14" align="center"><b>No Record Found !!!</b></td>
			</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</c:if>
	<c:if test="${display == 'updateOrderDetail'}">
		<td align="center">${orderDate}</td>
		<td align="center"><input type="button" onclick="viewFullOrderDetails('${order_id}');" value="${zapRef}"  class="btn"></td>
		<td align="center">${transactionId}</td>
		<td align="center">${paymentId}</td>
		<td align="center">${paymentStatus}</td>
		<td align="center">${name}</td>
		<td>
			<input type="hidden" name="carrierId_${order_id}" id="carrierId_${order_id}" value="${carrierId}"/>
			<div id="editDiv1_${order_id}" align="center">
				${carrierName}
			</div>
			<div id="updateDiv1_${order_id}" class="hiddenField" align="center">
				<select name="newCarrierId_${order_id}" id="newCarrierId_${order_id}">
					<c:forEach items="${carriers}" var="c">
					<c:choose>
	              					<c:when test="${carrierId == c.carrier_id }">
	              						<option selected="selected" value="${c.carrier_id}">${c.carrier_name}</option>
	              					</c:when>
	               				<c:otherwise>
	               					<option value="${c.carrier_id}">${c.carrier_name}</option>
	               				</c:otherwise>
	              				</c:choose> 
					</c:forEach>
				</select>
			</div>
		</td>
		<td>
			<div id="editDiv2_${order_id}" align="center">
				${trackingNo}
				<input class="btn" type="button" name="editbutton_${order_id}" id="editbutton_${order_id}" value="Edit" onclick="editOrderDetail('${order_id}');"/>
			</div>
			<div id="updateDiv2_${order_id}" class="hiddenField" align="center">
				<input type="text" name="newTrackingNo_${order_id}" id="newTrackingNo_${order_id}" value="${trackingNo}"/>
				<select name="newStatus_${order_id}" id="newStatus_${order_id}">
								<option value="${enroute}">Enroute</option>
								<option value="${delivered}">Delivered</option>
								<!-- ADDED NEW START -->
								<option value="${card_left}">Card Left</option>
								<option value="${check_address}">Check Address</option>
								<option value="${refused}">Refused </option>
								<option value="${rts}">RTS (Return to Sender)</option>
				</select><br/>
				<input class="btn" type="button" name="updatebutton_${order_id}" id="updatebutton_${order_id}" value="Update" onclick="updateOrderDetail('${order_id}');"/>
				<input class="btn" type="button" name="cancelbutton_${order_id}" id="cancelbutton_${order_id}" value="Cancel" onclick="calcelOrderDetail('${order_id}');"/>
			</div>
		</td>
		<td align="center">${collectionType}</td>
		<td align="center">${parcel}</td>
		<td align="right">${totalPrice}</td>
		<td align="center">${insurance}</td>
		<td align="center">
			<input type="hidden" name="orderStatus_${order_id}" id="orderStatus_${order_id}" value="${orderStatus}"/>	
			${orderStatus}
		</td>
		<td align="center">
						<div id="editDiv3_${order_id}" align="center">
							${signature}
							<input class="btn" type="button" name="editSingnature_${order_id}" id="editSingnature_${order_id}" value="Edit" onclick="editSignature('${order_id}');"/>
						</div>
						<div id="updateSignatureDiv3_${order_id}" align="center" class="hiddenField">
						   <input type="text" value="${signature}" name="txt_signature_${order_id}" id="txt_signature_${order_id}" />
						   	<input class="btn" type="button" name="updatebutton_${order_id}" id="updatebutton_${order_id}" value="Update" onclick="updateSignature('${order_id}');"/>
							<input class="btn" type="button" name="cancelbutton_${order_id}" id="cancelbutton_${order_id}" value="Cancel" onclick="cancelSignarure('${order_id}');"/>
						</div>
				</td>
	</c:if>
	
	
	
	
<script type="text/javascript">
$(document).ready(function(){
	now = new Date();
	$("#search_date").datepicker({
		dateFormat: "yy-mm-dd",
	});
});
</script>	
</c:otherwise>
</c:choose>