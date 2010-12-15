<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="4.0" indent="yes"></xsl:output>
		<xsl:template match="node()|@*">
			<xsl:copy>
				<xsl:apply-templates select="node()|@*"></xsl:apply-templates>
			</xsl:copy>
		</xsl:template>
		<xsl:template match="body">
			<body>
				<p>
					<a href="#">aaaaaaaaaaaaaaaaa</a>
					<a href="#">bbbbbbbbbbbbbbbbb</a>
				</p>
			<xsl:apply-templates></xsl:apply-templates>
			<hr />
			 Copyright 2010
			</body>
		</xsl:template>


</xsl:stylesheet>