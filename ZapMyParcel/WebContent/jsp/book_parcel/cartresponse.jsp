<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${option =='addToCart' and ErrorMsg != null}">
		<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${ErrorMsg}');</script></div>
	   <input type="hidden" name="temp_key" id="temp_key" value="${sessionScope.user_key}"/>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${ ErrorMsg != null}">
				<div id="emsg" class="${errorClass}"><script type="text/javascript">showMessage('${ErrorMsg}');</script></div>	
			</c:when>
			<c:otherwise>
				<c:if test="${option =='showCart'}">
						<br class="seperator_break">
						<input type="hidden" value="${itemInBasket }" name="basketsize" id="basketsize" />
						<input type="hidden" value="${total}" name="cart_total_price" id="cart_total_price" />
						<input type="hidden" value="${subTotal}" name="subTotal" id="subTotal" />
						<input type="hidden" value="${vat}" name="vat" id="vat" />
						
						
						<div class="module">
       					 <h5>Cart</h5>
       						 <div class="mo_container">
						<table width="100%" border="0" cellspacing="2" cellpadding="0" class="grid_cart">
			            <tr>
			              <th align="center">Item</th>
			              <th align="center">Weight</th>
			              <th align="center">Quantity</th>
			              <th align="center">Price</th>
			              <th align="center">SubTotal</th>
			              <th align="center">Action</th>
			              <th align="center">Remove</th>
			            </tr>
			   <c:choose>
					<c:when test="${fn:length(cartList) > 0 }">        
				    <c:forEach var="c" items="${cartList}">
				    	 <tr>
			              <td align="center"><img style="vertical-align: bottom;" src="images/box.png" width="45" height="46"></td>
			              <td align="center">${c.temp_weight} Kg</td>
			               <c:choose>
			              	<c:when test="${action !=null and action =='editItem' and c.temp_id == temp_id and c.user_key == user_key}">
			              			<td align="center">
			              				<input type="text" size="5" value="${c.temp_quantity}" name="qty" id="qty" onkeypress="return onlyIntegerNumber(event)" />
			              			</td>
			              	</c:when>
			              	<c:otherwise>
			              			 <td align="center">${c.temp_quantity}</td>
			              	</c:otherwise>
			              </c:choose>
			              
			             
			              <td align="center">&pound; ${c.temp_price}</td>
			              <td align="center">&pound; ${c.temp_subtotal }</td>
						  <c:choose>
			              	<c:when test="${action !=null and action =='editItem' and c.temp_id == temp_id and c.user_key == user_key}">
			              			<td align="center">
						              	<table align="center" class="none">
						              		<tr><td align="center">
						              				<span class="green_btn">
						              						<a href="javascript:void(0);" onclick="updateItem('${c.temp_id}','${user_key}')" >Update</a>
						              				</span>
						              			</td>
						              		</tr>
						              	</table>
			              		</td>
			              	</c:when>
			              	<c:otherwise>
			              		<td align="center">
						              	<table align="center" class="none">
						              		<tr><td align="center">
						              				<span class="green_btn">
						              						<a href="javascript:void(0);" onclick="editItem('${c.temp_id}','${user_key}')" >Edit</a>
						              				</span>
						              			</td>
						              		</tr>
						              	</table>
			              		</td>
			              	</c:otherwise>
			              </c:choose>	
			              <td align="center"><a onclick="removeItem('${c.temp_id}','${user_key}');" href="javascript:void(0);">
			              			<img src="images/remove.png.gif" width="18" height="18" style="vertical-align: middle;">
			              </a></td>
			            </tr>
				    </c:forEach>    
					 <tr>
					    <td align="center" style="padding: 8px; text-align: right;" colspan="7"> <strong>Sub Total : &pound; ${subTotal}</strong> </td>
					  </tr>
					  <tr>
					    <td align="center" style="padding: 8px; text-align: right;" colspan="7"> <strong>Insurance : &pound; ${insurance}</strong> </td>
					  </tr>
					  <tr>
					    <td align="center" style="padding: 8px; text-align: right;" colspan="7"> <strong>VAT @ 20% : &pound; ${vat}</strong> </td>
					  </tr>
			            <tr>
					    <td align="center" style="padding: 8px; text-align: right;" colspan="7"> <strong>Total : &pound; ${total}</strong> </td>
					  </tr>     
				   </c:when>
				   <c:otherwise>
				   			<tr>
			              <td align="center" colspan="7"><strong>Your shopping cart is empty</strong> </td>
			              </tr>
				   </c:otherwise>
				   </c:choose> 
			          </table>
			          			<div id="cartItemRemove_dialog" title="Remove the item from your cart?">
			          					<div id="emsg" class="warning" style="background:#fff url(images/attention.png) no-repeat; border:none;">Are you sure you want to remove this item from your cart?</div>
			          			</div>	
			          
			          
			         		 <c:if test="${fn:length(cartList) > 0 and action == '' }">       
						          <br class="seperator_break">
							      <table width="100%" border="0" cellspacing="0" cellpadding="0" >
							  		<tr>
							    		<!-- <td width="445"><span class="orang_btn"><a href="#">Close and Continue Shopping</a></span></td> -->
							    		<td width="445">&nbsp;</td>
							    		<td align="right" colspan="2"><span class="orang_btn"><a href="placeorder.html">Place Order</a></span></td>
							  		</tr>
						  		  </table>
					 		 </c:if>

			          </div></div>
			          <script type="text/javascript">
			           $(function(){
			      		
			      		$('#cartItemRemove_dialog').dialog({
			      			autoOpen: false,
			      			width: 600,
			      			buttons: {
			      				"Cancel": function() {
			      					$(this).dialog("close");
			      				},
			      				"Remove": function() {
			      					$(this).dialog("close");
			      					removeItemCart();
			      				}
			      			}
			      		});
			      		
			      		}); 
			          </script>
			          <input type="hidden" id="remove_temp_id" />
			          <input type="hidden" id="remove_user_key" />
			          
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:otherwise>

</c:choose>

