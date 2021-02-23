<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="include/header.jsp"/>
<link href="${pageContext.request.contextPath}/resources/css/home.css" rel="stylesheet"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://kit.fontawesome.com/0a2328e65c.js" crossorigin="anonymous"></script>
<script>
	$(".fa-search").click(function(){
		$(".search").submit();
	});
</script>
<iframe class="video" width="1280" height="720" src="https://www.youtube.com/embed/tEpYfv7hLio?controls=0&autoplay=1" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</body>
</html>