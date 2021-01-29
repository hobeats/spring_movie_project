<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<style>
	*{
		margin:0;
	}
	body{
		width: 70%;
		height: 80%;
		margin: auto;	
	}
	.movie_container{
		display: flex;
		justify-content: space-around;
	}
	
	.movie_container img{
		width: 100%;
	}
	.movie_detail{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		width:70%;
		padding: 20px;
	}
	#similar, #credit{
		display:flex;
		width: 1000px;
		margin-top: 10px;
	}

	#similar img, #credit img{
		width: 80%;
		margin-left: 5px;
		box-shadow: 9px 9px 39px -9px rgba(0,0,0,0.75);
		-webkit-box-shadow: 9px 9px 39px -9px rgba(0,0,0,0.75);
		-moz-box-shadow: 9px 9px 39px -9px rgba(0,0,0,0.75);
	}
	#similar img:hover{
		transform: scale(1.15);
		-webkit-transition: transform 0.1s ease-out;
		
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<input id="movie_id" type="hidden" value="${movie}" />
	<div class="movie_container">
		<div class="movie_poster">
		</div>
		<div class="movie_detail">
			<div class="movie_title">
			</div>
			<div id ="overview">
			</div>
			<div id ="genres">
				<span id="runtime"></span>
			</div>
			<div id="credit">
			
			</div>
			
		</div>
	</div>
	<span>유사한 영화</span>
	<div id="similar">
		
	</div>
</body>
<script>
	var movieId = $("#movie_id").val();
	showMovieDetail(movieId)
	function showMovieDetail(id){
		 $.getJSON("https://api.themoviedb.org/3/movie/"+id+"?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko-KR", function(data) {
			var date = (data.release_date).split('-');
			var year = date[0];
			console.log(data);
			console.log(data.overview);
			$(".movie_poster").html("<img src='http://image.tmdb.org/t/p/w500/" + data.poster_path + "'/>")
			$(".movie_title").html(data.title+"("+data.original_title+", "+year+")")
			$("#overview").html(data.overview);
			for(var i=0; i<data.genres.length; i++){
				$("#genres").append(data.genres[i].name + " ")
			}
			$("#runtime").html("<strong>상영시간 "+data.runtime+"분</strong>")
			
		 });
		 $.getJSON("https://api.themoviedb.org/3/movie/"+id+"/similar?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko-KR",function(json){
			console.log(json);
			 if (json != "Nothing found."){                 
					console.log(json);
					for(var i=0; i<6; i++){
						$('#similar').append('<div id="columns"><a href="detail?id='+json.results[i].id+'"><img src=\"http://image.tmdb.org/t/p/w500/' + json.results[i].poster_path + '\" class=\"img-responsive\" ></a></div>');	
					}
             }
		 });
		 
		 $.getJSON("https://api.themoviedb.org/3/movie/"+id+"/credits?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko-KR",function(credit){
			 if (credit != "Nothing found."){                 
					console.log(credit);
					for(var i=0; i<4; i++){
						$('#credit').append('<div id="columns"><a href="detail?id='+credit.cast[i].id+'"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.cast[i].profile_path + '\" class=\"img-responsive\" ></a></div>');	
					}
          }
		 })
	}
</script>
</html>