/**
 * 
 */
// function for alert pop up on successful DB insert
function displayResults() {
	if (document.myForm.hidden.value == 1) {
		alert("DATA INSERTED!!")
	}
}

// function to open viewContacts.jsp page when view contacts button clicked
function openPage(pageURL) {
	window.location.href = pageURL;
	}


function showhide(id){ 
	if (document.getElementById){ 
		obj = document.getElementById(id); 
		if (obj.style.display == "none"){ 
			obj.style.display = ""; 
		} else { 
			obj.style.display = "none"; 
		} 
	} 
} 


