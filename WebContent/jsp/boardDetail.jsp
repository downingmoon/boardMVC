<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="tableContainer">	
	<h1>${data.btitle}</h1>	
	<table>
		<tbody>
			<tr>				
				<th style="width:100%;">작성일시</th>				
			</tr>
			<tr>
				<td>${data.bregdate}</td>
			</tr>
			<tr>				
				<th style="width:100%;">내용</th>				
			</tr>	
			<tr>
				<td>${data.bcontent}</td>
			</tr>
		</tbody>
	</table>
	<div>
		<a href="boardRegModForm.bo?btype=${param.btype}&bid=${data.bid}"><button>글수정</button></a>
		<a href="boardDelete.bo?btype=${param.btype}&bid=${data.bid}"><button>글삭제</button></a>
	</div>
	<div>
		<c:forEach items="${commentDatas}" var="item">
			<div>
				${item.t_comment} / ${item.cregdate}
				<a href="boardCommentDelete.bo?cid=${item.cid}&btype=${param.btype}&bid=${data.bid}">삭제</a> 
			</div>
		</c:forEach>
	</div>
	<div>
		<form action="boardCommentReg.bo" method="post">
			<textarea name="t_comment"></textarea>
			<input type="submit" value="코멘트">
			<input type="hidden" name="btype" value="${param.btype}">
			<input type="hidden" name="bid" value="${data.bid}">
		</form>
	</div>
</div>