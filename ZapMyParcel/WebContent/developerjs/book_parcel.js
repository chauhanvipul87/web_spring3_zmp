var $currentTime = new Date();
	var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
	+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
	+ $currentTime.getDay() + $currentTime.getMonth()
	+ $currentTime.getFullYear() + Math.random();
	

function getBasketDetails(){
	
	$.post("getcartdetails.html?"+"&stamp=" + $timeStamp,function(rsp){
		$("#dialog_cartDetails").html(rsp);
		// this dialog box is in payment jsp file
		$('#dialog_cartDetails').dialog('open');
	});
}	
function trackParcel(){
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";

	var track_id	      = $("#track_id").val();
	if(track_id.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Your Order Id </li>";
	}else{
		if(track_id == 'Please Enter Your Order Id'){
			errorMsg = errorMsg+"<li>Please Enter Your Order Id </li>";
		}
		if(track_id.length > 30){
			errorMsg = errorMsg+"<li>Please Enter Your Order Id less then 30 Digit</li>";
		}
	}
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		$.post("track_order.html?user_order_id="+track_id+"&stamp=" + $timeStamp,function(rsp){
			if(rsp.search("showMessage")>-1){
				humanMsg.displayMsg('Something went wrong while fetching order details.');
			}else{
				$("#dialog_trackParcel").html(rsp);
				// this dialog box is in payment jsp file
				$('#dialog_trackParcel').dialog('open');
			}
			
		});
		
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
}

function getNearestAgent(){

		var $ajaxUrl = "droptoagentdetails.html";
		var $ajaxResponseLayer = "dialog_dropoffagentList";
		$arguments ="action=find";
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
}


function sendMail(){
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";

	var name	      = $("#name").val();
	var phone   	  = $("#phone").val();
	var email	      = $("#email").val();
	var message 	  = $("#message").val();
	
	if(name.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Your Name </li>";
	}else{
		if(name.length > 30){
			errorMsg = errorMsg+"<li>Please Enter Your Name less then 30 Character</li>";
		}
	}
	
	if(phone.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Telephone  Number </li>";
	}else{
		if(phone.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Telephone Number  less then 15 Digit</li>";
		}
	}
	
	if(email.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Email Address</li>";
	}else{
		if(!IsEmail(email.trim())){
				errorMsg = errorMsg+"<li>Please Enter Valid Email Address</li>";	
		}else{
			if(email.length >30){
				errorMsg = errorMsg+"<li>Please Enter Email Address less then 30 Character</li>";
			}
		}
	}
	
	if(message.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Message </li>";
	}else{
		if(message.length > 300){
			errorMsg = errorMsg+"<li>Please Enter Message less then 300 Character</li>";
		}
	}
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		
		var $ajaxUrl = "send_contactmail.html";
		var $ajaxResponseLayer = "error";
		$arguments ="name="+name+"&phone="+phone+"&email="+email+"&message="+message;
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
		
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
}

function goToByScroll(id){
     			$('html,body').animate({scrollTop: $("#"+id).offset().top},'slow');
		}

