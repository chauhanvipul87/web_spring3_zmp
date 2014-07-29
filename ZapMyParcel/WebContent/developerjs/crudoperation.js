
var $currentTime = new Date();
		var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
		+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
		+ $currentTime.getDay() + $currentTime.getMonth()
		+ $currentTime.getFullYear() + Math.random();
	
function viewDetail($id,$action){
	var $ajaxUrl=$action;
    var $arguments=preparedArguments($ajaxUrl,$id);
    //alert("ViewDetails : "+$ajaxUrl+" : "+$arguments);
    var $ajaxResponseLayer="";
    if($ajaxUrl == 'viewjobbag'){
    	sendReq($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
    }else{
    	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
    }
	return false;
} 

function viewDivisionDetail(id,$action){
	var $ajaxUrl=$action;
    var $arguments="division_id="+id;
    //alert("ViewDetails : "+$ajaxUrl+" : "+$arguments);
    var $ajaxResponseLayer="";    
    	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);    
	return false;
} 

function closeDialog(){
	$.modal.close();
}

function editDetail($id,$action){
	var $ajaxUrl=$action;
//	alert($ajaxUrl);
    var $arguments=preparedArguments($ajaxUrl,$id);
    
//    $arguments="";
//    alert($arguments);
    var $ajaxResponseLayer="";
  		sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	return false;
}
function editdivisionDetail(id,custid,custcode,$action){
	var $ajaxUrl=$action;
//	alert($ajaxUrl);
	var $arguments="division_id="+id+"&"+"custid="+custid+"&"+"custcode="+custcode;
//    alert($arguments);
    var $ajaxResponseLayer="";
  	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	return false;
}


function addForm($action){
	//alert('in add form ' +$action);
	if($action == 'editProductReplenish.html?action=add'){
		openAddProdReplDiv();
	}
	else{
		var cpage;
		var record;
		if(document.getElementById('currentPage') != null){
			cpage = document.getElementById('currentPage').value;
		}else if(document.getElementById('currentPage') == null && document.getElementById('current_Page') != null ){
			cpage = document.getElementById('current_Page').value;
		}
		
		if(document.getElementById('record') != null){
			record = document.getElementById('record').value;
		}
		
		var $ajaxUrl=$action;
	    var $arguments="current_page="+cpage+"&"+"record="+record;
	    var $ajaxResponseLayer="";
	    
		if($ajaxUrl.search("\\?") != -1){
			var temp=$ajaxUrl.split("?");
			$arguments += "&" + temp[1];
		}
		if($action =='showAddEtranetUserForm.html'){
			$arguments +="&customer_code="+$("#selectclient").val();
		}
		sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
		return false;
	}
}

function adddivisionForm(id,code,$action){
	
		var cpage;
		var record;
		if(document.getElementById('currentPage') != null){
			cpage = document.getElementById('currentPage').value;
		}
		
		if(document.getElementById('record') != null){
			record = document.getElementById('record').value;
		}
		var $ajaxUrl=$action;
		 var $arguments="current_page="+cpage+"&"+"record="+record+"&"+"customerid="+id+"&"+"custcode="+code;
	//	alert($ajaxUrl);
	    
	    var $ajaxResponseLayer="";
	  	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
		return false;

	/*}*/
}

function deleteDetail($id,$ajaxUrl,$ajaxResponseLayer){
	//alert($id);
	//alert($ajaxUrl);
	//alert($ajaxResponseLayer);
	var html="<table cellspacing=\"7\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"400\">" +
			 "<tr><td colspan='2'>Are you sure you want to delete selected record(s)?</td></tr>" +
			 "<tr><td>&nbsp;</td></tr> "+
			 "<tr><td width=\"50%\" class=\"label\"><input type=\"button\" value=\"Cancel\"  onclick=\"deletecancel();\"/></td>" +
			 "<td  style=\"text-align: left;\"><input type=\"button\" value=\"Proceed\" onClick=\"deleteok('"+$id+"','"+$ajaxUrl+"','"+$ajaxResponseLayer+"');\"/> "+
			 "</td></tr></table> ";
	$.modal(html);
}

