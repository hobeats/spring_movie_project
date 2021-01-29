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
<div class="container">
  <div class="search_center">
    <span class="service_title">movie.gg</span>
    <div id="fetch">
      <input type="text" id="term" />
      <select>
      	<option>영화</option>
      	<option>TV</option>
      	<option>사람</option>
      </select>
      <!--  <button id="search">검색</button> -->
    </div>
    <div id="poster">
    </div>
  </div>
</div>
</body>
<script>
$('#term').focus(function(){
    var full = $("#poster").has("img").length ? true : false;
    if(full == false){
       $('#poster').empty();
    }
 });

 var getPoster = function(){
	 $('#poster').html("");
     var film = $('#term').val();

     if(film == ''){
          $('#poster').html('<div class="alert">검색 항목을 입력해주세요.</div>');
     } else {
          $.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko&query=" + film + "&callback=?", function(json) {
             if (json != "Nothing found."){                 
					console.log(json);
					for(var i=0; i<json.results.length; i++){
						var date = (json.results[i].release_date).split('-');
						var year = date[0];
						$('#poster').append('<div id="columns"><a href="detail?id='+json.results[i].id+'"><img src=\"http://image.tmdb.org/t/p/w500/' + json.results[i].poster_path + '\" class=\"img-responsive\" ></a></div><div><strong>' + json.results[i].title +'('+year+') </strong></div>');	
					}
                } else {
                   $.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=goonies&callback=?", function(json) {
                     console.log(json);                 
                      $('#poster').html('<div class="alert"><p>We\'re afraid nothing was found for that search.</p></div><p>Perhaps you were looking for The Goonies?</p><img id="thePoster" src="http://image.tmdb.org/t/p/w500/' + json[0].poster_path + ' class="img-responsive" />');
                   });
                }
           });
        }
      return false;
 }
 //$('#search').click(getPoster);
 $('#term').keyup(function(event){
     if(event.keyCode == 13){
         getPoster();
     }
 });
</script>
</html>