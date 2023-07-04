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
				<fmt:message key="welcome" />
			</h1>
			<h2>
				<c:if test="${sessionScope.usuarioLogado != null}">
						&nbsp;&nbsp;&nbsp;
						
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
							<fmt:message key="locadora.create" />
						</a> 
					</c:if>
			</h2>
			<h3><fmt:message key="locadora.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<p>
			<form action="/<%= contextPath%>/listaLocadoras/listaPorCidade" method="get">
				<fmt:message key="rental_company_show_by_city"/>: 
				<select name="cidade">
					<c:forEach var="cidade" items="${requestScope.listaCidades}">	
						<option value ="${cidade}">${cidade}</option>
					</c:forEach>
				</select>
				<input type="submit" value="<fmt:message key="choose"/>">
			</p>
			</form>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="locadora.email" /></th>
					<th><fmt:message key="locadora.cnpj" /></th>
					<th><fmt:message key="locadora.cidade" /></th>
					<th><fmt:message key="locadora.senha" /></th>
					<th><fmt:message key="locadora.nome" /></th>
					<th><fmt:message key="actions.link" /></th>

				</tr>
				<c:forEach var="locadora" items="${requestScope.listalocadoras}">
					<tr>
						<td><c:out value="${locadora.email}" /></td>
						<td><c:out value="${locadora.cnpj}" /></td>
						<td><c:out value="${locadora.cidade}" /></td>
						<td><c:out value="${locadora.senha}" /></td>
						<td><c:out value="${locadora.nome}" /></td>

						
						<c:if test="${sessionScope.usuarioLogado != null}">
							<td>
							
								<a href="/<%= contextPath %>/locadoras/edicao?cnpj=<c:out value='${locadora.cnpj}' />">
									<fmt:message key="locadora.update" />
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="/<%= contextPath %>/locadoras/remocao?cnpj=<c:out value='${locadora.cnpj}' />">
									<fmt:message key="delete" />
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