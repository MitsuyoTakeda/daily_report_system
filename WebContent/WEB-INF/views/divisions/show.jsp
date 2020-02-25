<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:choose>
            <c:when test="${division != null }">
                <h2>${division.name}の詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>部署コード</th>
                            <td>${division.code }</td>
                        </tr>
                        <tr>
                            <th>部署名</th>
                            <td>${division.name }</td>
                        </tr>
                        <tr>
                            <th>登録日</th>
                            <td>
                                <fmt:formatDate value="${division.created_at }" pattern="yyyy/MM/dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日</th>
                            <td>
                                <fmt:formatDate value="${division.updated_at }" pattern="yyyy/MM/dd" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/divisions/edit?id=${division.id }' />">編集</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/divisions/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
