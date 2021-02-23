	if(message !=null && message !=''){
		if(message == "비밀번호가 일치하지 않습니다."){
			$(".alert").html(message);
			$(".pw").val('');
			$(".pw_input").focus();
		}else if(message == "이미 존재하는 이메일입니다."){
			$(".alert").html(message);
			$(".uid").val('');
			$(".email_addr1"),focus();
		}
	}
	
	$(".signUpForm").submit(function(e){
		var email = $(".uid").val();
		if(!(email_check(email))){
			$(".alert").html("잘못된 형식의 이메일 입니다.");
			e.preventDefault();
		}
	});
	
	function email_check(email) {
    	var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
   		return (email != '' && email != 'undefined' && regex.test(email));

	}
	
	