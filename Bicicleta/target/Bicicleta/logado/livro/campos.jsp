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
	<c:if test="${livro != null}">
		<input type="hidden" name="id" value="${cliente.id}" />
	</c:if>
	<tr>
		<td><label for="titulo"> <fmt:message key="cliente.email" />
		</label></td>
		<td><input type="text" id="email" name="titulo" size="45"
			required value="${cliente.email}" /></td>
	</tr>
	<tr>
		<td><label for="autor"> <fmt:message key="book.author" />
		</label></td>
		<td><input type="text" id="autor" name="autor" size="45" required
			value="${cliente.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="locadora"> <fmt:message
					key="cliente.publisher" />
		</label></td>
		<td><select name="locadora">
				<c:forEach items="${locadras}" var="locadora">
					<option value="${locadora.key}"
						${cliente.locadora.nome==locadora.value ? 'selected' : '' }>
						${locadora.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td><label for="ano"> <fmt:message key="cliente.cpf" />
		</label></td>
		<td><input type="number" id="ano" name="ano" size="5" required
			min="1500" value="${cliente.cpf}" /></td>
	</tr>
	<tr>
		<td><label for="preco"> <fmt:message key="cliente.nascimento" />
		</label></td>
		<td><input type="number" id="preco" name="preco" required
			min="0.01" step="any" size="5" value="${cliente.nascimento}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>