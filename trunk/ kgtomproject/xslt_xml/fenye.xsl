<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output method="html" omit-xml-declaration="yes"/>
 <xsl:param name="PageNo">1</xsl:param>
 <xsl:param name="FuncName">goPage</xsl:param>
 <xsl:param name="Count">2</xsl:param>
 <!--Count表示一页里显示的记录数，默认为2-->

 <xsl:template match="/Root">
  <xsl:variable name="MaxNo" select="count(Item)"/><!--总记录数-->
  
  <table border="1" align="center">
   <tr>
    <xsl:for-each select="Item[1]/*">
     <th>
      <xsl:value-of select="name()"/>
     </th>
    </xsl:for-each>
   </tr>
   <!--这段显示列名-->
   
   <xsl:for-each select="Item[(position() &lt;= $PageNo*$Count) and (position() &gt; (number($PageNo)-1)*$Count)]">
    <xsl:apply-templates select="."/>
   </xsl:for-each>
   <!--这段用来显示记录符合条件的记录-->
   
  </table>
  <div>
   <xsl:call-template name="PrevPage"/>
   <!--这段用来显示上一页-->
   
   <xsl:for-each select="Item[position() mod $Count = 1]">
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

 </xsl:template>

 <xsl:template match="Item">
  <tr>
   <td>
    <xsl:value-of select="No"/>
   </td>
   <td>
    <xsl:value-of select="Name"/>
   </td>
  </tr>
 </xsl:template>

 <xsl:template name="PrevPage"><!-- 上页 -->
  <xsl:variable name="Position" select="number($PageNo)-1"/>
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
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
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
  <a>
   <xsl:if test="(number($Position)-1)*$Count&lt;$MaxNo">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   下一页
  </a>
 </xsl:template>

 <xsl:template name="PageLink"><!-- 页号的超链接 -->
  <xsl:param name="Position"/>
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
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

</xsl:stylesheet>


