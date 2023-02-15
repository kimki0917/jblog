<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${jblog.title }</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath}">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="">로그아웃</a></li>
						<li><a
							href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그
								관리</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:if test="${not empty postVo }">
						<h4>${postVo.title }</h4>
						<p>${postVo.contents}</p>
					</c:if>
				</div>

				<ul class="blog-list">					
					<c:choose>
						<c:when test="${not empty postList }">
							<c:forEach items="${postList }" var="post">
								<li><a href="${pageContext.request.contextPath}/${jblog.id}/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.regDate }</span>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li>작성된 글이 없습니다.</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${list }" var="vo">
					<li><a
						href="${pageContext.request.contextPath}/${vo.id}/${vo.no}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>