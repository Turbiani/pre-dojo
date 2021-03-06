<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pre-dojo Amil</title>

    <!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>    
    <div class="container">
      <div class="jumbotron">
        <img src="https://logodownload.org/wp-content/uploads/2014/06/amil-logo.png" width="300"/>
      </div> 
      
      
      <form  class="form-horizontal" role="form" action="/report" method="POST">
        <div class="form-group">
          <label for="log" class="control-label col-sm-1">Logs:</label>
          <div class="col-sm-4">
            <textarea class="form-control" rows="5" id="log" name="log" ></textarea>
          </div>
           <button type="submit" class="btn btn-default">Submit</button>  
        </div>
      </form>
      
      <p>
		<#if error??>
		<div class="alert alert-danger">${error}.</div>
		</#if>
	  </p>
      
    </div>
		      
		
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>