
// Show programming languages from user repositories
function showLanguages(listLanguages) {
	
	
	$('#repos').empty();
	$('#profile').hide();
	
	//	Looping each repository
	$.each(listLanguages, function(index, language){
		
		if (language == null) {
			language = "Not specified";
		}
		
	  		//	Show programming language in the screen
		$('#repos').append(`
			<div class="well">
				<div class="row">
				<div class="col-md-7"><strong>${language}</strong></div>
				</div>
			</div>
		`);
	 	
   });
 }

//Show error message in the screen
function showError (errorMessage) {
	$('#repos').empty();
	$('#profile').show();
	$('#profile').html(`
		<div class="panel panel-default">
			<div class="panel-heading">
	        	<h3 class="panel-title" style="color: red">${errorMessage}</h3>
	        </div>
	    </div>
	    
	`);
}