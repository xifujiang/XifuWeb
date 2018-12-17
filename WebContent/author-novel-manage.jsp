<%@page import="jsu.sif.domain.Novel"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.jdbc.Util"%>
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
<title>小说管理</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/common.css" />
<style type="text/css">
.c-right {
	width: 80%;
	height: 640px;
	float: left;
}

.c-right .c_r_create {
	display: block;
	width: 100%;
	height: 40px;
}

.c-right .c_r_create a {
	float: right;
	margin-right: 20px;
	font-size: 16px;
	color: white;
	border: 1px burlywood solid;
	background: salmon;
}

.result-content {
	margin-top: 10px;
	width: 100%;
}

.result-content .result-tab th {
	font-weight: normal;
	font-size: 15px;
	text-align: center;
	background: #f9f9f9 0 bottom repeat-x;
}

.result-content .result-tab td {
	text-align: center;
	border-bottom: 1px solid #ddd;
	border-right: 1px solid #e1e1e1;
}

.result-content .result-tab tr {
	line-height: 35px;
}

.result-content .result-tab img {
	width: 70px;
	height: 100px;
}

.result-content .result-tab tr:nth-child(odd) {
	background: #f9f9f9;
}

.result-content .result-tab tr:hover {
	background: #f1f1f1;
}

.result-content .result-tab tr.even-tr {
	background: #e9fff2;
}
</style>
</head>

<body>
	<div class="c-right">
		<form name="myform" id="myform" method="post">
			<div class="c_r_create">
				<a href="${ctx }/create-novel.jsp" target="author-mainFrame"
					class="link-update" href="#">添加作品</a>
			</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th>序号</th>
						<th>封面</th>
						<th>书名</th>
						<th>分类</th>
						<th>最新章节</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${authorNovels }" var = "novels" varStatus="status">
					<tr>
					<td>${status.index +1 }</td>
						<td><img src="${ctx }/img/novel-img/${novels.nlogo}" /></td>
						<td>${novels.nname }</td>
						<td>${novels.nclass }</td>
						<c:if test="${empty novels.chapters[0].chapter}">
							<td>暂无章节</td>
						</c:if>
						<c:if test="${!empty novels.chapters[0].chapter }">
							<td>${novels.chapters[0].chapter }</td>
						</c:if>
						
						<c:if test="${empty novels.chapters[0].time}">
							<td>/</td>
						</c:if>
						<c:if test="${!empty novels.chapters[0].time }">
							<td>${novels.chapters[0].time }</td>
						</c:if>
						<td><a href="${ctx }/insert-chapter.jsp?nid=${novels.nid}&cid=${novels.chapters[0].cid}"
							target="author-mainFrame" class="link-update" >更新章节</a>
							 <!-- 如果要删除作品，到数据库中把内容删除 -->
							<a href="${ctx }/DeleteNovelServlet?nid=${novels.nid}">删除</a>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function confirmd() {
			var msg = "亲真的要删除这本小说？";
			if (confirm(msg) == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>

</html>