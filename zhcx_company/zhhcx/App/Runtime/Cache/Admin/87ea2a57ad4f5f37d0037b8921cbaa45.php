<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>珠海创新公司</title>
<link rel="stylesheet" type="text/css" href="__PUBLIC__/Css/common.css" />
 </head>
 <body>
 <div class="main">
 <h2>图片上传</h2>
 
 <div id="main" class="main" > 
<div class="image">

              
                         

 <p>
  <marquee onmouseover="this.stop()" onmouseout="this.start()" 
                        scrollamount="3" scrolldelay="1" direction="left" width="960">
                        <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): ++$i;$mod = ($i % 2 )?><img src="__PUBLIC__/Uploads/<?php echo ($vo["img_name"]); ?>" width="198" height="130" /><?php endforeach; endif; else: echo "" ;endif; ?>
    </marquee>

</p>
  最近上传                  
</div>
<div class="content">
<form id="upload" method='post' action="__URL__/upload/" enctype="multipart/form-data">
<table cellpadding=3 cellspacing=3 width="450PX">
<tr>
	<td colspan="2" class="tLeft">
	
	</td>
</tr>
<tr>
	<td class="tRight tTop"></td>
	<td class="tLeft tTop">
<div class="impBtn  fLeft" ><input name="image" id="image" type="file" class="file huge" /></div>
	<table id='tbl' style="clear:both"></table>
	<select name="category">
  <option value="打印耗材">打印耗材</option>
  <option value="精密模切">精密模切</option>
  <option value="特殊胶制品">特殊胶粘制品</option>
  
</select>
  <input type="submit" value="提交" class="button" >
</td>
</tr>
<tr>

</table>
</form>
</div>
</div>

</div>
 </body>
</html>