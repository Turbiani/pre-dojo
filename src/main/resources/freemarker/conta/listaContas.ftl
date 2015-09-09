<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>CRUD JPA-Spark-Freemarker</title>

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
			<h1>Painel administrativo</h1>
			<p>Desevolvido com JPA + MySQL, SparkJava + Freemarker + Bootstrap</p>
		</div>
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th>Titular</th>
            <th>Numero</th>
            <th>Agência</th>
            <th>Banco</th>
          </tr>
        </thead>
        <tbody>
          <#list contas as conta>
          <tr>
            <td>${conta["titular"]}</td>
            <td>${conta["numero"]}</td>
            <td>${conta["agencia"]}</td>
            <td>${conta["banco"]}</td>
          </tr>
          </#list>          
        </tbody>
      </table>
      	<a href="/">
			<span class="glyphicon glyphicon-arrow-left"></span> Voltar
		</a>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>