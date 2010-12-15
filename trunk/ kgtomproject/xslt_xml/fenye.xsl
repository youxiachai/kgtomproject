<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output method="html" omit-xml-declaration="yes"/>
 <xsl:param name="PageNo">1</xsl:param>
 <xsl:param name="FuncName">goPage</xsl:param>
 <xsl:param name="Count">2</xsl:param>
 <!--Count��ʾһҳ����ʾ�ļ�¼����Ĭ��Ϊ2-->

 <xsl:template match="/Root">
  <xsl:variable name="MaxNo" select="count(Item)"/><!--�ܼ�¼��-->
  
  <table border="1" align="center">
   <tr>
    <xsl:for-each select="Item[1]/*">
     <th>
      <xsl:value-of select="name()"/>
     </th>
    </xsl:for-each>
   </tr>
   <!--�����ʾ����-->
   
   <xsl:for-each select="Item[(position() &lt;= $PageNo*$Count) and (position() &gt; (number($PageNo)-1)*$Count)]">
    <xsl:apply-templates select="."/>
   </xsl:for-each>
   <!--���������ʾ��¼���������ļ�¼-->
   
  </table>
  <div>
   <xsl:call-template name="PrevPage"/>
   <!--���������ʾ��һҳ-->
   
   <xsl:for-each select="Item[position() mod $Count = 1]">
    <xsl:variable name="Position" select="position()"/>
    <xsl:call-template name="PageLink">
     <xsl:with-param name="Position" select="$Position"/>
    </xsl:call-template>
   </xsl:for-each>
   <!--���������ʾҳ��-->
   
   <xsl:call-template name="NextPage">
    <xsl:with-param name="MaxNo" select="$MaxNo"/>
   </xsl:call-template>
   <!--���������ʾ��һҳ-->
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

 <xsl:template name="PrevPage"><!-- ��ҳ -->
  <xsl:variable name="Position" select="number($PageNo)-1"/>
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
  <a>
   <xsl:if test="$Position>=1">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   ��һҳ
  </a>
  <xsl:call-template name="Separator"/>
 </xsl:template>

 <xsl:template name="NextPage"><!-- ��ҳ -->
  <xsl:param name="MaxNo"/>
  <xsl:variable name="Position" select="number($PageNo)+1"/>
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
  <a>
   <xsl:if test="(number($Position)-1)*$Count&lt;$MaxNo">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   ��һҳ
  </a>
 </xsl:template>

 <xsl:template name="PageLink"><!-- ҳ�ŵĳ����� -->
  <xsl:param name="Position"/>
  <xsl:variable name="Href">javascript:<xsl:value-of select="$FuncName"/>(<xsl:value-of select="$Position"/>);</xsl:variable>
  <a>
   <xsl:if test="$Position!=$PageNo">
    <xsl:attribute name="href"><xsl:value-of select="$Href"/></xsl:attribute>
   </xsl:if>
   �� <xsl:value-of select="position()"/> ҳ
  </a>
  <xsl:call-template name="Separator"/>
 </xsl:template>

 <xsl:template name="Separator"><!-- ҳ�ŵķָ��� -->
  &#160;|&#160;
 </xsl:template>

</xsl:stylesheet>


