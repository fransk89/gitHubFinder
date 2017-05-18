var gitHubId = "-1";

$(document).ready(function() {
	startGitHubFinder();
	
	$('#searchButton').on('click', function (e){
		
		//	Get username from input box
		var username = $('#searchUser').val();
		
		if (username != "") {
			getProgrammingLanguages(username);
		}
	});	
});


function startGitHubFinder(){
	var url = "/gitHubFinder/startGitHubFinder.do";
	
	//$("#result").toggleClass("hide");
	$("#searchUser").text("");
	$('#repos').empty();
	$('#profile').hide();
	
	if (gitHubId!="-1"){
		removeOldGitHub();
	}
	else{
    	$.ajax({url: url,
    		type: "POST",
    		contentType: "application/json",
    		dataType: "json",
    		success: function(response) {
    			gitHubId = response.id;
    		}});
	}	  		
	}

//	Get programming languages
function getProgrammingLanguages(username){
	
	var url = "/gitHubFinder/getProgrammingLanguages.do";
	var request = {username: username, id: gitHubId};
			
	$.ajax({url: url,
		type: "POST",
		data: JSON.stringify(request),
		contentType: "application/json",
		dataType: "json",
		success: function(response) {
			if (response != null && response.listLanguages != null) {
				showLanguages(response.listLanguages);
			} else {
				showError ('User repository does not exist');
			}
		},
		error:	function ( jqXHR, textStatus ) {
			showError ('Unexpected Error');
		}
	});
}


function removeOldGitHub(){
	var url = "/gitHubFinder/deleteSession.do";
	var request = {id: gitHubId};
	
	$.ajax({url: url,
		type: "POST",
		data: JSON.stringify(request),
		contentType: "application/json",
		dataType: "json",
		success: function(response) {
			gitHubId = "-1";
			startGitHubFinder();
		}});
}


