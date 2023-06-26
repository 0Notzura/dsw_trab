<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${cliente != null}">
				<fmt:message key="cliente.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="cliente.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${cliente != null}">
		<input type="hidden" name="id" value="${cliente.id}" />
	</c:if>
	<tr>
		<td><label for="titulo"> <fmt:message key="cliente.email" />
		</label></td>
		<td><input type="text" id="email" name="email" size="45"
			value="<c:out value='${cliente.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="telefone"> <fmt:message key="cliente.telefone" />
		</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required
			value="<c:out value='${cliente.telefone}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"> <fmt:message key="cliente.senha" />
		</label></td>
		<td><input type="text" id="senha" name="senha" size="40" required
			value="<c:out value='${cliente.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="sexo"> <fmt:message key="cliente.sexo" />
		</label></td>
		<td><input type="text" id="sexo" name="sexo" required
			value="<c:out value='${cliente.sexo}' />" /></td>
	</tr>
	<tr>
		<td><label for="cpf"> <fmt:message key="cliente.cpf" />
		</label></td>
		<td><input type="number" id="cpf" name="cpf" size="40" required
			value="<c:out value='${cliente.cpf}' />" /></td>
	</tr>
	<tr>
		<td><label for="nascimento"> <fmt:message key="cliente.nascimento" />
		</label></td>
		<td><input type="date" id="nascimento" name="nascimento" required
			value="<c:out value='${cliente.nascimento}' />" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>