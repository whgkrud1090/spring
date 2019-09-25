<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%-- 수업시간에 사용한 fmt태그를 이용하여 userPagingList의 reg_dt 
	 컬럼(date 타입)을 문자열 (yyyy/MM/dd)로 출력될 수 있게끔 소스 코드 수정 
--%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>
<%@ include file="/WEB-INF/views/commonJsp/basicLib.jsp"%>

</head>

<body>
<form id="frm" action="${cp }/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
	
	<!-- header -->
	<tiles:insertAttribute name="header"/>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<tiles:insertAttribute name="left"/>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			</div>
		</div>
	</div>
</body>
</html>