function showPayPayDetailForm(){
	$("#error").html('');
	
	$("#card_no").val('');
	$("#fname").val('');
	$("#lname").val('');
	$("#smonth").val('MM');
	$("#syear").val('YYYY');
	$("#emonth").val('MM');
	$("#eyear").val('YYYY');
	$("#cvv_no").val('');
	$("#issue_no").val('');
	
	$('#dialog').dialog('open');
}
function ValidatePaymentDetails(){
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	
	var CardType	      = $("#CardType").val();
	var card_no   		  = $("#card_no").val();
	var fname			  = $("#fname").val();
	var lname 	 		  = $("#lname").val();
	var smonth 	 		  = $("#smonth").val();
	var syear			  = $("#syear").val();
	var emonth			  = $("#emonth").val();
	var eyear			  = $("#eyear").val();
	var cvv_no			  = $("#cvv_no").val();
	var issue_no		  = $("#issue_no").val();
	
	
	if(CardType.trim() =='0'){
		errorMsg = errorMsg+"<li>Please Select Card Type</li>";
	}
	if(card_no.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Credit Card Number</li>";
	}
	if(card_no.trim() !='' && CardType.trim() !='0'){
		 if (!checkCreditCard (card_no,CardType)) {
			 errorMsg = errorMsg+"<li>"+ccErrors[ccErrorNo]+"</li>";
		  }
	}
	if(fname.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter First Name </li>";
	}else{
		if(fname.length > 30){
			errorMsg = errorMsg+"<li>Please Enter First Name less then 30 Character</li>";
		}
	}
	if(lname.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Last Name </li>";
	}else{
		if(lname.length > 30){
			errorMsg = errorMsg+"<li>Please Enter Last Name less then 30 Character</li>";
		}
	}
	
	if((smonth == '' || smonth == 'MM')  && (syear =='' || syear == 'YYYY')){
		errorMsg = errorMsg+"<li>Please Enter Start Date </li>";
	}else{
		if((smonth != '' || smonth != 'MM')){
			var month = parseInt(smonth);
			if(month >12){
				errorMsg = errorMsg+"<li>Please Enter Valid Start Month </li>";
			}
			if(smonth.length <2 || smonth.length >2){
				errorMsg = errorMsg+"<li>Please Enter Start Month In MM Format (eg. 05 or 11)</li>";
			}
			if((syear =='' || syear == 'YYYY')){
				errorMsg = errorMsg+"<li>Please Enter Start Year </li>";
			}
		}
		if((syear !='' || syear != 'YYYY')){
			var year = parseInt(syear);
			if(year <2000){
				errorMsg = errorMsg+"<li>Please Enter Start Year Greater than 2000. </li>";
			}
			if(syear.length <4 || syear.length >4){
				errorMsg = errorMsg+"<li>Please Enter Start Year In YYYY Format (eg. 2011 or 2012)</li>";
			}
			if((smonth == '' || smonth == 'MM')){
				errorMsg = errorMsg+"<li>Please Enter Start Month </li>";
			}
		}
	}
	if((emonth == '' || emonth == 'MM')  && (eyear =='' || eyear == 'YYYY')){
		errorMsg = errorMsg+"<li>Please Enter Expiration Date  </li>";
	}else{
		if((emonth != '' || emonth != 'MM')){
			
			var month = parseInt(emonth);
			if(month >12){
				errorMsg = errorMsg+"<li>Please Enter Valid Expiration Month </li>";
			}
			if(emonth.length <2 || emonth.length > 2){
				errorMsg = errorMsg+"<li>Please Enter Expiration Month In MM Format (eg. 05 or 11)</li>";
			}
			
			if((eyear =='' || eyear == 'YYYY')){
				errorMsg = errorMsg+"<li>Please Enter Expiration Year </li>";
			}
		}
		if((eyear !='' || eyear != 'YYYY')){
			
			var year = parseInt(eyear);
			if(year <2000){
				errorMsg = errorMsg+"<li>Please Enter Expiration Year Greater than 2000. </li>";
			}
			if(eyear.length <4 || eyear.length >4){
				errorMsg = errorMsg+"<li>Please Enter Expiration Year In YYYY Format (eg. 2011 or 2012)</li>";
			}
			if((emonth == '' || emonth == 'MM')){
				errorMsg = errorMsg+"<li>Please Enter Expiration Month </li>";
			}
		}
	}
	if(cvv_no.trim() ==''){
		errorMsg = errorMsg+"<li>Please Enter Security Code </li>";
	}else{
		if(cvv_no.length > 10){
			errorMsg = errorMsg+"<li>Please Enter Security Code less then 10 Character</li>";
		}
	}
	
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
			$('#dialog').dialog('close');
			goToByScroll("order_accordion");

			var CardType	      = $("#CardType").val();
			var card_no   		  = $("#card_no").val();
			var fname			  = $("#fname").val();
			var lname 	 		  = $("#lname").val();
			var smonth 	 		  = $("#smonth").val();
			var syear			  = $("#syear").val();
			var emonth			  = $("#emonth").val();
			var eyear			  = $("#eyear").val();
			var cvv_no			  = $("#cvv_no").val();
			var issue_no		  = $("#issue_no").val();
			var order_id          = $("#order_id").val();
			
			var $ajaxUrl = "payfororders.html";
			var $ajaxResponseLayer = "error";
			$arguments ="CardType="+CardType+"&card_no="+card_no+"&fname="+fname+"&lname="+lname+"&smonth="+smonth+"&syear="+syear;
			$arguments = $arguments +"&emonth="+emonth+"&eyear="+eyear+"&cvv_no="+cvv_no+"&issue_no="+issue_no+"&order_id="+order_id;
			
			sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
			return true;
			
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
	
}

function showCardTips(){
	$('#dialog_cardTips').dialog('open');
	
}
function findAddressByPostCode($prefix){
	
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	var postcode = $("#"+$prefix+"postcode").val();
	
	if(postcode.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter PostCode</li>";
	}else{
		if(postcode.length > 10){
			errorMsg = errorMsg+"<li>Please Enter PostCode less then 10 Character</li>";
		}
	}

	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		var $ajaxUrl = "findaddress.html";
		var $ajaxResponseLayer = "error";
		$arguments ="postcode="+postcode+"&prefix="+$prefix;
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return true;
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
}

