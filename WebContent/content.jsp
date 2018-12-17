<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>小说内容</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/content.css" />
</head>

<body>
	<div id="header">
		<div class="head_cen">
			<div class="h_left">
				<img src="img/word.jpg" />
			</div>
			<div class="h_right">
				<c:if test="${empty user}">
								欢迎您
							<a href="login.jsp" alt="">登录</a>
				</c:if>
				<c:if test="${!empty user }">
	                	欢迎：${user.username }
	                	<a href="#" style="margin-left: 10px; color: #666666">退出登录</a>
					<a href="${ctx }/person-divide.jsp" target="_blank"
						style="margin-left: 10px; color: #666666">个人中心</a>
				</c:if>
			</div>
			<div class="h_search">
				<form action="#">
					<input type="text" placeholder="输入你想要的信息" class="t_input">
					<input type="submit" value="提交" class="t_button">
				</form>
			</div>
		</div>
	</div>
	<div id="center">
		<div class="c_left_top">
			<div class="chapter_title">
				<span id=""> ${novelContent.chapter } </span>
			</div>
			<div class="novel_name">
				<span id=""> ${novel.nname } </span>
			</div>
			<div class="novel_author">
				<span id=""> ${novel.nauthor } </span>
			</div>
			<div class="c_right">
				<a href="${ctx }/GetChapterServlet?nid=${novel.nid }">目录</a> <a href="#">设置</a>
				 <a href="${ctx }/ReadContentServlet?nid=${novel.nid }&cid=${chapter.cid-1 }">上一章</a> <a
					href="#">${ctx }/ReadContentServlet?nid=${novel.nid }&cid=${chapter.cid+1 }</a> <a href="#">标签</a> <a href="#">打赏推荐</a> <a
					href="#">分享</a> <a href="#">意见反馈</a>
			</div>
			 <div class="out_actitle">
            <div class="in_actitle">
                <div class="content">
					<p>${novelContent.content }</p>
	                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>