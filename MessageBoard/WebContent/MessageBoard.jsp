<%@ page language="java"
	import="com.messageBoard.JdbcConn,java.util.*,java.text.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>留言板</title>
<style>
.center {
	margin: auto;
	width: 50%;
	border: 3px solid #200000;
	padding: 10px;
	border-radius: 10px;
}
.video {
	margin: auto;
	width: 50%;
}
</style>
<script type="text/javascript">
	function loadXMLDoc() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("POST", "/ajax/demo_post2.asp", true);
		xmlhttp.setRequestHeader("Content-type",
				"a pplication/x-www-form-urlencoded");
		xmlhttp.send("fname=Bill&lname=Gates");
	}
	function validateForm(){
		var x=document.forms["mb"]["text"].value;
		if (x==null || x==""){
		  alert("留言不能为空！");
		  return false;
		  }
		}
</script>
</head>
<body onload="checkForm()">
	<script type="text/javascript">
        function hint()
    	{
        	var message="<%=session.getAttribute("message")%>";
			if (message == "success") {
				alert("留言成功！");
			} else if (message == "fail") {
				alert("留言失败！");
			} else {

			}
		}
		hint();
	</script>
	<%
		session.setAttribute("message", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date(session.getCreationTime());
	%>
	session创建时间：<%=sdf.format(d)%>
	<br>
	<div class="video">
	<video width="640" height="360" controls="controls">
		<source src="video/444.mp4" type="video/mp4"> 
		Your browser does notsupport the video tag. 
	</video>
	</div>
	<br>
	<h1 style="color: blue; text-align: center">留言板</h1>
	<div class="center">
		<form name="mb" action="com.messageBoard/MessageServlet" method="post"
			onsubmit="return validateForm()">
			请写下你的留言：<br>
			<textarea name="text" rows="10" cols="130"></textarea>
			<input type="hidden" name="sessionid" value="12345"> <br>
			<input style="align-content: center" type="submit" value="留言"">
		</form>
		<div style="height: 600px;overflow:auto;width:100%;">
			<%
				JdbcConn jc = new JdbcConn();
				List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
				rs = jc.selectMessage();
				for (HashMap<String, Object> map : rs) {
			%>
			<ul style="border-bottom: 2px solid #C0C0C0; padding: 2px">
				<p style="font-family: 黑体; font-weight: bold"><%=map.get("message")%></p>
				<p style="text-align: right;"><%=map.get("time")%></p>
			</ul>
			<%
				}
			%>
		</div>
	</div>

</body>
</html>