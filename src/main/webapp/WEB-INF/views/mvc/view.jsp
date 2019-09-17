<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//pathBtn이 클릭 될때 이벤트 핸들러
		$('#pathBtn').on('click', function() {
			var subath = $("input[name=path]:checked").val();
			$('#frm').attr('action', '${cp }/mvc/path/' + subath);
			$('#frm').submit();
		})
	})
</script>
</head>
<body>
	<h2>mvc.view.jsp</h2>
	<h3>requestParam</h3>
	<form action="${cp }/mvc/requestParam">
		userId : <input type="text" name="userId" value="sally"/>
		<input type="submit" value="전송"/> 
	</form>
	
	<h3>path variable</h3>
	<form id="frm">
		brown <input type="radio" name="path" value="brown" checked="checked"/>
		sally <input type="radio" name="path" value="sally"/>
		<input type="button" id="pathBtn" value="전송"/>
	</form>
</body>
</html>