<!DOCTYPE html>
<html>
<head>
<title>Zap My Parcel</title>
<jsp:include page="../common/commoninclude_css.jsp"></jsp:include>
<jsp:include page="../common/commoninclude_js.jsp"></jsp:include>

<script type="text/javascript">
// Credits: Robert Penners easing equations (http://www.robertpenner.com/easing/).
jQuery.easing['BounceEaseOut'] = function(p, t, b, c, d) {
 if ((t/=d) < (1/2.75)) {
  return c*(7.5625*t*t) + b;
 } else if (t < (2/2.75)) {
  return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
 } else if (t < (2.5/2.75)) {
  return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
 } else {
  return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
 }
};

function mycarousel_initCallback(carousel)
{
    // Pause autoscrolling if the user moves with the cursor over the clip.
    carousel.clip.hover(function() {
        carousel.stopAuto();
    }, function() {
        carousel.startAuto();
    });
};
jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel({
        auto: 1,
  visible: 6,
        wrap: 'last',
  easing: 'BounceEaseOut',
        animation: 1000,
        initCallback: mycarousel_initCallback
    });
 
});
</script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript">


ddaccordion.init({ //top level headers initialization
	headerclass: "expandable", //Shared CSS class name of headers group that are expandable
	contentclass: "categoryitems", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

ddaccordion.init({ //2nd level headers initialization
	headerclass: "subexpandable", //Shared CSS class name of sub headers group that are expandable
	contentclass: "subcategoryitems", //Shared CSS class name of sub contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click" or "mouseover
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["opensubheader", "closedsubheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>
<style type="text/css">
.arrowlistmenu {
	/*width of accordion menu*/
}
.arrowlistmenu .menuheader { /*CSS class for menu headers in general (expanding or not!)*/
	font: normal 12px Arial;
	color: black;
	background:url(images/bullets.png) no-repeat center right;
	margin-bottom: 4px; /*bottom spacing between header and rest of content*/
	padding: 4px 0 7px 0px; /*header text is indented 10px*/
	cursor: hand;
	cursor: pointer;
	border-bottom:1px solid #ccc;
}
.arrowlistmenu .menuheader a { color:black; text-decoration:none;}

.arrowlistmenu .menuheader:hover {background-image: url(images/bullets-actv.png);}
.arrowlistmenu .expandable { border-bottom:none;}
.arrowlistmenu .openheader { /*CSS class to apply to expandable header when it's expanded*/
	background-image: url(images/bullets-actv.png);
	border-bottom:none;
}
.arrowlistmenu ul { /*CSS for UL of each sub menu*/
	list-style-type: none;
	margin: 0;
	padding: 0;
	margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
}
.arrowlistmenu ul li {
	padding-bottom: 2px; /*bottom spacing between menu items*/
}
.arrowlistmenu ul li .opensubheader { /*Open state CSS for sub menu header*/
	background: lightblue !important;
}
.arrowlistmenu ul li .closedsubheader { /*Closed state CSS for sub menu header*/
	background: lightgreen !important;
}
.arrowlistmenu ul li a {
	color: #707070;
	background: url(images/arrowbullet.png) no-repeat center left; /*custom bullet list image*/
	display: block;
	padding: 2px 0;
	padding-left: 19px; /*link text is indented 19px*/
	text-decoration: none;
	font-weight: bold;
	border-bottom: 1px solid #dadada;
	font-size: 90%;
}
.arrowlistmenu ul li a:visited {
	
}
.arrowlistmenu ul li a:hover { /*hover state CSS*/
	background-color: #F3F3F3;
}
.arrowlistmenu ul li a.subexpandable:hover { /*hover state CSS for sub menu header*/
	background: lightblue;
}
</style>
<style type="text/css">
.jcarousel-skin-tango .jcarousel-container-horizontal {
	width: 85%;
}
.jcarousel-skin-tango .jcarousel-clip-horizontal {
	width: 100%;
}
</style>
<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>
<script type="text/javascript">
ddsmoothmenu.init({
	mainmenuid: "smoothmenu1", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})
</script>

</head>

<body>
<!--header starts-->
<div id="heady">
  <div class="raw">
    <div style="float:left; padding:15px 0 0 0;"><a href="index.html"><img src="images/logo.png"></a></div>
    <div style="float:right; padding:15px 0 0 0;">
      <table width="4%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td> &nbsp;</td>
          <td valign="bottom"><div class="find_btn"><a href="javascript:void(0);" onclick="getNearestAgent();">Find Your Nearest Drop Off Point</a></div></td> 
          <td><!-- <div class="login"><a href="#">Log in</a> | <a href="register.html">Register</a></div> -->
            <div class="item_container">
              <table border="0" cellspacing="0" cellpadding="6">
                <tr>
                  <td align="center" valign="middle"><img src="images/box.png"></td>
                  <td><div class="cart_item"><a href="javascript:void(0);" id="itemInBasket" onclick="getBasketDetails();" >Items&nbsp;in&nbsp;Basket&nbsp;(0)</a></div></td>
                </tr>
              </table>
            </div></td>
        </tr>
      </table>
    </div>
    <div class="clear"></div>
  </div>
</div>
<!--header ends--> 

<!--menu starts-->
<div id="menu">
  <div class="raw">
    <!-- <ul>
      <li><a href="default.html">Home</a></li>
      <li><a href="faq.html">FAQs</a></li>
      <li><a href="import_parcel.html">Import a Parcel</a></li>
      <li><a href="#">Specials</a></li>
      <li><a href="#">Reviews</a></li>
      <li><a href="volume.html">Volume Calculator</a></li>
      <li><a href="our_partners.html">Partners</a></li>
      <li><a href="contact_us.html">Contact Us</a></li>
    </ul> -->
    <div id="smoothmenu1" class="ddsmoothmenu">
      <ul>
        <li><a href="index.html">Home</a></li>
        <li><a href="faq.html">FAQs</a>
          <!-- <ul>
            <li><a href="#">Sub Item 1.1</a></li>
            <li><a href="#">Sub Item 1.2</a></li>
            <li><a href="#">Sub Item 1.3</a></li>
            <li><a href="#">Sub Item 1.4</a></li>
            <li><a href="#">Sub Item 1.2</a></li>
            <li><a href="#">Sub Item 1.3</a></li>
            <li><a href="#">Sub Item 1.4</a></li>
          </ul> -->
        </li>
        <li><a href="import_parcel.html">Import a Parcel</a>
          <!-- <ul>
            <li><a href="#">Sub Item 1.1</a></li>
            <li><a href="#">Sub Item 1.2</a></li>
            <li><a href="#">Sub Item 1.3</a></li>
            <li><a href="#">Sub Item 1.4</a></li>
            <li><a href="#">Sub Item 1.2</a></li>
            <li><a href="#">Sub Item 1.3</a></li>
            <li><a href="#">Sub Item 1.4</a></li>
          </ul> -->
        </li>
       <!--  <li><a href="#">Specials</a></li> -->
        <!-- <li><a href="#">Reviews</a>
          <ul>
            <li><a href="#">Sub Item 2.1</a></li>
            <li><a href="#">Folder 2.1</a>
              <ul>
                <li><a href="#">Sub Item 2.1.1</a></li>
                <li><a href="#">Sub Item 2.1.2</a></li>
                <li><a href="#">Folder 3.1.1</a>
                  <ul>
                    <li><a href="#">Sub Item 3.1.1.1</a></li>
                    <li><a href="#">Sub Item 3.1.1.2</a></li>
                    <li><a href="#">Sub Item 3.1.1.3</a></li>
                    <li><a href="#">Sub Item 3.1.1.4</a></li>
                    <li><a href="#">Sub Item 3.1.1.5</a></li>
                  </ul>
                </li>
                <li><a href="#">Sub Item 2.1.4</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a href="#">Volume Calculator</a>
          <ul>
            <li><a href="UKVolume.html">UK Calculator</a></li>
            <li><a href="InternationalVolume.html">International Calculator</a></li>
          </ul>
        </li> -->
        <li><a href="our_partners.html">Partners</a></li>
         <li><a href="about.html">About Us</a>
        <li><a href="contact_us.html">Contact Us</a></li>
      </ul>
      <br style="clear: left" />
    </div>
    
    
  </div>
</div>
<!--menu ends--> 