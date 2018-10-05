<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>    
	<c:when test="${data != null and fn:length(data) > 0}">
	<div class="tableContainer">
		<table style="width:100%;">
			<thead>
				<tr>
					<th style="width:10%;">번호</th>
					<th style="width:60%;">제목</th>
					<th style="width:30%;">등록일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${data}">		
				<tr>
					<td class="txtct">${item.bid}</td>
					<td>
						<a href="boardDetail.bo?btype=${param.btype}&bid=${item.bid}">
						${item.btitle}
						</a>
					</td>
					<td>${item.bregdate}</td>
				</tr>			 
				</c:forEach>
			</tbody>
		</table>	
		<div>
			<c:forEach begin="1" end="${entireBoardPage}" step="1" var="cnt">
				<c:choose>
					<c:when test="${param.page == cnt}">
						${cnt}
					</c:when>
					<c:otherwise>
						<a href="boardList.bo?btype=${param.btype}&page=${cnt}">${cnt}</a>	
					</c:otherwise> 
				</c:choose>
				
			</c:forEach>
		</div>
	</div>
	
	</c:when>
	<c:otherwise>
		게시글이 없습니다.
	</c:otherwise>
</c:choose>
<div class="bottom">
	<a href="boardRegModForm.bo?btype=${param.btype}&bid=0"><button>글쓰기</button></a>
</div>	