function deleteDiv(id,custid,custcode,$ajaxUrl){
	//alert($id);
	//alert($ajaxUrl);
	//alert($ajaxResponseLayer);
	var html="<table cellspacing=\"7\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"400\">" +
			 "<tr><td colspan='2'>Are you sure you want to delete selected record(s)?</td></tr>" +
			 "<tr><td>&nbsp;</td></tr> "+
			 "<tr><td  width=\"50%\" class=\"label\"><input type=\"button\" value=\"Proceed\" onClick=\"deleteDivision('"+id+"','"+custid+"','"+custcode+"','"+$ajaxUrl+"');\"/> "+
			 "</td><td class=\"field\"><input type=\"button\" value=\"Cancel\"  onclick=\"deletecancel();\"/></td></tr></table> ";
	$.modal(html);
}

function deleteDivision(id,custid,custcode, $ajaxUrl){
	var $arguments = "division_id="+id+"&"+"custid="+custid+"&"+"custcode="+custcode+"&current_page="+$("#current_page").val()+"&record="+$("#record").val();
	//alert($arguments);
	var $ajaxResponseLayer="response";
	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	return false;
}


function ActiveDetail($id,$ajaxUrl,$ajaxResponseLayer){
	//alert($id);
	//alert($ajaxUrl);
	//alert($ajaxResponseLayer);
	var html="<table cellspacing=\"7\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"400\">" +
			 "<tr><td colspan='2'>Are you sure you want to Active / Deactive selected record(s)?</td></tr>" +
			 "<tr><td>&nbsp;</td></tr> "+
			 "<tr><td  width=\"50%\" class=\"label\"><input type=\"button\" value=\"Proceed\" onClick=\"deleteok('"+$id+"','"+$ajaxUrl+"','"+$ajaxResponseLayer+"');\"/> "+
			 "</td><td class=\"field\"><input type=\"button\" value=\"Cancel\"  onclick=\"deletecancel();\"/></td></tr></table> ";
	$.modal(html);
}

function deletecancel(){
	$.modal.close(); // must call this!

}
function deleteok($id,$ajaxUrl,$ajaxResponseLayer){
	//alert($id);
	//alert($ajaxUrl);
	//alert($ajaxResponseLayer);
    var $arguments=preparedArguments($ajaxUrl,$id);
   // alert($arguments);
    sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	return false;
}
function preparedArguments($ajaxUrl,$id){
//	alert('in prepared Parameter'+$ajaxUrl);
	var $arguments="";
	var $urlparam="";
//	alert($("#record").val());
	if($ajaxUrl == 'deletecostcode.html' || $ajaxUrl == 'deleteall.html' || $ajaxUrl == 'deleteallcustomer.html'|| $ajaxUrl == 'holdcustomer.html' || $ajaxUrl == 'deletecustomer.html'){

		$urlparam=$("#gridform").serialize()+"&";
		$urlparam +=$("#listcodecode").serialize();
  		$arguments="id="+$id+"&"+$urlparam;

	}else if($ajaxUrl == 'searchcostcode.html' || $ajaxUrl == 'list.html' || $ajaxUrl == 'listbranch.html' || $ajaxUrl == 'listlocationtype.html' || $ajaxUrl == 'listuom.html' 
		|| $ajaxUrl == 'listProductCategory.html' || $ajaxUrl == 'listProduct.html' || $ajaxUrl == 'customerInvAccList.html' || $ajaxUrl == 'listorderspicking.html'
		|| $ajaxUrl == 'listProductReplenish.html' || $ajaxUrl == 'listProductCategory.html' || $ajaxUrl == 'SearchProductList.html' || $ajaxUrl == 'customerInvAccList.html' || $ajaxUrl == 'listorderspicking.html' 
		|| $ajaxUrl == 'listjobbag.html' || $ajaxUrl ==  'listlocation.html'  ){
			
			$urlparam =$("#listcodecode").serialize()+"&current_page="+$id+"&record="+$("#record").val();
			$arguments=$urlparam;
			//alert($arguments);
	}else if($ajaxUrl == 'listdeliveryaddress.html'){
		//alert($("#deliveryaddress").serialize());
		$urlparam = $("#deliveryaddress").serialize()+"&current_page="+$id+"&record="+$("#record").val();
		$arguments=$urlparam;

	}else if($ajaxUrl == 'listintranetuser.html'){
		$urlparam =$("#searchuser").serialize()+"&current_page="+$id+"&record="+$("#record").val();
		$arguments=$urlparam;
		
	}else if ($ajaxUrl == 'searchdivisionlist.html' || $ajaxUrl == 'searchCostCentreCustomer.html'){
		$urlparam =$("#listcodecodes").serialize()+"&current_page="+$id+"&record="+$("#record").val()+"&";
		//$urlparam +=$("#listcodecodes").serialize();
		$arguments=$urlparam;
		//alert($arguments);
	}
	/*else if($ajaxUrl == 'searchCostCentreCustomer.html'){
		$urlparam =$("#listcodecode").serialize()+"&current_page="+$id+"&record="+$("#record").val()+"&";
		$arguments=$urlparam;
	}*/
	else if($ajaxUrl == 'updateintranetuser.html'){
		$urlparam=$("#gridform").serialize()+"&";
		$urlparam +=$("#searchuser").serialize()+"&"+$id;
		$arguments=$urlparam;

	}else{
		if($ajaxUrl.search("\\?") != -1){
			var temp=$ajaxUrl.split("?");
			$urlparam=temp[1]+"&";
		}
		$urlparam += $("#gridform").serialize();
		//alert($urlparam);
		$arguments="id="+$id+"&"+$urlparam;
		//alert($arguments);
	}
	 return $arguments;	
}

function sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
    //alert($ajaxResponseLayer);
	var $currentTime = new Date();
	var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
	+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
	+ $currentTime.getDay() + $currentTime.getMonth()
	+ $currentTime.getFullYear() + Math.random();

	if($ajaxUrl.search("\\?") != -1){
		var temp=$ajaxUrl.split("?");
	    $ajaxUrl=temp[0];
	}
//	alert($ajaxUrl+$arguments);
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
				if( $ajaxResponseLayer !="")  
			    {	
					//return checkAuthentication($response);
//					alert($ajaxUrl);
//					alert($ajaxResponseLayer);
//					alert($response.responseText.trim());

					if($ajaxUrl == 'putawayprint.html' || $ajaxUrl == 'reprintputawaydetails.html' ){
							//$("#"+$ajaxResponseLayer).html($response.responseText);
							if($response.responseText.search("showMessage")>-1){
								//alert('in if');
								$("#error").html($response.responseText.trim());
								return false;
					     }else{
//							printAjaxResponseContent($response.responseText);
							$("#tabs").tabs("select", 0); //selecting putaway confirmation
							return false;
					     }
					}else{
						var msg=getErrorStatus($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
//						alert(msg);
						if(msg){
						if($response.responseText.search("showMessage") > -1){
//							   alert($response.responseText.trim());
//							    alert($ajaxUrl);
							    $("#error").html($response.responseText.trim());
								   if($ajaxUrl == 'placeorder.html' || $ajaxUrl == 'confirmorder.html'){
									    listUnConfirmOrders();
										$("#tabs").tabs("select", 0);
								   }else{
									   if($ajaxUrl =='addinternetuserprofile.html'){
										   $.modal.close();// must call this!
										   listintranetUser();
									   }else{
										   if($ajaxUrl == 'cancelorder.html'){
												$.modal.close();// must call this!
												listOrders('1','10');
										 }else{
											 if($ajaxUrl =='amendorder.html'){
												$("#tabs").tabs("select", 0);
											 }else{
												 if($ajaxUrl =='cancelgoodsin.html'){
//													 listGoodsin('0','10');
													
												}else{
													if($ajaxUrl == 'productinfofororderamend.html'){
														$("#prodcode").val('');
														$("#selectproducttoenter").show();
														
														
													}else{
														if($ajaxUrl == 'vieworderdetails.html'){
															$("#tabs").show();
															$("#neworderresponse").html('');
															listOrders('1','10');
														}else{
															if($ajaxUrl =='addextranetuser.html' || $ajaxUrl =='editextranetuser.html' || 
																	$ajaxUrl =='deleteextranetuser.html' || $ajaxUrl =='deleteallextranetuser.html'){
																showExtranetUser();
															}
														}
													}
													
												}
											 }
										 }

									   }
								   }
								   return false;
							   }
							
				          }
						}
			 	    		
					// alert("again "+$response.responseText.trim());
					$("#"+$ajaxResponseLayer).html($response.responseText);
					// alert("again 2222"+$response.responseText.trim());
					//	alert('in model to close');
					$.modal.close();// must call this!
					if($ajaxUrl == 'bookingpopup.html'){
						/* next two method is comming from the planning.js */
						 getWeek(); 
						 currentWeek('book');
						 viewBookingPlanning();   // this method is in goodsinoperation.js 
						 $("#tabs").tabs("select", 3);
					}
//					alert('url = ' + $ajaxUrl);
					if($ajaxUrl == 'listProductReplenish.html' || $ajaxUrl == 'updateProductReplenishLevel.html'){
						var qtyCounter   = -1;
						var levelCounter = -1;
						var i;
						//To remove delete action from grid use following code
						
						//alert(document.getElementById("gridtbl").innerHTML);
						if(document.getElementById("gridtbl") != null){
							var tbl = document.getElementById("gridtbl").firstChild; // Gives grid element(table).
							//alert(tbl.innerHTML);
							var fc  = tbl.firstChild; 								 // Gives Headers for grid
							//alert(fc.innerHTML);
							var lc  = fc.lastChild;   								 // Gives Last header text from the grid which would be Action in all cases
							//alert(lc.innerHTML);
							fc.removeChild(lc);       								 // Removes Action header text from the grid.
							//alert("After = " + fc.innerHTML);
							//Check for quantity header
							var childTH = fc.childNodes; 
							for(i = 0; i < childTH.length; i++){
								var chkTHText = childTH[i].innerHTML;
								if(chkTHText == 'Quantity'){
									qtyCounter = i;
								}
								if(chkTHText == 'Replenishment Level'){
									levelCounter = i;
								}
							}
							//Check for quantity header ends
							var tbl1 = document.getElementById("gridtbl").lastChild; // Gives the body of the grid (rows)
							//alert(tbl1.innerHTML);
							// Go through loop till the last record in the grid
							for(i = 0; i < tbl1.childNodes.length; i++){
								var parent = tbl1.childNodes[i]; 					// Gives the single row of the grid starting from first
								//alert('parent ' + i + ' = ' + parent.innerHTML);
								var child    = parent.lastChild; 					// Gives the last element of the current row which will be Delete Action in all case
								//alert('child  = ' + child.innerHTML);
								parent.removeChild(child);		 					// Removes the Delete Action found from current row
								
								//Need to check for Quantity cell
								//if less than level than mark with red color
								var qtyTDText   = parent.childNodes[qtyCounter];
								var levelTDText = parent.childNodes[levelCounter];
								//alert(childTD.innerHTML);
								if(parseInt(qtyTDText.innerHTML) < parseInt(levelTDText.innerHTML)){
									//alert("Qty less than level");
									var j;
									for(j = 0; j < parent.childNodes.length; j++){
										parent.childNodes[j].style.color = '#FF0000';
									}
								}
								//Check for Quantity ends	
							}
						}
						//Also need to remove the deleteall options from pagination table
						//alert(document.getElementById("pagination"));
						if(document.getElementById("pagination") != null){
							var pgtbody = document.getElementById("pagination").firstChild;
							//alert('paginationtbl firstTR = ' + pgtbody.innerHTML);
							var pgtbodyTr = pgtbody.firstChild;
							//alert('Body content = ' + pgtbodyTr.innerHTML);
							var pgtbodyTrTd = pgtbodyTr.firstChild;
							//alert("ActionAll = " + pgtbodyTrTd.innerHTML);
							var pgtbodyTrTdChild = pgtbodyTrTd.childNodes;//Action Images(Export, DeleteAll, Reload)
							pgtbodyTrTd.removeChild(pgtbodyTrTdChild[1]); //Remove DeleteAll Action
						}
					}
					processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response.responseText);
					
				}else{
//					alert($response.responseText);
					$.modal($response.responseText,{onClose:hideError});
//					$("#"+$ajaxResponseLayer).html($response.responseText);
//					$("#"+$popupLayer).dialog('open');
					
				}
			}
		},
		error : function($obj) {
			/* call when error occure */
			hideProgressBar();
			alert("Something went wrong while processing your request.");
		}
	});  
}

