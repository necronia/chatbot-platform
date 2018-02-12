<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="nexcore.sprout.foundry.constants.SproutConstants"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Error</title>
</head>
<style type="text/css">
</style>
<script language="javascript">
	
</script>
<%
	//임시 에러 페이지로 에러 메시지를 보여주고 확인 버튼을 통해 특정 페이지 또는 메인 페이지로 이동함
	//프로젝트에서는 아래 샘플을 참조하여 화면을 구성

	String redirectUri = (String) request.getAttribute(SproutConstants.OLD_REDIRECT_URI);

	if (null == redirectUri || "".equals(redirectUri)) {
		redirectUri = request.getContextPath();
	} else {
		redirectUri = request.getContextPath() + redirectUri;
	}
%>
<body bgcolor="#ffffff" text="#000000">
	${errorMessage}
	</br>
	</br>
	<form action="/<%=redirectUri%>">
		<input type="submit" value="확인">
	</form>

	<!-- 타이틀 시작 -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="93%">문제 발생!</td>
		</tr>
	</table>
	<!-- 타이틀 끝 -->

	<!-- Exception 메시지 처리 -->
	<table width="100%" border="1">
		<tr valign="top">
			<td width="40%"><b>Error:</b></td>
			<td>${pageContext.exception}</td>
		</tr>
		<tr valign="top">
			<td><b>URI:</b></td>
			<td>${pageContext.errorData.requestURI}</td>
		</tr>
		<tr valign="top">
			<td><b>Status code:</b></td>
			<td>${pageContext.errorData.statusCode}</td>
		</tr>
		<tr valign="top">
			<td><b>Error code:</b></td>
			<td>${result}</td>
		</tr>
		<tr valign="top">
			<td><b>Message:</b></td>
			<td>${pageContext.exception.message}</br>${errorMessage}</td>
		</tr>
		<tr valign="top">
			<td><b>Stack trace:</td>
			<td><pre><%
			out.flush();
			exception.printStackTrace(response.getWriter());
			%>
			</pre>
			</td>
		</tr>
	</table>
</body>
</html>

