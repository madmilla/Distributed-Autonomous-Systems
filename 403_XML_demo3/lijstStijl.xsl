<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
   <head>
      <link href="mycss.css" rel="stylesheet" type="text/css"/>
   </head>
  <body>
    <h1>Verlanglijstjes</h1>
      <xsl:for-each select="trekking/lijstje">
		<h2><xsl:value-of select="naam" /></h2>
		<p>
		<xsl:for-each select="kado">
			* <xsl:value-of select="."/> <br />
		</xsl:for-each>
		</p>
      </xsl:for-each>

  </body>
  </html>
</xsl:template>

</xsl:stylesheet>