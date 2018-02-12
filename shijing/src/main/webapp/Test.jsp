<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<h1>注册</h1>
	<form action="account" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		电话<input type="text" name="phone" /> 密码<input type="password"
			name="password" /> 昵称<input type="text" name="nickname" />
		<button type="submit">提交</button>
	</form>	
	<h1>登陆</h1>
	<form action="auth" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		电话<input type="text" name="phone" /> 密码<input type="password"
			name="password" />
		<button type="submit">提交</button>
	</form>
	<h1>注销</h1>
	<form action="account/auth" method="post" accept-charset="'utf-8'" 
		onsubmit="document.charset='utf-8'">
		userId<input type="text" name="userId" /> token<input type="text"
			name="token" />
		<button type="submit">提交</button>
	</form>
	<h1>修改密码</h1>
	<form action="account/pass" method="POST" 
		onsubmit="document.charset='utf-8'">新密码<input type="text" name="nPass" />
		userId<input type="text" name="userId" /> token<input type="text"
			name="token" />
		<button type="submit">提交</button>
	</form>
	<h1>上传图片</h1>
	<form action="picture" method="post" enctype="multipart/form-data">
		选择要上传的图片：<input type="file" name="file" /> userId <input type="text"
			name="userId" /> 是否是悬赏而来<input
			type="text" name="status" value="true" /> token:<input type="text"
			name="token"> 
			longitude:<input type="text" name="longitude"> 
			latitude:<input type="text" name="latitude"> 
			des:<input type="text" name="des"> 
			name:<input type="text" name="name"> 
			<input type="submit" value="上传" />
	</form>
	<h1>评论</h1>
	<form action="comment" method="post">
		userId<input type="text" name="userId" /> 内容<input type="text"
			name="content" /> 要评价的图片Id<input type="text" name="pictureId" /> 状态<input
			type="text" name="status" /> token:<input type="text" name="token">
		<button type="submit">提交</button>
	</form>
	<h1>主页</h1>
	<form action="index" method="post">
		userId<input type="text" name="userId" /> token:<input type="text"
			name="token">
		<button type="submit">提交</button>
	</form>
	
	<h1>签到</h1>
	<form action="point" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		签到人ID<input type="text" name="userId" />token: <input type="text"
			name="token" />pathId<input type="text"
			name="pathId" />
		<button type="submit">提交</button>
	</form>
	
	<h1>制定要走的地方</h1>
	<form action="path" method="post" enctype="multipart/form-data">
		userId <input type="text"
			name="userId" /> token:<input type="text"
			name="token"> 		
			longitude:<input type="text" name="longitude"> 
			latitude:<input type="text" name="latitude"> 
			description:<input type="text" name="description"> 
			maxSize:<input type="text" name="maxSize">
			<input type="submit" value="设置" />
	</form>
	<h1>加关注</h1>
	<form action="collection" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		userID<input type="text" name="userId" /> 地点<input type="text"
			name="description" /> 经度<input type="text" name="longitude" /> 维度<input
			type="text" name="latitude" />token: <input type="text" name="token" />
		<button type="submit">提交</button>
	</form>
	<h1>修改头像</h1>
	<form action="account/img" method="post" enctype="multipart/form-data">
		选择要上传的图片：<input type="file" name="file" /> userId <input type="text"
			name="userId" /> token:<input type="text"
			name="token"> 
			
			<input type="submit" value="上传" />
	</form>
	<h1>我的关注</h1>
	<form action="collection/my" method="post" enctype="multipart/form-data">
		userId <input type="text"
			name="userId" /> token:<input type="text"
			name="token"> 		
			<input type="submit" value="查询" />
	</form>
	
	<h1>我的足迹</h1>
	<form action="path/my" method="post" enctype="multipart/form-data">
		userId <input type="text"
			name="userId" /> token:<input type="text"
			name="token"> 		
			<input type="submit" value="查询" />
	</form>
	
	<h1>积分消费</h1>
	<form action="test/point" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		消费ID<input type="text" name="userId" /> 消费行为<input type="text"
			name="action" />
		<button type="submit">提交</button>
	</form>
	<h1>添加积分规则</h1>
	<form action="test/add/point" method="post" accept-charset="'utf-8'"
		onsubmit="document.charset='utf-8'">
		积分消费数目<input type="text" name="cost" /> 积分行为<input type="text"
			name="action" />
		<button type="submit">提交</button>
	</form>
</body>
</html>