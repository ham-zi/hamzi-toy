<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
   
<script>
	const result = {
		"I" : 0,
		"E" : 0,
		"S" : 0,
		"N" : 0,
		"F" : 0,
		"T" : 0,
		"J" : 0,
		"P" : 0
	}
	
	function nextPage = {
		
	}
	
	function submitButton = {
		
	}
	
</script>

</head>
<body>
    <jsp:include page="../include/header.jsp" />
	<h1 id="title">MBTI 검사</h1>
	<c:forEach items="${questions}" var="question" varStatus="status">
	    <div>
			<h4 class="question" >${question.mbtiQuestionContent}</h4>
			<input id="question${status.count}" type="radio" class="positive3" name="${status.count}" value=3/>
			<input id="question${status.count}" type="radio" class="positive2" name="${status.count}" value=2/>
			<input id="question${status.count}" type="radio" class="positive1" name="${status.count}" value=1/>
			<input id="question${status.count}" type="radio" class="middle0" name="${status.count}" value=0/>
			<input id="question${status.count}" type="radio" class="nagative1" name="${status.count}" value=1/>
			<input id="question${status.count}" type="radio" class="nagative2" name="${status.count}" value=2/>
			<input id="question${status.count}" type="radio" class="nagative3" name="${status.count}" value=3/>
		</div>
		<br/>
	</c:forEach>
	
	<c:if></c:if>
	
    <jsp:include page="../include/footer.jsp" />

</body>
</html>