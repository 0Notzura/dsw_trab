<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>

		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="cliente.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/locadoras">
					<fmt:message key="locadora.entity" />
				</a>
				&nbsp;&nbsp;&nbsp;
				<a href="/<%=contextPath%>/usuarios"> 
					<fmt:message key="users.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/clientes/cadastro">
					<fmt:message key="cliente.create" />
				</a>
			</h2>
			<h3><fmt:message key="cliente.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="cliente.ID" /></th>
					<th><fmt:message key="cliente.email" /></th>
					<th><fmt:message key="cliente.telefone" /></th>
					<th><fmt:message key="cliente.senha" /></th>
					<th><fmt:message key="cliente.sexo" /></th>
					<th><fmt:message key="cliente.cpf" /></th>
					<th><fmt:message key="cliente.nascimento" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="cliente" items="${requestScope.listaclientes}">
					<tr>
						<td>${cliente.id}</td>
						<td>${cliente.email}</td>
						<td>${cliente.telefone}</td>
						<td>${cliente.senha}</td>
						<td>${cliente.sexo}</td>
						<td>${cliente.cpf}</td>
						<td>${cliente.nascimento}</td>
						<td>
							<a href="/<%= contextPath %>/clientes/edicao?id=${cliente.id}">
								<fmt:message key="update" />
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/<%= contextPath %>/clientes/remocao?id=${cliente.id}">
								<fmt:message key="delete" />
							</a>
						</td>						
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>