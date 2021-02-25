<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <jsp:include page="../include/header.jsp"/>
<link href="${pageContext.request.contextPath}/resources/css/userdetail.css" rel="stylesheet">
<style>
.uploadImage{
		width: 100px;
		height: 100px;
		border-radius: 50px;
		border: 1px solid #ccc;
	}
</style>
<body>
<div class="userdetail">
		<form id="detailForm" class="detail" method="post">
			<input type="hidden" name="uno" value="${userInfo.uno }" readonly/>
			<table>
				<tr>
					<td colspan="2">
						<img src="${userInfo.profile_path}" class="uploadImage" id="sImage"/><br/>
						<input type="hidden" id="profile_path" name="profile_path"/>
						<input type="file" id="pImage" accept="image/*"/>
					</td>
				</tr>
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
				(function(i){
					$.getJSON("https://api.themoviedb.org/3/movie/"+data[i].mid+"?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(result){
						console.log(result);
						var html = '<div class="columns"><a href="${pageContext.request.contextPath}/detail?id='+data[i].mid+'&option=movie"><img src=\"http://image.tmdb.org/t/p/w500/' + result.poster_path + '\" class=\"img-responsive\" ></a></div>';
							html += '<div class="title">'+result.original_title+'</div>';
							if(data[i].star != null){
								html += '<div class="star"><i class="fas fa-star"></i>'+data[i].star+'</div>';
							}
							if(data[i].review != null && !data[i].review == ""){
								html += '<i class="far fa-comment-dots"></i>'
							}
						$(".myReviewList").append(html)
						;
					});
				})(i);
			}
		}
	});
}
$(document).ready(function(){
	$("#modifyBtn").on("click", function(){
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
function getOriginalName(data){
	var idx = data.indexOf("_")+1;
	return data.substr(idx);
}

$("#pImage").on("change",function(){
	var files = this.files;
	console.log(files);
	var formData = new FormData();
	formData.append("file",files[0]);
	console.log(formData);
	
	$.ajax({
		type: "POST",
		url: "uploadProfile",
		contentType: false,
		processData: false,
		dataType: "text",
		data : formData,
		success: function(data){
			console.log(data);
			var value ="${pageContext.request.contextPath}/upload"+data
			$("#sImage").attr("src","${pageContext.request.contextPath}/upload"+data);
			$("#profile_path").val(value);
		}
	});
});
</script>


</body>
</html>