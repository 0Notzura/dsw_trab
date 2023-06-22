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
				<fmt:message key="publishers.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/clientes"> 
					<fmt:message key="books.entity" />
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
				<a href="/<%=contextPath%>/locadoras/cadastro">
					<fmt:message key="publishers.create" />
				</a> 
			</h2>
			<h3><fmt:message key="publishers.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="publisher.ID" /></th>
					<th><fmt:message key="publisher.CNPJ" /></th>
					<th><fmt:message key="publisher.name" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="locadora" items="${requestScope.listalocadoras}">
					<tr>
						<td><c:out value="${locadora.id}" /></td>
						<td><c:out value="${locadora.CNPJ}" /></td>
						<td><c:out value="${locadora.nome}" /></td>
						<td><a
							href="/<%= contextPath %>/locadoras/edicao?id=<c:out value='${locadora.id}' />">
								<fmt:message key="publishers.update" />
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/locadoras/remocao?id=<c:out value='${locadora.id}' />"
									<fmt:message key="publishers.delete" />
								</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>
</html>