<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<body>
	<div class="login-page">
	<h3><a href="">HOME</a></h3>
	<h3>SIGN UP</h3>
	<form action="signUpPost" class="" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
				<input type="text" name="userid" placeholder="id" required /> <br/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="userpw" placeholder="password" required /> <br/>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
				<input type="password" name="repw" placeholder="user re pass" required>
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>
					<input type="text" name="nickname" placeholder="nickname" required/> <br/>
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td>
					<input type="text" name="email" placeholder="email address" required/><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-success" value="회원가입">
				</td>
			</tr>
	</table>
	 			<p class="msg">Already registered? 
				<a href="login.jsp">Sign In</a>
	
	</form>
</div>
<script>
	var message = '${message}';
	if(message !=null && message !=''){
		alert(message);
	}
	</script>
</body>
</html>