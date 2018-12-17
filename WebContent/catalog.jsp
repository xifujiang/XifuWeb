<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/*绝对路径*/
	String ctx = request.getContextPath();
	pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>目录</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/header.css" />
		<link rel="stylesheet" type="text/css" href="css/catalog.css" />
	</head>

	<body>
		<!--头部-->
		<div id="header">

			<div class="h_top">
				<div class="low">
					<div class="list">
						<a href="#" title="首页">首页</a>
						<a href="#" title="小说专区">小说专区</a>
						<a href="#" title="视频专区">视频专区</a>
						<a href="#" title="同人专区">同人专区</a>
						<a href="#" title="周边区">周边区</a>
						<a href="#" title="讨论栏">讨论栏</a>
					</div>
					<div class="login">
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
				</div>
			</div>
			<div class="h_center">
				<div class="word">
					<img src="img/word.jpg" alt="">
				</div>
				<div class="search">
					<form action="#">
						<input type="text" placeholder="输入你想要的信息" class="t_input">
						<input type="submit" value="提交" class="t_button">
					</form>
					<div class="hot">
						<p>最新话题</p>
						<a href="#">镇魂</a>
						<a href="#">S.C.I迷案团</a>
						<a href="#">逐月之月</a>
						<a href="#">一年生</a>
					</div>
				</div>
			</div>
		</div>
		<!--中部-->
		<div id="novel_center">
			<div class="c_top">
				<div class="top_list">
					<ul>
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">更新</a>
						</li>
						<li>
							<a href="#">排行</a>
						</li>
						<li>
							<a href="#">专题</a>
						</li>
					</ul>
				</div>
				<div class="novel_search">
					<form action="#">
						<input type="text" placeholder="输入关键字" class="t_input">
						<input type="submit" class="t_button">
					</form>
				</div>
			</div>
			<div class="c_topic">
				<!--话题-->
				<ul>
					<li>
						<a href="#">古代架空</a>
					</li>
					<li>
						<a href="# ">近代现代</a>
					</li>
					<li>
						<a href="# ">星际科幻</a>
					</li>
					<li>
						<a href="# ">恐怖悬疑</a>
					</li>
					<li>
						<a href="# ">玄幻奇幻</a>
					</li>
				</ul>
			</div>
			<div class="cen_novel">
				<div class="c_topic_img">
									<img src="img/novel-img/breaksky.png" />
				
					<img src="E:/brother/novel-img/${novel.nlogo }" alt="">
				</div>
				<div class="c_topic_intro">
					<div>
						<span class="info-name">${novel.nname }</span>
					</div>
					<div class="info-tag">
						<span class="info-detail">作者：</span>
						<a href="#">${novel.nauthor }</a>
						<span class="info-detail">类别：</span>
						<a href="#">${novel.nclass }</a>
						<span class="info-detail">状态：</span>
						<label class="info-state">${novel.nstatic }</label>
					</div>
					<div class="info-pop">
						<span class="info-detail">点击：</span>
						<label class="info-state">${novel.nhot }</label>
						<span class="info-detail">推荐：</span>
						<label class="info-state">555</label>
						<span class="info-detail">收藏：</span>
						<label class="info-state">76483</label>
					</div>
					<div class="info-click">
						<button type="button" class="info-read">
							<span>点击阅读</span>
						</button>
						<div class="btn-group">
							<a href="#">收藏</a>
							<a href="#">打赏</a>
							<a href="#">推荐</a>
							<a href="#">分享</a>
						</div>
					</div>
				</div>
			</div>
			<div class="cen_simple">
				<p class = "cen_s_title">文章简介</p>
				<p class = "cen_s">${novel.nintro }</p>
			</div>
			<div class="cen_catalog">
				<p class = "cen_s_title">目录</p>
				<ul class = "cata_list">
					<c:forEach items="${allchapters }" var="chapter" varStatus="status">
						<li><a href="${ctx }/ReadContentServlet?nid=${novel.nid }&cid=${chapter.cid }" method="post"><span>${chapter.chapter }</span></a></li>
					</c:forEach>
					
				</ul>
			</div>
		</div>
	</body>

</html>