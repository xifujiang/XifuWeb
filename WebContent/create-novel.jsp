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
<!-- 上传作品 -->
<head>
<meta charset="UTF-8">
<title>新建小说</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/common.css" />
<style type="text/css">
.center {
	width: 80%;
	border: 1px solid silver;
	height: 640px;
	float: left;
}

.center .c_left {
	width: 40%;
	height: 640px;
	float: left;
}

.center .c_left .title {
	height: 40px;
	width: 40%;
	color: #CD5C5C;
	margin-right: 16px;
	float: left;
	font-size: 20px;
	padding-left: 10px;
}

.center .c_left .novel-info {
	width: 100%;
	height: 590px;
	/*border: solid silver 1px;*/
	padding-left: 10px;
}

.center .c_left .novel-info .form-item {
	width: 100%;
	height: 60px;
}

.center .c_left .novel-info .item-input {
	width: 160px;
	height: 30px;
	border: solid silver 1px;
	border-radius: 2px;
}

.center .c_left .novel-info .state_select {
	height: 30px;
	border: solid silver 1px;
	border-radius: 2px;
}

.center .c_left .novel-info .item-type {
	width: 160px;
	height: 30px;
	border: solid silver 1px;
	border-radius: 2px;
}

.center .c_left .novel-info .item-info {
	width: 200px;
	height: 60px;
	border: solid silver 1px;
	border-radius: 2px;
}

.center .c_left .novel-info .item-label {
	font-size: 16px;
	margin-right: 10px;
}

.center .c_left .novel-info .form-item {
	width: 100%;
	height: 60px;
}

.center .c_left .novel-info .form-item:last-child {
	width: 100%;
	height: 100px;
	border-bottom: #123456 2px solid;
}

.center .c_left .novel-info .c-button {
	margin-top: 20px;
	width: 200px;
	height: 68px;
}

.center .c_left .novel-info .c-button input {
	background: #CD5C5C;
	border: #CD5C5C solid 1px;
	color: white;
	height: 30px;
	width: 68px;
	margin-left: 60px;
}

.center .c_right {
	margin-top: 100px;
	width: 40%;
	height: 640px;
	float: left;
}
</style>
</head>

<body>
	<div class="center">
	<!-- 发送未编码的数据 -->
		<form action="${ctx }/CreateNovelServlet" enctype = "multipart/form-data" method="post">
			<div class="c_left">
				<div class="title">作品信息</div>
				<div class="novel-info">
					<div class="form-item">
						<label class="item-label"></label>
					</div>
					<div class="form-item">
						<label class="item-label">作品名称</label> <input type="text"
							placeholder="请输入作品名称" class="item-input" name="nname" />
					</div>
					<div class="form-item">
						<label class="item-label">作品进程</label> <select
							class="state_select" name="nstatic">&nbsp;&nbsp;
							<option value="1">完结</option>
							<option value="0">连载</option>
						</select>
					</div>
					<div class="form-item">
						<label class="item-label">作品分类</label> <select
							class="state_select" name="nclass">&nbsp;&nbsp;
							<option value="4">玄幻奇幻</option>
							<option value="3">恐怖悬疑</option>
							<option value="2">星际科幻</option>
							<option value="1">古代架空</option>
							<option value="0">近代现代</option>
						</select>
					</div>
					<div class="form-item">
						<label class="item-label">作品标签</label> <input type="text"
							class="item-type" name="nlable" />
					</div>
					<div class="form-item">
						<label class="item-label">作品介绍</label>
						<textarea autocomplete="off" spellcheck="false"
							placeholder="20-500字" class="item-cont" name="nintro" /></textarea>
					</div>
					<div class="c-button">
						<input type="submit" value="提交">
					</div>
				</div>
			</div>

			<div class="c_right">
				<div
					style="border: 1px dashed silver; widows: 240px; height: 240px;">
					上传封面
					<div class=""
						style="widows: 120px; height: 160px; border: 1px solidS silver; padding: 5px;">
					  	<img id="xmTanImg" src="${ctx }/img/novel-img/novel1.jpg" style="widows: 112px; height: 150px;"  />
						
					</div>

					<div id="xmTanDiv"></div>
					<p>
						<input type="file" id="xdaTanFileImg"
							onchange="xmTanUploadImg(this)" accept="image/*" name="nimg"/>
					</p>

				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		//判断浏览器是否支持FileReader接口
		if (typeof FileReader == 'undefined') {
			document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
			//使选择控件不可操作
			document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
		}
	
		//选择图片，马上预览
		function xmTanUploadImg(obj) {
			var file = obj.files[0];
	
			console.log(obj);
			console.log(file);
			console.log("file.size = " + file.size); //file.size 单位为byte
	
			var reader = new FileReader();
	
			//读取文件过程方法
			//				reader.onloadstart = function(e) {
			//					console.log("开始读取....");
			//				}
			//				reader.onprogress = function(e) {
			//					console.log("正在读取中....");
			//				}
			//				reader.onabort = function(e) {
			//					console.log("中断读取....");
			//				}
			//				reader.onerror = function(e) {
			//					console.log("读取异常....");
			//				}
			reader.onload = function(e) {
				console.log("成功读取....");
	
				var img = document.getElementById("xmTanImg");
				img.src = e.target.result;
			//或者 img.src = this.result;  //e.target == this
			}
	
			reader.readAsDataURL(file)
		}
	</script>
</body>

</html>