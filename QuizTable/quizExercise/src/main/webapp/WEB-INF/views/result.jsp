<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Ta-da!</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
	<!--<form>
		<table>
			<tr>
				<th>번호</th>
				<th>퀴즈</th>
				<th>답안작성</th>
			</tr>
		<c:forEach var="q" items="${quizList}" varStatus="s">
			<tr>
				<td>
					<label for="answer${s.count}">Quiz${s.count}</label>
				</td>
				<td>
					${q.content}
					<input type="hidden" name="question${s.count}" value="${s.count}" />
				</td>
				<td>
					<input type="text" name="answer${s.count}" id="answer${s.count}" />
				</td>
				<td>
					${quizResults[s.index]}
				</td>
			</tr>
		</c:forEach>
		</table>
	</form>-->
	<button onclick="location.href='index.jsp'">뒤로 가기</button>
</body>
</html>