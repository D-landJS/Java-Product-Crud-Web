<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Productos</title>
</head>
<body>

	<h1>Editar Producto</h1>
	
	<form action="Productos" method="POST">
	<c:set var="producto" value="${producto }"></c:set>
	<input type="hidden" name="op" value="editar">
	<input type="hidden" name="id" value="${producto.id }">
	
	<table border="1">

		<tr>
			<th>Nombre:</th>
			<td><input type="text" name="nombre" size="50" value="${producto.nombre } "></td>
		</tr>

		<tr>
			<th>Cantidad:</th>
			<td><input type="text" name="cantidad" size="50" value="${producto.cantidad }"></td>
		</tr>
		
		<tr>
			<th>Precio:</th>
			<td><input type="text" name="precio" size="50" value="${producto.precio}"></td>
		</tr>


	</table>
	
	<input type="submit" value="Enviar">
	</form>
</body>
</html>