function checkAuthentication($response){
	if($response.responseText.search("id=\"login-value\"")>-1){
		var link = window.location.pathname.substring(0,window.location.pathname.lastIndexOf("/")+1);
		link = link +"logout.html";
		window.location.href = link;
		return false;
	}
}

function getErrorStatus($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
	if($ajaxUrl == 'listputawaydetails.html' || $ajaxUrl == 'addnewproduct.html' ||$ajaxUrl == 'placeorder.html' || 
	$ajaxUrl == 'confirmorder.html' || $ajaxUrl == 'viewBookingPlanning.html' || $ajaxUrl == 'arrivedgoodsin.html'
		||$ajaxUrl == 'confirmbooking.html' || $ajaxUrl =='viewBookingPlanning.html'
	         || $ajaxUrl =='arrivedgoodsin.html' || $ajaxUrl =='viewbookingdetails.html' ||
	           $ajaxUrl =='viewcheckedingoodsin.html'  || $ajaxUrl =='listunconfirmedorder.html'
	        	   || $ajaxUrl =='checkedingoodsin.html' || $ajaxUrl == 'prepareconfirmorder.html' 
	        		   || $ajaxUrl =='removeunconfirmedorderproduct.html' || $ajaxUrl =='cancelorder.html'
	        			||  $ajaxUrl =='cancelgoodsin.html' || $ajaxUrl =='amendorder.html'
	        				|| $ajaxUrl =='addinternetuserprofile.html' || $ajaxUrl =='booking.html'
	        					|| $ajaxUrl =='listputaway.html' || $ajaxUrl == 'putaway_extradetails.html' 
	        						|| $ajaxUrl =='addgoodsin.html' || $ajaxUrl == 'addnewproduct_putaway.html' 
	        							|| $ajaxUrl =='showamendorderform.html' || $ajaxUrl == 'editcustomeraddressfororder.html'
	        								|| $ajaxUrl == 'changestatus_customeraddress.html' || $ajaxUrl =='updateaddressfororder.html'
	        									|| $ajaxUrl == 'productinfofororderamend.html' || $ajaxUrl =='vieworderdetails.html' || $ajaxUrl =='deleteallextranetuser.html' 
	        										|| $ajaxUrl == 'editextranetuser.html' || $ajaxUrl =='deleteextranetuser.html' || $ajaxUrl =='addextranetuser.html'){
		
		return true;
	}else{
		return false;
	}
	
}
function processAfterResponse($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer,$response){
	if($ajaxUrl == 'confirmbooking.html'){
		/* next three method are coming from the planning.js */
		getFirstWeekDay();
		getCurrentWeekDates();
		viewGoodsInPlanning();
		$("#tabs").tabs("select", 2);
	}else{
		if($ajaxUrl == 'arrivedgoodsin.html'){
			//showDialog($ajaxResponseLayer);
		}else{
			if($ajaxUrl == 'listunconfirmedorder.html'){
				$("#tabs").tabs("disable", 2);
			}else{
				if($ajaxUrl =='removeunconfirmedorder.html'){
					listUnConfirmOrders('1','10');
				}else{
					if($ajaxUrl == 'addgoodsin.html'){
						 listGoodsin('0','10');
						$("#tabs").tabs("select", 0);
						
					}else{
						if($ajaxUrl == 'prepareamendorder.html'){
							$("#tabs").tabs("select", 1);
							
						}else{
							if($ajaxUrl =='changecustomercode.html'){
								if($response.search("warning")>-1){
									var resultArray=$arguments.split("&");
									var customerStr=resultArray[resultArray.length-1];
									var customercodeArray=customerStr.split("=");
//									alert($arguments);
									$("#clientcode").val(customercodeArray[1]);
								}else{
									var resultArray=$arguments.split("&");
									var customercodeArray=resultArray[0].split("=");
//									alert($arguments);
									$("#clientcode").val(customercodeArray[1]);
								}
							}else{
								if($ajaxUrl == 'moveadvancegoodsin.html' || $ajaxUrl =='cancelbookgoodsin.html'){
									viewGoodsInPlanning();
								}else{
									if($ajaxUrl=='changeproduct_putaway.html'){
										var result=$arguments.split("&");
										var bookingStr=result[0].split("=");
										var $bookingID=bookingStr[1];
//										alert($bookingID);
										var goodsinStr=result[1].split("=");
										var $goodsinID=goodsinStr[1];
										//alert($goodsinID);
										loadafterProductChange($goodsinID,$bookingID);
									}else{
										if($ajaxUrl == 'addnewfinalproducts.html'){
										//	alert('after process');
											validateput_away();
										}else{
											if($ajaxUrl == 'amendorderdetails.html' || $ajaxUrl =='removeproductfromorder.html' 
												 ||$ajaxUrl == 'amendneworder.html' || $ajaxUrl =='amendproductqty.html' ){
												showAmendOrderForm();
											}else{
												if($ajaxUrl == 'addnote.html'){
													var orderid=$("#orderid").val();
													viewOrderDetails(orderid);
												}else{
													if($ajaxUrl =='changeproductstate.html'){
														searchProduct();
													}
													
												}
											}
										}
									 }
								 }
							 }
						  }
					  }
				  }
			  }
		  }
	   }			
}


