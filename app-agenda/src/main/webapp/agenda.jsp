<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<% ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");%>

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
		<h1>Agenda de Contatos</h1>
		<a href="novo.html" class="botao2">Novo Contato</a>
		<table id="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < lista.size(); i++) {%>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getTelefone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
</body>
</html>