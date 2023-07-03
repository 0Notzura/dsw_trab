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
					<fmt:message key="purchases.welcome" />
				</h1>
				<h2>
					<a href="lista"> <fmt:message key="purchases.list" />
					</a> &nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/logout.jsp"> <fmt:message
							key="exit.link" />
					</a>
				</h2>
			</div>
			<div align="center">
				<form action="insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="informartions"/>
						</legend>
						
						<label for="locadora">
							<fmt:message key="locadora"/>:
						</label> </br>

						<select name="locadora" id="locadora">
							<c:forEach var="locadora" items="${listaLocadoras}">
								<option value="${locadora.cnpj}">${locadora.cnpj}, ${locadora.nome}, ${locadora.cidade}</option>
							</c:forEach>
						</select> <br/>
						
						<br/>
							
						<label for="data">
							<fmt:message key="date"/>:
						</label> <br/>
						<input type="date" id="data" name="data"> <br/>
						
						<br/>
						
						<label for="horario">
							<fmt:message key="horario"/>:
						</label> </br>
						
						<select name="horario" id="horario">
							<option value="0">00:00 - 01:00</option>
							<option value="1">01:00 - 02:00</option>
							<option value="2">02:00 - 03:00</option>
							<option value="3">03:00 - 04:00</option>
							<option value="4">04:00 - 05:00</option>
							<option value="5">05:00 - 06:00</option>
							<option value="6">06:00 - 07:00</option>
							<option value="7">07:00 - 08:00</option>
							<option value="8">08:00 - 09:00</option>
							<option value="9">09:00 - 10:00</option>
							<option value="10">10:00 - 11:00</option>
							<option value="11">11:00 - 12:00</option>
							<option value="12">12:00 - 13:00</option>
							<option value="13">13:00 - 14:00</option>
							<option value="14">14:00 - 15:00</option>
							<option value="15">15:00 - 16:00</option>
							<option value="16">16:00 - 17:00</option>
							<option value="17">17:00 - 18:00</option>
							<option value="18">18:00 - 19:00</option>
							<option value="19">19:00 - 20:00</option>
							<option value="20">20:00 - 21:00</option>
							<option value="21">21:00 - 22:00</option>
							<option value="22">22:00 - 23:00</option>
							<option value="23">23:00 - 00:00</option>
						</select> <br/>
						
						</br>
						
						<input type="submit" name="enviar" value="<fmt:message key="register"/>"/>
					</fieldset>
				</form>
			</div>
			<c:if test="${!empty requestScope.mensagens}">
				<ul class="erro">
					<c:forEach items="${requestScope.mensagens}" var="mensagem">
						<li>${mensagem}</li>
					</c:forEach>
				</ul>
			</c:if>
		</body>
	</fmt:bundle>

	</html>