<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<jsp:include page="../include/header.jsp"/>
<link href="${pageContext.request.contextPath}/resources/css/result.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="search_result">

</div>
</body>


<script>
	var keyword = "${keyword}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/result.js"></script>
</html>