function buyMore_getInsurance(){
	$('#buy_dialog_insurancecover').dialog('open');
}

function buyMoreOrderItems(){
	var insurance               = $("#buy_insurance_cover").val();
	var package_content  		= $("#buy_package_content").val();
	var value_parcel	 		= $("#buy_value_parcel").val();
	var num_parcel	 		    = $("#buy_num_parcel").val();
	var delivery_instruction    = $("#buy_delivery_instruction").val();
	
	var cart_total_price      = $("#cart_total_price").val();
	var subTotal      		  = $("#subTotal").val();
	var vat      			  = $("#vat").val();
	
	var $arguments = "package_content="+package_content+"&value_parcel="+value_parcel+"&num_parcel="+num_parcel;
	$arguments 	  = $arguments +"&insurance="+insurance+"&delivery_instruction="+delivery_instruction;
	$arguments 	  = $arguments +"&total_price="+cart_total_price+"&subTotal="+subTotal+"&vat="+vat;
	
	var $ajaxUrl = "orderparcels.html";
	var $ajaxResponseLayer = "error";
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
}
function buyMoreOrder(){
	var dname = $("#dname").val();
	if(dname == undefined){ 
		// this happen when all delivery details is already enter then only parcel detail is only remaining.
		var errorMsg ="<h4>Following error(s) occured</h4><ol>";
		var package_content      = $("#buy_package_content").val();
		var value_parcel	     = $("#buy_value_parcel").val();
		var num_parcel	 	     = $("#buy_num_parcel").val();
		var insurance 		     = $("#buy_insurance_cover").val();
		var delivery_instruction = $("#buy_delivery_instruction").val();
		
		if(package_content.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Package Contents</li>";
		}else{
			if(package_content.length > 30){
				errorMsg = errorMsg+"<li>Please Enter Package Contents less then 30 Character</li>";
			}
		}
		if(value_parcel.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Value of Parcel</li>";
		}else{
			if(value_parcel.length > 10){
				errorMsg = errorMsg+"<li>Please Enter Value of Parcel less then 10 Digit</li>";
			}
		}
		/*if(num_parcel.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter No of Parcel</li>";
		}else{
			if(num_parcel.length > 10){
				errorMsg = errorMsg+"<li>Please Enter No of Parcel less then 10 Digit</li>";
			}
		}*/
		if(!document.getElementById("buy_prohibited_rest").checked){
			errorMsg = errorMsg+"<li>Please Select item which is not in Prohibited & restricted items.</li>";
		}
		
		if(!document.getElementById("buy_term_condition").checked){
			errorMsg = errorMsg+"<li>Please Accept all the tems and condition.</li>";
		}
		
		if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
			if(value_parcel.trim() !=""){
				if(value_parcel >50){
					if(insurance ==0.00){
						buyMore_getInsurance();
					}else{
						$("#buy_insurance_cover").val(insurance);
						buyMoreOrderItems();
					}
				}else{
					$("#buy_insurance_cover").val(insurance);
					buyMoreOrderItems();
				}
			}
			return true;
		}else{
			
			errorMsg =errorMsg+"</ol>";
			humanMsg.displayMsg(errorMsg);
			return false;
		}
		
	}else{
		
		var dname				  = $("#dname").val();
		var dpostcode   		  = $("#dpostcode").val();
		var daddress1			  = $("#daddress1").val();
		var dtown 	 		 	  = $("#dtown").val();
		var dcity 	 		      = $("#dcity").val();
		var dcountry			  = $("#dcountry").val();
		var dphone			      = $("#dphone").val();
		
		var spostcode   		  = $("#spostcode").val(); // for validation
		
		var errorMsg ="<h4>Following error(s) occured</h4><ol>";
		var addressFinder_Delivery	  =$("#addressFinder_Delivery").val();
		
		if(dname.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Your Name</li>";
		}else{
			if(dname.length > 30){
				errorMsg = errorMsg+"<li>Please Enter Your Name less then 30 Character</li>";
			}
		}
		if(dpostcode.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter PostCode</li>";
		}else{
			if(dpostcode.length > 10){
				errorMsg = errorMsg+"<li>Please Enter PostCode less then 10 Character</li>";
			}
		}
		
		if(addressFinder_Delivery != undefined &&  addressFinder_Delivery ==0){
			errorMsg = errorMsg+"<li>Please Select Delivery Address.</li>";
		}
		
		if(daddress1.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Address 1</li>";
		}
		if(daddress1.length >30){
			errorMsg = errorMsg+"<li>Please Enter Address less then 30 characters</li>";
		}
		
		if(dtown.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Town</li>";
		}else{
			if(dtown.length > 15){
				errorMsg = errorMsg+"<li>Please Enter Town less then 15 Character</li>";
			}
		}
		
		if(dcity.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter City</li>";
		}else{
			if(dcity.length > 15){
				errorMsg = errorMsg+"<li>Please Enter City less then 15 Character</li>";
			}
		}
		if(dcountry.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter County</li>";
		}else{
			if(dcountry.length > 15){
				errorMsg = errorMsg+"<li>Please Enter County less then 15 Character</li>";
			}
		}
		if(dphone.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Phone</li>";
		}else{
			if(dphone.length > 15){
				errorMsg = errorMsg+"<li>Please Enter Phone less then 15 Digit</li>";
			}
		}
		
		if(spostcode !=""){
			if(spostcode == dpostcode){
				errorMsg = errorMsg+"<li>The collection postcode and delivery postcode can't be same</li>";
			}
		}
		
		if(errorMsg =="<ol>"){
			errorMsg = errorMsg+"<li>Please click on next button to complete the delivery details.</li>";
			errorMsg =errorMsg+"</ol>";
			humanMsg.displayMsg(errorMsg);
			
		}else{
			errorMsg =errorMsg+"</ol>";
			humanMsg.displayMsg(errorMsg);
			return false;
			
		}
		
	}
}
function setAddressFields($prefix){
	var val ;
	if($prefix =='s'){
		 val=$("#addressFinder").val();
	}else{
		 val =$("#addressFinder_Delivery").val();
	}
	if(val ==0){
		humanMsg.displayMsg('<h4>Following error(s) occured</h4><ol><li>Please select address</li></ol>');
	}else{
		$prefix ="#"+$prefix; 
		var $address = val.split(",");
		if($address[0] != null && $address[0] != "" && $address[0] != undefined){
			$($prefix+"address1").val($address[0]);
		 } 
		if($address[1] != null && $address[1] != "" && $address[1] != undefined){
			$($prefix+"town").val($address[1]);
			$($prefix+"city").val($address[1]);
		 } 
		if($address[2] != null && $address[2] != "" && $address[2] != undefined){
			$($prefix+"county").val($address[2]);
		 } 
	}
}

