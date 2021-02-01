<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="result" method="get">
	<div class="container">
	  <div class="search_center">
	    <span class="service_title">movie.gg</span>
	    <div id="fetch">
	      <input type="text" name="keyword" class="search_keyword" />
	      <select name="option" class="search_optiion">
	      	<option value="movie">영화</option>
	      	<option value="person">인물</option>
	      </select>
	    </div>
	</div>
</div>
</form>
</body>
</html>