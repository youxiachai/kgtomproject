<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center" border="1">
	<tr>
		<td>
			<form action="__URL__/uploadFile" method="post">
				请选择文件 ： <input type="file" name="filename" /><br>
						FILEURL :	<input type="text" name="fileurl"/>
							<input type="submit" value="提交"/>
							<input type="reset" value="重置"/>
			</form> 
		</td>
	</tr>
</table>
</body>
</html>