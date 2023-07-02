<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">

	<tr>
		<th></th>
		<th>Título</th>
		<th>Editora</th>
		<th>Autor</th>
		<th>Ano</th>
		<th>Preço</th>
	</tr>
	<c:forEach var="cliente" items="${livros}">
		<tr>
			<td style="width: 10%; text-align: center"><input type="radio"
				id="${livro.key}" name="livro" value="${livro.key}" required></td>
			<td>${cliente.value.email}</td>
			<td>${cliente.value.telefone}</td>
			<td>${cliente.value.senha}</td>
			<td>${cliente.value.cpf}</td>
		</tr>
	</c:forEach>
</table>
<br/>
<br/>

<tr>
	<td colspan="2" align="center"><input type="submit"
		value="<fmt:message key="save.link" />" /></td>
</tr>