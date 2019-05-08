<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
   
    <title>MusicFestival后台管理系统</title>
     <link rel="stylesheet" type="text/css"href="<%=basePath%>layui-v2.4.3/layui/css/layui.css" media="screen">
     <link rel="stylesheet" type="text/css" href="<%=basePath%>css/menu.css">
     <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
      <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
       <script type="text/javascript" src="<%=basePath%>js/login.js"></script>
        <script type="text/javascript" src="<%=basePath%>layui-v2.4.3/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>

  </head>
  
  <body>
   <div class="header"> 
       <div class="logo">
         <p class="logoer">MusicFestival</p>
       
       </div>
   </div>    
	
<div class="container-fluid login_main">



 <!-- <div id="login_part" class="login_part">
    	<div style="font-size: 20px;margin-top: 10px;">Login</div>
    	<div style="border:0.5px solid #d9d7d74f;width: 415px;height: 0px;margin-top: 10px;"></div>
    	
    	<div id="login_user_name" class="login_user">
  			用户名：<input type="text" id="username" name="username" class="form-control" style="width: 300px;"  placeholder="请输入用户名" aria-describedby="basic-addon1">
    	</div>
		  <div id="login_user_pws" class="login_user">
  			密码：<input type="password" id="pwd" name="password" class="form-control" style="width: 300px;" placeholder="请输入密码" aria-describedby="basic-addon1">
    	</div>
     	<button type="submit" class="btn btn-default" style="width: 300px;
         	margin-top: 43px;background-color: #5bc0de;color: white;margin-bottom: 30px" onclick="confirm()">Login Now</button>
    	
    </div>
</div> -->

     <form action="uploadMany/handBookPicture.do" method="post" enctype="multipart/form-data">  
     
     <label>上传头像：</label><input type="file" name="file"><br>  
     
     <input type="submit">  
     </form>  
     
 <!-- <form action="upload/playPicture.do" method="post" enctype="multipart/form-data">  
     
      地图上传：<input type="file" name="file"><br>  
      <input type="text" id="photo_point_url" name="photo_point_url">  
     <input type="submit">  
     </form>  -->
     
  </body>
</html>
