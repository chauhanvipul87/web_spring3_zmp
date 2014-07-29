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
					<p>
						<strong>Please select the calculator you wish to use:</strong>
					</p>

					<span class="orang_btn" style="margin-right: 10px;"><a
						href="UKVolume.html">UK Volume Calculator</a>
					</span> <span class="orang_btn"><a href="InternationalVolume.html">International	Volume Calculator</a>
					</span>
					<div class="clear"></div>

					<br>
				<form id="volumetricweight" name="volumetricweight">
					<table width="100%" border="0" cellpadding="5"
						style="border: 1px solid #dedede;">
						<tr>
							<th colspan="2" bgcolor="#dedede">UK Volume Calculator</th>
						</tr>
						<tr>
							<td>Please Enter Dimensions:</td>
							<td valign="middle">
							
							<input size="5" type="text"  name="width" id="width" value="0"
							onfocus="if (this.value==this.defaultValue) this.value = ''"
							onblur="if (this.value=='') this.value = this.defaultValue"
							onkeypress="return isNumberKey(event)"> X 
							<input size="5" type="text" name="length" id="length" value="0"
							onfocus="if (this.value==this.defaultValue) this.value = ''"
							onblur="if (this.value=='') this.value = this.defaultValue"
							onkeypress="return isNumberKey(event)"> X 
							<input size="5" type="text" name="height" id="height" value="0"
							onfocus="if (this.value==this.defaultValue) this.value = ''"
							onblur="if (this.value=='') this.value = this.defaultValue" 
							onkeypress="return isNumberKey(event)">
							</td>
						</tr>
						<tr>
							<td>Please Select Measurement:</td>
							<td>
							<select name="measurement" id="measurement">
									<option value="1">Centimetres</option>
									<option value="2">Inches</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>Cubic Feet:</td>
							<td><input type="text" name="result" id="result">
							</td>
						</tr>
						<tr>
							
							<td colspan="1"></td>
							<td ><span class="orang_btn" style="margin-right:10px;">
							<a href="javascript:void(0);" onclick="calc_weight()">Calculate</a>
							</span>
							<input class="orang_btn" type="reset" value="Reset"/></td>
						</tr>
					</table>
			</form>
					<br>

					<p>Parcel must not exceed 4 cubic feet OR 1.2m in length</p>
					<p>Warning: Parcels that are oversized/ overweight/over length
						may cause considerable delay and/or damage to both yours and other
						people's parcels. Please be aware that oversized/overweight/over
						length items are not covered when sent using our service unless
						they are declared beforehand.</p>

				</div>
			</div>

			<br class="seperator_break">

			<div class="module">
				<h5>About Volume Calculator</h5>
				<div class="mo_container">
					<p>Our parcel volume calculator is an important tool for
						customers to use as by using it you will then be able to use our
						quick quote section as you will have the weight of your package.
						You need to choose whether you want to use the UK calculator or
						the international calculator as they both give you the
						measurements.</p>

					<!-- <p>
						<a href="#">more..</a>
					</p> -->


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
<script type="text/javascript" >

function isNumberKey(evt)
{
	var e = evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
   
    if(charCode == 46){
    	return true;
    }
    if((charCode >=35 && charCode <38) || charCode ==39 || e.which ==0){
    	return true;
    }
    
    if ((charCode > 31) && (charCode < 48 || charCode > 57) ){
   	       alert('Only digit is allowed.');
   	       return false;
   	 }else{
	   return true;
     }
    
}


function calc_weight(){
	
	if($("#measurement").val()=='1'){
		document.volumetricweight.result.value=((document.volumetricweight.width.value)*(document.volumetricweight.length.value)*(document.volumetricweight.height.value)/6000);
	}
	
	
}
</script>
</body>
</html>