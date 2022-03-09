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
		<div class="box-button">
			<a href="novo.html" class="botao novo-contato">
				<img src="images/novo-contato-icon.png">Novo Contato
			</a>
			<a href="pdf" class="botao pdf">			
				<img src="images/gerar-pdf-icon.png">Gerar PDF
			</a>
		</div>
		<div class="content-table">
			<table id="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Email</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
				<% for (int i = 0; i < lista.size(); i++) {%>
					<tr>
						<td><%=lista.get(i).getId()%></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getTelefone()%></td>
						<td><%=lista.get(i).getEmail()%></td>
						<td class="action">
							<a class="update" href="select?id=<%=lista.get(i).getId()%>">Editar</a>
							<a class="delete" href="javascript: confirmar(<%=lista.get(i).getId()%>)">Excluir</a>
						</td>
					</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
	<script src="scripts/confirmador.js"></script>
</body>
</html>