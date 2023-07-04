<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${usuario != null}">
				<fmt:message key="user.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="user.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<tr>
		<td><label for="titulo"> <fmt:message key="user.email" />
		</label></td>
		<td><input type="text" id="email" name="email" size="45" required
			value="<c:out value='${usuario.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="telefone"> <fmt:message key="user.telefone" />
		</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required
			value="<c:out value='${usuario.telefone}' />" /></td>
	</tr>
	<tr>
		<td><label for="sexo"> <fmt:message key="user.sexo" />
		</label></td>
		<td><input type="text" id="sexo" name="sexo" required
			value="<c:out value='${usuario.sexo}' />" /></td>
	</tr>
	<tr>
		<td><label for="cpf"> <fmt:message key="user.cpf" />
		</label></td>
		<td><input type="number" id="cpf" name="cpf" size="40" required
			value="<c:out value='${usuario.cpf}' />" /></td>
	</tr>
	<tr>
		<td><label for="nascimento"> <fmt:message key="user.nascimento" />
		</label></td>
		<td><input type="date" id="nascimento" name="nascimento" required
			value="<c:out value='${usuario.nascimento}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="user.nome" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${usuario.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="user.password" />
		</label></td>
		<td><input type="text" name="senha" size="20" required
			value="<c:out value='${usuario.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="papel"><fmt:message key="user.role" />
		</label></td>
		<td>
			<select name="papel" required>
				<option value="ADMIN" ${usuario.papel == "ADMIN" ? 'selected="selected"' : ''}>ADMIN</option>
				<option value="USER" ${usuario.papel == "USER" ? 'selected="selected"' : ''}>USER</option>
			</select>			
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>