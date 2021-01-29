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
	<input id="movie_id" type="hidden" value="${movie}" />
	<div class="movie_container">
		<div class="movie_poster">
		</div>
		<div class="movie_detail">
					
		</div>
	</div>
</body>
<script>
	var movieId = $("#movie_id").val();
	showMovieDetail(movieId)
	function showMovieDetail(id){
		 $.getJSON("https://api.themoviedb.org/3/movie/"+id+"?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko-KR", function(data) {
			 console.log(data);
			 $(".movie_poster").html("<img src='http://image.tmdb.org/t/p/w500/" + data.poster_path + "'/>")
			 $(".movie_detail").html(data.genres[0].name);
		 });
	}
</script>
</html>