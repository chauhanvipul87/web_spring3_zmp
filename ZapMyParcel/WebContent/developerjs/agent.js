function validateAgentRegister(){
	
	
	var email				= $("#email").val();
	var telephone   		= $("#telephone").val();
	var mobile 		 		= $("#mobile").val();
	var hear 		 		= $("#hear").val();
	
	var country1 	 		= $("#country1").val();
	var postcode1	 		= $("#postcode1").val();
	var searched_address 	= $("#searched_address").val();
	
	var address1			= $("#address1").val();
	var address2			= $("#address2").val();
	var address3			= $("#address3").val();
	var town				= $("#town").val();
	var county				= $("#county").val();
	var postcode2			= $("#postcode2").val();
	var country2			= $("#country2").val();
	var password			= $("#password").val();
	var cpassword			= $("#cpassword").val();
	
	var errorMsg ="<ol>";
	
	if(email.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Email Address</li>";
	}else{
		if(!IsEmail(email.trim())){
				errorMsg = errorMsg+"<li>Please Enter Valid Email Address</li>";	
		}
	}
	if(telephone.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Telephone Number</li>";
	}
	if(country1.trim() == 0 && country2.trim()== ""){
		errorMsg = errorMsg+"<li>Please Select Country</li>";
	}
	if(address1.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Address 1</li>";
	}
	if(address2.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Address 2</li>";
	}
	if(town.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Town</li>";
	}
	if(postcode2.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Postcode</li>";
	}
	if(country1.trim() == 0){
		if(country2.trim() ==""){
			errorMsg = errorMsg+"<li>Please Enter country</li>";
		}
	}
	if(password.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Password </li>";
	}
	if(cpassword.trim() ==""){
		errorMsg = errorMsg+"<li>Please Enter Confirm  Password </li>";
	}
	if(password != "" && cpassword != ""){
		if(password != cpassword){
			errorMsg = errorMsg+"<li>Password does not match the confirm password</li>";	
		}else{
			if(password.length >15){
				errorMsg = errorMsg+"<li>Please Enter Password less then 15 characters</li>";	
			}
		}
	}
	if(errorMsg =="<ol>"){
		$("#msg").hide();
		var $arguments ="email="+email+"&telephone="+telephone+"&mobile="+mobile+"&hear="+hear;
		$arguments = $arguments +"&country1="+country1+"&postcode1="+postcode1+"&address1="+address1+"&address2="+address2;
		$arguments = $arguments +"&address3="+address3+"&town="+town+"&county="+county+"&postcode2="+postcode2;
		$arguments = $arguments +"&country2="+country2+"&password="+password;
		addAgent($arguments);
		
	}else{
		$(".error").html(errorMsg);
		$("#msg").show();
	}
}

function addAgent($arguments){
	var $ajaxUrl = "addAgent.html";
	var $ajaxResponseLayer = "error";
	sendRequest($ajaxUrl, $arguments, "",$ajaxResponseLayer);
	return false;
}


function sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
	
	var $currentTime = new Date();
	var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
	+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
	+ $currentTime.getDay() + $currentTime.getMonth()
	+ $currentTime.getFullYear() + Math.random();
	
	 $.ajax({
		url : $ajaxUrl + "?" + $arguments + "&stamp=" + $timeStamp,
		cache : false,
		beforeSend : function() {
			/* call method before send request to server  */
			showProgressBar();
		},
		complete : function($response, $status) {
			/* hide progress bar once we get response */
			hideProgressBar();
			if ($status != "error" && $status != "timeout") {
				/* for set response in div */
				if($ajaxResponseLayer !="")  
			    {	
//					alert('$ajaxUrl ::'+$ajaxUrl);
					//return checkAuthentication($response);
					if($response.responseText.search("showMessage")>-1){
//						alert('in if');
						humanMsg.displayMsg($response.responseText.trim());
						//$("#error").html($response.responseText.trim());
						processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response.responseText);
						return false;
					}
					if($ajaxUrl == 'payfororders.html'){
						if($response.responseText.search("success")> -1){
							showProgressBar();
							setTimeout(
								  function() 
								  {
									  hideProgressBar();
								    //do something special
									 document.forms["frm_paymentConfrim"].submit();									  
								  }, 3000);
							return false;
						}else{
							if($response.responseText.search("Something went wrong while processing your request.")> -1){
								humanMsg.displayMsg($response.responseText.trim());
								return false;
							}else{
								$("#"+$ajaxResponseLayer).html($response.responseText);
								return false;
							}
						}
					}
					if($ajaxUrl =='findaddress.html'){
						var results = $arguments.split("&");
					//	alert(results[1]);
						if(results[1]== 'prefix=s'){
							$("#appendAddress").after($response.responseText.trim());
					    }else{
					    	$("#appendAddress_delivery").after($response.responseText.trim());
					    }
						return false;
					}
					
					if($ajaxUrl =='showdeliveryaddress_country.html'){
						 $('#dialog_deliveryAddress').dialog('open');
					}
					if($ajaxUrl =='droptoagentdetails.html'){
						 $('#dialog_dropoffagentList').dialog('open');
					}

					$("#"+$ajaxResponseLayer).html($response.responseText);
					processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response.responseText);
				}
				
			}
		},
		error : function($obj) {
			/* call when error occurs */
			hideProgressBar();
			alert("Something went wrong while processing your request."+$obj.responseText);
		}
	});  
}

function processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response){
	if($ajaxUrl =='addTocart.html'){
			goToByScroll("item_cart");
			$("#user_key").val($("#temp_key").val());
			var key = $("#user_key").val();
			showCart(key);
	}
	
	if($ajaxUrl =='showCart.html'){
		$("#itemInBasket").html("Items&nbsp;in&nbsp;Basket&nbsp;("+$("#basketsize").val()+")");
	}
	if($ajaxUrl =='updateItem_Cart.html' || $ajaxUrl =='removeItem_Cart.html'){
		showCart("");
		goToByScroll("item_cart");
	}
	
	if($ajaxUrl =='orderparcels.html'){
		if($response.search("showMessage")>-1){
			if($response.search("success")>-1){
				showProgressBar();
					setTimeout(
						  function() 
						  {
							  hideProgressBar();
						    //do something special
							  document.forms["frm_showOrderDetails"].submit();
						  }, 4000);
			}
	  }
	}
}

function showProgressBar() {
	//$("#ajax_loader").attr("display", "block");
		document.getElementById('ajax_loader').style.display = 'block';
	}

function hideProgressBar() {
	//alert('in hide progress');
	 document.getElementById('ajax_loader').style.display = 'none';
}

function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
}

function onlyIntegerNumber(evt){
	
	var e = evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
   
    if((charCode >=35 && charCode <38) || charCode ==39 || e.which ==0){
    	return true;
    }
    
    if ((charCode > 31) && (charCode < 48 || charCode > 57) ){
    		
    	   humanMsg.displayMsg("Please enter only digit (0-9)");
   	       //alert('Only digit is allowed.');
   	       return false;
   	 }else{
	   return true;
     }
	
}


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
    		humanMsg.displayMsg("Allow only numeric characters (0-9 or .)");
   	       return false;
   	 }else{
	   return true;
     }
}
if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}