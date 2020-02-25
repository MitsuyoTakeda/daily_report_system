<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${division != null }">
                <h2>${division.id }の編集ページ</h2>
                <form method="POST" action="<c:url value='/divisions/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="#" onclick="confirmDextroy();">削除</a></p>
                <form method="POST" action="<c:url value='/devisions/destroy' />">
                    <input type="hidden" value="${_token }">
                </form>

                <script>
                    function confirmDestroy(){
                        if(confirm("本当に削除してよろしいですか？")){
                            document.form[1].submit();
                        }
                    }

                </script>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/divisions/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>