function Screen2(option){
	var sname				  = $("#sname").val();
	var spostcode   		  = $("#spostcode").val();
	var saddress1			  = $("#saddress1").val();
	var saddress2 	 		  = $("#saddress2").val();
	var saddress3			  = $("#saddress3").val();
	var stown 	 		 	  = $("#stown").val();
	var scity 	 		      = $("#scity").val();
	var scounty			      = $("#scounty").val();
	var sphone			      = $("#sphone").val();
	var semail				  = $("#semail").val();
	
	var adddress_postcode	  =$("#addressFinder").val();
	
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	
	if(sname.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Your Name</li>";
	}else{
		if(sname.length > 30){
			errorMsg = errorMsg+"<li>Please Enter Your Name less then 30 Character</li>";
		}
	}
	if(spostcode.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter PostCode</li>";
	}else{
		if(spostcode.length > 10){
			errorMsg = errorMsg+"<li>Please Enter PostCode less then 10 Character</li>";
		}
	}
	if(adddress_postcode != undefined &&  adddress_postcode ==0){
		errorMsg = errorMsg+"<li>Please Select Delivery Address.</li>";
	}
	if(saddress1.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Address 1</li>";
	}
	if(saddress1.length >30){
		errorMsg = errorMsg+"<li>Please Enter Address less then 30 characters</li>";
	}
	if(stown.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Town</li>";
	}else{
		if(stown.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Town less then 15 Character</li>";
		}
	}

	if(scity.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter City</li>";
	}else{
		if(scity.length > 15){
			errorMsg = errorMsg+"<li>Please Enter City less then 15 Character</li>";
		}
	}
	if(scounty.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter County</li>";
	}else{
		if(scounty.length >15){
			errorMsg = errorMsg+"<li>Please Enter County less then 15 Character</li>";
		}
	}
	if(sphone.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Phone</li>";
	}else{
		if(sphone.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Phone Number less then 15 Digit</li>";
		}
	}
	
	if(semail.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Email Address</li>";
	}else{
		if(!IsEmail(semail.trim())){
				errorMsg = errorMsg+"<li>Please Enter Valid Email Address</li>";	
		}else{
			if(semail.length >30){
				errorMsg = errorMsg+"<li>Please Enter Email Address less then 30 Character</li>";
			}
		}
	}
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		//alert(option);
		if(option == 1){
			$( "#accordion" ).accordion({ active: 1 });
		}
		return true;
	}else{
		$( "#accordion" ).accordion({ active: 0 });
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
}

