<?php if (!defined('THINK_PATH')) exit();?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="__PUBLIC__/style/myPage.css" />
<link rel="stylesheet" type="text/css" href="__PUBLIC__/style/upload_list.css" />
<title>珠海市创星公司</title>
<link href="__PUBLIC__/images/css.css" tppabs="xxximages/css.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #000000;
}

.tz {
	font-size: 12px;
}
-->
</style>
<link href="__PUBLIC__/images/css.css" tppabs="xxximages/css.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
<!--
a:link {
	text-decoration: none;
	color: #000000;
}

a:visited {
	text-decoration: none;
	color: #666666;
}

a:hover {
	text-decoration: none;
	color: #FF6600;
}

a:active {
	text-decoration: none;
}
-->
</style>
<table width="1003" height="34" border="0" align="center"
	cellpadding="0" cellspacing="0" background="__PUBLIC__/images/t_1.gif"
	tppabs="xxximages/t_1.gif">
	<tr>
		<td width="515" align="left" class="tz">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设&nbsp;为&nbsp;设&nbsp;页&nbsp;——&nbsp;加&nbsp;入&nbsp;收&nbsp;藏&nbsp;——&nbsp;联&nbsp;系&nbsp;我&nbsp;们</td>
		<td width="488"><img src="__PUBLIC__/images/t_2.gif"
			tppabs="xxximages/t_2.gif" width="500" height="34"></td>
	</tr>
</table>
<table width="995" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="cs">
	<tr>
		<td>
		<table width="950" height="182" border="0" align="center"
			cellpadding="0" cellspacing="0"
			background="__PUBLIC__/images/top.jpg" tppabs="xxximages/top.jpg">
			<tr>
				<td align="left"></td>
			</tr>
		</table>
		<TABLE height=35 cellSpacing=0 cellPadding=0 width=950 align=center
			border=0>
			<TBODY>
				<TR>
					<TD vAlign=top align=center><img
						src="__PUBLIC__/images/menu.jpg" tppabs="xxximages/menu.jpg"
						width="947" height="37" border="0" usemap="#Map" /></TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
</table>

<map name="Map" id="Map">
	<area shape="rect" coords="36,8,86,32" href="__URL__/index"
		tppabs="xxxindex.asp" />
	<area shape="rect" coords="132,9,195,34" href="__URL__/about"
		tppabs="xxxabout.asp" />
	<area shape="rect" coords="246,9,311,32" href="__URL__/news"
		tppabs="xxxnews.asp" />
	<area shape="rect" coords="362,10,425,34" href="__URL__/products"
		tppabs="xxxproducts.asp" />
	<area shape="rect" coords="478,11,541,33" href="__URL__/case"
		tppabs="xxxcase.asp" />
	<area shape="rect" coords="592,10,643,34" href="__URL__/lyb1"
		tppabs="xxxlyb1.asp" />
	<area shape="rect" coords="692,8,756,33" href="__URL__/contact"
		tppabs="xxxcontact.asp" />
	<area shape="rect" coords="808,10,871,33" href="__URL__/service"
		tppabs="xxxservice.asp" />
</map>
<table width="995" border="0" align="center" cellpadding="0"
	cellspacing="0" class="cs">
	<tr>
		<td>
		<table width="950" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tbody>
				<tr>

					<td valign="top">
					<table height="34" cellspacing="0" cellpadding="0" width="680"
						border="0">
						<tbody>
							<tr>
								<td align="left">
								<table height="32" cellspacing="0" cellpadding="0"
									align="center" border="0">
									<tbody>
										<tr>

											<td width="680" background="__PUBLIC__/images/smen.jpg"
												tppabs="http://www.cxcyzg.cn/images/smen.jpg">&nbsp;&nbsp;<span
												class="bt">产&nbsp;品&nbsp;展&nbsp;示</span></td>
										</tr>
									</tbody>
								</table>
								</td>
							</tr>
						</tbody>
					</table>
				
					<table width="97%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="xw">
						
<tbody>
<?php if(is_array($file)): $i = 0; $__LIST__ = $file;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): ++$i;$mod = ($i % 2 )?><tr>
	 <tr>
    <td width="126" rowspan="2"><img src="__PUBLIC__/Uploads/<?php echo ($vo["img_name"]); ?>" width="198" height="130" /></td>
    <td width="64">&nbsp;</td>
  </tr>
	</tr><?php endforeach; endif; else: echo "" ;endif; ?>
</tbody>
<tr id="pagetr">
	<td colspan="5" align="right"><div class="msdn"><?php echo ($page); ?></div></td>
</tr>
					</table>
					</td>
					<td valign="top" width="250">
					<table cellspacing="0" cellpadding="0" width="221" border="0">
						<tbody>
							<tr>
								<td>
								<table height="75" cellspacing="0" cellpadding="0" width="221"
									border="0">
									<tbody>
										<tr>
											<td valign="top" align="middle"
												background="__PUBLIC__/images/ixb_11.gif"
												tppabs="xxximages/ixb_11.gif">
											<table class="bt" cellspacing="0" cellpadding="0" width="50%"
												border="0">
												<tbody>
													<tr>
														<td valign="bottom" align="middle" height="25">产品展示</td>
													</tr>
												</tbody>
											</table>
											</td>
										</tr>
									</tbody>
								</table>
								</td>
							</tr>
							<tr>
								<td valign="top" background="__PUBLIC__/images/ixb_2.gif"
									tppabs="xxximages/ixb_2.gif" height="160"><marquee
									onmouseover="this.stop()" onmouseout="this.start()"
									scrollamount="1" scrolldelay="1" direction="up" height="273">
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="xw" cellspacing="0" cellpadding="0" width="80%"
									align="center" border="0">
									<tbody>
										<tr>
											<td align="center">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								</marquee></td>
							</tr>
							<tr>
								<td><img height="37" src="__PUBLIC__/images/ixb_3.gif"
									tppabs="xxximages/ixb_3.gif" width="221" /></td>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
			</tbody>
		</table>
		</td>
	</tr>
</table>
<link href="__PUBLIC__/images/css.css" tppabs="xxximages/css.css"
	rel="stylesheet" type="text/css">
<table width="1000" border="0" align="center" cellpadding="0"
	cellspacing="0" class="cs">
	<tr>
		<td width="1000">
		<TABLE cellSpacing=0 cellPadding=0 width=995 align=center
			bgColor=#ffffff border=0>
			<TBODY>
				<TR>
					<TD><IMG height=11 src="__PUBLIC__/images/bg1.gif"
						tppabs="xxximages/bg1.gif" width=995></TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
</table>
<TABLE cellSpacing=0 cellPadding=0 width=995 align=center
	background="__PUBLIC__/images/bg.gif" tppabs="xxximages/bg.gif"
	border=0>
	<TBODY>
		<TR>
			<TD>
			<TABLE class=xw cellSpacing=0 cellPadding=0 width="90%" align=center
				border=0>
				<TBODY>
					<TR>
						<TD align=middle>xxxx&copy;xxx&nbsp;&nbsp;&nbsp;xxxx<BR>
						xxxx&nbsp;xxxx&nbsp;xxxx&nbsp;&nbsp;&nbsp;xxxxx<BR>
						xxxx&nbsp;&nbsp;&nbsp;&nbsp;<a href="admin/login.asp.htm"
							tppabs="xxxadmin/login.asp">后台管理</a>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>