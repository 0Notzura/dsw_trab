<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
				<html>
				<fmt:bundle basename="message">

					<head>
						<title>
							<fmt:message key="page.title" />
						</title>
					</head>

					<body>

						<% String contextPath=request.getContextPath().replace("/", "" ); %>
							<div align="center">
								<h1>
									<fmt:message key="cadastros.welcome" />
								</h1>
								<h2>
									<a href="/<%=contextPath%>/cadastros/cadastro">
										<fmt:message key="purchases.create" />
									</a>
									&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath}/logout.jsp">
										<fmt:message key="exit.link" />
									</a>
								</h2>
								<br />
								<h3>
									<fmt:message key="cadastro.list" />
								</h3>
								<br />
							</div>

							<div align="center">
								<table border="1">
									<caption></caption>
									<tr>
										<th>
											<fmt:message key="cadastro.ID" />
										</th>
										<th>
											<fmt:message key="cadastro.dia" />
										</th>
										<th>
											<fmt:message key="cadastro.hora" />
										</th>
										<th>
											<fmt:message key="cadastro.usuario.email" />
										</th>
										<th>
											<fmt:message key="cadastro.usuario.telefone" />
										</th>
										<th>
											<fmt:message key="cadastro.usuario.cpf" />
										</th>
										<th>
											<fmt:message key="cadastro.locadora.cnpj" />
										</th>
									</tr>
									<c:forEach var="cadastro" items="${requestScope.listacadastros}">
										<tr>
											<td>${cadastro.id}</td>
											<td>${cadastro.dia}</td>
											<td>${cadastro.hora}</td>
											<td>${cadastro.usuario.email}</td>
											<td>${cadastro.usuario.telefone}</td>
											<td>${cadastro.usuario.cpf}</td>
											<td>${cadastro.locadora.cnpj}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
					</body>
				</fmt:bundle>

				</html>