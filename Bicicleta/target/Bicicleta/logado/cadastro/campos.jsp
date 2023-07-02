<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${cadastro != null}">
				<fmt:message key="cadastro.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="cadastro.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${cadastro != null}">
		<input type="hidden" name="id" value="<c:out value='${cadastro.id}' />" />
	</c:if>
	<tr>
		<td><label for="email"> <fmt:message key="cadastro.email" /></label></td>
		<td><input type="email" id="email" name="email" size="18" required value="<c:out value='${cadastro.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="cnpj"><fmt:message key="cadastro.cnpj" /></label></td>
		<td><input type="text" name="cnpj" size="45" required value="<c:out value='${cadastro.CNPJ}' />" /></td>
	</tr>
	<tr>
		<td><label for="Cidade"> <fmt:message key="cadastro.cidade" /></label></td>
		<td><input type="text" id="cidade" name="cidade" size="18" required value="<c:out value='${cadastro.cidade}' />" /></td>
	</tr>
	<tr>
		<td><label for="Senha"> <fmt:message key="cadastro.senha" /></label></td>
		<td><input type="text" id="senha" name="senha" size="18" required value="<c:out value='${cadastro.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="Nome"> <fmt:message key="cadastro.nome" /></label></td>
		<td><input type="text" id="nome" name="nome" size="18" required value="<c:out value='${cadastro.nome}' />" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>