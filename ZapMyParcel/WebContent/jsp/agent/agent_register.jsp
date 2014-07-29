<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../home/header.jsp"></jsp:include>

<!--container starts-->
<div id="container">
  <div class="raw">
 	 <jsp:include page="../home/left_panel.jsp"></jsp:include>
<div id="middle_pnl">
      <div class="module">
      	<div id="error"></div>
        <h5>Become a Zap My Parcel agent</h5>
        <center>
        <div id="ajax_loader">
				<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
					/> &nbsp; Processing...Please wait...
		</div>
		</center>
        <div class="mo_container">
          <p>Zap My Parcel is always looking for new agents, if you are considering becoming one why not leave your details so we can get back to you.</p>
          <p>Joining the Zap My Parcel team can help bring several benefits to your business.</p>
          <ul style="margin:0 0 0 10px; padding:0">
            <li>It can help improve your foot traffic</li>
            <li>Make your business more reckognized</li>
            <li>Make you part of a team of leading parcel freight company</li>
          </ul>
          <br>
					<div id="msg" style="display: none;">
						<div class="error">
						</div>
					</div>
					<table width="100%" border="0" cellpadding="2" class="tabuler_forms">
            <tr>
              <th colspan="2" align="left">Personal Details</th>
            </tr>
            <tr>
              <td><b>*</b> Email :</td>
              <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
              <td><b>*</b> Telephone Number :</td>
              <td><input type="text" name="telephone" id="telephone" onkeypress="return isNumberKey(event)"></td>
            </tr>
            <tr>
              <td>Mobile Number :</td>
              <td><input type="text" name="mobile" id="mobile" onkeypress="return isNumberKey(event)"></td>
            </tr>
            <tr>
              <td>How did you hear about us :</td>
              <td><div class="styled-select">
                  <select id="hear">
                    <option value="0">Please Select</option>
                    <option value="friend">Friend</option>
					<option value="link">Link</option>
					<option value="web">Web Search</option>
					<option value="ad">Magazine Ad</option>
					<option value="show">Show</option>
					<option value="shop">Shop</option>
					<option value="other">Other</option>
                  </select>
                </div></td>
            </tr>
          </table>
          <br class="seperator_break">
          <table width="100%" border="0" cellpadding="2" class="tabuler_forms">
            <tr>
              <th colspan="4" align="left">Your Address</th>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Country :</td>
              <td align="left" valign="top">
              <div class="styled-select">
                  <select id="country1">
                    <option value="0">Please Select</option>
                    <c:if test="${fn:length(countryLst) > 0 }">
	                    <c:forEach items="${countryLst}" var="c" >
                    		 <option value="${c.id}">${c.country_name}</option>
                    	</c:forEach>
                    </c:if>
                  </select>
                </div></td>
              <td align="left" valign="top"><b>*</b> Address 1:</td>
              <td align="left" valign="top"><input type="text" name="address1" id="address1"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Postcode :</td>
              <td align="left" valign="top"><div style="float:left;">
                  <input type="text" name="postcode1" id="postcode1">
                  &nbsp;</div>
                <span class="orang_btn"><a href="#">Find</a></span></td>
              <td align="left" valign="top"><b>*</b> Address 2:</td>
              <td align="left" valign="top"><input type="text" name="address2" id="address2"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Select Address :</td>
              <td align="left" valign="top">
              <div class="styled-select">
                  <select id="searched_address">
                    <option>Please Select</option>
                    <option>The second option</option>
                   
                  </select>
                </div></td>
              <td align="left" valign="top">Address 3:</td>
              <td align="left" valign="top"><input type="text" name="address3" id="address3"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Town :</td>
              <td align="left" valign="top"><input type="text" name="town" id="town"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">County :</td>
              <td align="left" valign="top"><input type="text" name="county" id="county"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Postcode :</td>
              <td align="left" valign="top"><input type="text" name="postcode2" id="postcode2"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Country :</td>
              <td align="left" valign="top"><input type="text" name="country2" id="country2"></td>
            </tr>
          </table>
          <br class="seperator_break">
          
          <table width="100%" border="0" cellpadding="2" class="tabuler_forms">
            <tr>
              <th colspan="2" align="left">Security</th>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Password :</td>
              <td align="left" valign="top"><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Confirm Password:</td>
              <td align="left" valign="top"><input type="password" name="cpassword" id="cpassword"></td>
            </tr>
          </table>
          
           <br class="seperator_break">
	           <input type="button" onclick="validateAgentRegister();" value="Register Now" class="orang_btn" /> 
          <div class="clear"></div>
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