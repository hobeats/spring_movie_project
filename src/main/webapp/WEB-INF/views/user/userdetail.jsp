<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <jsp:include page="../include/header.jsp"/>
 
<html>
<link href="<c:url value="/resources/css/userdetail.css" />" rel="stylesheet">

<body>
<div class="userdetail">
		<form id="detailForm" class="detail" method="post">
			<input type="hidden" name="uno" value="${userInfo.uno }" readonly/>
			<table>
				<tr>
					<td><label>아이디</label></td>
					<td>
					<input type="text" name="uid" class="uid" value="${userInfo.uid}" readonly />
					</td>
				</tr>
				<tr>
					<td><label>비밀번호</label></td>
					<td>
						<input type="password" id="upw" name="upw" class="upw" /> <br/>
					</td>
				</tr>
				<tr>
					<td><label>닉네임</label></td>
					<td>
						<input type="text" id="nickName" name="nickName" class="nickName" value="${userInfo.nickName}"/> <br/>
					</td>
				</tr>
				
				<tr>
					<td class="button" colspan="2">
						<input type="submit" name="modify" id="modifyBtn"  value="수정"/>
						<input type="submit" name="delete" id="deleteBtn" value="회원 탈퇴"/>
					</td>
				</tr>
			</table>
		</form>
		<span><h2>내가 본 영화</h2></span>
		<div class="myReviewList">
			
		</div>
</div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

var obj = $("#detailForm");
getMyReview();
function getMyReview(){
	$.ajax({
		type:"post",
		url: "${pageContext.request.contextPath}/getList",
		data:{
			nickName : "${userInfo.nickName}"
		},
		success:function(data){
			for(var i=0; i<data.length; i++){
				$.getJSON("https://api.themoviedb.org/3/movie/"+data[i].mid+"/similar?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(result){
					$(".myReviewList").append('<div class="columns"><a href="detail?id='+data[i].mid+'&option=movie"><img src=\"http://image.tmdb.org/t/p/w500/' + results.poster_path + '\" class=\"img-responsive\" ></a></div>');
				});
			}
		}
	});
}
$(document).ready(function(){
	$("#modifyBtn").on("click", function(){
		if($("#nickName").val() == ""){
			alert("닉네임을 입력해주세요.");
			$("#nickName").focus();
			return false;
		}else if($("#upw").val() == ""){
			alert("비밀번호를 입력해주세요.");
			$("#upw").focus();
			return false;
		}
			obj.attr("action", "modify");
			obj.attr("method", "post");
			obj.submit();
	});
	
	$("#deleteBtn").on("click",function(){
		if($("#upw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#upw").focus();
			return false;
		}
		 var confirmResult = confirm('삭제 하시겠습니까?');
	
	     if (confirmResult == true) {
	    	obj.attr("action", "delete");
	    	obj.attr("method", "post");
	    	obj.submit();
	     }else {
	         return false;
	     }   
	});

	$("#homeBtn").on("click", function(){
		obj.attr("action", "home");
		obj.attr("method", "get");
		obj.submit();
		
	
	});
});

</script>


</body>
</html>