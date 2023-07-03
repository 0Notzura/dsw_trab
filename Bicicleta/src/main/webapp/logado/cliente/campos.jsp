<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${usuario != null}">
				<fmt:message key="usuario.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="usuario.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${usuario != null}">
		<input type="hidden" name="id" value="${usuario.id}" />
	</c:if>
	<tr>
		<td><label for="titulo"> <fmt:message key="usuario.email" />
		</label></td>
		<td><input type="text" id="email" name="email" size="45"
			value="<c:out value='${usuario.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="telefone"> <fmt:message key="usuario.telefone" />
		</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required
			value="<c:out value='${usuario.telefone}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"> <fmt:message key="usuario.senha" />
		</label></td>
		<td><input type="text" id="senha" name="senha" size="40" required
			value="<c:out value='${usuario.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="sexo"> <fmt:message key="usuario.sexo" />
		</label></td>
		<td><input type="text" id="sexo" name="sexo" required
			value="<c:out value='${usuario.sexo}' />" /></td>
	</tr>
	<tr>
		<td><label for="cpf"> <fmt:message key="usuario.cpf" />
		</label></td>
		<td><input type="number" id="cpf" name="cpf" size="40" required
			value="<c:out value='${usuario.cpf}' />" /></td>
	</tr>
	<tr>
		<td><label for="nascimento"> <fmt:message key="usuario.nascimento" />
		</label></td>
		<td><input type="date" id="nascimento" name="nascimento" required
			value="<c:out value='${usuario.nascimento}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="user.name" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${usuario.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="login"><fmt:message key="user.login" />
		</label></td>
		<td><input type="text" name="login" size="20" required
			value="<c:out value='${usuario.login}' />" /></td>
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
			<select name="papel">
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