var hideError = function closer() {

	$.modal.close();
	  //alert("Done!"); 
	}

function showDialog($id){
	$("#"+$id).toggle("slow");
}
function pagination($currpage, $ajaxUrl, $ajaxResponseLayer){
//	alert('in pagination:: '+$currpage);
	//alert("action ::"+$ajaxUrl);
	//alert("container ::"+$ajaxResponseLayer);
    var $arguments=preparedArguments($ajaxUrl,$currpage);
//    alert('in pagination:: '+$arguments);
  	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
  	return false;
}
function switchpage(e,$ajaxUrl,$ajaxResponseLayer,totalpage){
	var kcode = e.keyCode;
	var cpage =  document.getElementById('currentPage').value;

	if(kcode == 13){
		if(cpage > totalpage){
			var page = document.getElementById('current_page').value;
			document.getElementById('currentPage').value = page;
			return false;
		}else{
			pagination(cpage,$ajaxUrl,$ajaxResponseLayer);
			return false;
		}
	}else{
		return false;
	}
}
function checkallstatus(){
	 document.gridform.checkall.checked=false;
}
function togglecheckall()
{
	var field=document.gridform.chkbox;
	var chkall = document.gridform.checkall.checked;
	var len = field.length;
	if(len == undefined){
		if(chkall){
			field.checked = true;
		}else{
			field.checked = false;
		}
	}else{
		if(chkall){
			for(var i=0;i<len;i++){
				field[i].checked = true;
			}
		}else{
			for(var i=0;i<len;i++){
				field[i].checked = false;
			}
		}
	}
}
function deleteallselected($ajaxUrl, $ajaxResponseLayer)
{
    	
	var val = [];
/*	var currpage = $("#currentPage").val();
	var record = $("#record_option").val();
	var arglist = prepareArgumentList(action);*/
	var checkedval="";
	$('input[name=chkbox]').each(function(i)
	{
				val[i] = $(this).val();
				if(this.checked == true)
				{
						checkedval= checkedval  + "," + $(this).val();
				}
	});
	if(checkedval!="")
	{
		deleteDetail(checkedval,$ajaxUrl,$ajaxResponseLayer);
	}
	else
	{
		alert("Please select at least 1 record to proceed.");
		return false;
	}
}

