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
       <div id="error"></div>
    	<center>
	 		<div id="ajax_loader">
					<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
						/> &nbsp; Processing...Please wait...
			</div>
		</center>
        <h5>Contact Us</h5>
        <div class="mo_container"> If you find that you have a question that can't be answered by our website or our FAQ'S page please feel free to contact us either via phone or email. <br>
          Our office hours are 9am to 5pm, Monday to Friday excluding public holidays in which we are closed. <br>
          If you call is not answered please leave a message and a return contact. <br>
          <br>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="left" valign="top"><strong><h3>Address</h3></strong>
                Zap My Parcel<br>
                Unit 1<br>
                Fitzroy Business Park<br>
                Sandy lane<br>
                Sidcup<br>
                Kent <br>
                DA14 5NL<br></td>
              <td align="left" valign="top"><strong><h3>Phone</h3> </strong>
                02083086960
                <br>
                
                <img src="images/phone.png" width="154" height="131"></td>
            </tr>
            <tr>
              <td colspan="2" align="left" valign="top"><form name="frm_contact" method="post" >
                  <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td colspan="2" style="border-top:1px solid #CCC;"><br>
                        <h3><strong>Leave a message for us to call you</strong></h3></td>
                    </tr>
                    <tr>
                      <td>Name :<b>*</b></td>
                      <td><label for="textfield"></label>
                        <input type="text" name="name" id="name"></td>
                    </tr>
                    <tr>
                      <td>Telephone :<b>*</b></td>
                      <td><input type="text" name="phone" id="phone"  onkeypress="return onlyIntegerNumber(event);"></td>
                    </tr>
                    <tr>
                      <td>Email Address :<b>*</b></td>
                      <td><input size="50" type="text" name="email" id="email"></td>
                    </tr>
                    <tr>
                      <td>Message :<b>*</b></td>
                      <td><textarea name="message" cols="38" rows="4" id="message"></textarea></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td><span class="orang_btn"><a href="javascript:void(0);" onclick="sendMail();" >Post</a></span></td>
                    </tr>
                  </table>
                </form></td>
            </tr>
          </table>
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