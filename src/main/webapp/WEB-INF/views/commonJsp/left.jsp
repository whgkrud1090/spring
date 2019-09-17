<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
	<!-- a tag : get method -->
	<li class="active"><a href="${cp }/user/userList">사용자 리스트 <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/user/userListOnlyHalf">사용자리스트(onlyHalf) <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/user/userPagingList?page=1">사용자 페이징 리스트<span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/lprod/lprodList">제품그룹 리스트</a></li>
	<li class="active"><a href="${cp }/lprod/lprodPagingList?page=1">제품그룹 페이징 리스트</a></li>
</ul>