function exportallselected(action){
//alert('in exportALL');
    var val = [];
	var checkedval="";
	$('input[name=chkbox]').each(function(i)
	{
				val[i] = $(this).val();
				if(this.checked == true)
				{
						checkedval= checkedval  + "," + $(this).val();
				}
	});
	if(checkedval!="")
	{ 
		//alert("checkedval:"+checkedval);
		document.getElementById("ids").value=checkedval;
//		alert(document.getElementById("ids").value);
		document.getElementById("export_type").value='csv';
//		alert(action);
//		alert(document.gridform.chkbox);
		document.gridform.action = action;	
		document.gridform.submit();
	}
	else
	{	document.getElementById("ids").value="";
	    document.getElementById("export_type").value="";
		alert("Please select at least 1 record to proceed.");
		
		return false;
	} 
	
}
function callSelectedPage($ajaxUrl,$ajaxResponseLayer){
	var currpage = $("#current_page").val();
	var $arguments=preparedArguments($ajaxUrl,currpage);
	sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
}
function reloadGrid($ajaxUrl,$ajaxResponseLayer,$arguments){
	//alert('in reload grid'+$ajaxResponseLayer);
	if($ajaxUrl != null && $ajaxUrl != ''){
		sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	}
}
function showProgressBar() {
	//$("#ajax_loader").attr("display", "block");
		document.getElementById('ajax_loader').style.display = 'block';
	}