function Screen3(option){
	var lastbutton  	      = $("#lastbutton").val();
	if(lastbutton == undefined){
		var dname				  = $("#dname").val();
		var dpostcode   		  = $("#dpostcode").val();
		var daddress1			  = $("#daddress1").val();
		var daddress2 	 		  = $("#daddress2").val();
		var daddress3			  = $("#daddress3").val();
		var dtown 	 		 	  = $("#dtown").val();
		var dcity 	 		      = $("#dcity").val();
		var dcountry			  = $("#dcountry").val();
		var dphone			      = $("#dphone").val();
		var country_id            = $("#country_id").val();
		
		var spostcode   		  = $("#spostcode").val(); // for validation
		
		var errorMsg ="<h4>Following error(s) occured</h4><ol>";
		var addressFinder_Delivery	  =$("#addressFinder_Delivery").val();
		
		if(dname.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Your Name</li>";
		}else{
			if(dname.length > 30){
				errorMsg = errorMsg+"<li>Please Enter Your Name less then 30 Character</li>";
			}
		}
		if(dpostcode.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter PostCode</li>";
		}else{
			if(dpostcode.length > 10){
				errorMsg = errorMsg+"<li>Please Enter PostCode less then 10 Character</li>";
			}
		}
		
		if(addressFinder_Delivery != undefined &&  addressFinder_Delivery ==0){
			errorMsg = errorMsg+"<li>Please Select Delivery Address.</li>";
		}
		
		if(daddress1.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Address 1</li>";
		}
		if(daddress1.length >=30){
			errorMsg = errorMsg+"<li>Please Enter Address less then 30 characters</li>";
		}
		
		if(dtown.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Town</li>";
		}else{
			if(dtown.length > 10){
				errorMsg = errorMsg+"<li>Please Enter Town less then 10 Character</li>";
			}
		}
		
		if(dcity.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter City</li>";
		}else{
			if(dcity.length > 10){
				errorMsg = errorMsg+"<li>Please Enter City less then 10 Character</li>";
			}
		}
		if(dcountry.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter County</li>";
		}else{
			if(dcountry.length > 10){
				errorMsg = errorMsg+"<li>Please Enter County less then 10 Character</li>";
			}
		}
		if(dphone.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter Phone</li>";
		}else{
			if(dphone.length > 15){
				errorMsg = errorMsg+"<li>Please Enter Phone less then 10 Digit</li>";
			}
		}
		
		if(spostcode !=""){
			if(spostcode == dpostcode){
				errorMsg = errorMsg+"<li>The collection postcode and delivery postcode can't be same</li>";
			}
		}
		if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
			//alert(option);
			var arguments ="dname="+dname+"&dpostcode="+dpostcode+"&daddress1="+daddress1+"&daddress2="+daddress2;
				arguments = arguments +"&daddress3="+daddress3+"&dtown="+dtown+"&dcity="+dcity+"&dcountry="+dcountry+"&dphone="+dphone+"&country_id="+country_id;
			//alert(arguments);
			$.get('add_deliveryaddress.html?'+arguments, function(data) {
				//alert('Load was performed.');  
				$('#delivery_address_result').html(data);
				  
			});
			/*if(option == 2){
				$( "#accordion" ).accordion({ active: 2 });
			}*/
			return true;
		}else{
			$( "#accordion" ).accordion({ active: 1 });
			errorMsg =errorMsg+"</ol>";
			humanMsg.displayMsg(errorMsg);
			/*$(".error1").html(errorMsg);*/
			/*$("#msg").show();*/
			return false;
		}
	}else{
		return true;
		
	}
	
}
function changeScreen(){
	$( "#accordion" ).accordion({ active: 2 });
}
function selectAgent(collection_type){
	if(collection_type == "droptoagent"){
		var $ajaxUrl = "droptoagentdetails.html";
		var $ajaxResponseLayer = "dialog_dropoffagentList";
		$arguments ="";
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}
}
function finalScreen(){
	var msg1 = Screen2(3);
	if(msg1){
		var msg2 = Screen3(3);
		if(msg2){
			var errorMsg ="<h4>Following error(s) occured</h4><ol>";
			
			var package_content  = $("#package_content").val();
			var value_parcel	 = $("#value_parcel").val();
			var num_parcel	 	 = $("#num_parcel").val();
			var insurance 		 = $("#insurance_cover").val();
			var delivery_instruction = $("#delivery_instruction").val();
			
			if(package_content.trim() ==""){
				errorMsg = errorMsg+"<li>Please Enter Package Contents</li>";
			}else{
				if(package_content.length > 30){
					errorMsg = errorMsg+"<li>Please Enter Package Contents less then 30 Character</li>";
				}
			}
			if(value_parcel.trim() ==""){
				errorMsg = errorMsg+"<li>Please Enter Value of Parcel</li>";
			}else{
				if(value_parcel.length > 10){
					errorMsg = errorMsg+"<li>Please Enter Value of Parcel less then 10 Digit</li>";
				}
			}
			/*if(num_parcel.trim() ==""){
				errorMsg = errorMsg+"<li>Please Enter No of Parcel</li>";
			}else{
				if(num_parcel.length > 10){
					errorMsg = errorMsg+"<li>Please Enter No of Parcel less then 10 Digit</li>";
				}
			}*/
			if(!document.getElementById("prohibited_rest").checked){
				errorMsg = errorMsg+"<li>Please Select item which is not in Prohibited & restricted items.</li>";
			}
			
			if(!document.getElementById("term_condition").checked){
				errorMsg = errorMsg+"<li>Please Accept all the tems and condition.</li>";
			}
			var collection_type ="";
			$('[name=collection_type]:checked').each(function() {
				collection_type =$(this).val();
			});
			
			if(collection_type.trim() ==""){
				errorMsg = errorMsg+"<li>Please Select Collection Type : Drop Your Parcel to Our Agent/Home Collection</li>";
			}
			
			if(collection_type == "droptoagent"){
				agentID = $("#agentID").val();
				if(agentID ==""){
					errorMsg = errorMsg+"<li>Please Select Drop Off Agent.</li>";
					errorMsg =errorMsg+"</ol>";
					humanMsg.displayMsg(errorMsg);
					return false;
				}else{
					$("#msg").hide();
				}
			}else{
				agentID = "";
			}
			
			if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
				//alert(option);
				if(value_parcel.trim() !=""){
					if(value_parcel >50){
						if(insurance ==0.00){
							getInsurance();
						}else{
							$("#insurance_cover").val(insurance);
							 //userRegistration();
							 placeOrders();
						}
					}else{
						$("#insurance_cover").val(insurance);
						placeOrders();
						 //userRegistration();
					}
				}
			
				return true;
			}else{
				errorMsg =errorMsg+"</ol>";
				humanMsg.displayMsg(errorMsg);
				return false;
			}
		}
	}
}

