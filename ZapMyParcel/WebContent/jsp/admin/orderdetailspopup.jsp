<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<title>Zap My Parcel</title>
<style type="text/css">
.tabuler_forms {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	border: 1px solid #a2a2a2;
	border-collapse: collapse;
}

.tabuler_forms th {
	font-weight: bold;
	background: #f4f4f4;
	padding-left: 6px;
	border: 1px solid #a2a2a2;
}

.tabuler_forms td {
	border: 1px solid #a2a2a2;
}
</style>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="2"
		class="tabuler_forms">
		<tr style="background: #e7e7e7;">
			<td colspan="8" align="left"><div
					style="font-size: 14px; font-weight: normal; color: #333;">Order
					Details:</div>
			</td>
		</tr>
		<tr>
			<th align="left">Order ID</th>
			<th align="left">PayPal Transaction</th>
			<th align="left">Order Status</th>
			<th align="left">PayPal Status</th>
			<th align="left">Collection Type</th>
			<th align="left">Insurance Cover</th>
			<th align="left">Value Of Parcel</th>
			<th align="left">Order Date</th>
		</tr>
		<c:choose>
				<c:when test="${fn:length(order_data) > 0 }">        
					<c:forEach items="${order_data}" var="o"> 
							<tr>
								<td align="left">${o.user_order_id}</td>
								<td align="left">${o.transactionid}</td>
								<td align="left">${o.order_status}</td>
								<td align="left">${o.status}</td>
								<td align="left">${o.collection_type}</td>
								 <c:choose>
								 		<c:when test="${o.insurance_cover >0}">
								 			<td align="left">Yes</td>
								 		</c:when>
								 		<c:otherwise>
								 			<td align="left">No</td>
								 		</c:otherwise>
								 </c:choose>
								<td align="left">${o.value_parcel}</td>
								<td align="left">${o.order_date}</td>
							</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
							<td colspan="8" align="center">No Record Found.</td>
					</tr>		
				</c:otherwise>
		</c:choose>
		
	</table>
	<br />
	<table width="100%" border="0" cellspacing="0" cellpadding="2"
		class="tabuler_forms">
		<tr style="background: #e7e7e7;">
			<td colspan="2" align="left"><div
					style="font-size: 14px; font-weight: normal; color: #333;">Customer
					Details:</div>
			</td>
		</tr>
		<c:forEach items="${order_data}" var="o"> 
		<tr>
			<th width="20%" align="left">Name</th>
			<td align="left">${o.s_name }</td>
		</tr>
		<tr>
			<th align="left">PostCode</th>
			<td align="left">${o.s_postcode }</td>
		</tr>
		<tr>
			<th align="left">AddressLine 1</th>
			<td align="left">${o.s_addressLine1}</td>
		</tr>
		<tr>
			<th align="left">AddressLine 2</th>
			<td align="left">${o.s_addressLine2}</td>
		</tr>
		<tr>
			<th align="left">AddressLine 3</th>
			<td align="left">${o.s_addressLine3}</td>
		</tr>
		<tr>
			<th align="left">Town</th>
			<td align="left">${o.s_town}</td>
		</tr>
		<tr>
			<th align="left">City</th>
			<td align="left">${o.s_city }</td>
		</tr>
		<tr>
			<th align="left">Phone</th>
			<td align="left">${o.s_phone}</td>
		</tr>
		<tr>
			<th align="left">Email</th>
			<td align="left">${o.s_email }</td>
		</tr>
		<tr>
			<th align="left">Delivery Instructions</th>
			<td align="left">${o.delivery_Instructions}</td>
		</tr>
	  </c:forEach>
	</table>
	<br />
	<table width="100%" border="0" cellspacing="0" cellpadding="2"	class="tabuler_forms">
		<tr style="background: #e7e7e7;">
			<td colspan="4" align="left"><div
					style="font-size: 14px; font-weight: normal; color: #333;"
					align="center">Order Details</div>
			</td>
		</tr>
		<tr>
			<th align="left">Weight</th>
			<th align="left">Quantity</th>
			<th align="left">Price</th>
			<th align="left">SubTotal</th>
		</tr>
		<c:choose>
				<c:when test="${fn:length(cartList) > 0 }">        
					<c:forEach items="${cartList}" var="o"> 
						<tr><td align="left">${o.weight }</td>	
						<td align="left">${o.quantity }</td>	
						<td align="left">${o.Item_price }</td>	
						<td align="left">${o.Item_subtotal }</td></tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
							<td colspan="4" align="center">No Record Found.</td>
					</tr>		
				</c:otherwise>	
		</c:choose>
	</table>
	<br class ="seperator_break">
	<table border="0" cellpadding="2" cellspacing="0" width="100%" class="tabuler_forms">
    <tbody>
    	<c:choose>
				<c:when test="${fn:length(order_data) > 0 }">        
					<c:forEach items="${order_data}" var="o"> 
			      <tr>
			        <th align="left" width="20%">Sub Total :</th>
			        <td align="left">${o.sub_total}</td>
			      </tr>
			      <tr>
			        <th align="left" width="20%">Insurance :</th>
			        <td align="left">${o.insurance_cover }</td>
			      </tr>
			      <tr>
			        <th align="left" width="20%">Vat@20% :</th>
			        <td align="left">${o.vat}</td>
			      </tr>
			      <tr>
			        <th align="left" width="20%">Total :</th>
			        <td align="left">${o.total_price}</td>
			      </tr>
			      </c:forEach>
		       </c:when>
		</c:choose>
    </tbody>
  </table>
<br class ="seperator_break">
	     	<c:choose>
				<c:when test="${fn:length(getAgentDetails) > 0 }">
					<table width="100%" border="0" cellspacing="0" cellpadding="2"	class="tabuler_forms">
							<tr style="background: #e7e7e7;">
								<td colspan="11" align="left"><div
										style="font-size: 14px; font-weight: normal; color: #333;"
										align="center">Agent Details</div>
								</td>
							</tr>
							<tr>
								<th align="left">Agent Code</th>
								<th align="left">Agent Name</th>
								<th align="left">PostCode</th>
								<th align="left">AddressLine 1</th>
								<th align="left">AddressLine 2</th>
								<th align="left">AddressLine 3</th>
								<th align="left">Town</th>
								<th align="left">City</th>
								<th align="left">County</th>
								<th align="left">Phone</th>
								<th align="left">Email</th>
							</tr>
				        
					<c:forEach items="${getAgentDetails}" var="o"> 
								<tr>
								   <td align="left">${o.agent_code }</td>	
								   <td align="left">${o.agent_name }</td>	
								   <td align="left">${o.Postcode }</td>	
								   <td align="left">${o.address1 }</td>
								   <td align="left">${o.address2 }</td>
								   <td align="left">${o.address3 }</td>
								   <td align="left">${o.town }</td>	
								   <td align="left">${o.city }</td>	
								   <td align="left">${o.County }</td>	
								   <td align="left">${o.Phone }</td>	
								   <td align="left">${o.Email }</td>
					</c:forEach>
					</table>
		       </c:when>
		</c:choose>
</body>
</html>
		