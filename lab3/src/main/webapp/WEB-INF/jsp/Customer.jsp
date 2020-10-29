<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul class="dropdown-menu dropdown-user">
	<li><a href="#"><i class="fa fa-user fa-fw"></i>
	               用户：${USER_SESSION.user_name}
	    </a>
	</li>
	<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>
	<li class="divider"></li>
	<li>
		<a href="${pageContext.request.contextPath }/logout.action">
		<i class="fa fa-sign-out fa-fw"></i>退出登录
		</a>
	</li>
</ul>
</body>
</html>