	
	
	var page = 1;
	window.onload = function(){
		document.title = keyword + " :: 검색결과"
		showResult(page)
	}
	function showResult(page){
		$.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko&query="+keyword+"&page="+page, function(json) {
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
							$('.search_result').append('<div class="columns"><a href="detail?id='+json.results[i].id+'"><img src='+img+' class=\"img-responsive\" ><div><span class="title">' + json.results[i].title +'('+year+') </span></div></a></div>');
						}else{
							$('.search_result').append('<div class="columns"><a href="detail?id='+json.results[i].id+'"><div class="no_image"><span class="title">' + json.results[i].title +'('+year+') </span></div></a></div>');
						}
						
						
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