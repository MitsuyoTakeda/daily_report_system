<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url = "/WEB-INF/views/layout/app.jsp">
    <c:param name = "content">

        <c:if test = "${flush != null }">
            <div id = "flush_success">
                <c:out value="${flush }" />
            </div>
        </c:if>

        <h2>部署　一覧</h2>
        <table>
            <tbody>
                <tr>
                    <th>部署コード</th>
                    <th>部署名</th>
                    <th>詳細</th>
                </tr>

                <c:forEach var="division" items="${divisions }" varStatus="status">
                    <tr class = "row${status.count % 2 }">
                        <td><c:out value="${division.code }" /></td>
                        <td><c:out value="${division.name }" /></td>
                        <td><a href = "<c:url value='/divisions/show?id=${division.id }' />">詳細</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id = "pagenation">
            ( 全 ${divisions_count} 件 ）<br />

            <c:forEach var = "i" begin = "1" end = "${((divisions_count - 1) / 15) + 1 }" step = "1">
                <c:choose>
                    <c:when test="${i == page }">
                        <c:out value="${i }" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/divisions/index?page=${i }' />" ><c:out value="i" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='/divisions/new' />">新規登録</a></p>
    </c:param>
</c:import>