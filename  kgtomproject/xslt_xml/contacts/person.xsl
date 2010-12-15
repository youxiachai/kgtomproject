<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:param name="PageNo">1</xsl:param>
 <xsl:param name="FuncName">id=</xsl:param>
 <xsl:param name="Count">4</xsl:param>
  <!--Count表示一页里显示的记录数，默认为4-->
<xsl:template match="contacts">
<xsl:variable name="MaxNo" select="count(Person)"/><!--总记录数-->
<html>
    <head>
      <title>Contacts</title>
      <script type="text/javascript"><![CDATA[ 
		function init(){
		
		}
		

		
		
]]></script>
       
    </head>
    <body onload="init();">
      <h1>Contacts Library</h1>
     <p style="width: 390px; text-align:right;"><a href="addPerson.htm">&#187; Add New</a></p>
    <div id="page">   
     <table cellspacing="0">
        <tr>
          <th>姓名</th>
          <th>性别</th>
          <th>电话号码</th>
          <th>删除</th>
        </tr>
    <xsl:for-each select="Person[(position() &lt;= $PageNo*$Count) and (position() &gt; (number($PageNo)-1)*$Count)]">
    <xsl:apply-templates select="."/>
   </xsl:for-each>
   <!-- 用来显示内容 -->
       </table> 
       </div>	 
        <div>
   <xsl:call-template name="PrevPage"/>
   <!--这段用来显示上一页-->
   
   <xsl:for-each select="Person[position() mod $Count = 1]">
    <xsl:variable name="Position" select="position()"/>
    <xsl:call-template name="PageLink">
     <xsl:with-param name="Position" select="$Position"/>
    </xsl:call-template>
   </xsl:for-each>
   <!--这段用来显示页号-->
   
   <xsl:call-template name="NextPage">
    <xsl:with-param name="MaxNo" select="$MaxNo"/>
   </xsl:call-template>
   <!--这段用来显示下一页-->
  </div>
    </body>
</html>
<!-- 设置Person模板 -->
</xsl:template>
	<xsl:template match="Person">
		<tr>
			<td>

				<a>
					<xsl:attribute name="href">
          editPerson.php?id=<xsl:value-of select="@id" />
        </xsl:attribute>
					<xsl:value-of select="name" />
				</a>
			</td>
			<td>
				<xsl:value-of select="sex" />
			</td>
			<td>
				<xsl:value-of select="phone" />
			</td>
			<td>
				<a>
					<xsl:attribute name="href">
          deletePerson.php?id=<xsl:value-of select="@id" />
        </xsl:attribute>
					Delete?
				</a>
			</td>
		</tr>
	</xsl:template>
	

	<!-- 分割线下面是设置分页模板 -->
	<xsl:template name="PrevPage"><!-- 上页 -->
  <xsl:variable name="Position" select="number($PageNo)-1"/>
  <xsl:variable name="Href">personList.php?<xsl:value-of select="$FuncName"/><xsl:value-of select="$Position"/></xsl:variable>
  <a>
   <xsl:if test="$Position>=1">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   上一页
  </a>
  <xsl:call-template name="Separator"/>
 </xsl:template>
 
  <xsl:template name="NextPage"><!-- 下页 -->
  <xsl:param name="MaxNo"/>
  <xsl:variable name="Position" select="number($PageNo)+1"/>
  <xsl:variable name="Href">personList.php?<xsl:value-of select="$FuncName"/><xsl:value-of select="$Position"/></xsl:variable>
  <a>
   <xsl:if test="(number($Position)-1)*$Count&lt;$MaxNo">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   下一页
  </a>
 </xsl:template>

 <xsl:template name="PageLink"><!-- 页号的超链接 -->
  <xsl:param name="Position"/>
  <xsl:variable name="Href">personList.php?<xsl:value-of select="$FuncName"/><xsl:value-of select="$Position"/></xsl:variable>
  <a>
   <xsl:if test="$Position!=$PageNo">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   第 <xsl:value-of select="position()"/> 页
  </a>
  <xsl:call-template name="Separator"/>
 </xsl:template>

 <xsl:template name="Separator"><!-- 页号的分隔符 -->
  &#160;|&#160;
 </xsl:template>
 <!-- 分割线 -->
</xsl:stylesheet>




