<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<title>Jsp-basicLib</title>
<%@ include file="/WEB-INF/views/commonJsp/basicLib.jsp"%>

</head>
<body>
	
	<tiles:insertAttribute name="header"/>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<tiles:insertAttribute name="left"/>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<tiles:insertAttribute name="content"/>
			</div>
		</div>
	</div>
</body>
</html>
