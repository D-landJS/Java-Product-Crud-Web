<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Producto</title>
</head>
<body>

	<h1>Crear Producto</h1>
	
	<form action="Productos" method="POST">
	<input type="hidden" name="op" value="guardar">
	
	<table border="1">

		<tr>
			<th>Nombre:</th>
			<td><input type="text" name="nombre" size="50"></td>
		</tr>

		<tr>
			<th>Cantidad:</th>
			<td><input type="text" name="cantidad" size="50"></td>
		</tr>
		
		<tr>
			<th>Precio:</th>
			<td><input type="text" name="precio" size="50"></td>
		</tr>


	</table>
	
	<input type="submit" value="Enviar">
	</form>
	
</body>
</html>