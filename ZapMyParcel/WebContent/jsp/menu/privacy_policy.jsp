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
       <h5>Privacy Policy</h5>
       <div class="mo_container">

<strong><u>Zap my Parcel respects your right to privacy</u></strong><br><br>
None of the information we collect is passed on to any other parties, except when required as part of the business services we provide for you, or as required under UK law. 
<br><br>
Zap my Parcel will only collect details about you when you place an order. <br><br>
This will include your name and address, your e-mail address, and various other details necessary to complete a transaction and fulfil your order.<br><br>
Zap my Parcel does not collect any information which may be deemed "sensitive personal data" under the Data Protection Act 1998 Zap my Parcel will retain the details that you provide to complete a successful order with us. <br><br>
All customers email and postal addresses are automatically entered into a database so they may receive occasional emails from us about our services or general advice which we feel may benefit them. <br><br>
Your submission of an order is deemed to be your consent to provide us with your details. 
We use no other means of data collection.

    
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