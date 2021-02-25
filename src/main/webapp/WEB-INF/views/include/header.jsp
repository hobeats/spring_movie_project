<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIEBOM</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://kit.fontawesome.com/0a2328e65c.js" crossorigin="anonymous"></script>
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
</head>
<body>
	<nav>
		<div class="logo span"><a href="${pageContext.request.contextPath }/">MOVIE<span class="b">B</span><span class="o">O</span><span class="m">M</span></a></div>
		<div class="search_center">		
			<form action="${pageContext.request.contextPath }/result" class="search">
				<input type="text" name="keyword" value="${keyword}"class="search_keyword" />
				<i class="fas fa-search"></i>
			</form>
		</div>
		<div class="nav_user">
			<c:choose>
				<c:when test="${!empty sessionScope.userInfo}">
					<div class="userInfo"><img src="${userInfo.profile_path}"/></div>
				</c:when>
				<c:otherwise>
					<div class="span"><a href="${pageContext.request.contextPath }/user/login">로그인</a></div>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<div class="userHover">
			<div class="userDetail"><a href="${pageContext.request.contextPath }/user/userdetail">내 정보</a></div>
			<div class="logout"><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></div>
	</div>
	<script>
		$(".search_center").click(function(){
			$(".search_keyword").focus();
		});
		$(".userInfo").click(function(){
			$(".userHover").css("display","block");
		});
		
	</script>