<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <table border="1">
        <tr>
            <th><fmt:message key="locadora.ID" /></th>
            <th><fmt:message key="locadora.email" /></th>
            <th><fmt:message key="locadora.CNPJ" /></th>
            <th><fmt:message key="locadora.cidade" /></th>
            <th><fmt:message key="locadora.senha" /></th>
            <th><fmt:message key="locadora.nome" /></th>
            <th><fmt:message key="actions.link" /></th>

        </tr>
        <c:forEach var="locadora" items="${requestScope.listalocadoras}">
            <tr>
                <td><c:out value="${locadora.id}" /></td>
                <td><c:out value="${locadora.email}" /></td>
                <td><c:out value="${locadora.CNPJ}" /></td>
                <td><c:out value="${locadora.cidade}" /></td>
                <td><c:out value="${locadora.senha}" /></td>
                <td><c:out value="${locadora.nome}" /></td>
                
            </tr>
        </c:forEach>
    </table>
</html>