function hideProgressBar() {
	//hideMessage();
	//alert('in hide progress');
	//$("#ajax_loader").hide();
	 document.getElementById('ajax_loader').style.display = 'none';
}


function sendReq($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer){
	
	var $currentTime = new Date();
	var $timeStamp = $currentTime.getHours() + $currentTime.getMinutes()
	+ $currentTime.getSeconds() + $currentTime.getMilliseconds()
	+ $currentTime.getDay() + $currentTime.getMonth()
	+ $currentTime.getFullYear() + Math.random();
	
	if($ajaxUrl.search("\\?") != -1){
		var temp=$ajaxUrl.split("?");
	    $ajaxUrl=temp[0];
	}
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
				if(res == "404"){
					$("#emsg").html('<script type="text/javascript">showMessage("Something went wrong while processing your request. Please try again.")</script>');
					$("#emsg").attr('class','error');
				}else if($ajaxResponseLayer !="")  
			    {
					var res = $response.responseText;
					$("#"+$ajaxResponseLayer).html($response.responseText);
					$.modal.close();// must call this!
				}else{
					$.modal($response.responseText,{onClose:hideError});
				}
			}
		},
		error : function($obj) {
			/* call when error occure */
			hideProgressBar();
			$("#emsg").html('<script type="text/javascript">showMessage("Something went wrong while processing your request. Please try again.")</script>');
			$("#emsg").attr('class','error');
		}
	});  
}

function fillcostcodecombo(){
	var customercode =$("#customercode").val();
	if(customercode == ""){
		alert('Please select customer code');
	}else{
		var $ajaxUrl="fillcostcodedata.html";
	    var $arguments="customercode="+customercode;
	    var $ajaxResponseLayer="fillcostcodecombo";
		sendRequest($ajaxUrl,$arguments,"",$ajaxResponseLayer);
		return false;
	}
}

function listintranetUser(){
	var $ajaxUrl="listintranetuser.html";
    var $arguments="";
    var $ajaxResponseLayer="response";
	sendRequest($ajaxUrl,$arguments,"",$ajaxResponseLayer);
}

if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}

function changeBreadCrumb(str){
	$("#breadcrumb").html('<a href="home.html">Home</a> &raquo; '+str+' &raquo;');
	return false;
}

function putOnHold($id,$ajaxUrl,$ajaxResponseLayer){
    var $arguments=preparedArguments($ajaxUrl,$id);
	if($ajaxUrl != null && $ajaxUrl != ''){
		sendRequest($ajaxUrl,$arguments,$timeStamp,$ajaxResponseLayer);
	}
}

if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}