<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<style>
</style>
<title></title>
</head>
<body>
	<div class="detail_container"></div>
</body>
<script>
	window.onload = showDetail(${id}); 
	function showDetail(id){
			$.getJSON("https://api.themoviedb.org/3/movie/"+id+"?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko", function(data) {
					var date = (data.release_date).split('-');
					var year = date[0];
					document.title = data.title;
					console.log(data);
					$(".detail_container").append("<div class='movie_poster'><img src='http://image.tmdb.org/t/p/w500/" + data.poster_path + "'/></div>")
					$(".detail_container").append("<div class='movie_title'>"+data.title+"("+data.original_title+", "+year+")</div>")
					$(".detail_container").append("<div class='overview'>"+data.overview+"</div>");
					for(var i=0; i<data.genres.length; i++){
						$(".detail_container").append(data.genres[i].name + " ")
					};
					$(".detail_container").append("<strong>상영시간 "+data.runtime+"분</strong>")
					$.getJSON("https://api.themoviedb.org/3/movie/"+id+"/similar?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(json){
						console.log(json);
						for(var i=0; i<6; i++){
							$(".detail_container").append('<div id="columns"><a href="detail?id='+json.results[i].id+'&option=movie"><img src=\"http://image.tmdb.org/t/p/w500/' + json.results[i].poster_path + '\" class=\"img-responsive\" ></a></div>');	
						}
					});
					$.getJSON("https://api.themoviedb.org/3/movie/"+id+"/credits?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(credit){
						console.log(credit.crew);
						if (credit != "Nothing found."){                 
								for(var i=0; i<credit.crew.length; i++){
									if(credit.crew[i].job == "Director"){
										var director = credit.crew[i].name;
										$('.detail_container').append('<div id="columns"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.crew[i].profile_path + '\" class=\"img-responsive\" ></a>'+director+'(감독)</div>');		
										break;
									}
								}
								for(var i=0; i<4; i++){
									$('.detail_container').append('<div id="columns"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.cast[i].profile_path + '\" class=\"img-responsive\" ></a></div>');	
								}
			        	}
					});
			});
	
			
</script>
</html>