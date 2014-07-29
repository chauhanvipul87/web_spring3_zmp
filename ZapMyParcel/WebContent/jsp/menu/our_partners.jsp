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
       <h5>Our Partners</h5>
       <div class="mo_container">
        <p>
        <strong>
         Here at Zap My Parcel we use a range of world trusted couriers to provide the best UK and international parcel deliveries.
         </strong>
         <br><br>
Here are the couriers we know and trust to handle our deliveries.
        </p>
 		 
 <table width="100%" border="0" cellspacing="10" cellpadding="0">
  <tr>
    <td align="center"><img src="images/partner_cdl.gif" width="163" height="78"></td>
    <td align="center"><img src="images/partner_dpd.gif" width="152" height="77"></td>
  </tr>
  <tr>
    <td align="center"><img src="images/partner_ups.gif" width="89" height="108"></td>
    <td align="center"><img src="images/partner_royal_mail.gif" width="145" height="98"></td>
  </tr>
  <tr>
    <td align="center"><img src="images/partner_city_link.gif" width="174" height="52"></td>
    <td align="center"><img src="images/partner_dhl.gif" width="190" height="43"></td>
  </tr>
  <tr>
    <td align="center"><img src="images/partner_yodel.gif" width="165" height="43"></td>
    <td align="center"><img src="images/partner_yodel.gif" width="165" height="43"></td>
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

