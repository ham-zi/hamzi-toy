<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style>
	    .body-area {
	        width: 100%;
	        max-width: 1200px;
	        min-height: 600px;
	        margin: 48px auto;
	        padding: 0 24px;
	        box-sizing: border-box;
	        text-align: center;
	    }

	    .text-result {
	        color: brown;
	    }

	    .image-result {
	        display: block;
	        width: 300px;
	        max-width: 100%;
	        aspect-ratio: 1 / 1;
	        height: auto;
	        object-fit: contain;
	        margin: 24px auto;
	        padding: 16px;
	        box-sizing: border-box;
	        background-color: #fff;
	        border: 1px solid #e8e8e8;
	        border-radius: 24px;
	        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
	    }
	</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	
	
	
	<div class="body-area">
		<h1 class="text-result"> ${mbti.mbtiName} </h1>
		<img class="image-result"
		     src="${mbti.mbtiImageUrl}"
		     alt="${mbti.mbtiName} 캐릭터">
		<h4 class="text-result">${mbti.mbtiContext}</h4>
	</div>

	
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>