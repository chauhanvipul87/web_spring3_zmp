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
        <h5>Register Now</h5>
			<div class="emsg"></div>
        <div class="mo_container">
          <p>Zap My Parcel is always looking for new agents, if you are considering becoming one why not leave your details so we can get back to you.</p>
          <p>Joining the Zap My Parcel team can help bring several benefits to your business.</p>
          <ul style="margin:0 0 0 10px; padding:0">
            <li>It can help improve your foot traffic</li>
            <li>Make your business more reckognized</li>
            <li>Make you part of a team of leading parcel freight company</li>
          </ul>
          <br>
          <table width="100%" border="0" cellpadding="2" class="tabuler_forms">
            <tr>
              <th colspan="2" align="left">Personal Details</th>
            </tr>
            <tr>
              <td><b>*</b> Email :</td>
              <td><input type="text" name="textfield" id="textfield"></td>
            </tr>
            <tr>
              <td><b>*</b> Telephone Number :</td>
              <td><input type="text" name="textfield2" id="textfield2"></td>
            </tr>
            <tr>
              <td>Mobile Number :</td>
              <td><input type="text" name="textfield3" id="textfield3"></td>
            </tr>
            <tr>
              <td>How did you hear about us :</td>
              <td><div class="styled-select">
                  <select>
                    <option>Please Select</option>
                    <option>The second option</option>
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
              <td align="left" valign="top"><div class="styled-select">
                  <select>
                    <option>Please Select</option>
                    <option>The second option</option>
                  </select>
                </div></td>
              <td align="left" valign="top"><b>*</b> Address 1:</td>
              <td align="left" valign="top"><input type="text" name="textfield4" id="textfield4"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Postcode :</td>
              <td align="left" valign="top"><div style="float:left;">
                  <input type="text" name="textfield2" id="textfield2">
                  &nbsp;</div>
                <span class="orang_btn"><a href="#">Find</a></span></td>
              <td align="left" valign="top"><b>*</b> Address 2:</td>
              <td align="left" valign="top"><input type="text" name="textfield5" id="textfield5"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Select Address :</td>
              <td align="left" valign="top"><div class="styled-select">
                  <select>
                    <option>Please Select</option>
                    <option>The second option</option>
                  </select>
                </div></td>
              <td align="left" valign="top">Address 3:</td>
              <td align="left" valign="top"><input type="text" name="textfield6" id="textfield6"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Town :</td>
              <td align="left" valign="top"><input type="text" name="textfield7" id="textfield7"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">County :</td>
              <td align="left" valign="top"><input type="text" name="textfield8" id="textfield8"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Postcode :</td>
              <td align="left" valign="top"><input type="text" name="textfield9" id="textfield9"></td>
            </tr>
            <tr>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top">&nbsp;</td>
              <td align="left" valign="top"><b>*</b> Country :</td>
              <td align="left" valign="top"><input type="text" name="textfield10" id="textfield10"></td>
            </tr>
          </table>
          <br class="seperator_break">
          <table width="100%" border="0" cellpadding="2" class="tabuler_forms">
            <tr>
              <th colspan="2" align="left">Security</th>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Password :</td>
              <td align="left" valign="top"><input type="password" name="textfield11" id="textfield11"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Confirm Password:</td>
              <td align="left" valign="top"><input type="password" name="textfield12" id="textfield12"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Memorable Question:</td>
              <td align="left" valign="top"><input type="text" name="textfield13" id="textfield13"></td>
            </tr>
            <tr>
              <td align="left" valign="top"><b>*</b> Answer</td>
              <td align="left" valign="top"><input type="text" name="textfield14" id="textfield14"></td>
            </tr>
          </table>
          <br class="seperator_break">
          		<input type="submit" value="Register Now" class="orang_btn" />
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