p<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="message">
		<head>
			<title>
				<fmt:message key="rental_company_show_by_city"/>
			</title>
			<link href="logado/locadora/style.css" rel="stylesheet" type="text/css" />
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<div align="center">
				<h1>
					<fmt:message key="rental_company_show_by_city"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main.link"/>
					</a>
					<c:if test="${sessionScope.usuarioLogado != null}">
						&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/locadora/cadastro">
							<fmt:message key="rental_company_new"/>
						</a>	
					</c:if>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/listaLocadoras">
						<fmt:message key="locadoras.entity"/>
					</a>
				</h2>
			</div>
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="rental_company_list"/>
					</caption>
					<tr>
						<th><fmt:message key="locadora.cnpj"/></th>
						<th><fmt:message key="locadora.nome"/></th>
						<th><fmt:message key="locadora.email"/></th>
						<th><fmt:message key="locadora.cidade"/></th>
						<c:if test="${sessionScope.usuarioLogado != null}">
							<th><fmt:message key="actions"/></th>
						</c:if>
					</tr>
					<c:forEach var="locadora" items="${requestScope.listaLocadoras}">
						<tr>
							<td>${locadora.cnpj}</td>
							<td>${locadora.nome}</td>
							<td>${locadora.email}</td>
							<td>${locadora.cidade}</td>
							<c:if test="${sessionScope.usuarioLogado != null}">
							<td>
								<a href="/<%= contextPath%>/locadora/edicao?cnpj=${locadora.cnpj}">
									<fmt:message key="edition"/>
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message var="confirmation_text" key="confirmation_text"/>
								<a
									href="/<%= contextPath%>/locadora/remocao?cnpj=${locadora.cnpj}"
									onclick="return confirm(${confirmation_text});">	
									<fmt:message key="removal"/>
								</a>
							</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>