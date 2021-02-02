<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<body>
<div class="login-page">
  <form action="signInPost" class="login-form" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					 <input type="text" name="userid" placeholder="id" required/><br/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="userpw" placeholder="password" required><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label> 
						<input type="checkbox" name="userCookie" />
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-success" value="로그인" />
				</td>
			</tr>
		</table>
		<p class="msg">Not registered? 
      <a href="#">Create an account</a></p>
	</form>
</div>
</body>
<script>
	var message = '${message}';
	if(message !=null && message !=''){
		alert(message);
	}
	</script>


</html>