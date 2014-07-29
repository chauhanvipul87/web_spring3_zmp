<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left_pnl">
      <div class="module">
        <h5>Specials</h5>
        <div class="mo_container"> 
          <!-- <ul class="countries">
            <li><a href="#">UK</a></li>
            <li><a href="#">Africa</a></li>
            <li><a href="#">Europe</a></li>
            <li><a href="#">Australia</a></li>
            <li><a href="#">India</a></li>
            <li><a href="#">USA/Canada</a></li>
            <li><a href="#">All Others</a></li>
          </ul>-->
          
          <div class="arrowlistmenu">
            <h3 class="menuheader" href="javascript:void(0);" onclick="getPriceDetails('UK');">UK</h3>
            <h3 class="menuheader" href="javascript:void(0);" onclick="getPriceDetails('Africa');">Africa</h3>
            <h3 class="menuheader" href="javascript:void(0);" onclick="getPriceDetails('Australia');">Australia</h3>
            <h3 class="menuheader" href="javascript:void(0);" onclick="getPriceDetails('India');">India</h3>
            <h3 class="menuheader"><a href="javascript:void(0);" onclick="getPriceDetails('USA');">USA</a></h3>
            <h3 class="menuheader"><a href="javascript:void(0);" onclick="getPriceDetails('Canada');" >Canada</a></h3>
            
            <h3 class="menuheader expandable"><strong>All Others</strong></h3>
           <ul class="categoryitems">
		    <li>
			    <div class="styled-select">
			    	<select id="search_paypal_status" style="width: 198px;" onchange="getPriceDetails(this.value);">
			    			<option value="UK" >Select Other Specials </option>
			    			<option value="Argentina" >Argentina</option>
			    			<option value="Ghana">Ghana</option>
			    			<option value="Maldives">Maldives</option>
			    			<option value="Nepal">Nepal</option>
			    			<option value="New Zealand">New Zealand</option>
			    			<option value="Norway">Norway</option>
			    			<option value="Slovakia">Slovakia</option>
			    	</select>
			    </div>
		    </li>
		    </ul>
          </div>
        </div>
      </div>
      <br class="seperator_break">
      <a href="javascript:void(0);" onclick="selectOffer('${countryName}','1','${OneKgPrice}');"><img src="images/send_parcel_UK.gif" ></a> 
      <a href="contact_us.html"><img src="images/register_now.gif" ></a>
    </div>
    <form action="getCountryWisePriceList.html" method="post" id="frm_getPriceDetails" name="frm_getPriceDetails">
    		<input type="hidden" name="countryName" id="countryName" />
    </form>