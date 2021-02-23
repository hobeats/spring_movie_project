<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp"/>

<link href="<c:url value="/resources/css/join.css" />" rel="stylesheet">	

<html>
<body>	
	<div class="join_page">
		<form action="signUp" class="signUpForm" method="post">
			<table>
				<tr>
					<td></td>
					<td class="alert">
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
					<input type="text" name="uid" class="uid" placeholder="email" required />
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="upw" placeholder="password" class="pw pw_input" required /> <br/>
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
						<input type="text" name="nickName" placeholder="nickname" required/> <br/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" class="btn btn-success" value="회원가입">
					</td>
				</tr>
			</table>
		</form>
</div>
</body>


<script>
	var message = '${message}';
</script>
<script src="${pageContext.request.contextPath}/resources/js/join.js">
</script>
</html>