function userRegistration(){
	 $("#remail").val('');
	 $("#rpassword").val('');
	 $("#rcpassword").val('');
	$('#dialog_userRegistration').dialog('open');
}

function placeOrders(){
	var insurance        = $("#insurance_cover").val();
	var package_content  = $("#package_content").val();
	var value_parcel	 = $("#value_parcel").val();
	var num_parcel	 	 = $("#num_parcel").val();
	var delivery_instruction = $("#delivery_instruction").val();

	var collection_type ="";
	$('[name=collection_type]:checked').each(function() {
		collection_type =$(this).val();
		if(collection_type == "droptoagent"){
			agentID = $("#agentID").val();
		}else{
			agentID = "";
		}
	});
	
	var sname				  = $("#sname").val();
	var spostcode   		  = $("#spostcode").val();
	var saddress1			  = $("#saddress1").val();
	var saddress2 	 		  = $("#saddress2").val();
	var saddress3			  = $("#saddress3").val();
	var stown 	 		 	  = $("#stown").val();
	var scity 	 		      = $("#scity").val();
	var scounty			      = $("#scounty").val();
	var sphone			      = $("#sphone").val();
	var semail				  = $("#semail").val();
	var cart_total_price      = $("#cart_total_price").val();
	var subTotal      		  = $("#subTotal").val();
	var vat      			  = $("#vat").val();
	

	var arguments = "sname="+sname+"&spostcode="+spostcode+"&saddress1="+saddress1+"&saddress2="+saddress2+"&saddress3="+saddress3;
	arguments = arguments +"&stown="+stown+"&scity="+scity+"&scounty="+scounty+"&sphone="+sphone+"&semail="+semail;
	arguments = arguments +"&package_content="+package_content+"&value_parcel="+value_parcel+"&num_parcel="+num_parcel;
	arguments = arguments +"&insurance="+insurance+"&delivery_instruction="+delivery_instruction+"&collection_type="+collection_type;
	arguments = arguments +"&agentID="+agentID+"&total_price="+cart_total_price+"&subTotal="+subTotal+"&vat="+vat;
	
	var $ajaxUrl = "orderparcels.html";
	var $ajaxResponseLayer = "error";
	$arguments =arguments;
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
}

