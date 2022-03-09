<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="images/phone-icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="content">
		<h1>Editar Contato</h1>
		<form name="formContato" action="update">
			<table>
				<tr>
					<td><input type="text" name="id" class="small" value="<%out.print(request.getAttribute("id"));%>" readonly></td>
				</tr>
				<tr>
					<td><input type="text" name="nome" value="<%out.print(request.getAttribute("nome"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="telefone" class="small" value="<%out.print(request.getAttribute("telefone"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="email" value="<%out.print(request.getAttribute("email"));%>"></td>
				</tr>
			</table>
			<a class="botao salvar-contato" value="salvar" onclick="validar()">Salvar Contato</a>
		</form>
	</div>
	<script src="scripts/validador.js"></script>
</body>
</html>