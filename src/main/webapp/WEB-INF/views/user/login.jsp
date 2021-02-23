<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp"/>

<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

<body>
<div class="login_page">
  <form action="signInPost" class="login-form" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					 <input type="text" name="uid" placeholder="id" required/><br/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="upw" placeholder="password" required><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label> 
						<input type="checkbox" name="userCookie" />
						회원정보 기억하기
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-success" value="로그인" />
					<a href="${pageContext.request.contextPath}/user/join">회원가입</a>
				</td>
			</tr>
		</table>
      
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