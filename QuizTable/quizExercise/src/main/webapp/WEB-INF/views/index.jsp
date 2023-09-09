<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Quiz Ta-da!</title>
	<link rel="icon" href="${pageContext.request.contextPath}/resources/image/quiz.png">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
	<h1>Quiz Time!</h1>
	<form action="quizSubmitted" method="post">
		<table>
			<tr>
				<th>번호</th>
				<th>퀴즈</th>
				<th>답안 작성</th>
				<c:if test="${!empty quizResults}">
					<th>결과</th>
				</c:if>
			</tr>
		<c:forEach var="q" items="${quizList}" varStatus="s">
			<tr>
				<td>
					<label for="answer${s.count}">Quiz${s.count}</label>
				</td>
				<td>
					${q.content}
					<input type="hidden" name="contents" value="${q.content}" />
					<input type="hidden" name="questions" value="${s.count}" />
				</td>
				<td>
					<c:choose>
						<c:when test="${empty quizResults}">
							<input type="text" name="answers" id="answer${s.count}" />
						</c:when>
						<c:otherwise>
							<input type="text" name="answers" id="answer${s.count}" value="${q.answer}" />
						</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${!empty quizResults}">
					<td>
						${quizResults[s.index]}
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
        <div>
            <button type="submit">제출</button>
        </div>
	</form>
<%--	<c:if test="${!empty result}">--%>
<%--		<h1>${result}</h1>--%>
<%--	</c:if>--%>
</body>
</html>