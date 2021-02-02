<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>${keyword} :: 검색결과</title>
</head>
<body>
<div class="search_result">

</div>
</body>
<script>
	var page = 1;
	window.onload = function(){
		showResult(page)
	}
	function showResult(page){
		$.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko&query=${keyword}&page="+page, function(json) {
            if (json != "Nothing found."){
					console.log(json);
					
					for(var i=0; i<json.results.length; i++){
						if(json.results[i].release_date != null){
							var date = (json.results[i].release_date).split('-');
							var year = date[0];
						}else{
							var year = ""
						}
						if(json.results[i].poster_path != null){
							var img = "http://image.tmdb.org/t/p/w500/" + json.results[i].poster_path;
						}else{
							var img = "https://upload2.inven.co.kr/upload/2017/10/20/bbs/i15385224326.jpg";
						}
						$('.search_result').append('<div id="columns"><a href="detail?id='+json.results[i].id+'&option=movie"><img src='+img+' class=\"img-responsive\" ></a></div><div><strong>' + json.results[i].title +'('+year+') </strong></div>');
						
					}
               } else {
                 
               }
          });
	}
	$(window).scroll(function(){
		var dh = $(document).height();
		var wh = $(window).height();
		var wt = $(window).scrollTop();
		
		if((wt+wh)>=(dh -10)){
			page++;
			showResult(page);
		}
	});


</script>
</html>