function getInsurance(){
	$('#dialog_insurancecover').dialog('open');
}
function selectOffer(country,weight,price){
	$("#frm_country").val(country);
	$("#frm_weight").val(weight);
	$("#frm_price").val(price);

	/* this form place in footer.jsp because left side 
	 * send parcel to uk is also used this form, so its a common to both of them.
	 */
	document.forms["frm_specification"].submit();
}
function getPriceDetails(countryName){
	
	$("#countryName").val(countryName);
	document.forms["frm_getPriceDetails"].submit();
}
function removeItem(temp_id,userkey){
	$('#cartItemRemove_dialog').dialog('open');
	$("#remove_temp_id").val(temp_id);
	$("#remove_user_key").val(userkey);
}

function removeItemCart(){
	
	var temp_id =$("#remove_temp_id").val();
	var userkey =$("#remove_user_key").val();
	var $ajaxUrl = "removeItem_Cart.html";
	var $ajaxResponseLayer = "item_cart";
	var arguments ="user_key="+userkey+"&temp_id="+temp_id;
	sendRequest($ajaxUrl, arguments, "",$ajaxResponseLayer);
	return false;
}


function editItem(temp_id,userkey){
	var $ajaxUrl = "editItem_Cart.html";
	var $ajaxResponseLayer = "item_cart";
	$arguments ="user_key="+userkey+"&temp_id="+temp_id;
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}
function updateItem(temp_id,userkey){
	
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	var qty = $("#qty").val();
	
	if(qty.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Quantity</li>";
	}else{
		if(qty.length > 10){
			errorMsg = errorMsg+"<li>Please Enter quantity less then 10 Digit</li>";
		}
	}
	if(qty.trim() !=""){
		if(qty  == 0){
			errorMsg = errorMsg+"<li>Please Enter Quantity greater than zero</li>";
		}
	}
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		var $ajaxUrl = "updateItem_Cart.html";
		var $ajaxResponseLayer = "item_cart";
		
		var cart_total_price      = $("#cart_total_price").val();
		var subTotal      		  = $("#subTotal").val();
		var vat      			  = $("#vat").val();
		
		var $arguments = "total_price="+cart_total_price+"&subTotal="+subTotal+"&vat="+vat;
		$arguments = $arguments+"&user_key="+userkey+"&temp_id="+temp_id+"&quantity="+qty;
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		return false;
	}
}
function showCart(userkey){
	var $ajaxUrl = "showCart.html";
	var $ajaxResponseLayer = "item_cart";
	$arguments ="user_key="+userkey;
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}

function showCartPlaceOrder(){
	// this method is used in two places at a time of 
	//loading placeorder.jsp page and second when change the insurance value 
	var $ajaxUrl = "showCart.html";
	var $ajaxResponseLayer = "item_cart";
	var insurance_cover = $("#insurance_cover").val();
	if(insurance_cover == undefined){
		insurance_cover="0.00";
	}
	$arguments ="action=14927110-1b7b-4092-8a58-5435c731ba76"+"&insuranceVal="+insurance_cover;
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}

