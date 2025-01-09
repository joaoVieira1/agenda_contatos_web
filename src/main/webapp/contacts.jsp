<%@page import="br.edu.ifsp.dsw1.model.entity.Contact"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="includes/head.html" />
<body>
	<jsp:include page="includes/navBar.html" />

	<%
	var contacts = (List<Contact>)request.getAttribute("contacts");
	if (contacts == null && contacts.isEmpty()) {
		response.sendRedirect("index.jsp");
	} else {
	%>
	<main class="container-sm flex-grow-1  justify-content-center">
	
		<%
		String msg = (String) request.getAttribute("errorMessage");
		if (msg != null ) {
			out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">");
			out.println(msg);
			out.println("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
		}
		%>

		<!-- Formulário para buscar um contato pelo nome. -->
		<div class="card" style="margin-top: 24px;">
			<div class="card-body">
				<h5>Buscar contato</h5>
				<form action="contact.do?action=searchContact" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="textName" placeholder="Nome do contato">
						<button class="btn btn-outline-warning" type="submit" id="button-addon2">Buscar</button>
					</div>
				</form>
			</div>
		</div>
	
	
		<h1 style="text-align: center; margin: 30px;">Contatos Cadastrados</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">E-mail</th>
					<th scope="col">Ação</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
			<%
			int i = 1;
			for (var contact : contacts) {
			%>
				<tr>
					<th scope="row"><%= i %></th>
					<td><%= contact.getName() %></td>
					<td><%= contact.getFone() %></td>
					<td><%= contact.getEmail() %></td>
					<td><a href="contact.do?action=deleteContact&email=<%= contact.getEmail()%>">Deletar</a></td>
				</tr>
			<%i++;}%>
			</tbody>
		</table>

	</main>
	<%} %>
	<jsp:include page="includes/scripts.html" />
</body>
</html>