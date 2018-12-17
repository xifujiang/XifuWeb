<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*绝对路径*/
	String ctx = request.getContextPath();
	pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>作家左边列表</title>
		<link rel="stylesheet" type="text/css" href="${ctx }/css/common.css" />
		<style type="text/css">
			.c_blank{
				width: 33%;
				height:640px;
				float:left;
			}
			.c_left {
				width: 67%;
				height: 640px;
				float: left;
			}
			
			.c_left .sidebar_content {
				width: 100%;
				height: 640px;
				padding-top: 5px;
				border: 1px solid #e5e5e5;
			}
			
			.c_left .sidebar_content .sub_list {
				font-family: '微软雅黑';
				font-weight: normal;
				font-size: 20px;
				background: #fff;
			}
			
			.c_left .sidebar_content .sub_list li {
				padding-left: 21px;
				border-top: 1px solid #e5e5e5;
				margin-bottom: 15px;
			}
			
			.c_left .sidebar_content .sub_list li a {
				color: #555555;
			}
		</style>


	</head>

	<body>
		<div class = "c_blank">
			
		</div>
		<div class="c_left">
			<div class="sidebar_content">
				<ul class="sub_list">
					<li>
						<a href="#"><i class="icon_font">&#xe008;</i>作者首页</a>
					</li>
					<li>
						<a href="${ctx }/ReadNovelServlet" target="author-mainFrame"><i class="icon_font">&#xe005;</i>小说管理</a>
					</li>
					<li>
						<a href="#"><i class="icon_font">&#xe006;</i>收入管理</a>
					</li>
					<li>
						<a href="#"><i class="icon_font">&#xe004;</i>基本信息</a>
					</li>
					<li>
						<a href="#"><i class="icon_font">&#xe012;</i>作家福利</a>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>