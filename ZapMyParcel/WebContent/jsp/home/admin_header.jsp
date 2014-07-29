<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ZMP</title>
	<jsp:include page="../common/commoninclude_admincss.jsp" ></jsp:include>
	<jsp:include page="../common/commoninclude_adminjs.jsp" ></jsp:include>
	<!--menu js-->
	<script type="text/javascript">
 $(document).ready(function(){
	$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)
	$("ul.topnav li span").click(function() { //When trigger is clicked...
	//Following events are applied to the subnav itself (moving subnav up and down)
		$(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click
		$(this).parent().hover(function() {
		}, function(){
			$(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up
		});
		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
	});
});
</script>
	<!--menu js ends-->


	<!--jquery ui and core-->
	<script type="text/javascript">
			$(function(){

				// Accordion
	
				$("#accordion").accordion({ header: "h3", autoHeight: false });
	
				// Tabs
				$('#tabs').tabs();
	

				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() { 
							$(this).dialog("close"); 
						}, 
						"Cancel": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				$('#datepicker').datepicker({
					inline: true
				});
				
				// Slider
				$('#slider').slider({
					range: true,
					values: [17, 67]
				});
				
				// Progressbar
				$("#progressbar").progressbar({
					value: 20 
				});
				
				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				
			});
		</script>
	<!--jquery ui and core ends-->

	</head>
	<body>
    <div id="wraper"> 
      
      <!--header starts-->
      <div id="header">
        <div id="logobox">
          <div class="raws">
            <div style="float:left; padding-top:5px;"><img src="images/logo.png" /></div>
            <div class="loginbox">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="right" style="font-weight: bold;">
                  	Welcome,
					<%  
						Map<String,Object> sessionMap= (Map<String,Object>)request.getSession().getAttribute("userdetail");
                  		if(sessionMap.size() == 0){
                  			response.sendRedirect("adminlogin.html"); 
                  		}else{
                  			out.print(sessionMap.get("first_name").toString());
                  		}
					%>                	
                  	</td>
                  <td rowspan="3"><img src="images/user_icon.png" width="50" height="50" /></td>
                </tr>
                <tr align="right">
                  <td><a href="adminlogout.html">Logout</a></td>
                </tr>
                <tr align="right">
                  <td></td>
                </tr>
              </table>
            </div>
            <div class="clear"></div>
          </div>
        </div>
        
        
        <div id="navigation">
          <div class="raws"> 
            <!--navigation starts-->
            <ul class="topnav">
              <li><a href="#">Dashboard</a></li>
             <!--  <li> <a href="#">User</a>
                <ul class="subnav">
                  <li><a href="#">Sub Nav Link</a></li>
                  <li><a href="#">Sub Nav Link</a></li>
                </ul>
              </li>
              <li> <a href="#">Setings</a>
                <ul class="subnav">
                  <li><a href="#">Sub Nav Link</a></li>
                  <li><a href="#">Sub Nav Link</a></li>
                </ul>
              </li>
              <li><a href="#">Content</a></li>
              <li><a href="#">Articles</a></li>
              <li><a href="#">Submit</a></li>
              <li><a href="#">Contact&nbsp;Us</a></li> -->
            </ul>
            <!--navigation ends--> 
          </div>
        </div>
        
        <div class="header_shadow"></div>
        
      </div>
      <!--header ends--> 