function validateQuoteRequest(){
	var quote_name				  = $("#quote_name").val();
	var quote_email   			  = $("#quote_email").val();
	var quote_contant_no		  = $("#quote_contant_no").val();
	var quote_destination_country = $("#quote_destination_country").val();
	var quote_comments 	 		  = $("#quote_comments").val();
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	
	if(quote_name.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Your Name</li>";
	}else{
		if(quote_name.length > 30){
			errorMsg = errorMsg+"<li>Please Enter Your Name less then 30 Character</li>";
		}
	}
	if(quote_email.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Email Address</li>";
	}else{
		if(!IsEmail(quote_email.trim())){
				errorMsg = errorMsg+"<li>Please Enter Valid Email Address</li>";	
		}else{
			if(quote_email.length >30){
				errorMsg = errorMsg+"<li>Please Enter Email Address less then 30 Character</li>";
			}
		}
	}
	if(quote_contant_no.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Contanct Number</li>";
	}else{
		if(quote_contant_no.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Contanct Number less then 15 Digit</li>";
		}
	}
	if(quote_destination_country.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Destination Country</li>";
	}else{
		if(quote_destination_country.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Destination Country less then 15 Character</li>";
		}
	}
	if(quote_comments.length >=200){
		errorMsg = errorMsg+"<li>Please Enter comments less then 200 characters</li>";
	}
	
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		$('#dialog').dialog('close');
		var $arguments ="quote_email="+quote_email+"&quote_contant_no="+quote_contant_no+"&quote_destination_country="+quote_destination_country;
		$arguments 	   = $arguments+ "&quote_name="+quote_name+"&quote_comments="+quote_comments;
		sendQuote($arguments);
		
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
	}
	
}
function validateRegistration(){
	
	var remail			  = $("#remail").val();
	var rpassword   	  = $("#rpassword").val();
	var rcpassword		  = $("#rcpassword").val();
	
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	if(remail.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Email Address</li>";
	}else{
		if(!IsEmail(remail.trim())){
				errorMsg = errorMsg+"<li>Please Enter Valid Email Address</li>";	
		}else{
			if(remail.length >30){
				errorMsg = errorMsg+"<li>Please Enter Email Address less then 30 Character</li>";
			}
		}
	}
	if(rpassword.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Your Password</li>";
	}else{
		if(rpassword.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Your Password less then 15 Character</li>";
		}
	}
	if(rcpassword.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Confirm Your Password</li>";
	}else{
		if(rcpassword.length > 15){
			errorMsg = errorMsg+"<li>Please Enter Confirm Password less then 15 Character</li>";
		}
	}
	if(rcpassword !="" && rpassword !=""){
		if(rpassword != rcpassword){
			errorMsg = errorMsg+"<li>Please Confirm Password does not match with password</li>";
		}
	}
	
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
//		$("#error_loginmsg").hide();
		$('#dialog_userRegistration').dialog('close');
		var arguments = "email="+remail+"&password="+rpassword;
		$.get('userregistration.html?'+arguments, function(data) {
			if(data.trim() == "success"){
				placeOrders();
				
			}else{
				$("#error").html("<div id=\'emsg\' class=\'error\'><script type=\'text/javascript\'>showMessage(\'Something went wrong while processing your request.\');</script></div>");
			}
		});
		
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
//		$(".error2").html(errorMsg);
//		$("#error_loginmsg").show();
	}
	
	
}
function setAgent(agentID){
	 $("#agentID").val(agentID);	
	 $('#dialog_dropoffagentList').dialog('close');
}
function sendQuote($arguments){
	var $ajaxUrl = "request_quote.html";
	var $ajaxResponseLayer = "error";
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
	
}
function addToCart(){
	var weight				  = $("#weight").val();
	var main_price   	 	  = $("#main_price").val();
	var quantity		      = $("#quantity").val();
	var country_id   		  = $("#country_id").val();
	
	var errorMsg ="<h4>Following error(s) occured</h4><ol>";
	if(weight.trim() == ""){
		errorMsg = errorMsg+"<li>Please Enter Weight </li>";
	}else{
		if(weight.length > 10){
			errorMsg = errorMsg+"<li>Please Enter Weight less then 10 Digit</li>";
		}
	}
	if(weight.trim() == 0){
		errorMsg = errorMsg+"<li>Please Enter Weight greater than zero</li>";
	}
	if(quantity.trim() == ""){
		errorMsg = errorMsg+"<li>Please Enter Quantity </li>";
	}else{
		if(quantity.length > 10){
			errorMsg = errorMsg+"<li>Please Enter quantity less then 10 Digit</li>";
		}
	}
	if(quantity.trim() == 0){
		errorMsg = errorMsg+"<li>Please Enter Quantity  greater than zero </li>";
	}
	if(errorMsg =="<h4>Following error(s) occured</h4><ol>"){
		if(parseFloat(weight) > 20){
			 $("#msg").hide();
			 $("#quote_name").val('');
			 $("#quote_email").val('');
			 $("#quote_contant_no").val('');
			 $("#quote_destination_country").val('');
			 $("#quote_comments").val('');
			 $('#dialog').dialog('open');
			 return false;
		}
		if(main_price == undefined){
			main_price ='';
		}
		if(quantity == undefined){
			quantity= 1;
		}
		if(weight == undefined){
			weight= 1;
		}
		
		var $arguments ="weight="+weight+"&main_price="+main_price+"&quantity="+quantity+"&country_id="+country_id;
		//alert($arguments);
		var $ajaxUrl = "addTocart.html";
		var $ajaxResponseLayer = "error";
		sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
		return false;
		
	}else{
		errorMsg =errorMsg+"</ol>";
		humanMsg.displayMsg(errorMsg);
		
	}
}
function showDeliveryAddress(country_id){
	var $ajaxUrl = "showdeliveryaddress_country.html";
	var $arguments ="country_id="+country_id;
	var $ajaxResponseLayer = "dialog_deliveryAddress";
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}

function buy_showCartPlaceOrder(){
	// this method is used in two places at a time of 
	//loading placeorder.jsp page and second when change the insurance value 
	var $ajaxUrl = "showCart.html";
	var $ajaxResponseLayer = "item_cart";
	var insurance_cover = $("#buy_insurance_cover").val();
	if(insurance_cover == undefined){
		insurance_cover="0.00";
	}
	$arguments ="action=14927110-1b7b-4092-8a58-5435c731ba76"+"&insuranceVal="+insurance_cover;
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
	
}
if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}
