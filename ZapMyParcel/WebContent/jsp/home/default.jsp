<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../home/header.jsp"></jsp:include>
<!--container starts-->
<div id="container">
  <div class="raw">
  	<jsp:include page="../home/left_panel.jsp"></jsp:include>
<div id="middle_pnl">
			<div id="error"></div>
			 <center>
		         <div id="ajax_loader">
						<img style="vertical-align: middle;" alt="loading..." src="images/loading2.gif" height="35"
							/> &nbsp; Processing...Please wait...
				</div>
			</center>

              <ul id="mycarousel" class="jcarousel-skin-tango">
        <li><a href="#"><img src="images/scrolling_pick.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/city_link.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/city_link.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/royal_mail.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/ups.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/dpd.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/scrolling_pick.gif" width="68" height="68"></a></li>
        <li><a href="#"><img src="images/ups.gif" width="68" height="68"></a></li>
      </ul>
              <br class="seperator_break">
              <div class="orng_box">
        <div></div>
        <div style="float:left;">
                  <table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
                      <!-- <td  align="center" style="display:block; position:relative;"><img src="images/countries.png" ></td> -->
                      <c:choose>
                      	<c:when test="${option == 'getCountryWisePriceList' }">
                      			 <td  align="center" style="display:block; position:relative;"><img src="images/country.png" ></td>
                      	</c:when>
                      	<c:otherwise>
                      			 <td  align="center" style="display:block; position:relative;"><img src="images/specials.png" ></td>
                      	</c:otherwise>
                      
                      </c:choose>
            </tr>
            <c:if  test="${fn:length(countryNamesLst) > 0 }" >
            			<c:forEach var="c" items="${countryNamesLst}">
            				 <tr>
                    			  <td align="center"><div class="country_buttons" onclick="selectOffer('${c}','','');">${c}</div></td>
                    		</tr>	
            			</c:forEach>
            </c:if>
            
          </table>
                </div>
        <div style="float:left;">
                  <table width="100%" border="0" cellpadding="4" cellspacing="0">
            <tr>
                      <td align="center"><img src="images/1kg.png" alt="" /></td>
                      <td align="center"><img src="images/2kg.png" alt="" /></td>
                      <td align="center"><img src="images/5kg.png" alt="" /></td>
                      <td align="center"><img src="images/10kg.png" alt="" /></td>
                      <td align="center"><img src="images/15kg.png" alt="" /></td>
                      <td align="center"><img src="images/20kg.png" alt="" /></td>
                    </tr>
            <c:if  test="${fn:length(countryMap) > 0 }" >
            			<c:forEach var="cName" items="${countryNamesLst}">
						<tr>		   
							<c:set var="cmap" value="${countryMap[cName]}"/>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','1','${cmap.get('1')}');">&pound;${cmap.get('1')} </div></td>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','2','${cmap.get('2')}');">&pound;${cmap.get('2')} </div></td>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','5','${cmap.get('5')}');">&pound;${cmap.get('5')} </div></td>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','10','${cmap.get('10')}');">&pound;${cmap.get('10')} </div></td>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','15','${cmap.get('15')}');">&pound;${cmap.get('15')} </div></td>
							<td align="center"><div class="weight_btn" onclick="selectOffer('${cName}','20','${cmap.get('20')}');">&pound;${cmap.get('20')} </div></td>
						</tr>	
            			</c:forEach>
            </c:if>        
          </table>
                </div>
        <div class="clear"></div>
      </div>
           <br class="seperator_break">
            <c:choose> 
              <c:when test="${option != 'getCountryWisePriceList' }">
              <div class="module">
       			 <h5>About us</h5>
        		 <div class="mo_container">
                  <p> <strong>Welcome to Zap My Parcel your one stop site for UK & International deliveries at the lowest prices.</strong> </p>
                  <p>Here at Zap My Parcel we have UK & International deliveries down to an art. Zap My Parcel was born from a team of professionals with over 20 years' experience in the transport & logistics industry, so we know what a customer needs and wants from a parcel delivery company, High Quality, Low Cost & Reliability! We are able to offer all of these as we have formed key partnerships with the best carrier companies in the market today. </p>
                  <span class="orang_btn"><a href="about.html">Read More</a></span>
                  <div class="clear"></div>
                 </div>
     		 </div>
     		 </c:when>
     		 <c:otherwise>
     		 		<c:if test="${aboutCountryHTML !='' and aboutCountryHTML != null}" >
     		 					${aboutCountryHTML}
     		 		</c:if>
     		 </c:otherwise>
     		</c:choose>
     		 
     		 <div id="item_cart"></div>
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


<script type="text/javascript" >
$(document).ready(function() {
    //show cart.. details
	showCart("");
});

</script>
<!--footer ends-->
</body>
</html>
