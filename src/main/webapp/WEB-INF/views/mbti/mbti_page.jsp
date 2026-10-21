<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MBTI 검사</title>
   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .mbti-test { max-width: 820px; margin: 48px auto; padding: 0 24px; text-align: center; color: #263244; }
        .mbti-test #title { margin: 0 0 12px; font-size: 32px; font-weight: 700; }
        .mbti-description { margin: 0 0 36px; color: #657184; }
        .mbti-test .question-item { margin: 0; padding: 32px 12px; border-bottom: 1px solid #e8ecf1; }
        .mbti-test .question-item h4 { margin: 0 0 26px; font-size: 19px; font-weight: 600; line-height: 1.6; overflow-wrap: anywhere; }
        .mbti-test .answer-options { display: flex; align-items: center; justify-content: center; gap: 20px; }
        .mbti-test .answer-option { display: inline-flex; align-items: center; justify-content: center; margin: 0; cursor: pointer; }
        .mbti-test .answer-option input { appearance: none; -webkit-appearance: none; flex-shrink: 0; width: 34px; height: 34px; margin: 0; border: 2px solid var(--answer-color); border-radius: 50%; background: #fff; cursor: pointer; transition: background-color .15s, box-shadow .15s; }
        .mbti-test .answer-option input:hover { box-shadow: 0 0 0 5px var(--answer-halo); }
        .mbti-test .answer-option input:checked { background: var(--answer-color); box-shadow: inset 0 0 0 6px #fff; }
        .mbti-test .answer-option input:focus-visible { outline: 3px solid #263244; outline-offset: 5px; }
        .mbti-test .answer-positive { --answer-color: #36856b; --answer-halo: #e8f4ee; }
        .mbti-test .answer-neutral { --answer-color: #8993a3; --answer-halo: #f0f2f5; }
        .mbti-test .answer-negative { --answer-color: #82609d; --answer-halo: #f2ecf7; }
        .mbti-test .answer-strong input { width: 48px; height: 48px; }
        .mbti-test .answer-medium input { width: 40px; height: 40px; }
        .mbti-test .answer-scale { display: flex; justify-content: space-between; max-width: 430px; margin: 18px auto 0; font-size: 13px; color: #657184; }
        .mbti-test .answer-scale span:first-child { color: #36856b; }
        .mbti-test .answer-scale span:last-child { color: #82609d; }
        .mbti-test .page-indicator { margin: 32px 0 18px; color: #657184; font-size: 14px; }
        .mbti-test .page-actions { display: flex; justify-content: center; gap: 12px; }
        .mbti-test .page-actions button { padding: 12px 24px; border: 1px solid #d6dfe8; border-radius: 10px; background: #fff; color: #344256; font: inherit; font-weight: 600; cursor: pointer; }
        .mbti-test .page-actions .primary-action { background: #36856b; border-color: #36856b; color: #fff; }
        .mbti-test .page-actions button:hover { filter: brightness(.95); }
        .mbti-test .page-actions button:focus-visible { outline: 3px solid #263244; outline-offset: 3px; }
        @media (max-width: 480px) {
            .mbti-test { margin: 32px auto; padding: 0 16px; }
            .mbti-test .question-item { padding: 28px 0; }
            .mbti-test .question-item h4 { font-size: 17px; }
            .mbti-test .answer-options { gap: 10px; }
            .mbti-test .answer-option input { width: 26px; height: 26px; }
            .mbti-test .answer-strong input { width: 36px; height: 36px; }
            .mbti-test .answer-medium input { width: 30px; height: 30px; }
            .mbti-test .answer-scale { max-width: 290px; }
        }
    </style>
<script>
	const pageInfo = {
		page: ${pageInfo.currentPage},
		maxPage: ${pageInfo.maxPage}	
	};
	
	const result = {
		"IE" : 0,
		"SN" : 0,
		"FT" : 0,
		"JP" : 0
	}
	
	const mbtiType = {
		"I" : { axis : "IE", direction: 1 },
		"E" : { axis : "IE", direction: -1 },
		"S" : { axis : "SN", direction: 1 },
		"N" : { axis : "SN", direction: -1 },
		"F" : { axis : "FT", direction: 1 },
		"T" : { axis : "FT", direction: -1},
		"J" : { axis : "JP", direction: 1 },
		"P" : { axis : "JP", direction: -1 }
	};
	
	async function savePoint() {
		try{
			const response = await fetch("/mbti/save", {
				method: "POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body: JSON.stringify({
					page : pageInfo.page,
					result : result
				})
			});			
			return true;
		} catch (error) {
			alert("저장에 실패했습니다.");
			return false;
		}
	};
	
	function checkScore() {
		Object.keys(result).forEach(key=>{
			result[key] = 0;
		});
		const questions = document.querySelectorAll(".question-item");
		for(const question of questions) {
			const selected = question.querySelector('input:checked');
			
			if(!selected) {
				alert("모든 질문에 답해주세요.");
				return false;
			}
			
			const type = question.dataset.mbti.charAt(0);
			const target = mbtiType[type];
			const score = Number(selected.value) * Number(target.direction);
			result[target.axis] += score;
		}
	}
	
	async function nextPage() {
		checkScore();
		await savePoint();
		window.location.href = `/mbti?page=\${Number(pageInfo.page) + 1}`;
	};
	
	async function previousPage() {
		checkScore();
		await savePoint();
		window.location.href = `/mbti?page=\${Number(pageInfo.page) - 1}`;
	};
	
	async function submitButton() {
		checkScore();
		await savePoint();
		window.location.href = `/mbti/result`;
	};
	
</script>

</head>
<body>
    <jsp:include page="../include/header.jsp" />
	<main class="mbti-test"><h1 id="title">MBTI 검사</h1><p class="mbti-description">각 문장이 나와 얼마나 가까운지 선택해주세요.</p>
	<c:forEach items="${questions}" var="question" varStatus="status">
	    <div class="question-item" data-mbti="${question.mbtiTypeName}" role="group" aria-labelledby="question-title-${status.count}">
			<h4 id="question-title-${status.count}">${question.mbtiQuestionContent}</h4>
            <div class="answer-options">
                <label class="answer-option answer-positive answer-strong"><input type="radio" class="positive3" name="question${status.count}" value="3" aria-label="매우 동의"></label>
                <label class="answer-option answer-positive answer-medium"><input type="radio" class="positive2" name="question${status.count}" value="2" aria-label="동의"></label>
                <label class="answer-option answer-positive"><input type="radio" class="positive1" name="question${status.count}" value="1" aria-label="약간 동의"></label>
                <label class="answer-option answer-neutral"><input type="radio" class="middle0" name="question${status.count}" value="0" aria-label="중립"></label>
                <label class="answer-option answer-negative"><input type="radio" class="nagetive1" name="question${status.count}" value="-1" aria-label="약간 비동의"></label>
                <label class="answer-option answer-negative answer-medium"><input type="radio" class="nagetive2" name="question${status.count}" value="-2" aria-label="비동의"></label>
                <label class="answer-option answer-negative answer-strong"><input type="radio" class="nagetive3" name="question${status.count}" value="-3" aria-label="매우 비동의"></label>
            </div>
            <div class="answer-scale" aria-hidden="true"><span>동의</span><span>중립</span><span>비동의</span></div>
		</div>
		
	</c:forEach>
	
	
	
	<div class="page-indicator">페이지 ${pageInfo.currentPage} / ${pageInfo.maxPage}</div><div class="page-actions">
	<c:choose>
		<c:when test="${pageInfo.maxPage eq pageInfo.currentPage}">
			<button type="button" onclick="previousPage()">이전 페이지</button>
			<button type="button" class="primary-action" onclick="submitButton()">결과 제출</button>
		</c:when>
		<c:otherwise>			
			<c:if test="${pageInfo.currentPage gt 1}">				
				<button type="button" onclick="previousPage()">이전 페이지</button>
			</c:if>
			<button type="button" class="primary-action" onclick="nextPage()">다음 페이지</button>			
		</c:otherwise>
	</c:choose>
	
    </div></main><jsp:include page="../include/footer.jsp" />

</body>
</html>