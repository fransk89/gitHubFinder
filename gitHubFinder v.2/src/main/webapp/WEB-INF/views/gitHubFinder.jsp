<%@ include file="/WEB-INF/views/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Search GitHub Finder</title>
	
	    <!-- Bootstrap core CSS, jQuery -->
	    <link href="https://bootswatch.com/simplex/bootstrap.min.css" rel="stylesheet">
	   	<script src="https://code.jquery.com/jquery-3.1.1.js" ></script>
	    
	    <!-- Internal Javascript -->       
	    <script src="resources/scripts/gitHub.js"></script>
	    <script src="resources/scripts/view.js"></script>
	</head>

  	<body>
		<div class="container">
	      	<div class="searchContainer">
		        <h1>Search GitHub Finder</h1>
		       	<p class="lead" >Enter a username to fetch the best guess of the GitHub user's favourite programming language</p>
		        <input type="text" id="searchUser" class="form-control" placeholder="Github Username...">
	    	</div>
	    	<br>
	    	<div class="row">
             
                <div class="col-md-2">
                  <a type="button" id="searchButton" target="_blank" class="btn btn-default">Search</a>
                </div>
              </div>
           <br>
	      	<div id="profile"></div>
	      	<div id="repos"> </div>
		</div>
	</body>
</html>
