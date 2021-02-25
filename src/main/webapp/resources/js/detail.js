
window.onload = showDetail(mid); 
function showDetail(id){
		getList(mid);
		$.getJSON("https://api.themoviedb.org/3/movie/"+id+"?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko", function(data) {
				var date = (data.release_date).split('-');
				var year = date[0];
				document.title = data.title;
				console.log(data);
				$(".movie_poster").html("<img src='http://image.tmdb.org/t/p/w500/" + data.poster_path + "'/>")
				$(".detail_span").append("<div class='movie_title'>"+data.title+"("+data.original_title+", "+year+")</div>")
				$(".detail_span").append("<div class='overview'>"+data.overview+"</div>");
				for(var i=0; i<data.genres.length; i++){
					$(".detail_etc").append('<div class="detail_genre">'+data.genres[i].name+' </div>');
				};
				$(".detail_etc").append("<div><strong>상영시간 "+data.runtime+"분</strong><div>")
				$.getJSON("https://api.themoviedb.org/3/movie/"+id+"/similar?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(json){
					console.log(json);
					$(".if_you").html('<div>이 영화가 마음에 드셨다면..</div>');
					for(var i=0; i<5; i++){
						$(".detail_similar").append('<div class="columns"><a href="detail?id='+json.results[i].id+'&option=movie"><img src=\"http://image.tmdb.org/t/p/w500/' + json.results[i].poster_path + '\" class=\"img-responsive\" ></a></div>');	
					}
				});
				$.getJSON("https://api.themoviedb.org/3/movie/"+id+"/credits?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&language=ko",function(credit){
					console.log(credit);
					if (credit != "Nothing found."){                 
							for(var i=0; i<credit.crew.length; i++){
								if(credit.crew[i].job == "Director"){
									var director = credit.crew[i].name;
									if(credit.crew[i].profile_path != null){
										$('.detail_crew').append('<div class="columns"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.crew[i].profile_path + '\" class=\"img-responsive\" ><span>'+director+'<br/>(감독)</span></div>');		
										break;
									}else{
										$('.detail_crew').append('<div class="columns no_img"><span>'+director+'</span><br/>(감독)</div>');								
									}
								}
							}
							if(credit.cast.length > 3){
								for(var i=0; i<4; i++){
									if(credit.cast[i].profile_path != null){
										$('.detail_crew').append('<div class="columns"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.cast[i].profile_path + '\" class=\"img-responsive\" ><span>'+credit.cast[i].original_name+'<br/>('+credit.cast[i].character+' 역)</span></div>');
									}else{
										$('.detail_crew').append('<div class="columns no_img"><span>'+credit.cast[i].original_name+'<br/>('+credit.cast[i].character+' 역)</span></div>');
									}
								}
							}else{
								for(var i=0; i<3; i++){
									if(credit.cast[i].profile_path != null){
										$('.detail_crew').append('<div class="columns"><img src=\"http://image.tmdb.org/t/p/w500/' + credit.cast[i].profile_path + '\" class=\"img-responsive\" ><span>'+credit.cast[i].original_name+'<br/>('+credit.cast[i].character+' 역)</span></div>');
									}else{
										$('.detail_crew').append('<div class="columns no_img"><span>'+credit.cast[i].original_name+'<br/>('+credit.cast[i].character+' 역)</span></div>');
									}
								}
							}
		        	}
				});
		});
	};


	$(".c_submit").click(function(e){
		var review = $(".c_content").val();
		if(review == null || review == ""){
			alert("내용을 입력하세요");
			return;	
		}
		$.ajax({
			type:"POST",
			url: "writeReview",
			data:{
				mid : mid,
				nickName : nickName,
				review : review
			},
			success : function(result){
	  			console.log(result);
	  			$(".edit_box").css("display","none");
	  			getList();
	  		},
	  		error:function(data){
	  			location.href = data;
	  		}
		});
	});