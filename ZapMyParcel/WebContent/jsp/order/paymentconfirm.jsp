<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../home/header.jsp"></jsp:include>
<!--container starts-->
<div id="container">
  <div class="raw">
    <jsp:include page="../home/left_panel.jsp"></jsp:include>
    <div id="middle_pnl">
    <center>
	 		<div id="ajax_loader">
					<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
						/> &nbsp; Processing...Please wait...
			</div>
		</center>
    <div class="module">
      	<div class="mo_container">
          <div style=" font-family:Arial, Helvetica, sans-serif; font-size:22px; color:#090; margin:0 0 10px 0;">
          <c:choose>
           <c:when test="${SuccessWithWarning != null }">
           		Your Transaction has been Completed Successfully With Warning.
           </c:when>
           <c:otherwise>
           		Your Transaction has been Completed Successfully.
           </c:otherwise>
          </c:choose>
          </div>
          <table width="100%" border="0" cellpadding="3" class="tabuler_forms">
			<c:forEach var="p" items="${paymanentDetails}" >    
            <tr>
              <th align="left">Payment Transaction ID :</th>
              <td align="left">${p.transactionid }</td>
            </tr>
            <tr>
              <th align="left">Order ID :</th>
              <td align="left">${p.user_order_id}</td>
            </tr>
            <tr>
              <th align="left">Payment Amount :</th> 
              <td align="left">&#163; ${p.amount}</td>
            </tr>
            <c:if test="${SuccessWithWarning != null }">
	            <tr>
		              <th align="left">Message With Warning :</th> 
		              <td align="left">${SuccessWithWarning}</td>
	            </tr>
            </c:if>
           </c:forEach>
          </table>
        </div>
    	</div>
    	<br class="seperator_break">
    	<p>Thank you for using our services. <a href="index.html">Go Back To Home</a></p>
    	<br class="seperator_break">
    	<div class="module">
       					 <h5>List of ordered Items</h5>
       						 <div class="mo_container">
						<table width="100%" border="0" cellspacing="2" cellpadding="0" class="grid_cart">
			            <tr>
			              <th align="center">Item</th>
			              <th align="center">Weight</th>
			              <th align="center">Quantity</th>
			              <th align="center">Price</th>
			              <th align="center">SubTotal</th>
			             </tr>
			            <c:choose>
						<c:when test="${fn:length(cartList) > 0 }">        
					    <c:forEach var="c" items="${cartList}">
					    	 <tr>
					              <td align="center"><img style="vertical-align: bottom;" src="images/box.png" width="45" height="46"></td>
					              <td align="center">${c.weight} Kg</td>
					        	  <td align="center">${c.quantity}</td>
					        	  <td align="center">&pound; ${c.Item_price}</td>
				              	  <td align="center">&pound; ${c.Item_subtotal }</td>
				              </tr>
				           </c:forEach>
			           </c:when>
			           </c:choose>   
			           </table> 
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
    	 </div>
    	
    	  
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
</body>
</html>
