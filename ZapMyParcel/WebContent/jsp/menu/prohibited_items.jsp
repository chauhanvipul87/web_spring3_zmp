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
        <h5>Prohibited Items</h5>
        <div class="mo_container">
          <p>The following items(or any item similar in description of content) can not be carried any service.
Any person sending such an item may be subject to their ordering being cancelled without notice</p>
         <ul class="list">
         <li>Aerosol</li>
<li>Aftershaves</li>
<li>Air Bag</li>
<li>Alcoholic Beverages</li>
<li>Ammunition</li>
<li>Animals Of Any Form (Alive Or Dead)</li>
         </ul>
         
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
