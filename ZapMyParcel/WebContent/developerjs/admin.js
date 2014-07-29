function viewFullOrderDetails(order_id){
	$.post("viewFullOrderDetails.html?order_id="+order_id,function(rsp){
		if(rsp.search("showMessage_Admin")>-1){
			$("#error").html(rsp);
		}else{
			$("#dialog").html(rsp);
			$('#dialog').dialog('open');
		}
		
	});
}

function optimizedRecord(){
	
	var search_date		 = $("#search_date").val();
	var search_name 	 = $("#search_name").val();
	var search_type 	 = $("#search_type").val();
	var search_zmp_ref	 = $("#search_zmp_ref").val();
	
	var search_carrier 	 = $("#search_carrier").val();
	if(search_carrier ==0){
		search_carrier ="";
	}
	var search_Insurance 	 = $("#search_Insurance").val();
	if(search_Insurance ==0){
		search_Insurance ="";
	}
	var search_paypal_status  = $("#search_paypal_status").val();
	if(search_paypal_status ==0){
		search_paypal_status ="";
	}
	var search_tracking_no 	 = $("#search_tracking_no").val();
	var search_orderstatus = $("#search_orderstatus").val();
	if(search_orderstatus ==0){
		search_orderstatus ="";
	}
	
	var $arguments ="search_date="+search_date+"&search_name="+search_name+"&search_type="+search_type+"&search_zmp_ref="+search_zmp_ref;
	$arguments 	+= "&search_carrier="+search_carrier+"&search_Insurance="+search_Insurance+"&search_paypal_status="+search_paypal_status;
	$arguments 	+= "&search_tracking_no="+search_tracking_no+"&search_orderstatus="+search_orderstatus;
	
	$.post("adminReport.html?"+$arguments,function(rsp){
		$("#reportResponse").html(rsp);
	});
	
}

function getAdminReport(){
	var fromDates = $("#fromDates").val();
	var toDates = $("#toDates").val();
	if(dateValidation()=="false"){
		$("#search_div").hide();
		return false;
	}
	$("#search_div").show();
	$.post("adminReport.html?fromDates="+fromDates+"&toDates="+toDates,function(rsp){
		$("#reportResponse").html(rsp);
	});
}
function editOrderDetail(zapRef){
	var orderStatus = $("#orderStatus_"+zapRef).val();
	var carrierId = $("#carrierId_"+zapRef).val();
	$("#newCarrierId_"+zapRef).val(parseInt(carrierId));
	if(orderStatus != "To be Collected"){
		$("#newStatus_"+zapRef).val(orderStatus);
	}
	$("#editDiv1_"+zapRef).hide();
	$("#editDiv2_"+zapRef).hide();
	$("#updateDiv1_"+zapRef).show();
	$("#updateDiv2_"+zapRef).show();
}

function editSignature(order_id){
	$("#updateSignatureDiv3_"+order_id).show();
	$("#editDiv3_"+order_id).hide();
	
}
function updateSignature(order_id){
	
	var signature = $("#txt_signature_"+order_id).val();
	var args = "?signature="+signature+"&order_id="+order_id;
	$.post("updateSignature.html"+args,function(rsp){
		if(rsp.search("showMessage_Admin")>-1){
			$("#error").html(rsp);
		}else{
			$("#error").html("<div id=\"emsg\" class=\"success\"><script type=\"text/javascript\">showMessage_Admin('Signature updated Successfully');</script></div>");
			$("#response_"+order_id).html(rsp);	
		}
		cancelSignarure(order_id);
	});
}

function cancelSignarure(order_id){
	$("#updateSignatureDiv3_"+order_id).hide();
	$("#editDiv3_"+order_id).show();
}

function updateOrderDetail(zapRef){
	var orderId = zapRef;
	var carrierId = $("#newCarrierId_"+zapRef).val();
	var trackingNo = $("#newTrackingNo_"+zapRef).val();
	var status = $("#newStatus_"+zapRef).val();
	var args = "?orderId="+orderId+"&carrierId="+carrierId+"&trackingNo="+trackingNo+"&status="+status;
	$.post("updateOrderDetail.html"+args,function(rsp){
		if(rsp.search("showMessage_Admin")>-1){
			$("#error").html(rsp);
		}else{
			$("#error").html("<div id=\"emsg\" class=\"success\"><script type=\"text/javascript\">showMessage_Admin('Order updated Successfully');</script></div>");
			$("#response_"+zapRef).html(rsp);	
		}
		calcelOrderDetail(zapRef);
	});
}

function calcelOrderDetail(zapRef){
	$("#editDiv1_"+zapRef).show();
	$("#editDiv2_"+zapRef).show();
	$("#updateDiv1_"+zapRef).hide();
	$("#updateDiv2_"+zapRef).hide();
}
function dateValidation(){
	var fromDates = $("#fromDates").val();
	var toDates = $("#toDates").val();
	if(fromDates=="" && toDates == ""){
		alert("Please select From and To Date.");
	    return "false";
	}
	if(fromDates ==""){
		alert("Please select From Date.");
	    return "false";
	}
	if(toDates ==""){
		alert("Please select To Date.");
	    return "false";
	}
	var temp1="";
	var temp2="";
    var dt1  = fromDates.substring(8,10); 
    var mon1 = fromDates.substring(5,7); 
    var yr1  = fromDates.substring(0,4);  
    var dt2  = toDates.substring(8,10); 
    var mon2 = toDates.substring(5,7); 
    var yr2  = toDates.substring(0,4); 
    temp1 = mon1 +"/"+ dt1 +"/"+ yr1;
    temp2 = mon2 +"/"+ dt2 +"/"+ yr2;
    var cfd = Date.parse(temp1);
    var ctd = Date.parse(temp2);
    var date1 = new Date(cfd); 
    var date2 = new Date(ctd);
    if(date1 > date2){ 
	     alert("From Date should be less than ToDate");
	     return "false";
	} 
    return "true";
}

if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}