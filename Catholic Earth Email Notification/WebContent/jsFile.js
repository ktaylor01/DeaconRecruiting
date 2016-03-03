/**
 */
function validateemail() {
	var x = document.myform.email.value;
	var atposition = x.indexOf("@");
	var dotposition = x.lastIndexOf(".");
	if (atposition < 1 || dotposition < atposition + 2
			|| dotposition + 2 >= x.length) {
		alert("Please enter a valid e-mail address");
		return false;
	} else {
		return true;
	}
}

function validatefname() {

	var name = document.forms["myform"]["fname"].value;

	if (name == null || name == "") {
		alert("Name can't be blank");
		return false;
	} else {
		return true;
	}
}

function validatelname() {
	var name = document.forms["myform"]["lname"].value;
	if (name == null || name == "") {
		alert("Name can't be blank");
		return false;
	} else {
		return true;
	}
}

function displayResults() {
	if (document.myform2.hidden.value == 1) {
		alert("Email sent Successfully :)")	
	}
}

function registered() {
	if (document.myform.hidden.value == 1) {
		alert("You are registered!  Check your email  :)")	
	}else{
		alert("Ooops looks like you're already signed up! :)")
	}
}