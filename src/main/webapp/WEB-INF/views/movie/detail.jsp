<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/detail.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/star-rating-svg-master/dist/jquery.star-rating-svg.js"></script>
<link href="${pageContext.request.contextPath}/resources/js/star-rating-svg-master/src/css/star-rating-svg.css" rel="stylesheet">
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="detail">
		<div class="detail_poster">
			<div class="movie_poster">
			</div>
			<span>나의 평점</span>
			<div class="my-rating" data-rating="${review.star}">
			</div>
		</div>
		<div class="detail_detail">
			<div class="detail_span">
			</div>
			<div class="detail_etc">
			</div>
			<div class="detail_crew">
			</div>
			<div class="if_you"></div>
			<div class="detail_similar">
			</div>
		</div>
	</div>
	<div class="review_container">
		<c:choose>
			<c:when test="${!empty review}">
				<div class="review_my">
					<div class=''>나의 리뷰</div>
					<div class=''>${review.review }</div>
					<div class=''><i class='fas fa-star'></i>${review.star}</div>
					<div class=''><input type='hidden' value="${review.rno}"/>
					<c:if test="${!empty isliked}">
						<i class='fas fa-heart'></i>
					</c:if>
					<c:if test="${empty isliked}">
						<i class='far fa-heart'></i>
					</c:if>
					<input type='text' class='like_cnt' value='${review.liked}' readonly/></div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="edit_box">
					<input type="text" name="nickName" class="c_uname" value="리뷰쓰기"readonly/>
					<div class="review_box">
						<textarea name="content" class="c_content"></textarea>
						<button class="c_submit"><i class="fas fa-pencil-alt"></i></button>
					</div>
					<input type="hidden" name="star" class="star" value="${review.star}"/>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="review_list">
			
		</div>
	</div>
</body>
<script>
	var mid = ${id};
	var nickName = "${userInfo.nickName}";
	var contextPath = "${pageContext.request.contextPath}";
	function getList(){
		$(".review_list").html("");
		$.getJSON(contextPath+"/list/"+mid, function(data){
			for(var i=0; i<data.length; i++){
				(function(i){
					$.ajax({
						type: "POST",
						url: "isliked",
						data:{
							rno: data[i].rno,
							mid: mid,
							nickName: nickName
						},
						success: function(result){
							var time = getDate(data[i].regdate);	
							if(result != null && result.rno == data[i].rno){
								var html ="<div class=''>"+data[i].nickName+" <span>"+time+"</span></div>";
									html +="<div class=''>"+data[i].review+"</div>";
									html +="<div class=''><i class='fas fa-star'></i>"+data[i].star+"</div>";
									html +="<div class=''><input type='hidden' value="+data[i].rno+"/>";
									html += "<i class='fas fa-heart'></i>"
									html +=" <input type='text' class='like_cnt' value='"+data[i].liked+"' readonly/></div>";
								$(".review_list").append(html);
							}else{
								var html ="<div class=''>"+data[i].nickName+" <span>"+time+"</span></div>";
									html +="<div class=''>"+data[i].review+"</div>";
									html +="<div class=''><i class='fas fa-star'></i>"+data[i].star+"</div>";
									html +="<div class=''><input type='hidden' value="+data[i].rno+"/>";
									html += "<i class='far fa-heart'></i>"
									html +=" <input type='text' class='like_cnt' value='"+data[i].liked+"' readonly/></div>";
								$(".review_list").append(html);
							}
						}
					});
				})(i);
			};
		});
	}
	$(".my-rating").starRating({
		 totalStars: 5,
		 starShape: 'rounded',
		 starSize: 50,
		 hoverColor: '#4A801E',
		 disableAfterRate: false,
		 callback: function(currentRating, $el){
		  	$.ajax({
		  		type:"POST",
		  		url: "rating",
		  		data:{
		  			mid : mid,
		  			nickName : nickName,
		  			star : currentRating
		  		},
		  		success : function(data){
		  			console.log(data);
		  			getList();
		  		},
		  		error:function(data){
		  		}
		  	});
		 }
	});
	function getDate(date){
		var time = Math.ceil((new Date() - date)/1000)
		console.log(time);
		if(time>59 && time<3600){
			var time = Math.floor(time/60);
			return time + "분 전";
		}else if(time>3590 && time<86400){
			var time = Math.floor(time/3600);
			return time + "시간 전";
		}else if(time>86399 && time<2592000){
			var time = Math.floor(time/86400);
			return time + "일 전";
		}else if(time>2591999 && time<31104000){
			var time = Math.floor(time/2592000);
			return time + "개월 전";
		}else if(time>31103999){
			var time = Math.floor(time/31104000);
			return time + "년 전";
		}else{
			return time + "초 전";
		}
	}
	$(document).on("click",".fa-heart",function(){
		var oVal = parseInt($(this).next().val());
		var rno = parseInt($(this).prev().val());
		if($(this).hasClass("fas")){
			$(this).attr("class","far fa-heart");
			$(this).next().val(oVal - 1)
			$.ajax({
				type: "post",
				url: "dislike",
				data:{
					rno: rno,
					mid: mid,
					nickName: nickName
				},
				success:function(){
					console.log("좋아요 취소");
					getList();
				}
			});	
		}else{
			$(this).attr("class","fas fa-heart");
			$(this).next().val(oVal + 1)
			$.ajax({
				type: "post",
				url: "like",
				data:{
					rno: rno,
					mid: mid,
					nickName: nickName
				},
				success:function(){
					console.log("좋아요 누름");
					getList();
				}
			});	
		}
	});
</script>
<script src="${pageContext.request.contextPath}/resources/js/detail.js">
</script>
</html>