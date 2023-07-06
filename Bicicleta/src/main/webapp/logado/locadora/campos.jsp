<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
				<table border="1">
					<caption>
						<c:choose>
							<c:when test="${editora != null}">
								<fmt:message key="locadora.update" />
							</c:when>
							<c:otherwise>
								<fmt:message key="locadora.create" />
							</c:otherwise>
						</c:choose>
					</caption>

					<tr>
						<td><label for="email">
								<fmt:message key="locadora.email" />
							</label></td>
						<td><input type="email" id="email" name="email" size="18" required
								value="<c:out value='${locadora.email}' />" /></td>
					</tr>
					<tr>
						<td><label for="cnpj">
								<fmt:message key="locadora.cnpj" />
							</label></td>
						<td>
							<c:if test="${locadora != null}">
								<input type="number" name="cnpj" value="<c:out value='${locadora.cnpj}' />" size="18" readonly	/>
							</c:if>
							<c:if test="${locadora == null}">
								<!-- O segundo if será executado apenas quando locadora for null -->
								<input type="number" name="cnpj" value="" size="18" required/>
							</c:if>
						</td>
					</tr>
					
					
					<tr>
						<td><label for="Cidade">
								<fmt:message key="locadora.cidade" />
							</label></td>
						<td><input type="text" id="cidade" name="cidade" size="18" required
								value="<c:out value='${locadora.cidade}' />" /></td>
					</tr>
					<tr>
						<td><label for="Senha">
								<fmt:message key="locadora.senha" />
							</label></td>
						<td><input type="text" id="senha" name="senha" size="18" required
								value="<c:out value='${locadora.senha}' />" /></td>
					</tr>
					<tr>
						<td><label for="Nome">
								<fmt:message key="locadora.nome" />
							</label></td>
						<td><input type="text" id="nome" name="nome" size="18" required
								value="<c:out value='${locadora.nome}' />" /></td>
					</tr>
					<tr>

					<tr>
						<td colspan="2" align="center"><input type="submit" value="<fmt:message key='save.link' />" />
						</td>
					</tr>
				</table>