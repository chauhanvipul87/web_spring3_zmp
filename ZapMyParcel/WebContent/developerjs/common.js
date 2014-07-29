/**
 * 
 */
//Validation Error Message Hide on Form Load Event
function loadform()
{
$('.formError').fadeTo("fast", 0.3, function() {
    $(this).remove();
});
}
//for alerting error messages
function printErrors($errors){
    $str = "Following error(s) occured\n\n";
    $counter = 1;
    var $i;
    for($i = 0; $i <= $errors.length; $i++){
        if($errors[$i] != undefined && $errors[$i] != "" && $errors[$i] != null){
            $str += "\t" + $counter + ". "+$errors[$i] + "\n";
            $counter++;
        }
    }
    return $str;
}

function showMessage(msg){
	//alert(msg);
	humanMsg.displayMsg(msg);
	/*
		var timeout = 5000;
		$("#emsg").html(msg);
		$("#emsg").show('fast').delay(timeout).hide('fast');
	*/
}


function showMessage_Admin(msg){
		var timeout = 5000;
		$("#emsg").html(msg);
		$("#emsg").show('fast').delay(timeout).hide('fast');
}

