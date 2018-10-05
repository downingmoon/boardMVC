<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="subContainer">
	<form action="boardRegModProc.bo" method="post">
		<div>
			<label>제목: <input type="text" name="btitle" value="${data.btitle}" required></label>
		</div>
		<div>
			<label>
				내용 <br>
				<textarea name="bcontent" required>${data.bcontent}</textarea>
			</label>
		</div>
		<input type="hidden" name="btype" value="${param.btype}">
		<input type="hidden" name="bid" value="${param.bid}">		
		<input type="submit" value="전송">		
	</form>
</div>