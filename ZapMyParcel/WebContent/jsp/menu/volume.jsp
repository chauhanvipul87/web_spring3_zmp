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
       <h5>Parcel Volume Calculator</h5>
       <div class="mo_container">
        <p><strong>Please select the calculator you wish to use:</strong></p>
		
        <span class="orang_btn" style="margin-right:10px;"><a href="UKVolume.html">UK Volume Calculator</a></span>
        
        <span class="orang_btn"><a href="InternationalVolume.html">International Volume Calculator</a></span> 
		<div class="clear"></div>

       </div>
      </div>
      
      <br class="seperator_break">
      
      <div class="module">
       <h5>About Volume Calculator</h5>
       <div class="mo_container">
        <p>Our parcel volume calculator is an important tool for customers to use as by using it you will then be able to use our quick quote section as you will have the weight of your package. You need to choose whether you want to use the UK calculator or the international calculator as they both give you the measurements. </p>
        
     <!--    <p>
          <a href="#">more..</a>
        </p>
		 -->
        
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